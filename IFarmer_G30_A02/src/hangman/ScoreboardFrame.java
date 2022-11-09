package hangman;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import linked_data_structures.DoublyLinkedList;


/**
 * <p>Title: ScoreboardFrame </p>
 * <p>Description: ScoreboardFrame.java displays a frame of all stored players, with their names, number of games played and number of games won.</p>
 * <p>Course: 420-G30 Programming III</p>
 * @author Isabel Farmer
 */

public class ScoreboardFrame {

	protected JFrame frame;
	private DoublyLinkedList<Player> allPlayers = null;
	Scoreboard scoreboard = null;
	JTextArea textArea;
	private JPanel dispPanel;

	/**
	 * Create the application.
	 */
	public ScoreboardFrame(DoublyLinkedList<Player> allPlayers1) {
		allPlayers = allPlayers1;
		scoreboard = new Scoreboard(allPlayers);
		scoreboard.orderPlayers();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 758, 590);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		dispPanel = new JPanel();
		dispPanel.setBounds(10, 11, 722, 531);
		frame.getContentPane().add(dispPanel);
		dispPanel.setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 500, 500);
		textArea.setFont(new Font("Courier New", 0, 12));

		JScrollPane scrollBar2 = new JScrollPane(textArea);
		scrollBar2.setBounds(778, 56, 500, 500);
		dispPanel.add(scrollBar2, BorderLayout.CENTER);

		displayPlayers();

	} // initialize()

	protected void displayPlayers() {

		textArea.setText(String.format("%30s\n%31s\n", "Hangman Scoreboard", "- - - - - - - - - -"));
		textArea.append(String.format("%-20s%-20s%-20s\n", "Player name", "Games played", "Wins"));
		textArea.append(String.format("%-100s\n", "- - - - - - - - - - - - - - - - - - - - - - -"));

		for (int i = 0; i < scoreboard.players.getLength(); i++) {
			Object player = scoreboard.players.getElementAt(i);

			textArea.append(String.format("%-20s%-20s%-20s\n", ((Player) player).getPlayerName(),
					((Player) player).getNumGamesPlayed(), ((Player) player).getNumGamesWon()));

		}
	} // displayPlayers()

} // class
