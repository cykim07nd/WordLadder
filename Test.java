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
	public void testValidateResult() throws NoSuchLadderException {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = wordLadderSolver.computeLadder("heads", "tails");
		assertEquals(true,wordLadderSolver.validateResult("heads","tails",result));
	}

}
