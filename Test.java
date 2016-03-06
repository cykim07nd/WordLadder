/* Chan-Young Kim
 * EID: ck23586 
 * 
 * Kassandra Perez
 * EID: kap2589
 *
 * EE422C-Assignment 4
 */
package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	@org.junit.Test
	public void testComputeLadder1() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("heads", "heads");
		assertEquals(0,result.size());
	}

	@org.junit.Test
	public void testComputeLadder2() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("atlas", "zebra");
		assertEquals(null,result);
	}
	
	@org.junit.Test
	public void testComputeLadder3() throws NoSuchLadderException{
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("atl", "zbsra");
		assertEquals(null,result);
	}

	
	/*	result = a wordLadder
	 *	Validate result would be true since computeLadder creates a valid result
	 */
	@org.junit.Test
	public void testValidateResult1() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("heads", "tails");
		assertEquals(true,wordLadderSolver.validateResult("heads","tails",result));
	}

	
	/*	result = 0 since there are no steps in the ladder
	 *	Validate result would be true 
	 */
	@org.junit.Test
	public void testValidateResult2() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("heads", "heads");
		assertEquals(true,wordLadderSolver.validateResult("heads","heads",result));
	}
	
	
	/*	result = null since there is no wordLadder
	 *	Validate result would be true 
	 */
	@org.junit.Test
	public void testValidateResult3() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("atlas", "zebra");
		assertEquals(true,wordLadderSolver.validateResult("atlas", "zebra",result));
	}
	
	
	/*	result = null since there is no wordLadder
	 *	Validate result would be true 
	 */
	@org.junit.Test
	public void testValidateResult4() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("atl", "zbsra");
		assertEquals(true,wordLadderSolver.validateResult("atl", "zbsra",result));
	}
	
	
	// 2 steps in the ladder change the same position
	@org.junit.Test
	public void testValidateResult5() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = new ArrayList<String>();
		result.add("heads"); result.add("heals");result.add("teals");result.add("tells");result.add("tills");
		result.add("gills");result.add("bills");result.add("balls");result.add("bails");result.add("tails");
		assertEquals(false,wordLadderSolver.validateResult("heads", "tails",result));
	}
	
	
	// A word in the Ladder is not in the dictionary
	@org.junit.Test
	public void testValidateResult6() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = new ArrayList<String>();
		result.add("heads"); result.add("heals");result.add("teals");result.add("tells");result.add("talls");
		result.add("tills");result.add("bills");result.add("balls");result.add("bails");result.add("tails");
		assertEquals(false,wordLadderSolver.validateResult("heads", "tails",result));
	}
	
	
	// A word in the ladder is smaller than 5 letters
	@org.junit.Test
	public void testValidateResult7() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = new ArrayList<String>();
		result.add("heads"); result.add("heals");result.add("teals");result.add("tells");result.add("tills");
		result.add("bill");result.add("balls");result.add("bails");result.add("tails");
		assertEquals(false,wordLadderSolver.validateResult("heads", "tails",result));
	}

	
	// A word Ladder that doesn't end with the ending word
	@org.junit.Test
	public void testValidateResult8() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = new ArrayList<String>();
		result.add("heads"); result.add("heals");result.add("teals");result.add("tells");result.add("tills");
		result.add("bill");result.add("balls");result.add("bails");
		assertEquals(false,wordLadderSolver.validateResult("heads", "tails",result));
	}
	
	
	// A step that changes more than one letter
	@org.junit.Test
	public void testValidateResult9() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = new ArrayList<String>();
		result.add("heads"); result.add("heals");result.add("teals");result.add("tells");result.add("tills");
		result.add("bill");result.add("balls");result.add("tails");
		assertEquals(false,wordLadderSolver.validateResult("heads", "tails",result));
	}
}
