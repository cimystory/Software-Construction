/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2.beverage;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.CoffeeSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.TeaSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.recipe.Recipe;


public abstract class Beverage {

    /**
     * Calculates and returns the cost of the {@link Beverage}.
     *
     * @return The cost of this {@link Beverage} in cents.
     */
	private String description;
	private Recipe rec;
	private SizeFactor SF;
	private int SFcost;
	
	/*
	 * Explicitly defined implicit Constructor 
	 */
	
	public Beverage()
	{
		this.SFcost = 0;
	}
	
	/*
	 *  Constructor that sets SizeFactor,
	 *  Recipe,
	 *  Description
	 *  And SFCost based on the SizeFactor and Bevergae type
	 */
	
	public Beverage(SizeFactor sf, Recipe r, String d)
	{
		this.SF = sf;
		System.out.print(SF.getCost());
		this.rec = r;
		this.description = d;
		this.SFcost = SF.getCost();
		//System.out.print("constructor");
	}
	
	/*
	 * Gives you the cost of the SizeFactor only.
	 * This is because according to UML diagram,
	 * only beverage class can communicate with SizeFactor.
	 * So all inherited classes have to talk to this class
	 * to get the SizeFactor cost
	 */
	
    public int getCost() 
    {
    	return SFcost;// IMPLEMENT ME
    }
    
    /*
     * Description is not hard and fast.
     */
    
    public String getDescription()
    {
    	return description;	
    }
    
    /*
     * Preparation for the Beverage
     * Calling the prepare from Recipe Class
     */
    
    public void prepare()
    {
    	this.rec.prepare();
    }
    
    /*
     * Setting the SizeFactor to CoffeSize or TeaSize
     */
    
    public void setSizeFactor(SizeFactor input)
    {
    	this.SF = input;
    }
    
    /*
     * Setting the recipe to the passed recipe 
     */
    
    public void setRecipe(Recipe r)
    {
    	this.rec = r;
    }

}
