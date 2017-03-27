///////////////  SHAHAN ALI MEMON ///////////////
///////////////       samemon      ///////////////
///////////////		15214 - HW0    //////////////

package edu.cmu.qatar.cs214.hw.hw0;

import java.util.ArrayList;

public class FriendGraph {
// states
	private ArrayList <Person> nodes = new ArrayList<Person> ();
	
//methods
	
	// This method simply adds p to the graph
	
	public void addPerson(Person p)
	
	{
		this.nodes.add(p);
	}
	
	public ArrayList<Person> getGraph()
	{
		return this.nodes;
	}
	
	// fn addFriendship(a,b) -> ()
	// Requires : true
	// Description : function just adds a to b's friendlist
					// and b to a's friendlist.
	//Ensures : b is in a.getFriends and a is in b.getFriends.
	
	
	public void addFriendship(Person a, Person b)
	{
	//Here I need to add b to a's friends and a to b's friends.
		a.addFriends(b);
		b.addFriends(a);
	}
	
	public void unsetFlags()
	{
		for(int i = 0; i < nodes.size();i++)
		{
			nodes.get(i).unsetFlag();
		}
		
	}
	
	// fn : getDistance(a,b) -> d
	// Requires : true
	// Description:function simply does a breadth first search to find
	// distance between a and b
	// Ensures : d is the shortest distance b/w a and b
	
	public int getDistance(Person a,Person b)
	{ 
		
		ArrayList<Person> Q = new ArrayList<Person>(); //Array List for parent
		ArrayList<Person> q = new ArrayList<Person>(); //Array List for friends.
		this.unsetFlags(); //Important if finding distance for more than one people in one program.
		a.setFlag(); 	   //mark
		Q.add(a); 		   //enqueue
		int distance = 0;
		
		
		while(Q.isEmpty()!=true)
		{
			Person temp = new Person("");
			temp = Q.get(0);
			Q.remove(0);
			
			if (temp == b) // If we found b
			{
				return distance;
			}

			else
			{
				for(int i = 0; i < temp.getFriends().size() ;i++)
				{
					Person tempO = new Person("");
					tempO = temp.getFriends().get(i);
					if(tempO.getFlag()!= true)
					{
						tempO.setFlag(); //mark
						q.add(tempO); //enqueue
					}
				}

				if(Q.isEmpty() == true)
				{
					Q = q;
					q = new ArrayList<Person>();
					distance+=1;
				}
				
				
			}
			
		}
		// If no link/edge found b/w a and b
		//return distance;
		return -1;
	}

}






