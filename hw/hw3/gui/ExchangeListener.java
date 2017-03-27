package edu.cmu.qatar.cs214.hw.hw3.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;

public class ExchangeListener implements ActionListener 
{
	private Interface in;
	private GameEngine GE;
	
	ExchangeListener(Interface I,GameEngine GE)
	{
		this.in = I;
		this.GE = GE;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		ArrayList<Integer> rackIndices = this.in.getRackIndices();
		ArrayList<Character> rackChars = this.GE.getRack(this.GE.getCurrentPlayer());
		ArrayList<Character> lettersToExchange = new ArrayList<Character>();
		for(int i = 0; i < rackIndices.size(); i++)
		{
			lettersToExchange.add(rackChars.get(rackIndices.get(i)));
		}
		
		GE.play(lettersToExchange);
		in.advanceCurrentPlayer();
		
	}

}
