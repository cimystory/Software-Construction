/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor;

import java.util.HashMap;

import edu.cmu.qatar.cs214.hw.hw1.staff.Vertex;

public class TeaSizeFactor implements SizeFactor
{
	private String size;
	private int cost;
	/*
	 * Setting size according to the size by the user
	 */
	public TeaSizeFactor(String s)
	{
		this.size = s;
		if (this.size.equals("small"))
		{
			this.cost = 20;
		}
		else if(this.size.equals("medium"))
		{
			this.cost = 50;
		}
		else if(this.size.equals("large"))
		{
			this.cost = 70;
		}
		
		
	}
	@Override
	/*
	 * (non-Javadoc)
	 * @see edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor#getCost()
	 */
	public int getCost()
	{
		
		return this.cost;
		
	}
}
