
package edu.cmu.qatar.cs214.hw.hw3.tile;

public abstract class tile 
{
	private char letter;
	
	public tile(char l)
	{
		this.letter = l;
	}
	
	public char getLetter()
	{
		return this.letter;
	}
}
