package edu.cmu.qatar.cs214.project;

public class BusData 
{
	private String name;
	private String currLocation;
	private int speed;
	
	public BusData(String name,int speed)
	{
		this.name = name;
		this.currLocation = "";
		this.speed = speed;
	}
	
	public void setSpeed(int sp)
	{
		this.speed = sp;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setCurrLocation(String location)
	{
		this.currLocation = location;
	}
	
	public String getLocation()
	{
		return this.currLocation;
	}
	
}
