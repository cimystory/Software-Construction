/*
 * NAME : SHAHAN ALI MEMON
 * COURSE : 15-214
 * ANDREWID : samemon
 * HW : 1
 */

package edu.cmu.qatar.cs214.hw.hw1.graph;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cmu.qatar.cs214.hw.hw1.staff.Graph;
import edu.cmu.qatar.cs214.hw.hw1.staff.Vertex;

public class Algorithms {

	/** *********************** Algorithms ****************************
	*/

	/**
	 * This method finds the shortest distance between two vertices. It
	 * returns -1 if the two nodes are not connected.
	 * 
	 * Precondition: The graph is not null and a and b are vertices in
	 * the graph.
	 * 
	 * @param graph
	 *            the friends graph
	 * @param a
	 *            the first Vertex
	 * @param b
	 *            the second Vertex
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b)	
	{
		// This will be used to store flags for each object
		HashMap<Vertex,Boolean> flagMap = new HashMap<Vertex,Boolean>();
		Vertex [] g = graph.getVertices().clone();
		
		ArrayList<Vertex> Q = new ArrayList<Vertex>(); //Array List for parent
		ArrayList<Vertex> q = new ArrayList<Vertex>(); //Array List for friends.
		int distance = 0;
		
		// initializing all the flags with false
		for(int i=0; i<g.length;i++)
		{
			flagMap.put(g[i], false);
		}
		

		flagMap.put(a, true);
		Q.add(a);
		
		while(Q.isEmpty()!=true)
		{
			Vertex temp = new Vertex("temp");
			temp = Q.get(0);
			Q.remove(0);
			
			if (temp == b) // If we found b
			{
				return distance;
			}

			else
			{
				
				Vertex[] neighBours = graph.getNeighbors(temp);
				int neighbourLen = neighBours.length;
				
				// Breadth first search
				for(int i = 0; i < neighbourLen;i++)
				{
					Vertex tempO = new Vertex("tempO");
					tempO = neighBours[i];
					if(flagMap.get(tempO) != true)
					{
						flagMap.put(tempO,true); //mark
						q.add(tempO); //enqueue
					}
				}

				if(Q.isEmpty() == true)
				{
					Q = q;
					q = new ArrayList<Vertex>();
					distance+=1;
				}
				
				
			}
			
		}
		return -1;
		
	}

	/**
	 * This method is used to find common friends between v1 and v2. The 
	 * resulting array should not contain any duplicates, and should have 
	 * length equal to the number of vertices. It should not contain any
	 * nulls. The vertices in the array can be ordered arbitrarily.
	 *
	 * If there are no common friends, then return an array of size 0.
	 * 
	 * Precondition: The graph is not null and a and b are vertices in
	 * the graph.
	 * 
	 * @param graph
	 *            the friends graph
	 * @param a
	 *            the first Vertex
	 * @param b
	 *            the second Vertex
	 */
	public static Vertex[] commonFriends(Graph graph, Vertex a, Vertex b) 
	{
		
		Vertex[] array1 = graph.getNeighbors(a);
		Vertex[] array2 = graph.getNeighbors(b);
		ArrayList<Vertex> temp = new ArrayList<Vertex>();
		
		// lengths for the loop
		int len1 = array1.length;
		int len2 = array2.length;
		
		// nested loops for finding the common friends and inserting in list
		for(int i = 0; i < len1 ; i++ )
			for(int j = 0; j < len2; j++)
			{
				if(array1[i]==array2[j])
				{
					temp.add(array1[i]);
				}
			}
		
		// copying back to the array
		int sizeOfList = temp.size();
		Vertex[] returnArray = new Vertex[sizeOfList];
		for(int i=0; i<sizeOfList; i++)
		{
			returnArray[i] = temp.get(i);
		}
		return returnArray;
	}

	/**
	 * This method is used to find the person who has the most common friends
	 * with a particular user. If there is a tie, you can return any of the
	 * people who are tied.
	 * 
	 * Precondition: The graph is not null and source is a vertex in
	 * the graph.
	 * 
	 * @param graph
	 *            the friends graph
	 * @param source
	 *            the Vertex(Person) in question
	 */
	public static Vertex suggestFriend(Graph graph, Vertex source) 
	{
		
		Vertex[] array1 = graph.getVertices();
		Vertex destination = array1[0];
		int max = 0;
		
		// searching through all vertices by using commonFriends function
		for(int i = 0; i< array1.length;i++)
		{
			int numOfCommon = commonFriends(graph, source,array1[i]).length;
			if (numOfCommon > max && !array1[i].equals(source))
			{
				max = numOfCommon;
				destination = array1[i];
			}
			
		}
		
		return destination;
		
	}

}
