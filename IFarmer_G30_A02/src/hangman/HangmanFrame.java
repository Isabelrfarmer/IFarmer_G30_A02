package hangman;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.util.*;

//import org.junit.jupiter.api.ClassOrderer.Random;

public class HangmanFrame  implements ActionListener, KeyListener {

	protected JFrame hangmanFrame;
	private JLabel lblWelcomeInstructions;
	private JLabel lblWelcome;
	private JLabel lblHaveFunMsg;
	private JLabel lblGuessMsg;
	private JTextField txtFieldPlayerGuess;
	private JLabel lblPlayerGuess;
	private JTextArea previousGuessPane;
	private JSeparator separator;
	private JLabel lblPreviousGuess;
	private JPanel wordGuessingPane;
	private JMenuBar menuBar;
	private JMenu menu_main;
	private JMenuItem newMenuItem_viewScoreBoard;
	private JMenuItem newMenuItem_startNewGame;
	private JMenuItem newMenuItem_quit;
	private JMenuItem newMenuItem_hint;
	private JMenuItem newMenuItem_rules;
	private JMenuItem newMenuItem_resumeGame;
	private JButton btnMakeGuess;
	private JTextArea wordTextAreas[];
	private JLabel hangmanImgLabel;

	protected Player player;

	private JButton btnNewPlayer;
	private JButton btnReturningPlayer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanFrame window = new HangmanFrame();
					window.hangmanFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HangmanFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
	
	/**
	 * Create the application.
	 */
//	public HangmanFrame() {
//		initialize();
//	}

