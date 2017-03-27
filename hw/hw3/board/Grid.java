package edu.cmu.qatar.cs214.hw.hw3.board;

//import java.io.BufferedReader;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.cmu.qatar.cs214.hw.hw3.pair.Pair;
//import edu.cmu.qatar.cs214.hw.hw3.square.DLSSquare;
//import edu.cmu.qatar.cs214.hw.hw3.square.DWSSquare;
//import edu.cmu.qatar.cs214.hw.hw3.square.Square;
//import edu.cmu.qatar.cs214.hw.hw3.square.TLSSquare;
//import edu.cmu.qatar.cs214.hw.hw3.square.TWSSquare;
//import edu.cmu.qatar.cs214.hw.hw3.square.ordSquare;
import edu.cmu.qatar.cs214.hw.hw3.tile.letterTile;

public class Grid 
{
	// I thought making a square class was unnecessary to removed it.
	//private Square[] board;
	//private Character[] prevMap;
	private Character[] currMap;
	private Character[] potentialBoard;
	private HashMap<Character,Color> SquareColors;
	private boolean startFlag;
	private String map;
	private int mapLen = 15;
	
	// Constructor to initialize the Grid by reading the String
	public Grid()
	{
		/*
		 * Start flag is used to check if player places first move near the star
		 * i.e index = 112 - if not its not valid move.
		 */
		this.startFlag = false;
		this.currMap = new Character[this.mapLen*this.mapLen];
		/* Potential board saves our temporary changed board
		 * so that if the move is valid, we dont do extra work 
		*/
		this.SquareColors = new HashMap<Character,Color>();
		this.potentialBoard = new Character[this.mapLen*this.mapLen];
		readMap();
		// Putting this String board in the String array, this is what we will modify.
		fillBoardArray();
		// For now our previous map is same as current
		//this.prevMap = this.currMap.clone();
	}
	
	/*
	 * readMap() basically reads the map from file or String.
	 * We can change this map in the file or string and the game will
	 * work fine.
	 */
	
	//  ReadsMap from file or from String
	private void readMap()
	{
		/* For me ! means its an ordinary square
		 * 		  @ means its a double letter
		 * 		  # means its a double word
		 * 		  $ means its a triple letter
		 * 		  % means its a triple word
		 */
		// We can read this from the file as well. But for now this is ok.
		this.map = 	 "%!!@!!!%!!!@!!%"
				   + "!#!!!$!!!$!!!#!"
				   + "!!#!!!@!@!!!#!!"
				   + "@!!#!!!@!!!#!!@"
				   + "!!!!#!!!!!#!!!!"
				   + "!$!!!$!!!$!!!$!"
				   + "!!@!!!@!@!!!@!!"
				   + "%!!@!!!*!!!@!!%"
				   + "!!@!!!@!@!!!@!!"
				   + "!$!!!$!!!$!!!$!"
				   + "!!!!#!!!!!#!!!!"
				   + "@!!#!!!@!!!#!!@"
				   + "!!#!!!@!@!!!#!!"
				   + "!#!!!$!!!$!!!#!"
				   + "%!!@!!!%!!!@!!%";
		
		this.SquareColors.put('%', Color.RED);
		this.SquareColors.put('!', Color.WHITE);
		this.SquareColors.put('@', Color.CYAN);
		this.SquareColors.put('#', Color.PINK);
		this.SquareColors.put('$', Color.YELLOW);
		this.SquareColors.put('*', Color.LIGHT_GRAY);
	}
	
	public Color returnColor(char c)
	{
		return this.SquareColors.get(c);
	}
	
	/*
	 * This is to check whether the startFlag is on or off given an index
	 */
	
	public boolean checkStartFlag(int index)
	{
		if(this.startFlag == true)
			return true;
		//System.out.println("112 has" + currMap[112]);
		if(index != 112 && this.currMap[112] == '*')
			{
			this.startFlag = true;
			return true;
			}
		return false;
	}
	
	/*
	 * Given the index and the letterScore,
	 * returnScore returns the letterScore along with the
	 * square score if the square is Double or tripple letter
	 */
	
	public int returnScore(int index,int letterScore)
	{
		if(this.map.charAt(index)  == '@')
		{
			return letterScore * 2;
		}
		
		else if(this.map.charAt(index)  == '$')
		{
			return letterScore*3;
		}
		return letterScore;
	}
	
