/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.coffee;

import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.CoffeeSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor;

public class Espresso extends Coffee
{
	private int cost;
	
	public Espresso(CoffeeSizeFactor input)
	{
		super(input);
		this.cost = 100;
	}
	
	public int getCost()
	{
		//System.out.println("Here");
		//System.out.println(super.getCost());
		return this.cost + super.getCost();
	}
	
	public void prepare()
	{
		super.prepare();
	}
}
