package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTest {

	@Test
	void testConstructor() {
		try {
			Dictionary d = new Dictionary();
		}
		catch(NullPointerException e) {
			fail("Dictionary cannot be initialized");
		}
		
	} // testConstructor()
	
	@Test
	void testReadFile() {
		Dictionary d = new Dictionary();
		assertEquals(d.readFile(), true);
		
	} // testReadFile()
	
	@Test
	void testAddWord() {
		Dictionary d = new Dictionary();
		d.addWord("Halloween");
		assertEquals(d.wordsList.getElementAt(0), "Halloween");
		
	} // testAddWord()
	
	@Test
	void testAddWordMultiple() {
		Dictionary d = new Dictionary();
		d.addWord("Halloween");
		d.addWord("Reed");
		d.addWord("Green");
		assertEquals(d.wordsList.getElementAt(2), "Halloween");
		assertEquals(d.wordsList.getElementAt(1), "Reed");
		assertEquals(d.wordsList.getElementAt(0), "Green");
		
	} // testAddWordMultiple()
	
	@Test
	void testIsValidWord() {
		Dictionary d = new Dictionary();
		
		assertEquals(d.wordIsValid("qqqqqwwwwweeeee") , true);// 15 letters
		assertEquals(d.wordIsValid("qqqqqwwwwweeeeee"), false);// 16 letters
		assertEquals(d.wordIsValid("qqqqqwwwwweeee"), true);// 14 letters
		assertEquals(d.wordIsValid("") , false);// 0 letters
	} // testIsValidWord()
	
	
	@Test
	void testIsValidWord1() {
		Dictionary d = new Dictionary();
		
		assertEquals(d.wordIsValid("45") , false);
		assertEquals(d.wordIsValid("1"), false);
		assertEquals(d.wordIsValid("$"), false);
		assertEquals(d.wordIsValid("sdasd4asd5") , false);
		
	} // testAddWord()
	
	@Test
	void testSplitWord() {
		Dictionary d = new Dictionary();
		String str = "Halloween";
		d.splitWord(str);
		for(int i = str.length(); i <= 0; i--) {
			char l = str.charAt(i);
			assertEquals(d.currentWord.getElementAt(i).charAt(0), l);
		}

	} // testReadFile()

} // class
