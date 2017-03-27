package edu.cmu.qatar.cs214.hw.hw3.gameEngine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import edu.cmu.qatar.cs214.hw.hw3.Player.Player;
import edu.cmu.qatar.cs214.hw.hw3.board.Grid;
import edu.cmu.qatar.cs214.hw.hw3.dictionary.Dictionary;
import edu.cmu.qatar.cs214.hw.hw3.pair.Pair;
import edu.cmu.qatar.cs214.hw.hw3.tile.Bag;
import edu.cmu.qatar.cs214.hw.hw3.tile.letterTile;

public class GameEngine 
{
	private ArrayList<Player> players;
	private int currentPlayer;
	private Player currPlayer;
	private Grid grid; 
	private Bag bag;
	//private boolean firstPlayFlag;
	private Dictionary dict;


///////////////////////////////////////////////////////////////////////////////
	// GameEngine being created given the number of players.
	public GameEngine(int numberOfPlayers)
	{
		// create the list for players
		this.players = new ArrayList<Player>();//[numberOfPlayers];
		// initialize the currentPlayer
		this.currentPlayer = 0;
		// create a grid
		this.grid = new Grid();
		// create a bag of letetrTiles
		this.bag = new Bag();
		dict = new Dictionary();
		//System.out.println("now we create number of players");
		instantiatePlayers(numberOfPlayers);
		// currentPlayer sets to 0th player in the arrayList
		this.currPlayer = this.players.get(0);
		// Distributing tiles in the rack for each player.
		this.distribute();
	}
	
/////////////////////////////////////////////////////////////////////////////////
	// Used by constructor to create players
	private void instantiatePlayers(int numberOfPlayers)
	{
		// create the players
		for(int i = 0; i < numberOfPlayers; i++)
		{
			
			this.players.add(new Player(i));
		}
	}
	
	public Color getSquareColor(char c)
	{
		return this.grid.returnColor(c);
	}
//////////////////////////////////////////////////////////////////////////////////
	// Gives back the current Player
	public int getCurrentPlayer()
	{
		return this.currentPlayer;
	}
	
	public void printBoard()
	{
		Character[] tempBoard = this.grid.getBoardArray();
		System.out.println("\nCurrent board is \n");
		for(int i = 0; i < tempBoard.length ; i++)
		{
			if(i % 15 == 0)
				System.out.println("");
			System.out.print(tempBoard[i]);
		}
	}
	
	public Character[] getBoard()
	{
		return this.grid.getBoardArray();
	}
//////////////////////////////////////////////////////////////////////////////////
	// Advances the player.
	private void advanceCurrentPlayer() 
	{
		if (this.players.size() == 0)
			throw new RuntimeException("No players added to the game");

		this.currentPlayer++;
		
		// At the end of each round we distribute the tiles.
		if (this.currentPlayer == this.players.size()) 
		{
			// At the end of each round we distribute the letterTiles
			this.distribute();
			this.currentPlayer = 0;
		}
		
		// changing the current Player to the new Player.
		this.currPlayer = players.get(currentPlayer);
		//notifier.notifyPlayerChanged(players.get(currentPlayer).getName(),players.get(currentPlayer).getSymbol());
	}
	
///////////////////////////////////////////////////////////////////////////////////
	// Gives back a list of all the players palying the game.
	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}

