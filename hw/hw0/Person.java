///////////////  SHAHAN ALI MEMON ///////////////
///////////////       samemon      ///////////////
///////////////		15214 - HW0    //////////////


package edu.cmu.qatar.cs214.hw.hw0;

import java.util.ArrayList;

public class Person 
{
	// We define states
	private ArrayList <Person> friends = new ArrayList<Person> ();
	private String name;
	private boolean flag;
	
	
	// Methods //
	
	// Constructor
	public Person(String name)
	{
		this.name = name;
		this.flag = false;
	}
	
	// This is basically a getter function since my states are private
	public ArrayList<Person> getFriends()
	{
		return this.friends;
	}
	
	// This function adds f to this person's friends' ArrayList.
	public void addFriends(Person f)
	{
		this.friends.add(f);
	}
	
	// SetFlag function is needed for FriendGraph BFS
	public void setFlag()
	{
		this.flag = true;
	}
	
	// Unset function is needed for FriendGraph BFS
	public void unsetFlag()
	{
		this.flag = false;
	}
	
	//Since our attributes are private
	public boolean getFlag()
	{
		return this.flag;
	}

}