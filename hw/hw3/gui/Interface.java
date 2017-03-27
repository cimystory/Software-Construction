package edu.cmu.qatar.cs214.hw.hw3.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;
import edu.cmu.qatar.cs214.hw.hw3.pair.Pair;

public class Interface extends JPanel
{
	private static final int MAPLEN = 15;
	private static final int RACKLEN = 7;
	private static final int STILELEN = 5;
	private JButton[] squares = new JButton[MAPLEN*MAPLEN];
	private JButton[] spTiles = new JButton[STILELEN];
	private JButton[] rack = new JButton[RACKLEN];
	private GameEngine GE;
	private int currentPlayer;
	private JLabel currentPlayerLabel;
	private JLabel currentPlayerScore;
	private ArrayList<Integer> rackIndices;
	private ArrayList<Integer> squareIndices;
	private ArrayList<Character> rackChars;
	
	public Interface(GameEngine ge)
	{
		this.GE = ge;
		this.init();
	}
	
	private void init()
	{
		setLayout(new BorderLayout());
		this.currentPlayer = this.GE.getCurrentPlayer();
		add(createBoardPanel(), BorderLayout.CENTER);
		this.currentPlayerLabel = new JLabel();
		this.currentPlayerScore= new JLabel();
		this.currentPlayerLabel.setText("Player "+this.GE.getCurrentPlayer());
		this.currentPlayerScore.setText("Score : "+this.GE.getCurrentScore());
		add(createBoxLayout(),BorderLayout.SOUTH);
		add(createMoveGridLayout(),BorderLayout.WEST);
		this.rackIndices = new ArrayList<Integer>();
		this.rackChars = this.GE.getRack(this.currentPlayer);
		this.squareIndices = new ArrayList<Integer>();
	}
	
	private JPanel createBoxLayout()
	{
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(this.currentPlayerLabel);
		panel.add(this.currentPlayerScore);
		panel.add(makeRack());
		panel.add(makeSpecialRack());
		return panel;
		
	}
	
	private JPanel createMoveGridLayout()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		
		JButton play = new JButton();
		play.setBackground(Color.WHITE);
		play.setText("PLAY");
		play.addActionListener(new PlayListener(this.GE,this));
		panel.add(play);
		
		JButton exchange = new JButton();
		exchange.setText("EXCHANGE");
		exchange.setBackground(Color.WHITE);
		exchange.addActionListener(new ExchangeListener(this,this.GE));
		panel.add(exchange);
		
