package edu.cmu.qatar.cs214.hw.hw3.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;

public class PassListener implements ActionListener
{
	private Interface In;
	private GameEngine GE;
	
	public PassListener(GameEngine GE,Interface In)
	{
		this.In = In;
		this.GE = GE;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		//this.GE.advanceCurrentPlayer();
		this.GE.play();
		In.advanceCurrentPlayer();
	}
	
	
}
