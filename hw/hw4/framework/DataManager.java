/*
 * Name  : Shahan Ali Memon
 * ID    : samemon
 * HW    : 4
 */


package edu.cmu.qatar.cs214.hw.hw4.framework;

import java.util.ArrayList;

public class DataManager
{	
	private ArrayList<Data> data;
	private String currentSource;
	
	public DataManager(ArrayList<Data> d, String source)
	{
		this.data = new ArrayList<Data>();
		this.data = d;
		this.currentSource = source;
	}
	
	public void setSource(String source)
	{
		this.currentSource = source;
	}
	
	public String getSource()
	{
		return this.currentSource;
	}
	
	public ArrayList<Pair<String,String>> getPostsWithDates()
	{
		ArrayList<Pair<String,String>> rArray = 
				new ArrayList<Pair<String,String>>();
		int count = this.data.size();
		ArrayList<Data> dArray = data;
		for(int i=0; i<count; i++)
		{		
			Data tempD = dArray.get(i);
			Pair<String,String> temp = 
					new Pair<String,String>(tempD.getPost(),
											tempD.getDate());
			rArray.add(temp);
		}
			
		return rArray;
	}
	
	public ArrayList<Pair<String,String>> getPostsWithIds()
	{
		ArrayList<Pair<String,String>> rArray = 
				new ArrayList<Pair<String,String>>();
		int count = this.data.size();
		ArrayList<Data> dArray = data;
		for(int i=0; i<count; i++)
		{		
			Data tempD = dArray.get(i);
			Pair<String,String> temp = 
					new Pair<String,String>(tempD.getPost(),
											tempD.getId());
			rArray.add(temp);
		}
		
		return rArray;
	}
	
	public ArrayList<String> getAllLefts(ArrayList<Pair<String,String>> data)
	{
		int count = data.size();
		ArrayList<String> lefts = new ArrayList<String>();
		for(int i=0; i<count; i++)
		{
			lefts.add(data.get(i).getLeft());
		}
		return lefts;
	}
	
	public ArrayList<String> getAllRights(ArrayList<Pair<String,String>> data)
	{
		int count = data.size();
		ArrayList<String> rights = new ArrayList<String>();
		for(int i=0; i<count; i++)
		{
			rights.add(data.get(i).getRight());
		}
		return rights;
	}
	
	public String getLeft(Pair<String,String> p)
	{
		return p.getLeft();
	}
	
	public String getRight(Pair<String,String> p)
	{
		return p.getRight();
	}
	
	public ArrayList<String> getPosts()
	{
		return this.getAllLefts((this.getPostsWithDates()));
	}
	
	public ArrayList<String> getDates()
	{
		return this.getAllRights((this.getPostsWithDates()));
	}
	
	public ArrayList<String> getIds()
	{
		return this.getAllRights((this.getPostsWithIds()));
	}
}