	public HangmanFrame(String test) {

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		hangmanFrame = new JFrame();
		hangmanFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (player != null) {
					if (!player.isPlayerStored(player)) {
						player.addPlayer(player);
					}

					player.serialize();
				}

				System.exit(0);

			}
		});
		hangmanFrame.setTitle("Hangman");
		hangmanFrame.setBounds(100, 100, 569, 331);
		hangmanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hangmanFrame.getContentPane().setBackground(new Color(231, 223, 198));
		hangmanFrame.getContentPane().setLayout(null);

		lblWelcome = new JLabel("Welcome to Hangman!");
		lblWelcome.setFont(new Font("Stencil", Font.BOLD, 31));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(48, 50, 457, 65);
		lblWelcome.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(34, 116, 165), null));
		hangmanFrame.getContentPane().add(lblWelcome);

		lblWelcomeInstructions = new JLabel("Please select one of the options below to begin");
		lblWelcomeInstructions.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblWelcomeInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeInstructions.setBounds(59, 114, 434, 47);

		hangmanFrame.getContentPane().add(lblWelcomeInstructions);

		btnNewPlayer = new JButton("New Player");
		btnNewPlayer.setBackground(new Color(233, 241, 247));
		btnNewPlayer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnNewPlayer.setBounds(104, 193, 149, 33);
		btnNewPlayer.setBorder(BorderFactory.createLineBorder(new Color(34, 116, 165), 1));
		hangmanFrame.getContentPane().add(btnNewPlayer);

		btnReturningPlayer = new JButton("Returning Player");
		btnReturningPlayer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnReturningPlayer.setBackground(new Color(233, 241, 247));
		btnReturningPlayer.setBounds(302, 193, 149, 33);
		btnReturningPlayer.setBorder(BorderFactory.createLineBorder(new Color(34, 116, 165), 1));
		hangmanFrame.getContentPane().add(btnReturningPlayer);

		btnNewPlayer.addActionListener(this);
		btnReturningPlayer.addActionListener(this);

	} // initialize()

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewPlayer) {
			newGame();
		} // if newGame
		else if (e.getSource() == btnReturningPlayer) {
			returningPlayer();
		} // if resume game
		else if (e.getSource() == btnMakeGuess) {
			String pAns = txtFieldPlayerGuess.getText().trim();
			makeGuess(pAns);
		} // player makes guess
		else if (e.getSource() == newMenuItem_hint) {
			getHint();
		} // get hint
		else if (e.getSource() == newMenuItem_quit) {
			quitGame();

		} // quit game
		else if (e.getSource() == newMenuItem_rules) {
			HangmanRulesFrame rules = new HangmanRulesFrame();
			rules.frmHangmanRules.setVisible(true);
		} // get game rules
		else if (e.getSource() == newMenuItem_resumeGame) {
			resumeGame();
		} // resume a game
		else if (e.getSource() == newMenuItem_viewScoreBoard) {
			ScoreboardFrame scoreboard = new ScoreboardFrame(player.allPlayers);
			scoreboard.frame.setVisible(true);
		} // get scoreboard
		else if (e.getSource() == newMenuItem_startNewGame) {
			newGame();
		} // start a new game
	} // actionPerformed(ActionEvent)

	private void resumeGame() {
		String[] choices = new String[player.allPlayers.getLength() + 1];

		choices[0] = "Select Player";
		int choiceIndex = 1;
		for (int i = 0; i < player.allPlayers.getLength(); i++) {

			choices[choiceIndex] = player.allPlayers.getElementAt(i).getPlayerName();
			choiceIndex++;

		}
		String resume = (String) JOptionPane.showInputDialog(null, "Choose Player", "Returning Player",
				JOptionPane.QUESTION_MESSAGE, null, choices, // Array of choices
				choices); // Initial choice

		if (resume != null) {
			for (int i = 0; i < player.allPlayers.getLength(); i++) {
				if (player.allPlayers.getElementAt(i).getPlayerName().equals(resume)) {

					player = player.allPlayers.getElementAt(i);
					initializeNewGameFrame();

					for (int j = 0; j < player.previousGuesses.getLength(); j++) {

						String pAns = player.previousGuesses.getElementAt(j);
						if (player.takeTurn(pAns)) {

							for (int k = 0; k < wordTextAreas.length; k++) {
								if (wordTextAreas[k].getText().trim().equals(pAns.trim())) {
									wordTextAreas[k].setForeground(new Color(0, 0, 0));
									wordTextAreas[k].setFont(new Font("Trebuchet MS", Font.BOLD, 25));
								} // if
							} // for
						} else {
							updateGuessesMade(pAns);
						} // incorrect letter guess
					}
					updateHangmanImage(player.getIncorrectGuessCount());

				}
			}
		}

	}

	private void quitGame() {

		player.addPlayer(player);
		if (!player.isPlayerStored(player)) {
			player.addPlayer(player);
		}
		player.serialize();

		System.exit(0);
	} // quitGame()

	private void getHint() {

		if (!player.gotHint) {
			int i = wordTextAreas.length - 1;
			boolean hintLetterFound = false;
			String hintLetter;

			while (i > 0 && !hintLetterFound) {
				Random rand = new Random();
				int randomNum = rand.nextInt(((player.gameWord.length() - 1) - 0) + 1) + 0;
				if (!wordTextAreas[randomNum].getFont().isBold()) {
					hintLetter = wordTextAreas[randomNum].getText();
					makeGuess(hintLetter);
					hintLetterFound = true;
				} // if			
				i--;
			} // while
			player.gotHint = true;
		} // if
		else {
			JOptionPane.showMessageDialog(hangmanFrame, "You have already used your hint", "Hint Not Available",
					JOptionPane.ERROR_MESSAGE);
		}
	} // getHint()

	protected void makeGuess(String pAns) {
		txtFieldPlayerGuess.requestFocusInWindow(); // places cursor in player input for each turn
		if (validate(pAns)) {
			if (player.takeTurn(pAns)) {
				player.previousGuesses(pAns);
				for (int i = 0; i < wordTextAreas.length; i++) {
					if (wordTextAreas[i].getText().trim().toLowerCase().equals(pAns.trim().toLowerCase())) {
						wordTextAreas[i].setForeground(new Color(0, 0, 0));
						wordTextAreas[i].setFont(new Font("Trebuchet MS", Font.BOLD, 25));
					} // if
				} // for
			} else {
				player.previousGuesses(pAns);
				updateGuessesMade(pAns);
				player.incrementIncorrectGuessCount();
				updateHangmanImage(player.getIncorrectGuessCount());
				updateIncorrectGuessesRemaining(player.getIncorrectGuessCount());
			} // incorrect letter guess

		} else {
			JOptionPane.showMessageDialog(hangmanFrame,
					"Please enter a single letter. Letter must not have already been guessed.", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);
		} // invalid player input

		txtFieldPlayerGuess.setText("");

		if (!isGameOver()) {
			gameIsOver();

		} // if game is over

	} // makeGuess(String)

	protected void gameIsOver() {
		int nextGame;
		boolean isWon = false;
		if (player.getIncorrectGuessCount() == 0) {

			nextGame = JOptionPane.showConfirmDialog(hangmanFrame,
					"Sorry " + player.getPlayerName()
							+ ", you have run out of guesses and lost the game :'( The answer was " + player.gameWord
							+ ". \nWould you like to play again?",
					"Game over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		} else {
			isWon = true;

			nextGame = JOptionPane.showConfirmDialog(hangmanFrame,
					"Congratulations " + player.getPlayerName()
							+ "! You have won, thanks for playing :) Would you like to play again?",
					"Game over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		}
		disableBtns();
		if (nextGame == JOptionPane.YES_OPTION) {
			player.setUpNewGame(isWon);
			if (!player.chooseWord()) {
				JOptionPane.showMessageDialog(hangmanFrame, "The " + player.words.filename + " file could not be read.",
						"Invalid File", JOptionPane.ERROR_MESSAGE);
			} else {
				player.chooseWord();
			}

			initializeNewGameFrame();
		} else {
			if (!player.isPlayerStored(player)) {
				player.addPlayer(player);
			}

			player.serialize();
			System.exit(0);
		}
	} // gameIsOver()

	private void disableBtns() {
		newMenuItem_hint.setEnabled(false);
		btnMakeGuess.setEnabled(false);
	} // disableBtns()

	private boolean isGameOver() {
		boolean isOver = true;
		int correctCount = 0;
		String wordCheck = player.gameWord.replaceAll("\\s+", ""); // remove any spaces from word
		for (int i = 0; i < wordTextAreas.length; i++) {
			if (wordTextAreas[i].getFont().isBold()) {
				correctCount++;

				if (correctCount == wordCheck.length()) {
					isOver = false;
				} // inner if

			} // outer if

		} // for

		if (player.getIncorrectGuessCount() == 0) {

			isOver = false;
		} // if player runs out of incorrect guesses
		return isOver;

	} // isGameOver()

	private void updateIncorrectGuessesRemaining(int incorrectGuessCount) {
		String guessRemainingStr = "<html> You have " + "<B><FONT COLOR=RED>" + incorrectGuessCount
				+ "</FONT></B> guesses remaining</html>";

		lblGuessMsg.setText(guessRemainingStr);
	} // updateIncorrectGuessesRemaining(int

	private void updateGuessesMade(String pAns) {

		String prevGuessStr = "";

		for (int i = player.previousGuesses.getLength() - 1; i >= 0; i--) {
			if (!player.takeTurn(player.previousGuesses.getElementAt(i))) {
				if (previousGuessPane.getText().isBlank() || prevGuessStr == "") {
					prevGuessStr += player.previousGuesses.getElementAt(i);
				} else {
					prevGuessStr += ", " + player.previousGuesses.getElementAt(i);
				}
			}
		} // for

		previousGuessPane.setText(prevGuessStr);
	} // updateGuessesMade(String)

	private void updateHangmanImage(int incorrectGuessCount) {
		Image img;

		switch (incorrectGuessCount) {
		case 5:
			img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg1.png")).getImage()
					.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
			;
			hangmanImgLabel.setIcon(new ImageIcon(img));

			break;
		case 4:
			img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg2.png")).getImage()
					.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
			;
			hangmanImgLabel.setIcon(new ImageIcon(img));
			break;
		case 3:
			img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg3.png")).getImage()
					.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
			;
			hangmanImgLabel.setIcon(new ImageIcon(img));
			break;
		case 2:
			img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg4.png")).getImage()
					.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
			;
			hangmanImgLabel.setIcon(new ImageIcon(img));
			break;
		case 1:
			img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg5.png")).getImage()
					.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
			;
			hangmanImgLabel.setIcon(new ImageIcon(img));
			break;
		case 0:
			img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg6.jpg")).getImage()
					.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
			;
			hangmanImgLabel.setIcon(new ImageIcon(img));

		} // switch
	}// updateHangmanImage(int)

	protected boolean validate(String pAns) {
		boolean isValid = true;

		if (pAns.isBlank()) {
			isValid = false;
		}
		if (pAns.length() > 1) {
			isValid = false;
		}

		for (int i = 0; i < player.previousGuesses.getLength(); i++) {
			if (pAns.equals(player.previousGuesses.getElementAt(i))) {
				isValid = false;
			}
		} // for

		if (isValid) {
			if (!Character.isLetter(pAns.charAt(0))) {
				isValid = false;
			} // check if pAns is anything other than a alphabetical letter
		} // if has value

		return isValid;
	} // validate(String)

	private void returningPlayer() {
		try {
			Player placeHolder = new Player();
			placeHolder.deserialize();
			player = placeHolder.getMostRecentPlayer();
			initializeNewGameFrame();
			setUpResumesGame();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(hangmanFrame, "There is no recent game to load.",
					"Returning Player not available", JOptionPane.ERROR_MESSAGE);
		}

	} // returningPlayer()

	private void setUpResumesGame() {
		for (int i = 0; i < player.previousGuesses.getLength(); i++) {

			String pAns = player.previousGuesses.getElementAt(i);

			if (player.takeTurn(pAns)) {

				for (int k = 0; k < wordTextAreas.length; k++) {
					if (wordTextAreas[k].getText().trim().equals(pAns.trim())) {
						wordTextAreas[k].setForeground(new Color(0, 0, 0));
						wordTextAreas[k].setFont(new Font("Trebuchet MS", Font.BOLD, 25));
					} // if
				} // for
			} else {

				updateGuessesMade(pAns);

			} // incorrect letter guess
		}

		boolean isWon = isGameOver();
		if (!isWon) {
			player.setUpNewGame(!isWon);
			if (!player.chooseWord()) {
				JOptionPane.showMessageDialog(hangmanFrame, "The " + player.words.filename + " file could not be read.",
						"Invalid File", JOptionPane.ERROR_MESSAGE);
			} else {
				player.chooseWord();
			}

			initializeNewGameFrame();
		} // if game is over
		updateHangmanImage(player.getIncorrectGuessCount());
	} // setUpResumesGame()

	protected void newGame() {

		player = new Player(getName());
		if (!player.chooseWord()) {
			JOptionPane.showMessageDialog(hangmanFrame, "The " + player.words.filename + " file could not be read.",
					"Invalid File", JOptionPane.ERROR_MESSAGE);
			System.exit(5);
		} else {
			player.chooseWord();
		}
		player.incrementNumGamesPlayed();
		player.deserialize();
		initializeNewGameFrame();

	}// newGame()

	private String getName() {
		String pName = (String) JOptionPane.showInputDialog(hangmanFrame, "Enter name: ", "Enter Name",
				JOptionPane.PLAIN_MESSAGE);
		if (pName == null) {
			System.exit(-1);
		} // the user cancels right away
		while (pName.isBlank()) {
			pName = (String) JOptionPane.showInputDialog(hangmanFrame, "Enter name: ", "Enter Name",
					JOptionPane.PLAIN_MESSAGE);
			if (pName == null) {
				System.exit(-1);
			} // If the user cancels after clicking ok once
		} // loop while name is not entered

		return pName;
	} // getName()

	private void initializeNewGameFrame() {
//		player.incrementNumGamesPlayed();
		hangmanFrame.getContentPane().removeAll();
		hangmanFrame.revalidate();
		hangmanFrame.repaint();
		hangmanFrame.setBounds(100, 100, 980, 712);
		hangmanFrame.getContentPane().setBackground(new Color(231, 223, 198));

		Border border = BorderFactory.createLineBorder(new Color(34, 116, 165), 3);

		String funMessageStr = "Welcome " + player.getPlayerName() + ", have fun playing!";
		lblHaveFunMsg = new JLabel(funMessageStr);
		lblHaveFunMsg.getPreferredSize();
		lblHaveFunMsg.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHaveFunMsg.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblHaveFunMsg.setBounds(900 - lblHaveFunMsg.getPreferredSize().width, 37,
				lblHaveFunMsg.getPreferredSize().width, 25);
		hangmanFrame.getContentPane().add(lblHaveFunMsg);

		txtFieldPlayerGuess = new JTextField();
		txtFieldPlayerGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtFieldPlayerGuess.setBounds(715, 145, 30, 31);
		hangmanFrame.getContentPane().add(txtFieldPlayerGuess);
		txtFieldPlayerGuess.setColumns(10);

		lblPlayerGuess = new JLabel("Guess:");
		lblPlayerGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPlayerGuess.setBounds(643, 143, 96, 31);
		hangmanFrame.getContentPane().add(lblPlayerGuess);

		btnMakeGuess = new JButton("Guess");
		btnMakeGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		btnMakeGuess.setBounds(760, 147, 73, 27);
		hangmanFrame.getContentPane().add(btnMakeGuess);

		previousGuessPane = new JTextArea();
		previousGuessPane.setBounds(552, 413, 342, 100);
		previousGuessPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		previousGuessPane.setEditable(false);
		hangmanFrame.getContentPane().add(previousGuessPane);

		separator = new JSeparator();
		separator.setBounds(552, 367, 342, 2);
		separator.setForeground(new Color(34, 116, 165));
		separator.setBorder(border);
		hangmanFrame.getContentPane().add(separator);

		lblPreviousGuess = new JLabel("Previous Guesses");
		lblPreviousGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPreviousGuess.setBounds(552, 382, 164, 20);
		hangmanFrame.getContentPane().add(lblPreviousGuess);

		wordGuessingPane = new JPanel();
		wordGuessingPane.setBounds(552, 212, 342, 48);
		wordGuessingPane.setBackground(new Color(233, 241, 247));
		hangmanFrame.getContentPane().add(wordGuessingPane);

		wordGuessingPane.setBorder(border);

		menuBar = new JMenuBar();
		hangmanFrame.setJMenuBar(menuBar);

		menu_main = new JMenu("Main Menu");
		menuBar.add(menu_main);

		newMenuItem_viewScoreBoard = new JMenuItem("View Scoreboard");
		menu_main.add(newMenuItem_viewScoreBoard);

		newMenuItem_resumeGame = new JMenuItem("Resume game");
		menu_main.add(newMenuItem_resumeGame);

		newMenuItem_startNewGame = new JMenuItem("Start New Game");
		menu_main.add(newMenuItem_startNewGame);

		newMenuItem_quit = new JMenuItem("Quit");
		menu_main.add(newMenuItem_quit);

		JMenu menu_help = new JMenu("Help");
		menuBar.add(menu_help);

		newMenuItem_hint = new JMenuItem("Get Hint");
		menu_help.add(newMenuItem_hint);

		newMenuItem_rules = new JMenuItem("Game Rules");
		menu_help.add(newMenuItem_rules);

		newMenuItem_hint.setEnabled(true);
		btnMakeGuess.setEnabled(true);
		btnMakeGuess.setEnabled(true);

		newMenuItem_resumeGame.addActionListener(this);
		newMenuItem_startNewGame.addActionListener(this);
		newMenuItem_viewScoreBoard.addActionListener(this);
		newMenuItem_quit.addActionListener(this);
		newMenuItem_rules.addActionListener(this);
		newMenuItem_hint.addActionListener(this);
		btnMakeGuess.addActionListener(this);
		int initialPosTextArea = 3;
		int initialPosSeperator = -4;
		wordTextAreas = new JTextArea[player.words.currentWord.getLength()];
		int letterTrack = player.words.currentWord.getLength() - 1;
		for (int i = 0; i < player.gameWord.length(); i++) {

			JTextArea textArea = new JTextArea(1, 1);
			textArea.setForeground(new Color(233, 241, 247));
			textArea.setBackground(new Color(233, 241, 247));
			textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
			textArea.setEditable(false);
			textArea.setBounds(3, 0, 40, 30);

			wordGuessingPane.add(textArea);

			wordTextAreas[i] = textArea;

			if (player.gameWord.charAt(i) == ' ') {
				textArea.setBackground(new Color(233, 241, 247));
			} else {
				textArea.setText(player.words.currentWord.getElementAt(letterTrack));

				textArea.setForeground(new Color(255, 255, 0)); // make letters hidden

				textArea.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
			}

			initialPosTextArea += 600;
			initialPosSeperator += 600;

			--letterTrack;

		} // for

		hangmanImgLabel = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg0.png")).getImage()
				.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
		hangmanImgLabel.setIcon(new ImageIcon(img));
		hangmanImgLabel.setBounds(10, 37, 520, 558);
		hangmanImgLabel.setBorder(border);
		hangmanFrame.getContentPane().add(hangmanImgLabel);

		String guessRemainingStr = "<html> You have " + "<B><FONT COLOR=RED>" + player.incorrectGuessCount
				+ "</FONT></B> guesses remaining</html>.";
		lblGuessMsg = new JLabel(guessRemainingStr);

		lblGuessMsg.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGuessMsg.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblGuessMsg.setBounds(605, 71, 293, 30);
		hangmanFrame.getContentPane().add(lblGuessMsg);

		txtFieldPlayerGuess.addKeyListener(this);

	} // initializeGameFrame()

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String pAns = txtFieldPlayerGuess.getText().trim();
			makeGuess(pAns);
			txtFieldPlayerGuess.setText("");
		} // allows user to press enter to make guess
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
} // class

