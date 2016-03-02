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
import java.util.List;

public class A4Driver
{
    public static void main(String[] args)
    {
    	String file = "A4words.dat";
        Assignment4Interface wordLadderSolver = new WordLadderSolver(file);
		if (args.length != 1) 
			{
				System.err.println ("Error: Incorrect number of command line arguments");
				System.exit(-1);
			}
			try 
			{
				FileReader freader = new FileReader(args[0]);
				BufferedReader reader = new BufferedReader(freader);
				
				for (String s = reader.readLine(); s != null; s = reader.readLine()) 
				{
					try 
			        {
						String words[] = s.toLowerCase().split(" +");
						String first = words[0].toLowerCase();
						String last = words[1].toLowerCase();
			            List<String> result = wordLadderSolver.computeLadder(first, last);
			            boolean correct = wordLadderSolver.validateResult(first, last, result);
			        } 
			        catch (NoSuchLadderException e) 
			        {
			            e.printStackTrace();
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
    }
        
}
