/*
 * Name  : Shahan Ali Memon
 * ID    : samemon
 * HW    : 4
 */

package edu.cmu.qatar.cs214.hw.hw4.framework;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class App extends JPanel
{
	private ArrayList<DataPlugin> dataPlugins;
	private ArrayList<AnalyserPlugin> analyserPlugins;
	private DataPlugin activeDP; //This parameter is just used to get a source title
	private ArrayList<JPanel> visualPanels; //For giving different Panels to visual Plugins
	private ArrayList<Data> currentData; // To keep track of data I am dealing with currently
	private String query;
	
	/*
	 * Constructor
	 */
	
	public App()
	{
		this.dataPlugins = new ArrayList<DataPlugin>();
		this.analyserPlugins = new ArrayList<AnalyserPlugin>();
		init();
	}
	/*
	 * Main Constructor
	 */
	public App(ArrayList<DataPlugin> dp,ArrayList<AnalyserPlugin> ap)
	{
		this.dataPlugins = new ArrayList<DataPlugin>();
		this.analyserPlugins = new ArrayList<AnalyserPlugin>();
		this.visualPanels = new ArrayList<JPanel>();
		this.currentData = new ArrayList<Data>();
		this.dataPlugins = dp;
		this.analyserPlugins = ap;
		if(dp.size() > 0)
		{
			this.activeDP = dataPlugins.get(0);
		}
		init();
		
	}	
	/*
	 * This code only creates tabs throughout the window
	 * for analyses/visual plugins
	 */
	private JPanel createTabs()
	{
		JPanel Outpanel = new JPanel();
		Outpanel.setLayout(new GridLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		// Creating initial visual panels for later update
		for(int i = 0; i < this.analyserPlugins.size();i++)
		{
			JPanel panel = new JPanel();
	        tabbedPane.addTab(this.analyserPlugins.get(i).getTitle(),panel);
	        tabbedPane.setMnemonicAt(i, KeyEvent.VK_1);
	        this.visualPanels.add(panel);
		}
		// Add the tabbedPane to the panel and return
		Outpanel.add(tabbedPane);
		return Outpanel;
	}
	
	/*
	 * This function initiates the basic GUI for framework 
	 */
	
	private void init()
	{
		setLayout(new BorderLayout());
		add(createQueryPanel(), BorderLayout.SOUTH);
		add(createTabs(),BorderLayout.CENTER);
	}
	
	/*
	 * This function calls all the data plugins and gets data
	 */
	
	private boolean processQuery()
	{
		// Going through all the data plugins installed
		
		for(int i=0; i<this.dataPlugins.size();i++)
		{
			this.currentData.addAll(this.dataPlugins.
					get(i).getData(this.query));
		}
		
		// Check if the query was valid
		
		if(this.currentData != null && this.currentData.size()>0)
		{
			return true;
		}
		
		return false;
	}
	
	/*
	 * This func. analyses the query and shows it on screen 
	 */
	
	private void analyseQuery()
	{
		int size = this.analyserPlugins.size();
		
		/*
		 * Go through all visual plugins and analyse it
		 * by giving them dataManager to talk to, as well as
		 * a JPanel to fill stuff.
		 */
		
		for(int i=0; i < size; i++)
		{	
			visualPanels.get(i).removeAll();
			assert(this.currentData.size() != 0);
			analyserPlugins.get(i).Analyse(this.visualPanels.get(i),
					new DataManager(this.currentData,
							this.activeDP.getSource()));
			
		}
		
	}
	
	/*
	 * This function runs when button is clicked
	 */
	
	private void buttonClicked(JTextField textfield)
	{
		
		String input = textfield.getText();
		this.query = input;
		boolean signal = processQuery();
		if(signal == true)
		{
			analyseQuery();
		}
		else
		{
			JOptionPane.showInternalMessageDialog(this, "Invalid Query");
		}
	}
	
	/*
	 * This function creates a panel for query 
	 */
	
	private JPanel createQueryPanel()
	{
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JButton button = new JButton();
		button.setText("Process");
		button.setBackground(Color.white);
		
		//contentPane.add(button, BorderLayout.EAST);
		JTextField textfield = new JTextField("");
		textfield.setText("");
		textfield.setPreferredSize(new Dimension(100, 20));
		panel.add(textfield);
		panel.add(button);
		button.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				buttonClicked(textfield);
				
			}
		});

		this.setLocation(100, 100);
		return panel;
	}
	
	/*
	 * This functions gives back the number of posts.
	 */
	protected int getCountOfPosts()
	{
		return this.activeDP.getCountOfPosts();
	}
	
}
