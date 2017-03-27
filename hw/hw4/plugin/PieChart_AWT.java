/*
 * Name  : Shahan Ali Memon
 * ID    : samemon
 * HW    : 4
 */


package edu.cmu.qatar.cs214.hw.hw4.plugin;
import java.util.HashMap;


import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
 
/*
 * Code taken from : http://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm
 */

public class PieChart_AWT extends ApplicationFrame 
{
   private HashMap<String,Integer> map;
   private String title;
   
   public PieChart_AWT(String title, HashMap<String,Integer> map) 
   {
	   super(title); 
	   this.title = title;
	   this.map = new HashMap<String,Integer>();
       this.map = map;
       setContentPane(createDemoPanel());
   }


 private PieDataset createDataset() 
   {
	  DefaultPieDataset dataset = new DefaultPieDataset();
	  
	  //String[] keys = (String[]) this.map.keySet().toArray();
	  for (String key : map.keySet())
	  {
		  System.out.println(key);
		  System.out.println(map.get(key));
		  dataset.setValue(key, new Double(map.get(key)));
	  }
      return dataset;         
   }

   private JFreeChart createChart( PieDataset dataset )
   {
      JFreeChart chart = ChartFactory.createPieChart(      
         this.title,  
         dataset,         
         true,           
         true, 
         false);
      return chart;
   }
   public JPanel createDemoPanel( )
   {
      JFreeChart chart = createChart(createDataset());  
      return new ChartPanel(chart);
   }
}