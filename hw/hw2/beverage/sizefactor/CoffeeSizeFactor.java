/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor;
/*
 * Coffe Cup size
 */
public class CoffeeSizeFactor implements SizeFactor

{

	private String size;
	private int cost;
	/*
	 * Setting size according to the input of user
	 */
	public CoffeeSizeFactor(String s)
	{
		//System.out.print("SizeFactorCoffe Cons");
		this.size = s;
		
		if (this.size == "small")
		{
			this.cost = 40; 
		}
		else if(this.size == "medium")
		{
			this.cost = 70;
		}
		else if (this.size == "large")
		{
			this.cost = 100;
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
