package edu.cmu.qatar.cs214.hw.hw3.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JOptionPane;

//import edu.cmu.qatar.cs214.hw.hw3.gameEngine.GameEngine;

public class SpecialListener implements ActionListener
{
	private Interface I;
	private int index;
	
	SpecialListener(Interface I,int i)
	{
		this.I = I;
		this.index = i;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		//System.out.println(this.index);
		JOptionPane.showInternalMessageDialog(this.I, "Invalid Move. Your Score is < 1000");
	}
}
