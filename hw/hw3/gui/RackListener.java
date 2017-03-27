package edu.cmu.qatar.cs214.hw.hw3.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RackListener implements ActionListener
{
	private int index;
	private Interface in;
	
		public RackListener(int index, Interface i)
		{
			this.index = index;
			this.in = i;
		}
	
		public void actionPerformed(ActionEvent e)
		{
			ArrayList<Integer> rackIndices = this.in.getRackIndices();
			if(rackIndices.contains(index))
			{
				JOptionPane.showInternalMessageDialog(this.in, "This tile is already selected");
			}
			else
			{
			this.in.addRackIndex(this.index);
			}
		}
}
