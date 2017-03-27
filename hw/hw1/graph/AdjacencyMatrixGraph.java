/*
 * NAME : SHAHAN ALI MEMON
 * COURSE : 15-214
 * ANDREWID : samemon
 * HW : 1
 */


package edu.cmu.qatar.cs214.hw.hw1.graph;
import java.util.HashMap;

import edu.cmu.qatar.cs214.hw.hw1.staff.Graph;
import edu.cmu.qatar.cs214.hw.hw1.staff.Vertex;


public class AdjacencyMatrixGraph implements Graph
{

	/* This is the 1-D array representing 
	adjacencyMatrix with boolean values*/
	
	private boolean[] matrix;
	
	//This stores all the vertices only.
	private Vertex[] graph;
	
	//maxV stores the maximum Vertices
	private int maxV;
	
	//count stores the number of vertices.
	private int count;
	
	//pointers contains vertices with their index in matrix
	private HashMap<Vertex, Integer> pointers;
	// neigbourCount keeps count of neigbours of Vertex
	private HashMap<Vertex,Integer> neighbourCount;
	
	// Constructor
	public AdjacencyMatrixGraph(int maxVertices)
	{
		this.count = 0;
		this.maxV = maxVertices;
		this.matrix = new boolean[this.maxV*this.maxV];
		this.pointers =  new HashMap<Vertex,Integer>();
		this.graph=new Vertex[this.maxV];
		///////////////////////////////////////
		// This is something I'm doing for convenience
		this.neighbourCount = new HashMap<Vertex,Integer>();
		////////////////////////////////////////
	}
	
/*
	fn addVertex(v) -> void
	requires : true
	ensures : v is in graph
*/
	
	@Override
	public void addVertex(Vertex v) 
	{
		this.graph[count] = v;
		this.pointers.put(v, this.count);
		this.count++;
	
		/////////////////////////////
		this.neighbourCount.put(v, 0);
		/////////////////////////////
	}

/*
	fn addEdge(v1,v2) -> void
	requires : v1 and v2 are in the graph
	ensures : graph.isAdjacent(v1,v2) == true
*/
	@Override
	public void addEdge(Vertex v1, Vertex v2) 
	{
		
		//getting row,column to access the matrix
		int row = this.pointers.get(v1);
		int column = this.pointers.get(v2);
		int numberOfColumns = this.maxV;
		
		if(!this.matrix[row*numberOfColumns+column])
		{
			// since its undirected, so we add both sides.
			this.matrix[row*numberOfColumns+column] = true;
			this.matrix[column*numberOfColumns+row] = true;
		
		//Debugging :
		//System.out.println(Arrays.toString(matrix));
		
			//////////////////////////////////////////////
			int countv1 = this.neighbourCount.get(v1);
			int countv2 = this.neighbourCount.get(v2);
			this.neighbourCount.put(v1, ++countv1);
			this.neighbourCount.put(v2, ++countv2);
			//////////////////////////////////////////////
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
		int row = this.pointers.get(v1);
		int column = this.pointers.get(v2);
		int numberOfColumns = this.maxV;
		return this.matrix[row*numberOfColumns+column];
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
		//getting the length of array.
		int arrayLength = this.neighbourCount.get(v);
		
		/*Debugging
		That means arrayLength is fine
		System.out.println(arrayLength);
		*/
		
		if(arrayLength == 0)
		{
			return new Vertex[0];
		}
		
		else
		{
			int index = this.pointers.get(v);
			//System.out.println(index);
			int numberOfColumns = this.maxV;
			int arrayIndexer = 0;
			Vertex[] neighbours = new Vertex[arrayLength];
			
			//making the new list of neighbours.
			
			for(int column=0;column<numberOfColumns;column++)
			{
				int target = column*numberOfColumns+index;
				
				if(this.matrix[target])
				{
					neighbours[arrayIndexer] = this.graph[column];
					arrayIndexer+=1;
				}
			}
			return neighbours;
		}
	}

	@Override

/*
	fn getVertices()-> array[]
	requires : true
	ensures : array[] is of size 0 if graph is empty
	array[] contains all the vertices if otherwise.
*/
	
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