	/*
	 * Given the indices and a totalScore, returnScore returns the
	 * score of the word taking into consideration
	 * any special squares.
	 */
	
	public int returnScore(ArrayList<Integer> indices, int letterScore)
	{

		int totalScore = letterScore;
		boolean flagTW = false;
		boolean flagDW = false;
		
		for(int i=0; i<indices.size();i++)
		{
			if(this.map.charAt(indices.get(i)) == '%')
			{
				flagTW = true;
			}
			else if(this.map.charAt(indices.get(i)) == '#')
			{
				flagDW = true;
			}
		}
		
		if(flagTW == true)
		{
			totalScore = letterScore * 3;
		}
		
		if(flagDW == true)
		{
			totalScore = letterScore * 2;
		}
		return totalScore;
	}
	
	/*
	 * fillBoardArray() basically fills the 
	 * array of board with the map we read from file/string.
	 * This array will be used for later purposes of manipulation.
	 */
	
	private void fillBoardArray()
	{
		
		for(int i=0; i<mapLen*mapLen ; i++)
		{
			this.currMap[i] = this.map.charAt(i);
		}
	}
	
	/*
	 * getBoardArray gives back the board saved in array
	 */
	
	public Character[] getBoardArray()
	{
		return this.currMap;
	}
	
	/*
	 * playMove(moves) basically just updates the board with the moves given
	 */
	public boolean playMove(HashMap<letterTile,Integer> moves)
	{
		this.currMap = this.potentialBoard;
		
		
		//System.out.println("After making the move, here is the board");
		for(int i = 0; i < currMap.length ; i++)
		{
			if(i % 15 == 0)
				System.out.println("");
			System.out.print(currMap[i]);
		}
		
		return true;
	}
/*
 * I made this function to separate the currentBoard and the previous Board
 * to accomodate the idea of special tiles and separate views.
 * But I could not implement special tiles.
 */
	
	public Character[] getCurrentBoard()
	{
		// I need to change this.
		return this.currMap;
	}
	
/*
 * wordsInColumn basically gets the words in the column
 */

