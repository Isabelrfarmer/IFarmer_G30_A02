package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import linked_data_structures.DoublyLinkedList;

class ScoreboardTest {

	@Test
	void testDefaultConstructor() {
		Scoreboard s = new Scoreboard();
		assertEquals(s.players.getLength(), 0);
	} // testDefaultConstructor()

	@Test
	void testConstructor() {
		DoublyLinkedList<Player> players = new DoublyLinkedList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player("john"));

		Scoreboard s = new Scoreboard(players);
		assertEquals(s.players.getLength(), 4);
		assertEquals(((Player) s.players.getElementAt(0)).getPlayerName(), "john");
	} // testConstructor()

	@Test
	void tesAddPlayersBoundary() {
		DoublyLinkedList<Player> players = new DoublyLinkedList<Player>();
		players.add(new Player("john"));
		Scoreboard s = new Scoreboard(players);
		assertEquals(((Player) s.players.getElementAt(0)).getPlayerName(), "john");
	} // testConstructor()

	@Test
	void testOrderPlayers() {
		DoublyLinkedList<Player> players = new DoublyLinkedList<Player>();
		players.add(new Player("abraham"));
		players.add(new Player("carol"));
		players.add(new Player("zebra"));
		players.add(new Player("john"));

		Scoreboard s = new Scoreboard(players);
		s.orderPlayers();
		assertEquals(((Player) s.players.getElementAt(0)).getPlayerName(), "abraham");
		assertEquals(((Player) s.players.getElementAt(1)).getPlayerName(), "carol");
		assertEquals(((Player) s.players.getElementAt(2)).getPlayerName(), "john");
		assertEquals(((Player) s.players.getElementAt(3)).getPlayerName(), "zebra");
	} // testOrderPlayers()

	@Test
	void testOrderPlayersSingle() {
		DoublyLinkedList<Player> players = new DoublyLinkedList<Player>();
		players.add(new Player("abraham"));

		Scoreboard s = new Scoreboard(players);
		s.orderPlayers();
		assertEquals(((Player) s.players.getElementAt(0)).getPlayerName(), "abraham");
	} // testOrderPlayers()

} // class
