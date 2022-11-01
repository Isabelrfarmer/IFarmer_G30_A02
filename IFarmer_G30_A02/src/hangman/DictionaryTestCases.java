package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTestCases {

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
