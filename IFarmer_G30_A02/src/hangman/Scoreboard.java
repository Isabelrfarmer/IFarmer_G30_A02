package hangman;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import linked_data_structures.DLNode;
import linked_data_structures.DoublyLinkedList;
import linked_data_structures.SinglyLinkedList;

/**
 * <p>Title: Scoreboard </p>
 * <p>Description: Scoreboard.java handles the logic behind the data used in the ScoreboardFrame.java display.</p>
 * <p>Course: 420-G30 Programming III</p>
 * @author Isabel Farmer
 */

public class Scoreboard<E> implements Serializable {
	protected DoublyLinkedList<Player> players = new DoublyLinkedList<Player>();
	protected Player[] sortedPlayers = new Player[players.getLength()];

	protected Scoreboard() {
	} // Scoreboard()

	public Scoreboard(DoublyLinkedList<Player> allPlayers) {
		players = allPlayers;
		sortedPlayers = new Player[players.getLength()];
		orderPlayers();
	} // Scoreboard(DoublyLinkedList<Player>

	protected void orderPlayers() {

		// populate array to sort
		for (int i = 0; i < players.getLength(); i++) {
			sortedPlayers[i] = players.getElementAt(i);
		}

		// sort
		boolean sorted = false;
		int loopend = sortedPlayers.length;

		while (loopend > 0 && !sorted) {
			sorted = true;
			for (int i = 0; i < sortedPlayers.length - 1; i++) {
				if (sortedPlayers[i].getPlayerName().compareToIgnoreCase(sortedPlayers[i + 1].getPlayerName()) > 0) {
					swap(i, i + 1);
					sorted = false;
				} // swap elements if not in order
			} // for
		} // while not sorted

		// repopulate dll with sorted data
		players = new DoublyLinkedList<Player>();
		for (int i = sortedPlayers.length - 1; i >= 0; i--) {
			players.add(sortedPlayers[i]);
		}

	} // orderPlayer()

	private void swap(int p1, int p2) {
		Player temp = sortedPlayers[p1];
		sortedPlayers[p1] = sortedPlayers[p2];
		sortedPlayers[p2] = temp;
	} // swap(int, int)

	protected DoublyLinkedList<Player> getPlayersList() {
		return players;
	} // getPlayersList()

} // class
