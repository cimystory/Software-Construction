package edu.cmu.qatar.cs214.hw.hw3.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary 
{
	private HashMap<Character,ArrayList<String>> dictionary;
	
	public Dictionary()
	{
		this.dictionary = new HashMap<Character, ArrayList<String>>();
		this.initDict();
		this.makeDictionary();
	}
	
	private void initDict()
	{
		for(int i=97 ; i<=97+26; i++)
		{
			this.dictionary.put((char)i, new ArrayList<String>());
		}
	}
	
	private void makeDictionary()
	{
        try {
        	File file = new File("assets/words.txt");
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()) 
            {
                String line = scanFile.nextLine().replace("\n", "").replace("\r", "");
                //System.out.println("line from file is "+line);
                Character firstLetter = line.charAt(0);
                ArrayList<String> temp = this.dictionary.get(firstLetter);
                //System.out.println("Size of arrayList I got is "+temp.size());
                temp.add(line);
                this.dictionary.replace(firstLetter, temp);
                
            }
            scanFile.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
	}
	
	public boolean isWord(String word)
	{
		//System.out.println("word I got to check in dict is "+word);
		return this.dictionary.get(word.charAt(0)).contains(word);
	}
	
}
