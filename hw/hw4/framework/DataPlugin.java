package edu.cmu.qatar.cs214.hw.hw4.framework;

import java.util.ArrayList;

public interface DataPlugin 
{	
	public ArrayList<Data> getData(String Query);

	public String getSource();
	
	public int getCountOfPosts();
}
