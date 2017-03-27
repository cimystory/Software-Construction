/*
 * NAME : SHAHAN ALI MEMON
 * COURSE : 15-214
 * ANDREWID : samemon
 * HW : 1
 */
package edu.cmu.qatar.cs214.hw.hw1.graph;
import edu.cmu.qatar.cs214.hw.hw1.staff.Graph;
import edu.cmu.qatar.cs214.hw.hw1.staff.Vertex;

//import java.util.Arrays;
import java.util.HashMap;

public class AdjacencyListGraph implements Graph
{
	// adjacencyList : Vertex[] contains neigbours as values
	private HashMap<Vertex, Vertex[]> adjacencyList;
	
	// neigbourCount keeps count of neigbours of Vertex
	private HashMap<Vertex,Integer> neighbourCount;
	
	// The main graph is stored here.
	private Vertex[] graph;
	
	// maxV is for array bound to raise exception on overflow
	private int maxV;
	
	// Keeps count of the number of vertices in graph
	private int count;
	
	// Constructor to initialize all values
	public AdjacencyListGraph(int maxVertices)
	{
		this.count = 0;
		this.maxV = maxVertices;
		this.graph = new Vertex[maxVertices];
		this.adjacencyList =  new HashMap<Vertex,Vertex[]>();
		this.neighbourCount = new HashMap<Vertex,Integer>();
	}
	
/*
	fn addVertex(v) -> void
	requires : true
	ensures : v is in graph
*/
	
	@Override
	public void addVertex(Vertex v)
	{
		//Add to graph and increase count
		this.graph[count] = v;
		this.count++;
		
		//create new vertex with 0 neighbours.
	    Vertex[] tempArray = new Vertex[maxV];
		this.adjacencyList.put(v, tempArray);
		this.neighbourCount.put(v, 0);
		
	}

/*
	fn addEdge(v1,v2) -> void
	requires : v1 and v2 are in the graph
	ensures : graph.isAdjacent(v1,v2) == true
*/
	
	@Override
	public void addEdge(Vertex v1, Vertex v2)
	{
		// check if there is an edge already
		if (!(this.isAdjacent(v1, v2)))
		{
			// checking the available spot
			int countv1 = this.neighbourCount.get(v1);
			int countv2 = this.neighbourCount.get(v2);
			
			// creating tempArrays. I should clone this.
			// But it's ok.
			Vertex[] tempArray1 = this.adjacencyList.get(v1);
			Vertex[] tempArray2 = this.adjacencyList.get(v2);
			
			// assign an edge and increment count
			tempArray1[countv1] = v2;
			countv1++;
			
			// do same for the other
			tempArray2[countv2] = v1;
			countv2++;
			
			//This step might be redundant though but its ok.
			this.adjacencyList.put(v1, tempArray1);
			this.adjacencyList.put(v2, tempArray2);
			
			// change the neigbours count for each
			this.neighbourCount.put(v1, countv1);
			this.neighbourCount.put(v2, countv2);
		}
		
	}

/*
	fn isAdjacent(v1,v2) -> bool
	requires : v1 and v2 are in the graph
	ensures : true if v1 is a neighbour to v2 and vice versa
*/
	@Override
	public boolean isAdjacent(Vertex v1, Vertex v2) 
	{
		Vertex[] tempArray1 = this.adjacencyList.get(v1).clone();
		//Vertex[] tempArray2 = this.adjacencyList.get(v2).clone();
		int length = this.neighbourCount.get(v1);
		for(int i=0; i<length;i++)
		{
			if (tempArray1[i] == v2)
			{
				return true;
			}
		}
		return false;
	}

/*
	fn getNeigbours(v) -> array[]
	requires : v is in the graph
	ensures : array[] is of size 0 if v has no neighbour
			  array[] contains vertices adjacent to v if otherwise.
*/
	
	@Override
	public Vertex[] getNeighbors(Vertex v) 
	{
		// count to see the last filled spot's index
		int countv = this.neighbourCount.get(v);
		
		// if 0 return empty array of size 0.
		if(countv == 0)
		{
			return new Vertex[0];
		}
		
		else
		{
			Vertex[] returnArray = new Vertex[countv];
			Vertex[] tempArray = this.adjacencyList.get(v).clone();
			// removing redundant null spaces
			for(int i=0; i < countv; i++)
			{
				returnArray[i] = tempArray[i];
			}
			return returnArray;
		}
		
	}

/*
	fn getVertices()-> array[]
	requires : true
	ensures : array[] is of size 0 if graph is empty
	array[] contains all the vertices if otherwise.
*/

	@Override
	public Vertex[] getVertices() 
	{	
		Vertex[] returnArray = new Vertex[this.count];
		Vertex[] tempArray = this.graph;
		// removing redundant null spots
		for(int i=0; i < this.count; i++)
		{
			returnArray[i] = tempArray[i];
		}
		return returnArray;
	}
	
}
	
