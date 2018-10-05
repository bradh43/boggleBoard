import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * BoggleBoard JUnit test
 * @author Brad
 */
public class BoggleBoardTest {

	BoggleBoard boggleBoard;
	
	@Before
	public void setUp() throws Exception {
		boggleBoard = new BoggleBoard("board.txt");
	}

	@Test
	public void testGetBoardSize() {
		assertEquals(4, boggleBoard.getBoardSize());
		
	}

	@Test
	public void testGetScore() {
		assertEquals(0, boggleBoard.getScore());
		boggleBoard.updateScore(1);
		assertEquals(1, boggleBoard.getScore());
	}

	@Test
	public void testUpdateScore() {
		assertEquals(0, boggleBoard.getScore());
		boggleBoard.updateScore(4);
		assertEquals(4, boggleBoard.getScore());
	}

	@Test
	public void testCalculatePoints() {
		assertEquals(0, boggleBoard.calculatePoints("as"));
		assertEquals(1, boggleBoard.calculatePoints("one"));
		assertEquals(1, boggleBoard.calculatePoints("ones"));
		assertEquals(2, boggleBoard.calculatePoints("happy"));
		assertEquals(3, boggleBoard.calculatePoints("swells"));
		assertEquals(5, boggleBoard.calculatePoints("volcano"));
		assertEquals(11, boggleBoard.calculatePoints("superfluous"));
	}

	@Test
	public void testValidWord() {
		assertEquals(true, boggleBoard.validWord("a"));
		assertEquals(false, boggleBoard.validWord("z"));
	}

}
