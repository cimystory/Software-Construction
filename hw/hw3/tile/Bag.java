package edu.cmu.qatar.cs214.hw.hw3.tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bag 
{
	//private ArrayList <letterTile> bag; 
	private HashMap<Character, Integer> bag; //hashmap to store characters in the bag with quaintity as value
	private HashMap<Character, Integer> letterScores; // characters are keys and scores are their letterscores
	//private int sizeOfBag;
	private Random randomGenerator;
	
	// Constructor to create a random generator and initialize size of bag as 100.
	// It also calls a private function to fill the bag with letters.
	public Bag()
	{
		 //sizeOfBag = 100;
		 letterScores = new HashMap<Character,Integer>();
		 this.randomGenerator = new Random();
		 bag = new HashMap<Character,Integer>();
		 
		 try {
			 	// Reading from a file how many As, Bs , etc should be there.
			 	// I did this for Design for change.
			 	// With this we can simply change the quantity and score of a letter
			 	// in a file and it would still work.
			 
	        	File file = new File("assets/LetterTileData.txt");
	            Scanner scanFile = new Scanner(file);
	            while (scanFile.hasNextLine()) 
	            {
	                String line = scanFile.nextLine().replace("\n", "").replace("\r", "");
	                String[] splitLine = line.split(" ", 3);
	                Character firstLetter = splitLine[0].charAt(0);
	                int quantity = Character.getNumericValue(splitLine[1].charAt(0));
	                int score =  Character.getNumericValue(splitLine[2].charAt(0));

	                this.bag.put(firstLetter, quantity);
	                this.letterScores.put(firstLetter, score);

	                
	            }
	            scanFile.close();
	        } 
	        catch (FileNotFoundException e) 
	        {
	            e.printStackTrace();
	        }
	}
	
	// Filling the bag with letters and their quantity.
	//Fill the letterScores hashmap with letters and its scores.
	
	
	// Given the oldRack return the new rack for the player.
	
	public ArrayList<letterTile> loadRack(ArrayList<letterTile> oldRack)
	{
		int more = 7 - oldRack.size();
		ArrayList<letterTile> newrack = new ArrayList<letterTile>();
		
		for(int i = 0; i < more; i++)
		{
			
			boolean flag = false;
			while(flag != true)
			{
				char randomChar = (char)((this.randomGenerator.nextInt(26))+65);
				//System.out.print(randomChar);
				int quantity = this.bag.get(randomChar);
				
				if (quantity > 0)
				{
					flag = true;
					//System.out.print(randomChar);
					newrack.add(new letterTile(randomChar,letterScores.get(randomChar)));
					bag.replace(randomChar,quantity-1);
				}
			}
		}
		
		newrack.addAll(oldRack);
		return newrack;
	}
	
	public void fillBag(ArrayList<Character> chars)
	{
		for(int i = 0;i<chars.size();i++)
		{
			Character c = chars.get(i);
			this.bag.put(c,this.bag.get(c)+1);
		}
	}
	
	
	public int getScore(Character key)
	{
		return this.letterScores.get(key);
	}
}