//////////////////////////////////////////////////////////////////////////////////
	
	private boolean validateMove(ArrayList<Pair<letterTile,Integer>> moves)
	{
		// Argument for this function is arrayList of moves.
		// A move is defined as a pair of letterTile and its index to be placed.
		boolean startFlag = false;
		
		for(int i=0; i<moves.size(); i++) //checking if there is already a tile on the index user gave
		{
			Character tempC = this.grid.getBoardArray()[moves.get(i).getRight()];
			if( (int)tempC <= 90 && (int)tempC >= 65)
			{
				return false;
			}
			
			if(grid.checkStartFlag(moves.get(i).getRight()))
			{
				startFlag = true;
			}
			//System.out.println("Index test passed");
		}
		
		if(startFlag == false)
			return false;
		
		// if it passed index test, now get all the words made on the board with the move given.
		
		ArrayList<String> wordsMade = grid.wordsOnBoard(moves);
		
		/*System.out.println("Words given to GamEngine are :");
		for(int i=0; i< wordsMade.size(); i++)
		{
			System.out.println(wordsMade.get(i));
		}*/
		
		int wordLen = wordsMade.size();
		
		for(int i=0; i < wordLen; i++)
		{
			if(! dict.isWord(wordsMade.get(i).toLowerCase()))
			{
				return false;
			}
		}
		
		System.out.println("Validation passed");
		return true;
		
	}
	
	// This function should be called after each round
	
	public ArrayList<Character> getRack(int p)
	{
		ArrayList<letterTile> tempRack =  players.get(p).returnRack();
		ArrayList<Character> returnRack = new ArrayList<Character>();
		for(int i= 0; i< tempRack.size() ;i++)
		{
			returnRack.add(tempRack.get(i).getLetter());
		}
		return returnRack;
	}
	
	private void distribute()
	{
		int numberOfPlayers = players.size();
		
		for(int i = 0; i < numberOfPlayers; i++)
		{
			// I am aware that this is bad but have no choice.
			Player tempPlayer = players.get(i);
			
			ArrayList<letterTile> rack = tempPlayer.returnRack();
			ArrayList<letterTile> newRack = bag.loadRack(rack);

			tempPlayer.loadRack(newRack);	
		}
	}
	
	private boolean validateSize(int size1, int size2)
	{
		return size1 == size2;
	}
	
	public boolean play(int currPlayer, ArrayList<Pair<Character,Integer>> move)
	{
		// first we make the move according to the player's moves.
		HashMap<letterTile,Integer> validMoves = 
				new HashMap<letterTile,Integer>();
		ArrayList<Pair<letterTile,Integer>> moves =
				new ArrayList<Pair<letterTile,Integer>>();
		// Get the rack of the current player
		ArrayList<letterTile> rack = this.currPlayer.returnRack();
		
		// Get all the characters of the move player has made.
		ArrayList<Character> moveChars =  new ArrayList<Character>();
		
		for(int i=0 ;i < move.size() ; i++)
		{
			//System.out.println("Characters user chose ");
			//System.out.println(move.get(i).getLeft());
			moveChars.add(move.get(i).getLeft()); //Put all characters user has entered
		}
		
		
		// Go through the rack and get the letterTiles with characters same as what
		// the user entered and put those letter tiles in moves
		
		for(int i=0; i < rack.size(); i++)
		{
			// get the ith tile
			letterTile temp = rack.get(i);
			
			// get the character of letetrTile
			Character c = temp.getLetter();
			
			boolean flagToStop = false;
		
			// check if the set of moves contains that character
			if(moveChars.contains(c))
			{
				// if yes, make the pair  (move,coordinate)
				moveChars.remove(c);
				
				for(int j=0; j < move.size(); j++)
				{
					Pair<Character, Integer> tempP = move.get(j);
					
					if(tempP.getLeft() == c && flagToStop == false)
					{
						Pair<letterTile,Integer> tPair =
								new Pair(temp,tempP.getRight());
						//and add that pair to moves.
						//rack.remove(i);
						moves.add(tPair);
						flagToStop = true;
					}
				}
			}
			

		}
		// Once you have gotten all the moves, validate that 
		// what user entered was actually in his rack by checking size of move and moves.
		
		if (! validateSize(moves.size(),move.size()))
		{
				return false;
		}
		
		// once done. Now validate if he made right moves i.e all words are correct.
		
		else if(validateMove(moves))
		{
			for(int i=0; i< moves.size(); i++)
			{
				validMoves.put(moves.get(0).getLeft(), moves.get(0).getRight());
			}
			
			
			grid.playMove(validMoves);
			for(int n = 0; n<moves.size();n++)
			{
				rack.remove(moves.get(n).getLeft());
			}
			
			addScore(move);
			
			this.advanceCurrentPlayer();
			return true;
		}
		
		//rack.addAll(new ArrayList<letterTile>(validMoves.keySet()));
		return false;
		
	}
	
	public boolean play()
	{
		advanceCurrentPlayer();
		return true;
	}
	
	public int getCurrentScore()
	{
		return this.players.get(currentPlayer).getScore();
	}
	
	private void addScore(ArrayList<Pair<Character,Integer>> move)
	{
		int totalScore = 0;
		Pair<Character,Integer> pTemp;
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for(int i=0; i<move.size(); i++)
		{
			pTemp = move.get(i);
			int index = pTemp.getRight();
			totalScore += this.grid.
					returnScore(index,this.bag.getScore(pTemp.getLeft()) );
			indices.add(index);
		}
		//System.out.println(totalScore);
		totalScore = this.grid.returnScore(indices, totalScore); 
		//System.out.println(totalScore);
		this.players.get(currentPlayer).setScore(totalScore);
		//System.out.println(this.players.get(currentPlayer).getScore());
	}
	
	public boolean play(ArrayList<Character> lettersToExchange)
	{
		ArrayList<letterTile> rack =  this.players.
				get(this.currentPlayer).returnRack();
		ArrayList<letterTile> rackRemove = new ArrayList<letterTile>();
		for(int i=0; i < rack.size(); i++)
		{
			
			if(lettersToExchange.contains(rack.get(i).getLetter()))
			{
				rackRemove.add(rack.get(i));
			}
		}
		
		rack.removeAll(rackRemove);
		rack = this.bag.loadRack(rack);
		players.get(this.currentPlayer).loadRack(rack);
		advanceCurrentPlayer();
		return true;
	}
	
}