		JButton pass = new JButton();
		pass.setText("PASS");
		pass.setBackground(Color.WHITE);
		pass.addActionListener(new PassListener(this.GE,this));
		panel.add(pass);
		return panel;
		
	}

	private JPanel createBoardPanel() 
	{
		JPanel panel = new JPanel();
		Character[] board = this.GE.getBoard();
		panel.setLayout(new GridLayout(this.MAPLEN, this.MAPLEN));	
		for (int y = 0; y < this.MAPLEN; y++) 
		{
			for (int x = 0; x < this.MAPLEN; x++) 
			{
				int ind = y * this.MAPLEN + x;
				char c = board[ind];
				squares[ind] = new JButton();
				squares[ind].setBackground(GE.getSquareColor(c));
				squares[ind].setText(" ");
				squares[ind].
				addActionListener(new SquareListener(ind, this.GE,this));
				panel.add(squares[ind]);
			}
		}
		
		return panel;
	}
	
	private JPanel makeRack()
	{
		JPanel panel = new JPanel();
		// Getting the current rack
		ArrayList<Character> rackChar = GE.getRack(GE.getCurrentPlayer());
		// Setting the panel horizontally in 1 line
		panel.setLayout(new GridLayout(1,this.RACKLEN));
		//creating a new rackLabel
		JLabel rackLabel = new JLabel();
		rackLabel.setText("Rack : ");
		panel.add(rackLabel);
		// Now creating buttons for the rack
		for(int i = 0; i < this.RACKLEN; i++)
		{

			this.rack[i] = new JButton();
			this.rack[i].setBackground(Color.WHITE);
			this.rack[i].setText(rackChar.get(i).toString());
			this.rack[i].addActionListener(new RackListener(i,this));
			panel.add(this.rack[i]);
		}
		return panel;
	}
	
	private JPanel makeSpecialRack()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,this.STILELEN));
		JLabel SrackLabel = new JLabel();
		SrackLabel.setText("Special rack : ");
		panel.add(SrackLabel);
		this.spTiles[0] = new JButton();
		this.spTiles[0].setText("Boom");
		panel.add(this.spTiles[0]);
		
		this.spTiles[0].
		addActionListener(new SpecialListener(this, 0));
		
		this.spTiles[0].setBackground(Color.WHITE);
		this.spTiles[1] = new JButton();
		this.spTiles[1].setText("Negative");
		this.spTiles[1].setBackground(Color.WHITE);
		panel.add(this.spTiles[1]);
		
		this.spTiles[1].
		addActionListener(new SpecialListener(this, 1));
		
		this.spTiles[2] = new JButton();
		this.spTiles[2].setText("Reverse");
		this.spTiles[2].setBackground(Color.WHITE);
		panel.add(this.spTiles[2]);
		this.spTiles[2].
		addActionListener(new SpecialListener(this, 2));
		
		this.spTiles[3] = new JButton();
		this.spTiles[3].setText("Good Tile");
		this.spTiles[3].setBackground(Color.WHITE);
		panel.add(this.spTiles[3]);
		this.spTiles[3].
		addActionListener(new SpecialListener(this, 3));
		
		this.spTiles[4] = new JButton();
		this.spTiles[4].setBackground(Color.WHITE);
		this.spTiles[4].setText("End Game Tile");
		panel.add(this.spTiles[4]);
		this.spTiles[4].
		addActionListener(new SpecialListener(this, 4));
	
		return panel;
	}

	public void addRackIndex(int index) 
	{
		// TODO Auto-generated method stub
		this.rackIndices.add(index);
	}
	
	public ArrayList<Integer> getRackIndices()
	{
		return this.rackIndices;
	}
	
	public void addSquareIndex(int index) 
	{
		// TODO Auto-generated method stub
		this.squareIndices.add(index);
	}
	
	public ArrayList<Integer> getSquareIndices()
	{
		return this.squareIndices;
	}

	public void advanceCurrentPlayer()
	{
		// TODO Auto-generated method stub
		emptyRackIndices();
		emptySquareIndices();
		updatePlayer();
		updateScore();
		updateRack();
	}

	public void updateBoard(ArrayList<Pair<Character,Integer>> moves) 
	{
		// TODO Auto-generated method stub
		for(int i = 0; i<moves.size();i++)
		{
			Pair<Character,Integer> pTemp = moves.get(i);
			String c = pTemp.getLeft().toString();
			int index = pTemp.getRight();
			this.squares[index].setText(c);
		}
		
	}

	public void emptyRackIndices() 
	{
		// TODO Auto-generated method stub
		this.rackIndices = new ArrayList<Integer>();
	}

	public void emptySquareIndices()
	{
		// TODO Auto-generated method stub
		this.squareIndices = new ArrayList<Integer>();
	}
	
	private void updatePlayer()
	{
		this.currentPlayerLabel.setText("Player "+this.GE.getCurrentPlayer());
		this.currentPlayer = this.GE.getCurrentPlayer();
	}
	
	private void updateScore()
	{
		this.currentPlayerScore.setText("Score : "+this.GE.getCurrentScore());
		
	}
	
	private void updateRack()
	{
		ArrayList<Character> rack = this.GE.getRack(this.currentPlayer);
		this.rackChars = rack;
		for(int i = 0; i < this.RACKLEN; i++)
		{
			this.rack[i].setText(this.rackChars.get(i).toString());
		}
	}
	
}