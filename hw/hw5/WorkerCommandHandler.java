package edu.cmu.qatar.cs214.hw.hw5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkerCommandHandler implements Runnable 
{
	private final Socket mSocket;
	private List<WorkerInfo> mWorkers;
	// Getting the number of available processors to JVM.
	private static final int POOL_SIZE = 
			Runtime.getRuntime().availableProcessors();
	private final ExecutorService mExecutor;
	
	public WorkerCommandHandler(Socket clientSocket, 
						List<WorkerInfo> mWorkers) 
	{
		// TODO Auto-generated constructor stub
		this.mSocket = clientSocket;
		this.mWorkers = mWorkers;
		/* Now if I have more processors available, but less workers,
		 * I should not bother others. So we need to limit the number
		 * of threads executing simultaenosuly even further.
		 */
		this.mExecutor = Executors.newFixedThreadPool(POOL_SIZE);
		
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		// Let us make the object input stream to read from the client
		try
		{
			assert (mSocket != null);
			ObjectInputStream inStream = new ObjectInputStream(mSocket.getInputStream());
			assert (inStream != null);
			// We expect to get a two tasks. 1)map and 2) reduce
			MapTask mp = (MapTask) inStream.readObject();
			ReduceTask rt = (ReduceTask) inStream.readObject();
			assert (mp != null);
			assert (rt != null);
			
			
			// Now we have map and reduce task that we can map, shuffle and reduce
			System.out.println("***Mapping***");
			
			executeMapTask(mp);
			
			System.out.println("***Shuffling***");
			
			shuffle();
			
			System.out.println("***Reducing***");
			
			String result = executeReduceTask(rt);
			
			// Now we send back the result
			assert (mSocket!=null);
			ObjectOutputStream outStream = new ObjectOutputStream(mSocket.getOutputStream());
			outStream.writeObject(result);
			mExecutor.shutdown();
			
		}
		catch (IOException e)
		{
			System.out.println("Error in WorkerCommandHandler's receiving objects");
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
            try 
            {
            	if (mSocket != null) {
            		mSocket.close();
            	}
            } catch (IOException e) 
            {
            	System.out.println("Socket problem in Worker Command Handler");
            }
         }
	}

	private int executeMapTask(MapTask mp) 
	{
		// TODO Auto-generated method stub
		// Now let us do the map task
		// For this we need a hashmap of partitions, pointing to workers who are
		//doing the partition and then we assign each worker a particular partition to
		//execute.
		HashMap<Partition, List<WorkerInfo>> dict = new HashMap<Partition,List<WorkerInfo>> ();
		// Now we go to each worker and we put it inside our hashmap worker list if the worker has 
		// access to partition.
		for(int i=0;i < this.mWorkers.size();i++)
		{
			// So if we already have the partition in the 
			//dctionary, we add the worker to the list
			WorkerInfo wtemp = mWorkers.get(i);
			List<Partition> ps = wtemp.getPartitions();
			for(int j=0 ; j < ps.size(); j++)
			{
				Partition ptemp = ps.get(j);
				if (dict.containsKey(ptemp))
				{
					List<WorkerInfo> newList = dict.get(ptemp);
					newList.add(wtemp);
					dict.replace(ptemp,newList);
				}
				//else we create a new key and a new list and add it
				else
				{
					List<WorkerInfo> newList = new ArrayList<WorkerInfo>();
					newList.add(wtemp);
					dict.put(ptemp, newList);
				}
			}
			
		}
		
		// Now once we have done this, we assign partitions to the workers creatively
		List<MapCallable> mapCallables = new ArrayList<MapCallable>();
		
		for(Partition key : dict.keySet())
		{
			// Now my program just gets the first worker in the list
			// and asssigns it the task
			List<WorkerInfo> workers = dict.get(key);
			mapCallables.add(new MapCallable(mp, workers.get(0), key));
		}
		
		// The ith future object in 'countResults' corresponds to the
        // result of the ith callable object in 'countCallables'.
        List<Future<Integer>> results = null;
        try {
            // Executes all of the Callable tasks on background threads,
            // blocking until each call() method has either returned or
            // thrown an exception. This method provides an extremely
            // convenient way of executing multiple tasks in parallel (hint:
            // think about how you could use this in hw6).
            results = mExecutor.invokeAll(mapCallables);
        } catch (InterruptedException e) {
            // If you are interested, there is a link to a great article
            // about when/how to deal with InterupttedExceptions below.
            // http://www.ibm.com/developerworks/java/library/j-jtp05236/index.html

            // Note that this exception will never be thrown in this sample
            // implementation (and more specifically, it would only ever be
            // thrown if the ExecutorService detected that we had explicitly
            // interuptted one of its threads, or if we had called
            // mExecutor.shutdownNow() in the middle of a call to invokeAll(),
            // etc). Understanding these concepts are beyond the scope of the
            // class, but just in case some of you were wondering. :)
            Thread.currentThread().interrupt();
        }
		
        //Now its time to get the results from the workers.
        
		int totalCount = 0;
		for (int i = 0; i < results.size(); i++) 
		{
			MapCallable callables = mapCallables.get(i);
			Future<Integer> result = results.get(i);
			try {
				totalCount += result.get();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (ExecutionException e) {
				WorkerInfo down = callables.getWorker();
				mWorkers.remove(down);
				totalCount = 1;
			}
		}
		return totalCount; //basically represents that the task is done successfully or not
	}

	private void shuffle() 
	{
		// TODO Auto-generated method stub
		
	}

	
	// Now let us reduce it.
	
    private String executeReduceTask(ReduceTask rt) 
    {
    	// Make a list of all the reduce Callables such that they would return us something when done.
    	List<ReduceCallable> reduceCallables = new ArrayList<ReduceCallable>();
    	
    	// Get each worker and give it to callables.
    	for(int i=0; i< mWorkers.size();i++)
    	{
    		reduceCallables.add(new ReduceCallable(rt,mWorkers.get(i)));
    	}
    	
    	// The results we get in future.
    	List<Future<String>> results = null;
    	
    	//Invoke all of them
    	try 
    	{
			results = mExecutor.invokeAll(reduceCallables);
		} 
    	
    	catch (InterruptedException e) 
    	{
			Thread.currentThread().interrupt();
		}
		
    	// get results from workers
    	//Build a path
		String finalResult = "";
		
		for (int i = 0; i < results.size(); i++) 
		{
			// Take each reduce callable
			ReduceCallable redCallable = reduceCallables.get(i);
			
			// Get all the results
			Future<String> result = results.get(i);
			try 
			{
				String r = result.get();
				//Create a final result
				finalResult += r + "\n";
			} 
			catch (InterruptedException e) 
			{
				Thread.currentThread().interrupt();
			} 
			catch (ExecutionException e) 
			{
				
				WorkerInfo done = redCallable.getWorker();
				// Once done remove the worker from the callable.
				mWorkers.remove(done);
				finalResult = "";
			}
		}
		return finalResult;
    }

}