	private ArrayList<String> wordsInColumn(int c, Character[] tempBoard)
	{
		ArrayList<String> returnWords = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		String temp = "";
		
		for(int i =0; i<this.mapLen;i++)
		{
			int index = i * this.mapLen + c;
			Character tChar = tempBoard[index];
			
			if(tChar >= 65 && tChar <= 90)
			{
				sb.append(tChar);
			}
			
			else
			{
				temp = sb.toString();
				if(!temp.equals("") && temp.length() > 1)
				{
					returnWords.add(temp);
					temp = "";
					sb = new StringBuilder();
				}
				
			}
		}
		
		return returnWords;
	}
	
	
	//  Given the row r and the board, this function gives back all the words in that row
	private ArrayList<String> wordsInRow(int r, Character[] tempBoard)
	{
		ArrayList<String> returnWords = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		String temp = "";
		
		for(int i = 0; i<this.mapLen;i++)
		{
			int index = r * this.mapLen + i;
			Character tChar = tempBoard[index];
			
			if(tChar >= 65 && tChar <= 90)
			{
				sb.append(tChar);
			}
			
			else
			{
				temp = sb.toString();
				if(!temp.equals("") && temp.length() > 1)
				{
					returnWords.add(temp);
					temp = "";
					sb = new StringBuilder();
				}
				
			}
		}
		
		return returnWords;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	private String findLeft(Pair<letterTile,Integer> moveSent,Character[] tempBoard)
	{
		
		StringBuilder sb = new StringBuilder();
		int ind = moveSent.getRight();
		int row = ind / this.mapLen;
		boolean stop = false;
		String leftWord = "";
		while(ind >= (row * this.mapLen) && stop!=true)
		{
			//System.out.println("inside loop");
			Character cTemp = tempBoard[ind];
			//System.out.println(cTemp);
			if((int)cTemp <= 90 && (int)cTemp >= 65) //This means its not a character
			{
				sb.append(cTemp);
				ind--;			
			}
			else
			{
				stop = true;
			}
		}
		leftWord = sb.reverse().toString();
		return leftWord;
	}
	
	
	private String findRight(Pair<letterTile,Integer> moveSent,Character[] tempBoard)
	{
		StringBuilder sb = new StringBuilder();
		int ind = moveSent.getRight()+1;
		int row = ind / this.mapLen;
		//int col = ind % this.mapLen;
		boolean stop = false;
		//String leftWord = "";
		String rightWord = "";
		
		while(ind < ((row+1)*this.mapLen) && stop!=true )
		{
			Character cTemp = tempBoard[ind];
			
			if((int)cTemp <= 90 && (int)cTemp >= 65) //This means its not a character
			{
				sb.append(cTemp);
				ind++;

			}
			
			else
			{
				stop = true;
			}
		}
		rightWord = sb.toString();
		return rightWord;
	}
	
	private String findUp(Pair<letterTile,Integer> moveSent,Character[] tempBoard)
	{
		StringBuilder sb = new StringBuilder();
		int ind = moveSent.getRight();
		//int row = ind / this.mapLen;
		int col = ind % this.mapLen;
		boolean stop = false;
		//String leftWord = "";
		String upWord = "";
		
		while(ind >= col && stop!= true)
		{
			Character cTemp = tempBoard[ind];
			if((int)cTemp <= 90 && (int)cTemp >= 65) //This means its not a character
			{
				sb.append(cTemp);
				ind = ind-this.mapLen;
				
				
			}
			else
			{
				stop = true;
			}
		}
		
		upWord = sb.reverse().toString();
		return upWord;
	}
	
	private String findDown(Pair<letterTile,Integer> moveSent,Character[] tempBoard)
	{
		StringBuilder sb = new StringBuilder();
		int ind = moveSent.getRight()+this.mapLen;
		//int row = ind / this.mapLen;
		int col = ind % this.mapLen;
		boolean stop = false;
		//String leftWord = "";
		String downWord = "";
		
		while(ind <=  (this.mapLen * (this.mapLen - 1) + col - 1) && stop!=true)
		{
			Character cTemp = tempBoard[ind];
			if((int)cTemp <= 90 && (int)cTemp >= 65) //This means its not a character
			{
				sb.append(cTemp);
				ind = ind+this.mapLen;
				
			}
			else
			{
				stop = true;
			}
		}
		downWord = sb.toString();
		return downWord;
	}
	
	
	public ArrayList<String> wordsOnBoard(ArrayList<Pair<letterTile,Integer>> moves)
	{
		int lenOfMove  = moves.size(); //Size of the move
		//System.out.println("LENGTH OF MOVE IS "+lenOfMove);
		ArrayList<String> wordsMade = new ArrayList<String>(); //Return list of all the words
		HashMap<Integer,Character> specialActive = 
				new HashMap<Integer,Character>(); //for Special tiles
		Character[] tempBoard = getCurrentBoard().clone(); //Creating a temporary board	
		//This flag will show if player used an existing letter
		boolean crossFlag = false; //This has to be true
		
		// making changes to the temporary board
		
		for(int i=0; i < lenOfMove; i++)
		{
			// Get the move from the list
			
			Pair<letterTile,Integer> tempPair = moves.get(i);
			
			// Get the index of the move
			int index = tempPair.getRight();
			
			// get the character on that index of the board.
			Character c = tempBoard[index];
			
			// Checking for special tiles
			// If there is a special tile put it in a list
			if(c == '-' || c == '&' || c == '(' || c == ')' || c == '^')
			{
				specialActive.put(i,c);
			}
			
			// make the changes to the board now.
			//System.out.println(tempPair.getLeft().getLetter());
			tempBoard[tempPair.getRight()] = tempPair.getLeft().getLetter();
		}
		
		// Printing to the board
		/*System.out.println("Printing board");
		for(int i = 0; i < tempBoard.length ; i++)
		{
			if(i % 15 == 0)
				System.out.println("");
			System.out.print(tempBoard[i]);
		}*/
		
		// Now there is a difference between a single letter move
		
		if(lenOfMove == 1)
		{
			// That means I need to check in all directions.
			Pair<letterTile,Integer> onlyMove = moves.get(0);
			String wordMadeH = findLeft(onlyMove,tempBoard) +  findRight(onlyMove,tempBoard);
			if(!wordMadeH.equals("") && !(wordMadeH.length() == 1))
			{
				crossFlag = true;
				
				wordsMade.add(wordMadeH);
				//System.out.println("Word made is "+wordMadeH);
			}
			//Checking for a word from up to down
			String wordMadeV = findUp(onlyMove,tempBoard) + findDown(onlyMove,tempBoard);
			if(!wordMadeV.equals("") && !(wordMadeV.length() == 1))
			{
				wordsMade.add(wordMadeV);
				//System.out.println("Word made is "+wordMadeV);
				//System.out.println("Word added to the list is "+wordMade);
				crossFlag = true;
			}
		}
/////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////The main part of the program //////////////////////////////////		
////////////////////////////////////////////////////////////////////////////////////////	
		else if(lenOfMove > 1)
		{
		// Now I need to know if the word he made is vertical or horizontal
			int x1 = moves.get(0).getRight() % this.mapLen;
			int x2 = moves.get(1).getRight() % this.mapLen;
			int x = x1 - x2;
			String mainWord = ""; //I will make this as a function in future
			Pair<letterTile,Integer> firstMove = moves.get(0);
			
			if(x == 0) //That means its vertical move
			{
				// This means I need to check vertically 1 main word and horizontally len(move) words
				// to see if they are valid
				
				//let us first check the main word
				// check up

				mainWord = findUp(firstMove,tempBoard)+ findDown(firstMove,tempBoard);
				
				wordsMade.add(mainWord);
				
				// We need to check all the crossing words for which we need all the rows
				for(int i=0; i < lenOfMove; i++)
				{
					int r = moves.get(i).getRight() / this.mapLen;
					wordsMade.addAll(wordsInRow(r,tempBoard));
				}
				crossFlag = checkCrossFlagV(moves);
			}
			
			else // That means its horizontal move
			{
				// This means I need to check horizontally 1 main word and vertically len(move) words
				// to see if they are valid
				
				// Let us first check the mainWord

				mainWord = findLeft(firstMove,tempBoard)+ findRight(firstMove,tempBoard);
				wordsMade.add(mainWord);
				
				// We need to check all the crossing words for which we need all the columns
				
				for(int i=0; i < lenOfMove; i++)
				{
					int column = moves.get(i).getRight() % this.mapLen;
					wordsMade.addAll(wordsInColumn(column,tempBoard));
				}	
				
				crossFlag = checkCrossFlagH(moves);
			}	
			//System.out.println("mainword is "+mainWord);
			
		}
		
		if(crossFlag)
		{
		this.potentialBoard = tempBoard;
		return wordsMade;
		}
		else
		return new ArrayList<String>();
	}

	private boolean checkCrossFlagH(ArrayList<Pair<letterTile, Integer>> moves) 
	{
		//Check if there is a gap that means there is a cross
		Integer[] indices = new Integer[moves.size()];
		for(int i=0;i<moves.size();i++)
		{
			indices[i] = moves.get(i).getRight();
		}
		Arrays.sort(indices);
		for(int i = 0; i < indices.length-1; i++)
		{
			if(Math.abs(indices[i] - indices[i+1]) > 1)
				return true;
		}
		
		// Now we check if its extending something
		if(indices[0] % 15 != 0)
		{
			char prev  = this.currMap[indices[0]-1];
			if((int) prev >= 65 && (int) prev <= 90)
				return true;
		}
		
		if((indices[moves.size()-1] + 1) % 15 != 0)
		{
			char next = this.currMap[indices[moves.size()-1]+1];
			if((int) next >= 65 && (int) next <= 90)
				return true;
		}
		
		
		
		return true;
		//Check if there is a hook
	}
	
	private boolean checkCrossFlagV(ArrayList<Pair<letterTile, Integer>> moves) 
	{

		//Check if there is a gap that means there is a cross
		Integer[] indices = new Integer[moves.size()];
		for(int i=0;i<moves.size();i++)
		{
			indices[i] = moves.get(i).getRight();
		}
		Arrays.sort(indices);
		for(int i = 0; i < indices.length-1; i++)
		{
			if(Math.abs(indices[i] - indices[i+1]) > this.mapLen)
				return true;
		}
		
		// Now we check if its extending something.
		if(indices[0] / this.mapLen != 0)
		{
			char prev  = this.currMap[indices[0]-this.mapLen];
			if((int) prev >= 65 && (int) prev <= 90)
				return true;
		}
		
		
		if((indices[moves.size()-1] + this.mapLen) % this.mapLen != 0)
		{
			char next = this.currMap[indices[moves.size()-1]+this.mapLen];
			if((int) next >= 65 && (int) next <= 90)
				return true;
		}
		
		// Now we check if there is a hook
		
		return true;
	}
	
	
	
}
