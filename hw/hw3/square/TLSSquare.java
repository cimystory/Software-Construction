package edu.cmu.qatar.cs214.hw.hw3.square;

public class TLSSquare extends Square
{
	public TLSSquare(int x, int y)
	{
		super(x,y,'$');
		//super.setName('$');
	}
	
	public int getScore()
	{
		int tempScore = super.getScore();
		return tempScore * tempScore * tempScore;
	}
}
