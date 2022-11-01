package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTestCases {

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
	} // testConstructor()

	@Test
	void testIncrementGuessCount() {
		Player player = new Player("John");
		assertEquals(player.getIncorrectGuessCount(), 6);

		player.incrementIncorrectGuessCount();
		assertEquals(player.getIncorrectGuessCount(), 5);

	} // testConstructor()

	@Test
	void testPreviousGuess() {
		Player player = new Player("John");
		player.previousGuesses("j");
		assertEquals(player.previousGuesses.getElementAt(0), "j");

	} // testConstructor()
	
	@Test
	void testTakeTurn() {
		Player player = new Player("John");
		player.gameWord = "k";
		System.out.println(player.gameWord + "test");
		assertEquals(player.takeTurn("j"), false);
		assertEquals(player.takeTurn("k"), true);
	} // testConstructor()
	
} // class
