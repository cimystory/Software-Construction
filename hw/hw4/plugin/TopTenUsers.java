/*
 * Name  : Shahan Ali Memon
 * ID    : samemon
 * HW    : 4
 */

package edu.cmu.qatar.cs214.hw.hw4.plugin;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;
import edu.cmu.qatar.cs214.hw.hw4.framework.AnalyserPlugin;
import edu.cmu.qatar.cs214.hw.hw4.framework.DataManager;

public class TopTenUsers implements AnalyserPlugin
{
	private DataManager infoExpert;
	
	@Override
	public void Analyse(JPanel jp,DataManager DM)
	{
		// TODO Auto-generated method stub
		this.infoExpert = DM;
		ArrayList<String> users = infoExpert.getIds();
		HashMap<String,Integer> userCount = new HashMap<String,Integer>();
		assert(users.size() != 0);
		//System.out.println(posts.size());
		
		for(int i =0; i< users.size();i++)
		{
			userCount.put(users.get(i), 0);
		}
		
		for(int i=0; i<users.size(); i++)
		{
			String key = users.get(i);
			userCount.replace(key, userCount.get(key)+1);
		}
		
		fillPanel(jp,userCount);
	}
	

	private void fillPanel(JPanel jp,HashMap<String,Integer> userCount)
	{
		PieChart_AWT demo = new PieChart_AWT("Top Users",userCount);
	    jp.add(demo.getContentPane(),BorderLayout.CENTER);
	}
	
	@Override
	public String getTitle() 
	{
		// TODO Auto-generated method stub
		return "Top Ten Users";
	}
}
