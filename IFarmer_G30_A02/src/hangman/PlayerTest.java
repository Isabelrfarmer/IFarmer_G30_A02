package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import linked_data_structures.DoublyLinkedList;
import linked_data_structures.SinglyLinkedList;

class PlayerTest {

	@Test
	void testConstructor() {
		Player player = new Player();

		assertEquals(player.getPlayerName(), "Unknown");
		assertEquals(player.getNumGamesPlayed(), 0);
		assertEquals(player.getNumGamesWon(), 0);
		assertEquals(player.getIncorrectGuessCount(), 6);
		assertEquals(player.gotHint, false);
	} // testConstructor()

	@Test
	void testConstructor2() {
		Player player = new Player("John");

		assertEquals(player.getPlayerName(), "John");
		assertEquals(player.getNumGamesPlayed(), 0);
		assertEquals(player.getNumGamesWon(), 0);
		assertEquals(player.getIncorrectGuessCount(), 6);
		assertEquals(player.gotHint, false);
		assertEquals(player.getWord(), player.gameWord);
	} // testConstructor2()

	@Test
	void testIncrementGuessCount() {
		Player player = new Player("John");
		assertEquals(player.getIncorrectGuessCount(), 6);

		player.incrementIncorrectGuessCount();
		assertEquals(player.getIncorrectGuessCount(), 5);

	} // testIncrementGuessCount()

	@Test
	void testPreviousGuess() {
		Player player = new Player("John");
		player.previousGuesses("j");
		assertEquals(player.previousGuesses.getElementAt(0), "j");

	} // testPreviousGuess()

	@Test
	void testTakeTurn() {
		Player player = new Player("John");
		player.gameWord = "k";
		assertEquals(player.takeTurn("j"), false);
		assertEquals(player.takeTurn("k"), true);
	} // testTakeTurn()

	@Test
	void removeUsedWord() {
		Player player = new Player("John");
		int wordNum = 2;
		player.gameWord = player.words.wordsList.getElementAt(wordNum);
		player.removeWord(wordNum);

		if (player.words.wordsList.getElementAt(wordNum).equals(player.gameWord)) {
			fail("Word has not been deleted");
		}

	} // removeUsedWord()

	@Test
	void testChooseWordFalse() {
		Player player = new Player("John");
		player.words.wordsList = new SinglyLinkedList<String>();

		assertEquals(player.chooseWord(), false);

	} // testChooseWordFalse()

	@Test
	void testChooseWordTrue() {
		Player player = new Player("John");

		assertEquals(player.chooseWord(), true);

	} // testChooseWordTrue()

	@Test
	void testSerializeTrue() {
		Player player = new Player("John");

		assertEquals(player.serialize(), true);

	} // testSerializeTrue()

	@Test
	void testSerializeFalse() {
		Player player = new Player("John");
		player.storeGames = "";
		assertEquals(player.serialize(), false);

	} // testSerializeFalse()

	@Test
	void testDeserialize() {
		Player player = new Player("John");
		player.allPlayers.add(player);
		player.serialize();
		assertEquals(player.deserialize(), true);
	} // testDeserialize()

} // class
