package edu.cmu.qatar.cs214.project;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DriverGUI extends JPanel
{
	private JButton maleDorms;
	private JButton femaleDorms;
	private DriverApp app;
	
	DriverGUI(DriverApp app)
	{
		this.app = app;
	}
	
	public void init()
	{
		setLayout(new BorderLayout());
		//this.maleDorms = new JButton();
		//this.femaleDorms = new JButton();
	}

	private void setLayout(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}
	
}
