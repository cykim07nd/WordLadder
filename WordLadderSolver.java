/*
    Chan-Young Kim
 */

package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
	SortedSet<String> Candidates;
	ArrayList<String> SolutionList;
	public WordLadderSolver() {
		super();
	}
    

	// delcare class members here.
	Dictionary dictionary= new Dictionary("A4words.dat");
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

	
    public WordLadderSolver(String file) {
    	this.dictionary = new Dictionary(file);
	}
	// do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	SolutionList = new ArrayList<String>();
    	throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    public boolean MakeLadder(String fromWord,String toWord, int place)
    {
    	SolutionList.add(fromWord);
    	if(place != 1){
    		
    	}
    	if(place != 2){
    		
    	}
    	if(place != 3){
    		
    	}
    	if(place != 4){
    		
    	}
    	if(place != 5){
    		
    	}
    	
    	
    }
    

    // add additional methods here
}
