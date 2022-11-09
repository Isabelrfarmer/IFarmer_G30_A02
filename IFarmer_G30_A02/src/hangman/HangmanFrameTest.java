package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HangmanFrameTest {

	@Test
	void testValidate() {
		HangmanFrame f = new HangmanFrame();
		f.player = new Player();
		f.hangmanFrame.setVisible(false);
		f.player.previousGuesses.add("c");
		assertEquals(f.validate("p"), true);
		assertEquals(f.validate("P"), true);
		assertEquals(f.validate("pa"), false);
		assertEquals(f.validate("c"), false);
		assertEquals(f.validate("#"), false);
		assertEquals(f.validate("1"), false);
		assertEquals(f.validate("#"), false);
		assertEquals(f.validate(""), false);
		assertEquals(f.validate(" "), false);
	} // testValidate()

} // class
