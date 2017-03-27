/*
 * Name  : Shahan Ali Memon
 * ID    : samemon
 * HW    : 4
 */


package edu.cmu.qatar.cs214.hw.hw4.plugin;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import edu.cmu.qatar.cs214.hw.hw4.framework.AnalyserPlugin;
import edu.cmu.qatar.cs214.hw.hw4.framework.App;
import edu.cmu.qatar.cs214.hw.hw4.framework.Data;
import edu.cmu.qatar.cs214.hw.hw4.framework.DataManager;

public class SentimentAnalyser implements AnalyserPlugin
{
	private DataManager infoExpert;
	private SentimentClassifier SC;
	
	public SentimentAnalyser()
	{
		this.SC = new SentimentClassifier();
		//this.SC.classify("I feel I am gonna die");
	}
	
/*
 * (non-Javadoc)
 * @see edu.cmu.qatar.cs214.hw.hw4.framework.AnalyserPlugin#Analyse(javax.swing.JPanel, java.util.ArrayList)
 */
	@Override
	public void Analyse(JPanel jp,DataManager expert) 
	{
		// TODO Auto-generated method stub
		//this.SC = new SentimentClassifier();
		this.infoExpert = expert;
		
		ArrayList<String> posts = infoExpert.getPosts();
		HashMap<String,Integer> sentiments = new HashMap<String,Integer>();
		sentiments.put("pos", 0);
		sentiments.put("neu", 0);
		sentiments.put("neg", 0);
		assert(posts.size() != 0);
		//System.out.println(posts.size());
		
		for(int i=0; i<posts.size(); i++)
		{
			String sent = SC.classify(posts.get(i));
			//System.out.println(sent);
			sentiments.replace(sent, sentiments.get(sent)+1);
		}
		
		fillPanel(jp,sentiments);
	}
	
	private void fillPanel(JPanel jp,HashMap<String,Integer> sentiments)
	{
		PieChart_AWT demo = new PieChart_AWT("Sentiment Analysis",sentiments);  
	    //demo.setSize(560,367);    
	    //RefineryUtilities.centerFrameOnScreen(demo);    
	    //demo.setVisible(true);
	    jp.add(demo.getContentPane(),BorderLayout.CENTER);
	    
	}

	@Override
	public String getTitle() 
	{
		// TODO Auto-generated method stub
		return "Sentiment Analysis";
	}
	
	
}
