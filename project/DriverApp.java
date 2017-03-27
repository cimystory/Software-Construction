package edu.cmu.qatar.cs214.project;

public class DriverApp 
{
	private BusData data;
	private int defaultSpeed = 30;
	
	public DriverApp(String name)
	{
		this.data = new BusData(name,defaultSpeed);
	}
	
	public void setCurrLocation(String location)
	{
		this.data.setCurrLocation(location);
	}
	
	public void setSpeed(int sp)
	{
		data.setSpeed(sp);
	}
	
	/*
	 * execute will talk to the server and send the data
	 */
	
	public void execute()
	{
		
	}
}
