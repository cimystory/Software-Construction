package edu.cmu.qatar.cs214.hw.hw3.Player;

import java.util.ArrayList;

import edu.cmu.qatar.cs214.hw.hw3.tile.letterTile;
//import edu.cmu.qatar.cs214.hw.hw3.tile.tile;

public class Player 
{
	private int score;
	//private int name;
	private ArrayList<letterTile> rack;
	//private ArrayList<tile> specialRack;
	private Character[] myView;
	
	public Player(int n)
	{
		//this.name = n;
		this.score = 0;
		this.rack = new ArrayList<letterTile>();
		//this.specialRack = new ArrayList<tile>();
	}
	
	public void loadRack(ArrayList<letterTile> r)
	{
		this.rack = r;
	}
	
	public void setScore(int change)
	{
		this.score = this.score + change;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void purchaseSpecialT()
	{
		return;
	}
	
	public ArrayList<letterTile> returnRack()
	{
		return this.rack;
	}
	
	public void setView(Character[] v)
	{
		this.myView = v;
	}
	
	public Character[] getView(Character[] v)
	{
		return this.myView;
	}
	
	public ArrayList<Character> getSpecialTiles()
	{
		ArrayList<Character> returnList = new ArrayList<Character>();
		//for(int i=0;i<this.specialRack;i++)
		//{
			//returnList.add(specialRack.get(i))
		//}
		return returnList;
	}
	
}
