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

	@org.junit.Test
	public void testValidateResult1() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("heads", "tails");
		assertEquals(true,wordLadderSolver.validateResult("heads","tails",result));
	}

	@org.junit.Test
	public void testValidateResult2() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("heads", "heads");
		assertEquals(true,wordLadderSolver.validateResult("heads","heads",result));
	}
	
	@org.junit.Test
	public void testValidateResult3() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("atlas", "zebra");
		assertEquals(true,wordLadderSolver.validateResult("atlas", "zebra",result));
	}
	
	@org.junit.Test
	public void testValidateResult4() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("atl", "zbsra");
		assertEquals(true,wordLadderSolver.validateResult("atl", "zbsra",result));
	}
}
