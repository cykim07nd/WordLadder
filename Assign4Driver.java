/* Chan-Young Kim
 * EID: ck23586 
 * 
 * Kassandra Perez
 * EID: kap2589
 *
 * EE422C-Assignment 4
 */
package assignment4;

import java.util.List;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
    	String file = "A4words.dat";
        Assignment4Interface wordLadderSolver = new WordLadderSolver(file);
        try 
        {
            List<String> result = wordLadderSolver.computeLadder("heads", "tails");
            boolean correct = wordLadderSolver.validateResult("heads", "tails", result);
        } 
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
    }
}
