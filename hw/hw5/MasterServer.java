package edu.cmu.qatar.cs214.hw.hw5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.cmu.qatar.cs214.hw.hw5.util.Log;
import edu.cmu.qatar.cs214.hw.hw5.util.StaffUtils;
/**
 * This class represents the "master server" in the distributed map/reduce
 * framework. The {@link MasterServer} is in charge of managing the entire
 * map/reduce computation from beginning to end. The {@link MasterServer}
 * listens for incoming client connections on a distinct host/port address, and
 * is passed an array of {@link WorkerInfo} objects when it is first initialized
 * that provides it with necessary information about each of the available
 * workers in the system (i.e. each worker's name, host address, port number,
 * and the set of {@link Partition}s it stores). A single map/reduce computation
 * managed by the {@link MasterServer} will typically behave as follows:
 *
 * <ol>
 * <li>Wait for the client to submit a map/reduce task.</li>
 * <li>Distribute the {@link MapTask} across a set of "map-workers" and wait for
 * all map-workers to complete.</li>
 * <li>Distribute the {@link ReduceTask} across a set of "reduce-workers" and
 * wait for all reduce-workers to complete.</li>
 * <li>Write the locations of the final results files back to the client.</li>
 * </ol>
 */
public class MasterServer extends Thread {
    private final int mPort;
    private final List<WorkerInfo> mWorkers;
    private static final String TAG = "Master Server";
    // Following lines are taken from WorkerServer file in lecture26
    ///////////////////////////////////////////////////////////////////////////////
    private final ExecutorService mExecutor;
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    ////////////////////////////////////////////////////////////////////////////////
    /**
     * 
     * The {@link MasterServer} constructor.
     *
     * @param masterPort The port to listen on.
     * @param workers Information about each of the available workers in the
     *        system.
     */
    public MasterServer(int masterPort, List<WorkerInfo> workers) {
        this.mPort = masterPort;
        this.mWorkers = workers;
        // For concurrency.
        this.mExecutor = Executors.newFixedThreadPool(POOL_SIZE);
    }

    @Override
    //Most of the code taken from Lecture 26
    public void run(){
    try {
    	// Initiate a new Server Socket
        ServerSocket serverSocket = null;
        try {
        	// Open the port we have been given
            serverSocket = new ServerSocket(mPort);
        } catch (IOException e) {
        	// If we are not able to open, show error
            Log.e(TAG, "Could not open server socket on port " + mPort + ".", e);
            return;
        }
        // Else we are listening
        Log.i(TAG, "Listening for incoming commands on port " + mPort + ".");
        
        // Now start listening
        while (true) {
            try {
            	// Accept the connection that is made to serverSocket
                Socket clientSocket = serverSocket.accept();
                // And give it to the executor to execute it using my assistant worker handler
                // Except that this time Im sending my workers as well to my assistant
                mExecutor.execute(new WorkerCommandHandler(clientSocket,this.mWorkers));
            } catch (IOException e) {
                Log.e(TAG, "Error while listening for incoming connections.", e);
                break;
            }
        }

        Log.i(TAG, "Shutting down...");

        try {
            serverSocket.close();
        } catch (IOException e) {
            // Ignore because we're about to exit anyway.
        }
    } finally {
        mExecutor.shutdown();
    }

  }

    /********************************************************************/
    /***************** STAFF CODE BELOW. DO NOT MODIFY. *****************/
    /********************************************************************/

    /**
     * Starts the master server on a distinct port. Information about each
     * available worker in the distributed system is parsed and passed as an
     * argument to the {@link MasterServer} constructor. This information can be
     * either specified via command line arguments or via system properties
     * specified in the <code>master.properties</code> and
     * <code>workers.properties</code> file (if no command line arguments are
     * specified).
     */
    public static void main(String[] args) 
    {
        StaffUtils.makeMasterServer(args).start();
    }

}
