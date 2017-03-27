package edu.cmu.qatar.cs214.hw.hw4.starter;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.cmu.qatar.cs214.hw.hw4.framework.AnalyserPlugin;
import edu.cmu.qatar.cs214.hw.hw4.framework.App;
import edu.cmu.qatar.cs214.hw.hw4.framework.DataPlugin;
import edu.cmu.qatar.cs214.hw.hw4.plugin.SentimentAnalyser;
import edu.cmu.qatar.cs214.hw.hw4.plugin.TopTenUsers;
import edu.cmu.qatar.cs214.hw.hw4.plugin.TwitterDP;

public class Main 
{
	
	private static ArrayList<DataPlugin> dp;
	private static ArrayList<AnalyserPlugin> ap;
	private static App framework;
	
	public static void main(String[] args) 
	{
		dp = new ArrayList<DataPlugin>();
		ap = new ArrayList<AnalyserPlugin>();
		dp.add(new TwitterDP());
		ap.add(new SentimentAnalyser());
		
		/* This plugin is not implemented properly 
		 * but just to show show that we can extend
		 * it.
		 */
		
		ap.add(new TopTenUsers());
		
		
		SwingUtilities.invokeLater(new Runnable() 
		{
				@Override
				public void run() 
				{
					create();
				}
		});
	}
	
	/*
	 * This function creates  JFrame for
	 * the main framework.
	 * Idea taken from Professor's code 
	 */
	
	private static void create()
	{
		// Create and set-up the window
		JFrame Window = new JFrame("Social media Analyser");
		Window.setSize(800,600);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create and set up the content pane
		framework = new App(dp,ap);
		//Window.setOpaque(true);
		Window.setLayout(new BorderLayout());
		Window.setContentPane(framework);
		Window.setMinimumSize(Window.getSize());
		
		// Display the window.
		Window.pack();
		Window.setVisible(true);
		
	}
}

