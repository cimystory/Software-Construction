package edu.cmu.qatar.cs214.hw.hw3.square;

import edu.cmu.qatar.cs214.hw.hw3.tile.letterTile;

public class DLSSquare extends Square
{
	
	public DLSSquare(int x, int y)
	{
		
		super(x,y,'@');
		//System.out.println("setting name");
	}
	
	public int getScore()
	{
		int tempScore = super.getScore();
		return tempScore * tempScore;
	}
}