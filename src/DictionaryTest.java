import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Dictionary JUnit test 
 * @author Brad
 */
public class DictionaryTest {
	
	Dictionary dictionary;

	@Before
	public void setUp() throws Exception {
		dictionary = new Dictionary("words.txt");
	}

	@Test
	public void testGetWord() {
		assertEquals("happy", dictionary.getWord(66454));
	}

	@Test
	public void testGetSize() {
		assertEquals(172823, dictionary.getSize());
	}

	@Test
	public void testFindWord() {
		assertEquals(66454, dictionary.findWord("happy"));
		assertEquals(-1, dictionary.findWord("notValidWord"));
	}

	@Test
	public void testBinarySearch() {
		assertEquals(66454, dictionary.binarySearch("happy", 0, dictionary.getSize()));
		assertEquals(-1, dictionary.binarySearch("notValidWord", 0, dictionary.getSize()));
	}

	@Test
	public void testIsWord() {
		assertEquals(true, dictionary.isWord("happy"));
		assertEquals(false, dictionary.isWord("notValidWord"));
	}

}
