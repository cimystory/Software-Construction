/*
 * Name : Shahan Ali Memon
 * Id : samemon
 * Hw : Hw3
 */

package edu.cmu.qatar.cs214.hw.hw2;

import java.text.DecimalFormat;
import java.util.Scanner;

import edu.cmu.qatar.cs214.hw.hw2.beverage.Beverage;
import edu.cmu.qatar.cs214.hw.hw2.beverage.coffee.Decaf;
import edu.cmu.qatar.cs214.hw.hw2.beverage.coffee.Espresso;
import edu.cmu.qatar.cs214.hw.hw2.beverage.coffee.HouseBlend;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.CoffeeSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.SizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.sizefactor.TeaSizeFactor;
import edu.cmu.qatar.cs214.hw.hw2.beverage.tea.GreenTea;
import edu.cmu.qatar.cs214.hw.hw2.beverage.tea.RedTea;
import edu.cmu.qatar.cs214.hw.hw2.beverage.tea.WhiteTea;
import edu.cmu.qatar.cs214.hw.hw2.ingredients.Chocolate;
import edu.cmu.qatar.cs214.hw.hw2.ingredients.FlavoredBeverage;
import edu.cmu.qatar.cs214.hw.hw2.ingredients.Ginger;
import edu.cmu.qatar.cs214.hw.hw2.ingredients.Jasmine;
import edu.cmu.qatar.cs214.hw.hw2.ingredients.Milk;
import edu.cmu.qatar.cs214.hw.hw2.ingredients.WhipCream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Coffee Shop!");
        System.out.println("Type \\h for help. Type \\q to quit.");

        Scanner scanner = new Scanner(System.in);
        DecimalFormat decimalFormatter = new DecimalFormat("#.00");

        while (true) {
            System.out.print("\n> ");

            String line = scanner.nextLine();
            if (line.equals("\\q")) {
                break;
            }
            if (line.equals("\\h")) {
                System.out.println("Usage: <beverage name> <size> [<ingredient> <ingredient> ...]");
                System.out.println("Example: greentea large milk ginger");
                System.out.println("Note: beverage names and ingredients must not contain spaces.");
                continue;
            }

            String[] words = line.split(" ");
            if (words.length < 2) {
                System.out.println("Invalid input.");
                continue;
            }

            // words[0] = beverage name
            String name = words[0].toLowerCase();

            // words[1] = size
            String size = words[1].toLowerCase();

            // words[2+] (optional) = ingredients
            int numIngredients = words.length - 2;
            String[] ingredients = new String[numIngredients];
            System.arraycopy(words, 2, ingredients, 0, numIngredients);

            Beverage beverage = makeBeverage(name, size, ingredients);
            if (beverage == null) {
                System.out.println("Invalid input.");
                continue;
            }

            int totalCostCents = beverage.getCost();
            String cost = decimalFormatter.format(totalCostCents / 100.0);
            System.out.printf("The total cost of your order is: $%s\n", cost);
            System.out.println("The beverage is prepared as follows: ");
            beverage.prepare();
            //TODO: Insert code to print the recipe here
        }
        scanner.close();
    }

    private static Beverage makeBeverage(String name, String size, String[] ingredients) {
        Beverage beverage = null;
        
        if (name.equals("espresso")) 
        {
           // beverage = ?
        	CoffeeSizeFactor sizefactor = new CoffeeSizeFactor(size);
        	beverage = new Espresso(sizefactor);
        	
        } else if (name.equals("decaf")) 
        {
            // beverage = ?
        	CoffeeSizeFactor sizefactor = new CoffeeSizeFactor(size);
        	beverage = new Decaf(sizefactor);
        } 
        else if (name.equals("houseblend")) 
        {
            // beverage = ?
        	CoffeeSizeFactor sizefactor = new CoffeeSizeFactor(size);
        	beverage = new HouseBlend(sizefactor);
        } 
        else if (name.equals("greentea")) 
        {
            // beverage = ?
        	TeaSizeFactor sizefactor = new TeaSizeFactor(size);
        	beverage = new GreenTea(sizefactor);
        } 
        else if (name.equals("redtea")) 
        {
            // beverage = ?
        	TeaSizeFactor sizefactor = new TeaSizeFactor(size);
        	beverage = new RedTea(sizefactor);
        } 
        else if (name.equals("whitetea")) 
        {
            // beverage = ?
        	TeaSizeFactor sizefactor = new TeaSizeFactor(size);
        	beverage = new WhiteTea(sizefactor);
        } 
        else {
            // Invalid beverage type.
            return null;
        }

        for (String ingredient : ingredients) {
            beverage = addIngredient(beverage, ingredient.toLowerCase());
            if (beverage == null) {
                // Invalid ingredient.
                return null;
            }
        }

        return beverage;
    }

    private static FlavoredBeverage addIngredient(Beverage beverage, String ingredient) {
        FlavoredBeverage flavoredBeverage = null;
        if (ingredient.equals("milk")) {
            flavoredBeverage = new Milk(beverage);
        } 
        else if (ingredient.equals("whippedcream")) 
        {
           flavoredBeverage = new WhipCream(beverage);
           
        } 
        else if (ingredient.equals("jasmine"))
        {
        	 flavoredBeverage = new Jasmine(beverage);
        } 
        else if (ingredient.equals("ginger")) 
        {
        	 flavoredBeverage = new Ginger(beverage);
        } 
        else if (ingredient.equals("chocolate")) 
        {
        	 flavoredBeverage = new Chocolate(beverage);
        }
        else {
            // Invalid ingredient.
            return null;
        }

        return flavoredBeverage;
    }

}
