package edu.cmu.qatar.cs214.hw.hw3.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;
import edu.cmu.qatar.cs214.hw.hw3.pair.Pair;

public class PlayListener implements ActionListener
{
	private GameEngine GE;
	private Interface In;
	
	public PlayListener(GameEngine GE, Interface In)
	{
		this.GE = GE;
		this.In = In;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ArrayList<Character> rack = GE.getRack(GE.getCurrentPlayer());
		ArrayList<Integer> squareIndices = In.getSquareIndices();
		ArrayList<Integer> rackIndices = In.getRackIndices();
		ArrayList<Pair<Character,Integer>> moves = new ArrayList<Pair<Character,Integer>>();
		for(int i=0; i<rackIndices.size();i++)
		{
			int index = squareIndices.get(i);
			char c = rack.get(rackIndices.get(i));
			Pair<Character,Integer> pTemp = new Pair<Character,Integer>(c,index);
			moves.add(pTemp);
		}
		
		int currentPlayer = GE.getCurrentPlayer();

		if(this.GE.play(currentPlayer,moves) == false)
		{
			JOptionPane.showInternalMessageDialog(this.In, "Invalid Move.");
			In.emptyRackIndices();
			In.emptySquareIndices();
		}
		
		else
		{
			System.out.println("Validated");
			JOptionPane.showInternalMessageDialog(this.In, "Well Done");
			In.updateBoard(moves);
			In.advanceCurrentPlayer();
		}
		
		
		
	}

}
