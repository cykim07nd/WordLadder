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
	String[] alphabet={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	ArrayList<String> SolutionList;
	
	public WordLadderSolver() 
	{
		super();
	}
    

	// delcare class members here.
	Dictionary dictionary= new Dictionary("A4words.dat");
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

	
    public WordLadderSolver(String file) 
    {
    	this.dictionary = new Dictionary(file);
	}
	// do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	int i =0;
    	int differences = 0;
    	SolutionList = new ArrayList<String>();
    	if((!dictionary.isWord(startWord))|(!dictionary.isWord(endWord)))
    	{
    		System.out.printf("For the input words \"%s\" and \"%s\"\n", startWord, endWord);
    		System.out.printf("At least one of the words %s and %s are not legitimate 5-letter words from the dictionary", startWord, endWord);
    		return null;
    	}
    	for(char ch:startWord.toCharArray())
    	{
    		if(ch != endWord.charAt(i))
    		{
    			differences++;
    		}
    		i++;
    	}
    	if(differences == 0)
    	{
    		SolutionList.add(startWord);
    		printSolution(SolutionList,startWord,endWord);
    		return SolutionList;
    	}
    	if(differences == 1)
    	{
    		SolutionList.add(startWord);
    		SolutionList.add(endWord);
    		printSolution(SolutionList,startWord,endWord);
    		return SolutionList;
    	}
    	if(MakeLadder(startWord,endWord, 0))
    	{
    		printSolution(SolutionList,startWord,endWord);
    		return SolutionList;
    	}
    	else{
    		System.out.printf("For the input words \"%s\" and \"%s\"\n", startWord, endWord);
    		System.out.printf("There is no word ladder between %s and %s!\n", startWord, endWord);
    	}
    	//throw new UnsupportedOperationException("Not implemented yet!");
		return null;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	if((startWord == endWord)&&wordLadder.isEmpty())
    	{
    		return true;
    	}
    	if(startWord.length()!= 5 || endWord.length() != 5)
    	{
    		return false;
    	}
    	if(!wordLadder.get(0).equals(startWord) || !wordLadder.get(wordLadder.size()-1).equals(endWord))
    	{
    		return false;
    	}
    	
    	Iterator<String> i = wordLadder.iterator();
        int placement = -1;
        String prevString = startWord;
        String newString = i.next();
    	while(i.hasNext())
        {
        	newString = i.next();
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
    
    public boolean MakeLadder(String fromWord,String toWord, int place)
    {
    	SolutionList.add(fromWord);
    	SortedSet<String> Candidates = new TreeSet<String>();
    	if(place != 1){
    		String sub1 = fromWord.substring(1);
    		for(String letter: alphabet)
    		{	 
    			if(Check(letter+sub1,toWord,0,Candidates)){
    				return true;
    			}
    		}
    	}
    	if(place != 2){
    		String sub1of2 = fromWord.substring(0,1);
    		String sub2of2 = fromWord.substring(2);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1of2+letter+sub2of2,toWord,0,Candidates)){
    				return true;
    			}
    		}
    	}
    	if(place != 3){
    		String sub1of2 = fromWord.substring(0,2);
    		String sub2of2 = fromWord.substring(3);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1of2+letter+sub2of2,toWord,0,Candidates)){
    				return true;
    			}
    		}
    	}
    	if(place != 4){
    		String sub1of2 = fromWord.substring(0,3);
    		String sub2of2 = fromWord.substring(4);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1of2+letter+sub2of2,toWord,0,Candidates)){
    				return true;
    			}
    		}
    	}
    	if(place != 5){
    		String sub1 = fromWord.substring(0,4);
    		for(String letter: alphabet)
    		{	 
    			if(Check(sub1 + letter,toWord,0,Candidates)){
    				return true;
    			}
    		}
    	}
    	for(String word:Candidates)
    	{
    		if(MakeLadder(word.substring(1,6),toWord,Integer.parseInt(word.substring(6))))
    		{
    			return true;
    		}
    	}
    	SolutionList.remove(fromWord);
    	return false;
    }
     private boolean Check(String word, String toWord, int place, SortedSet<String> Candidates)
     {
    	 if(dictionary.isWord(word))
    	 {
			int index=SolutionList.indexOf(word);
			if(index == -1)
			{
				int i = 0;
				int difference = 0;
				for(char ch: word.toCharArray())
				{
					if(ch!=toWord.charAt(i))
					{
						difference++;
					}
					i++;
				}
				if(difference==1){
					SolutionList.add(word);
					SolutionList.add(toWord);
					return true;
				}
				else
				{
					Candidates.add(Integer.toString(difference)+word+Integer.toString(place));
					return false;
				}
			}
    	 }
    	 return false;
     }
     
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
