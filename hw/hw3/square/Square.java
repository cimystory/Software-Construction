package edu.cmu.qatar.cs214.hw.hw3.square;

import edu.cmu.qatar.cs214.hw.hw3.tile.letterTile;

public abstract class Square 
{
	private int x;
	private int y;
	private letterTile TILE;
	private char name; //name of tile
	
	public Square(int x, int y,char n)
	{
		this.x = x;
		this.y = y;
		this.TILE = null;
		this.name = n;
		
		//System.out.println("Inside Square's Constructor");
	}
	
	public int getRow()
	{
		return this.y;
	}
	
	public int getColumn()
	{
		return this.x;
	}
	
	public void placeTile(letterTile TILE)
	{
		this.TILE = TILE;
	}
	
	private letterTile getTile()
	{
		return this.TILE;
	}
	
	public void setName(char name)
	{
		this.name = name;
		
	}
	
	public char getName()
	{
		//System.out.println(this.name);
		return this.name;
		
	}

	protected int getScore() 
	{
		// TODO Auto-generated method stub
		letterTile lT = this.getTile();
		if (lT == null)
		{
			return 0;
		}
		
		else
		{
			return lT.getScore();
		}	
	}
	
}
