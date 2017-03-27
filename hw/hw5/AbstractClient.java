package edu.cmu.qatar.cs214.hw.hw5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.cmu.qatar.cs214.hw.hw5.plugin.wordcount.WordCountClient;
import edu.cmu.qatar.cs214.hw.hw5.plugin.wordprefix.WordPrefixClient;

/**
 * An abstract client class used primarily for code reuse between the
 * {@link WordCountClient} and {@link WordPrefixClient}.
 */
public abstract class AbstractClient {
    private final String mMasterHost;
    private final int mMasterPort;

    /**
     * The {@link AbstractClient} constructor.
     *
     * @param masterHost The host name of the {@link MasterServer}.
     * @param masterPort The port that the {@link MasterServer} is listening on.
     */
    public AbstractClient(String masterHost, int masterPort) {
        mMasterHost = masterHost;
        mMasterPort = masterPort;
    }

    protected abstract MapTask getMapTask();

    protected abstract ReduceTask getReduceTask();

    public void execute()
    {
        final MapTask mapTask = getMapTask();
        final ReduceTask reduceTask = getReduceTask();
        
        // Initializing it outside because I need to close it
        Socket msocket = null;
        // Initializing it here because I need to print it
        String result = "";
        
        // TODO: Submit the map/reduce task to the master and wait for the task
        // to complete.
        try {
			msocket = new Socket(mMasterHost, mMasterPort);
			assert (msocket!= null);
			ObjectOutputStream outStream = new ObjectOutputStream(msocket.getOutputStream());
			// Send the mapTask to the server
			outStream.writeObject(mapTask);
			// Send the reduce Task to the Server
			outStream.writeObject(reduceTask);
			
			// Now read what server sends back i.e the final result
			ObjectInputStream inStream = new ObjectInputStream(msocket.getInputStream());
			result = (String) inStream.readObject();
        	}
        catch(IOException e)
        {
        	System.out.println("connection problem");
        }
        catch (ClassNotFoundException e)
        {
        	System.out.println("Some problem with reading object from server");
        }
        System.out.println("Result for mapReduce is : " + result);
        // Now finally when we have read the results from the server we should close the socket.
        assert (msocket!=null);
        
        try 
        {
			msocket.close();
		} 
        catch (IOException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      }

}
