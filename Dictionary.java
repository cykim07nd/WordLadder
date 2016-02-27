package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary
{
	private ArrayList<String> words;
	
	public Dictionary(String file) {
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
	
	
	public ArrayList<String> getWords()
	{
		return words;
	}
	
	
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
