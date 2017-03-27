package edu.cmu.qatar.cs214.hw.hw3.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;

public class SquareListener implements ActionListener 
{
	private GameEngine game;
	private Interface in;
	private int index;
	
	public SquareListener(int index, GameEngine game,Interface in) 
	{
		this.game = game;
		this.index = index;
		this.in = in;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(index);
		ArrayList<Integer> rackIndices = this.in.getRackIndices();
		ArrayList<Integer> squareIndices = this.in.getSquareIndices();
		
		if(rackIndices.size() == 0)
		{
			JOptionPane.showInternalMessageDialog(this.in, "Invalid Move. Select the letters from the rack first altogether say A-B-C. Then select their places say 1-2-3 altogether");
		}
		
		else if(rackIndices.size() == squareIndices.size())
		{
			JOptionPane.showInternalMessageDialog(this.in, "Your moves are locked. Press the move to Proceed");
		}
		
		else
		{
			this.in.addSquareIndex(index);
		}
		
		
		
	}
	
	

}
