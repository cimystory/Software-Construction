package edu.cmu.qatar.cs214.hw.hw3.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;


public class Main 
{
	private static final int numPlayers = 2;
	
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() {
				createAndShowGameBoard();
			}
		});
	}
	
	private static void createAndShowGameBoard(){
		// Create and set-up the window
		JFrame gameWindow = new JFrame("Scrabble");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameEngine GE = new GameEngine(numPlayers);
		
		// Create and set up the content pane
		Interface gameBoard = new Interface(GE);
		gameBoard.setOpaque(true);
		gameWindow.setContentPane(gameBoard);
		// Display the window.
		gameWindow.pack();
		gameWindow.setVisible(true);
	}

}
