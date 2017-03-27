package edu.cmu.qatar.cs214.hw.hw3;

import java.util.ArrayList;
import java.util.Scanner;
import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;
import edu.cmu.qatar.cs214.hw.hw3.pair.Pair;

import javax.swing.JFrame;

public class Main 
{

	public static void main(String[] args) 
	{
		//Random moveGen = new Random();
		Scanner scanner = new Scanner(System.in);
		
		//Read an entire line
		//System.out.println("Please type the number of players?");
		int number = 2;//scanner.nextInt();
		
		// After getting the number of players, we instantiate the gameEngine
		GameEngine GE = new GameEngine(number);
		
		// This flag shall be used to stop the game
		boolean flag = true;
		
		// To keep track of player.
		int currentPlayer = GE.getCurrentPlayer();
		
		while(flag != false)
		{
			System.out.print("\nPlayer");
			System.out.print(currentPlayer);
			System.out.println(":");
			GE.printBoard();
			System.out.println();
			System.out.print("Your current Score is : ");
			System.out.println(GE.getCurrentScore());
			ArrayList<Character> CurrRack =  GE.getRack(currentPlayer);
			
			
			System.out.println("You have the following tiles "
					+ "on your rack : \n");
			System.out.print("Rack : ");
			
			// Printing the rack for the current Player.
			for(int i=0; i<CurrRack.size();i++)
			{
				System.out.print(CurrRack.get(i) + " ");
			}
			
			// To save the moves by the player
			
			ArrayList<Pair<Character,Integer>> move = new 
					ArrayList<Pair<Character,Integer>>();
			// To stop when the player is done
			boolean stop = false;
			
			// To count the number of moves entered by the player
			int count = 0;
			
			// Enter where you want to place the move
			System.out.println("\nEnter\n1 for Play\n"
					+ "2 for Exchange\n3 for Pass\n4 to Purchase Special tile\n"
					+ "5 to place special tile\n");
			int choice = scanner.nextInt();
			
			if(choice == 3)
			{
				GE.play();
				currentPlayer = GE.getCurrentPlayer();
			}
			
			else if(choice == 2)
			{
				ArrayList<Character> toEx = new ArrayList<Character>();
				System.out.println("Enter the letters you want to exchange. Press "
						+ "'.' when done");
				char c;
				while((c = scanner.next().charAt(0))!='.')
				{
					toEx.add(c);
				}
				
				GE.play(toEx);
				
				CurrRack =  GE.getRack(currentPlayer);
				
				
				System.out.println("You have the following tiles on your rack : \n");
				System.out.print("Rack : ");
				// Printing the rack for the current Player.
				for(int i=0; i<CurrRack.size();i++)
				{
					System.out.print(CurrRack.get(i) + " ");
				}
				System.out.println();
				currentPlayer = GE.getCurrentPlayer();
				
			}
			
			else if(choice == 1)
			{
			
			System.out.println("Enter your move followed by the index. Enter "
					+ "'.' when done");
			
			// Take the moves from the player.
			while(stop != true && count < 7)
			{
				
				char character = scanner.next().charAt(0);
				
				// If player wants to stop giving the moves.
				if(character == '.')
				{
					stop = true;
				}
				
				// else get the position.
				else
				{
					int position = scanner.nextInt();
					Pair<Character,Integer> tempMove = 
							new Pair<Character,Integer>(character,position);
					move.add(tempMove);
				}
				
				// Add the count of moves
				count++;
			}
			
			// Once you have gotten all the moves.
			
			if(GE.play(currentPlayer,move)!=false)
			{
				currentPlayer = GE.getCurrentPlayer();
			}
			
			else
			{
				System.out.println("Invalid Move");
			}
		}
		
			else if(choice == 4)
			{
				System.out.println("Invalid move. Choose again");
			}
			
			else if(choice == 5)
			{
				System.out.println("Invalid move. Choose again");
			}
			else
			{
				System.out.println("Invalid move. Choose again");
			}
	}

	}

}
