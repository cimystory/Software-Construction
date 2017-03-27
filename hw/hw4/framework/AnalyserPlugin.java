package edu.cmu.qatar.cs214.hw.hw4.framework;

import javax.swing.JPanel;

public interface AnalyserPlugin 
{
	public void Analyse(JPanel jp,DataManager DM);
	
	public String getTitle();
}
