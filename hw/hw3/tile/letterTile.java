package edu.cmu.qatar.cs214.hw.hw3.tile;


public class letterTile extends tile
{
	//private char letter;
	private int score;
	//private HashMap<Character, Integer> letterScores;

	public letterTile(char randomChar, Integer integer) 
	{
		// TODO Auto-generated constructor stub
		super(randomChar);
		this.score = integer;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public char getLetter()
	{
		return super.getLetter();
	}
	
}
