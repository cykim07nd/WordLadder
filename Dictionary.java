/* Chan-Young Kim
 * EID: ck23586 
 * 
 * Kassandra Perez
 * EID: kap2589
 *
 * EE422C-Assignment 4
 */
package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary
{
	// Attribute
	private ArrayList<String> words;

	// Constructor
	public Dictionary(String file) 
	{
		super();
		ArrayList<String> words = new ArrayList<String>();
		try 
		{
		FileReader freader = new FileReader(file);
		BufferedReader reader = new BufferedReader(freader);
		
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				if(s.charAt(0)!='*')
				{	
					s = s.substring(0,5);		
					words.add(s);
					
				}

			}
		reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		this.words = words;
	}
	
	/**
	 *	Gets the attribute Words
	 * 
	 *	@return A list of strings represents the dictionary 
	 */
	public ArrayList<String> getWords()
	{
		return words;
	}
	
	/**
	 *	Checks if a word is in the dictionary
	 * 
	 *	@param word The word to check if its in the dictionary  
	 * 	@return true if the word is in the dictionary 
	 * 		    false if the word is not in the dictionary
	 */
	public boolean isWord(String word)
	{
		int i = this.words.indexOf(word);
		if(i == -1)
		{
			return false;
		}
		return true;
	}
}
