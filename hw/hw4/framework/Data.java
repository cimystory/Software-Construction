package edu.cmu.qatar.cs214.hw.hw4.framework;

public class Data 
{
	private String userId;
	private String post;
	private String date;
	
	public void setId(String uId)
	{
		this.userId = uId;
	}
	
	public String getId()
	{
		return this.userId;
	}
	
	public void setDate(String d)
	{
		this.date = d;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public void setPost(String p)
	{
		this.post = p;
	}
	
	public String getPost()
	{
		return this.post;
	}
	
	
}
