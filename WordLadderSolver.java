/* Chan-Young Kim
 * EID: ck23586 
 * 
 * Kassandra Perez
 * EID: kap2589
 *
 * EE422C-Assignment 4
 */

package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{	
	// Attributes 
	String[] alphabet={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	ArrayList<String> SolutionList;
	
	// Constructor 
	public WordLadderSolver() 
	{
		super();
	}
    

	Dictionary dictionary= new Dictionary("A4words.dat");
	
	// Constructor
    public WordLadderSolver(String file) 
    {
    	this.dictionary = new Dictionary(file);
	}
    
    
    /**
     *	Creates the word ladder
     *
     *	@param startWord The starting word in the word ladder.
     *	@param endWord The ending word in the word ladder.
     *	@return the WordLadder for two words
     */
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	int i =0;
    	int differences = 0;
    	SolutionList = new ArrayList<String>();
    	if((!dictionary.isWord(startWord))|(!dictionary.isWord(endWord)))						//check if 5-letter word
    	{
    		System.out.printf("For the input words \"%s\" and \"%s\"\n", startWord, endWord);
    		System.out.printf("At least one of the words %s and %s are not legitimate 5-letter words from the dictionary\n**********\n", startWord, endWord);
    		return null;
    	}
    	for(char ch:startWord.toCharArray())													//count the number of differences between startword and endword
    	{
    		if(ch != endWord.charAt(i))
    		{
    			differences++;
    		}
    		i++;
    	}
    	if(differences == 0)
    	{																						//if the startword and endword is equal, print out both words, and return an empty list
    		System.out.printf("For the input words \"%s\" and \"%s\"\n%s %s\n**********\n",startWord,endWord,startWord,endWord); 
    		return SolutionList;
    	}
    	if(differences == 1)																	//if the words are already a word ladder, add two words to our solution and print
    	{	
    		SolutionList.add(startWord);														
    		SolutionList.add(endWord);
    		printSolution(SolutionList,startWord,endWord);
    		return SolutionList;
    	}
    	if(MakeLadder(startWord,endWord, 0))													//use MakeLadder to generate a solution. It returns true if there is a word ladder, false if not
    	{
    		if(validateResult(startWord,endWord,SolutionList))
    		{
    			printSolution(SolutionList,startWord,endWord);									//print solution and return solution
        		return SolutionList;
    		}
    		System.out.println("Error in ladder generation");
    	}
    	else{
    		System.out.printf("For the input words \"%s\" and \"%s\"\n", startWord, endWord);	//print the lack of solution and return null
    		System.out.printf("There is no word ladder between %s and %s!\n********** \n", startWord, endWord);
    	}
    	return null;
    }

    
    /**
     *	Check if the given wordLadder is a wordLadder
     *
     *	@param startWord The starting word in the word ladder.
     *	@param endWord The ending word in the word ladder.
     *	@param wordLadder The wordLadder to check if the solution is valid
     *	@return	true if the wordLadder is valid
     *			false if the wordLadder is not valid
     */
    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	// Checks if startWord and endWord are the same
    	if((startWord.equals(endWord))&&(wordLadder.size()==0))
    	{
    		return true;
    	}
    	
    	// checks if the startWord and endWord are in the dictionary
    	if(!dictionary.isWord(startWord) && !dictionary.isWord(endWord) && wordLadder == null)
    	{
    		return true;
    	}
    	
    	// Checks if there is no wordLadder 
    	if(wordLadder == null|| ((startWord.length() != 5 || endWord.length() != 5) && wordLadder == null ))
    	{
    		return true;
    	}
    	
    	// Checks that the beginning and end of the wordLadder are startWord and endWord
    	if(!wordLadder.get(0).equals(startWord) || !wordLadder.get(wordLadder.size()-1).equals(endWord))
    	{
    		return false;
    	}

    	
    	// Goes through the wordLadder and checks that the each word has a different position changed
    	Iterator<String> i = wordLadder.iterator();
        int placement = -1;
        String prevString = startWord;
        String newString = i.next();
    	while(i.hasNext())
        {
        	newString = i.next();
        	
        	// check if the word is not in the dictionary
        	if(!dictionary.isWord(newString))
        	{
        		return false;
        	}
        	
        	// if more than one letter is changed between a step in the wordLadder
        	if(countLetterDifferent(prevString,newString) != 1)
        	{
        		return false;
        	}
        	else
        	{
        		if(placement == -1)
            	{
            		placement = placement(prevString,newString);
            	}
        		else 
        		{
        			int newPlacement = placement(prevString,newString);
        			if(placement == newPlacement)
        			{
        				return false;
        			}
        			placement = newPlacement;
        		}
        	}
        	prevString = newString; 
        }
    	return true;
    }
    
    
    /**
     *	Finds which letter was changed between a step on the wordLadder
     *
     *	@param fromWord The word before the wordLadder step 
     *	@param toWord The word after the wordLadder step
     *	@return	the index of the letter was changed between fromWord and toWord
     */
    private int placement(String fromWord, String toWord)
    {
    	for(int i = 0; i < fromWord.length() ;i++)
    	{
    		if(fromWord.charAt(i) != toWord.charAt(i))
    		{
    			return i;
    		}
    	}
		return 0;
    }
    
    
    /**
     *	Finds how many letters were changed between a step in the wordLadder
     *
     *	@param fromWord The word before the wordLadder step 
     *	@param toWord The word after the wordLadder step
     *	@return the number of letters that were changed between steps on the wordLadder
     */
    private int countLetterDifferent(String fromWord, String toWord)
    {
    	int count = 0;
    	for(int i = 0; i < fromWord.length() ;i++)
    	{
    		if(fromWord.charAt(i) != toWord.charAt(i))
    		{
    			count++;
    		}
    	}
    	return count;
    }
    
    /**
     *	Makes the wordLadder using recursion
     *
     *	@param fromWord The word before the step
     *	@param toWord The word after the step
     *	@param place is the position of the letter that was changed last
     *	@return	true if the change is valid
     *			false if the change is not valid 
     */
    public boolean MakeLadder(String fromWord,String toWord, int place)						//uses recursion and finds a word ladder
    {
    	SolutionList.add(fromWord);															//add fromword to solution
    	SortedSet<String> Candidates = new TreeSet<String>();								//make a new list of candidates
    	if(place != 1){																		//if the place is not 1, replace letter 1 with all possible letters of the alphabet
    		String sub1 = fromWord.substring(1);
    		for(String letter: alphabet)
    		{	 
    			if(Check(letter+sub1,toWord,1,Candidates)){									//use check function to see if its a word, if its a match, and if it is already in a solution
    				return true;
    			}
    		}
    	}
    	if(place != 2){																		//repeat the above steps with letter 2
    		String sub1of2 = fromWord.substring(0,1);
    		String sub2of2 = fromWord.substring(2);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1of2+letter+sub2of2,toWord,2,Candidates)){
    				return true;
    			}
    		}
    	}
    	if(place != 3){
    		String sub1of2 = fromWord.substring(0,2);										//repeat the above steps with letter 3
    		String sub2of2 = fromWord.substring(3);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1of2+letter+sub2of2,toWord,3,Candidates)){
    				return true;
    			}
    		}
    	}
    	if(place != 4){
    		String sub1of2 = fromWord.substring(0,3);										//repeat the above steps with letter 4
    		String sub2of2 = fromWord.substring(4);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1of2+letter+sub2of2,toWord,4,Candidates)){
    				return true;
    			}
    		}
    	}
    	if(place != 5){																		//repeat the above steps with letter 5
    		String sub1 = fromWord.substring(0,4);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1 + letter,toWord,5,Candidates)){
    				return true;
    			}
    		}
    	}
    	for(String word:Candidates)
    	{
    		if(MakeLadder(word.substring(1,6),toWord,Integer.parseInt(word.substring(6))))
    		{
    			return true;																//go through every word in the candidate list and call this same function unless we find a solution
    		}
    	}
    	SolutionList.remove(fromWord);														//solution with this word do not exist, remove word from solution and return false
    	return false;
    }
    
    
    /**
     *	Check if the word that is being added to the wordLadder is a valid word
     *
     *	@param word The starting word in the word ladder.
     *	@param toWord The ending word in the word ladder.
     *	@param place The position of the letter that was changed
     *	@param Candidates The list of words that could be the wordLadder
     *	@return	true if the word is valid
     *			false if the word is not validate 
     */
     private boolean Check(String word, String toWord, int place, SortedSet<String> Candidates)
     {
    	 if(dictionary.isWord(word))														//check if the given word is in the dictionary
    	 {
			int index=SolutionList.indexOf(word);											//check if the given word is already in the solution
			if(index == -1)
			{
				int i = 0;
				int difference = 0;
				for(char ch: word.toCharArray())											//count the difference between fromword and endword
				{
					if(ch!=toWord.charAt(i))
					{
						difference++;
					}
					i++;
				}
				if(difference==1){
					SolutionList.add(word);													//if the difference is 1, we found a word ladder, add to solution and return
					SolutionList.add(toWord);
					return true;
				}
				else
				{
					Candidates.add(Integer.toString(difference)+word+Integer.toString(place));	//if its not a solution it could still lead to a solution so add to the candidates
					return false;
				}
			}
    	 }
    	 return false;
     }
     
     
     /**
      *	Check if the given wordLadder is a wordLadder
      *
      *	@param solution The list of words in the wordLadder
      *	@param begin The beginning of the wordLadder
      *	@param end the end of the wordLadder
      */
     void printSolution(List<String> solution,String begin, String end)
     {
    	 System.out.printf("For the input words \"%s\" and \"%s\" the following word ladder was found\n", begin, end);
    	 for(String word: solution)
    	 {
    		 System.out.printf("%s ", word);
    	 }
    	 System.out.println("\n**********");
     }
}
