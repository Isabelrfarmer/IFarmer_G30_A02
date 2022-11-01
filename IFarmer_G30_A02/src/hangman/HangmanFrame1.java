package hangman;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HangmanFrame1 implements ActionListener {

	private JFrame hangmanFrame;
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
					HangmanFrame1 window = new HangmanFrame1();
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
	public HangmanFrame1() {
		initialize();
	}

	public HangmanFrame1(String test) {
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		hangmanFrame = new JFrame();
		hangmanFrame.setTitle("Hangman");
		hangmanFrame.setBounds(100, 100, 569, 331);
		hangmanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hangmanFrame.getContentPane().setBackground(new Color(231, 223, 198));
		hangmanFrame.getContentPane().setLayout(null);

		lblWelcome = new JLabel("Welcome to Hangman!");
		lblWelcome.setFont(new Font("Stencil", Font.BOLD, 31));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(48, 50, 457, 65);
		lblWelcome.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(34,116,165), null));
		hangmanFrame.getContentPane().add(lblWelcome);

		lblWelcomeInstructions = new JLabel("Please select one of the options below to begin");
		lblWelcomeInstructions.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblWelcomeInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeInstructions.setBounds(59, 114, 434, 47);
	
		hangmanFrame.getContentPane().add(lblWelcomeInstructions);

		btnNewPlayer = new JButton("New Player");
		btnNewPlayer.setBackground(new Color(233,241,247));
		btnNewPlayer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnNewPlayer.setBounds(104, 193, 149, 33);
		btnNewPlayer.setBorder(BorderFactory.createLineBorder(new Color(34,116,165), 1));
		hangmanFrame.getContentPane().add(btnNewPlayer);

		btnReturningPlayer = new JButton("Returning Player");
		btnReturningPlayer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnReturningPlayer.setBackground(new Color(233,241,247));
		btnReturningPlayer.setBounds(302, 193, 149, 33);
		btnReturningPlayer.setBorder(BorderFactory.createLineBorder(new Color(34,116,165), 1));
		hangmanFrame.getContentPane().add(btnReturningPlayer);

		btnNewPlayer.addActionListener(this);
		btnReturningPlayer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewPlayer) {
			newGame();
		} // if newGame
		else if (e.getSource() == btnReturningPlayer) {
			returningPlayer();
		} // if resume game
		else if (e.getSource() == btnMakeGuess) {
			String pAns = txtFieldPlayerGuess.getText();
			makeGuess(pAns);
		} else if (e.getSource() == newMenuItem_hint) {
			getHint();
		} else if (e.getSource() == newMenuItem_quit) {
			System.exit(0);
		} else if (e.getSource() == newMenuItem_rules) {
			HangmanRulesFrame rules = new HangmanRulesFrame();
			rules.frame.setVisible(true);
		}
	} // actionPerformed(ActionEvent)

	private void getHint() {

		if (!player.gotHint) {
			int i = wordTextAreas.length - 1;
			boolean hintLetterFound = false;
			String hintLetter;
			System.out.println("hi");
			while (i > 0 && !hintLetterFound) {

				if (!wordTextAreas[i].getFont().isBold()) {
					hintLetter = wordTextAreas[i].getText();
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

		if (validate(pAns)) {
			if (player.takeTurn(pAns)) {
				player.previousGuesses(pAns);
				for (int i = 0; i < wordTextAreas.length; i++) {
					if (wordTextAreas[i].getText().trim().equals(pAns.trim())) {
						wordTextAreas[i].setForeground(new Color(0,0,0));
						wordTextAreas[i].setFont(new Font("Trebuchet MS", Font.BOLD, 25));
					} // if
				} // for
//				updateGuessesMade(pAns);
			} else {
				player.previousGuesses(pAns);
				updateGuessesMade(pAns);
				player.incrementIncorrectGuessCount();
				updateHangmanImage(player.getIncorrectGuessCount());
				updateIncorrectGuessesRemaining(player.getIncorrectGuessCount());
			} // incorrect letter guess

		} else {
			JOptionPane.showMessageDialog(hangmanFrame, "Please enter a single letter. Letter must not have already been guessed.", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);
		} // invalid player input

		txtFieldPlayerGuess.setText("");

		if (!isGameOver()) {
			if (player.getIncorrectGuessCount() == 0) {
//				JOptionPane.showMessageDialog(hangmanFrame, "Sorry! You have run out of guesses and lost the game :(",
//						"Game Over", JOptionPane.ERROR_MESSAGE);
//				JOptionPane.showMessageDialog(hangmanFrame, "Sorry! You have run out of guesses and lost the game :'(");
			
			
//				JOptionPane.showConfirmDialog(this, "Congratulations "
//						+ game.getName()
//						+ "!! The game is over and you have won. Thanks for playing! Would you like to play again?",
//						"Game over", JOptionPane.YES_NO_OPTION,
//						JOptionPane.INFORMATION_MESSAGE);
			
			} else {
//				JOptionPane.showMessageDialog(hangmanFrame, "Congratulation! You have won :)", "Game Over",
//						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(hangmanFrame, "Congratulation! You have won :)");
			
			
//				JOptionPane.showConfirmDialog(this, "Congratulations "
//						+ game.getName()
//						+ "!! The game is over and you have won. Thanks for playing! Would you like to play again?",
//						"Game over", JOptionPane.YES_NO_OPTION,
//						JOptionPane.INFORMATION_MESSAGE);
			
			
			}
			disableBtns();
		} // if game is over

	} // makeGuess(String)

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
				System.out.println(correctCount == wordCheck.length());
				if (correctCount == wordCheck.length()) {
					isOver = false;
				} // inner if

			} // outer if

		} // for

		if (player.getIncorrectGuessCount() == 0) {
			System.out.println("done");
			isOver = false;
		} // if player runs out of incorrect guesses
		return isOver;

	} // isGameOver()

	private void updateIncorrectGuessesRemaining(int incorrectGuessCount) {
		String guessRemainingStr =
				  "<html> You have " +
				  "<B><FONT COLOR=RED>" +incorrectGuessCount + "</FONT></B> guesses remaining</html>";
//		String guessRemainingStr = "You have " +  + " guesses remaining.";
		lblGuessMsg.setText(guessRemainingStr);
	} // updateIncorrectGuessesRemaining(int

	private void updateGuessesMade(String pAns) {

		String prevGuessStr = "";

		for (int i = player.previousGuesses.getLength() - 1; i >= 0; i--) {
			if (i == player.previousGuesses.getLength() - 1) {
				prevGuessStr += player.previousGuesses.getElementAt(i);
			} else {
				prevGuessStr += ", " + player.previousGuesses.getElementAt(i);
			}

		} // for

		previousGuessPane.setText(prevGuessStr);
	} // updateGuessesMade(String)

	private void updateHangmanImage(int incorrectGuessCount) {
		Image img;
		System.out.println(incorrectGuessCount);
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

	private boolean validate(String pAns) {
		boolean isValid = true;
	
	for(int i = 0; i < player.previousGuesses.getLength(); i++) {
		if(pAns.equals(player.previousGuesses.getElementAt(i))) {
			isValid = false;
		}
	} // check if player guess has already been guessed
		
		 if(!Character.isLetter(pAns.charAt(0))) {
            isValid = false;
         } // check if pAns is anything other than a alphabetical letter
		return isValid;
	} // validate(String)

	private void returningPlayer() {
//		JPanel inputPanel = new JPanel();
//		String[] choices = { "A", "B", "C", "D", "E", "F" };
//		String input = (String) JOptionPane.showInputDialog(null, "Choose Player", "Returning Player",
//				JOptionPane.QUESTION_MESSAGE, null, choices, // Array of choices
//				choices[1]); // Initial choice
//		String input = JOptionPane.showInternalMessageDialog(null, "Functionality not implemented", "Returning Player",
//				JOptionPane.OK_OPTION); // Initial choice

		JOptionPane.showMessageDialog(hangmanFrame, "Functionality not implemented");

	} // returningPlayer()

	protected void newGame() {
		String pName = JOptionPane.showInputDialog(hangmanFrame, "Enter Name:");
		player = new Player(pName);
		initializeNewGameFrame();

	}// newGame

	private void initializeNewGameFrame() {
		hangmanFrame.getContentPane().removeAll();
		hangmanFrame.revalidate();
		hangmanFrame.repaint();
		hangmanFrame.setBounds(100, 100, 980, 712);
		hangmanFrame.getContentPane().setBackground(new Color(231, 223, 198));
		
Border border = BorderFactory.createLineBorder(new Color(34,116,165), 3);

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
		separator.setForeground(new Color(34,116,165));
		separator.setBorder(border);
		hangmanFrame.getContentPane().add(separator);

		lblPreviousGuess = new JLabel("Previous Guesses");
		lblPreviousGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPreviousGuess.setBounds(552, 382, 164, 20);
		hangmanFrame.getContentPane().add(lblPreviousGuess);

		wordGuessingPane = new JPanel();
		wordGuessingPane.setBounds(552, 212, 342, 48);
		wordGuessingPane.setBackground(new Color(233,241,247));
		hangmanFrame.getContentPane().add(wordGuessingPane);
		
		
		
//		Border border = BorderFactory.cre(0, new Color(255,113,105), new Color(255,255,255));
//		 Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(255,113,105), new Color(255,255,255));
        // set the border of this component
//		 Border etchedBorder = BorderFactory.createEtchedBorder(1, Color.RED, Color.PINK);
		wordGuessingPane.setBorder(border);

		menuBar = new JMenuBar();
		hangmanFrame.setJMenuBar(menuBar);

		menu_main = new JMenu("Main Menu");
		menuBar.add(menu_main);

		newMenuItem_viewScoreBoard = new JMenuItem("View Scoreboard");
		menu_main.add(newMenuItem_viewScoreBoard);

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

//					JTextArea textArea = new JTextArea(3, 3);
//			textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
////			textArea.setAlignment(Component.CENTER);
//			textArea.setEditable(false);
//			textArea.setBounds(initialPosTextArea, 45, 27, 70);
//			wordGuessingPane.add(textArea);

			JTextArea textArea = new JTextArea(1, 1);
			textArea.setForeground(new Color(233,241,247));
			textArea.setBackground(new Color(233,241,247));
//			textArea.setBackground(new Color(240, 240, 240)); // same colour as default background
			textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
			textArea.setEditable(false);
			textArea.setBounds(3, 0, 40, 30);

			wordGuessingPane.add(textArea);

			wordTextAreas[i] = textArea;

			if (player.gameWord.charAt(i) == ' ') {
				textArea.setBackground(new Color(233,241,247));
			} else {
				textArea.setText(player.words.currentWord.getElementAt(letterTrack));
				textArea.setForeground(new Color(233,241,247)); // make letters hidden
				textArea.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
			}
//			JSeparator separator_1 = new JSeparator();
//			separator_1.setBounds(initialPosSeperator, 86, 40, 2);
//			wordGuessingPane.add(separator_1);

			initialPosTextArea += 600;
			initialPosSeperator += 600;

			--letterTrack;

		} //for
		
//		((Window) wordGuessingPane).pack();

		hangmanImgLabel = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg0.png")).getImage()
				.getScaledInstance(560, 560, Image.SCALE_SMOOTH);
		hangmanImgLabel.setIcon(new ImageIcon(img));
		hangmanImgLabel.setBounds(10, 37, 520, 558);
//		Border border = BorderFactory.createLineBorder(new Color(255,113,105), 5);
		 
        // set the border of this component
		hangmanImgLabel.setBorder(border);
		hangmanFrame.getContentPane().add(hangmanImgLabel);

		String guessRemainingStr = 
				  "<html> You have " +
				  "<B><FONT COLOR=RED>" +player.incorrectGuessCount + "</FONT></B> guesses remaining</html>.";
		lblGuessMsg = new JLabel(guessRemainingStr);
//		lblHaveFunMsg.getPreferredSize();
		lblGuessMsg.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGuessMsg.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblGuessMsg.setBounds(605, 71, 293, 30);
		hangmanFrame.getContentPane().add(lblGuessMsg);

//		
//		JLabel lblYouHave = new JLabel("You have");
//		lblYouHave.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
//		lblYouHave.setBounds(693, 71, 81, 20);
//		hangmanFrame.getContentPane().add(lblYouHave);
//		
//		JLabel lblGuessesRemaining = new JLabel("guesses remaining");
//		lblGuessesRemaining.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
//		lblGuessesRemaining.setBounds(813, 66, 151, 31);
//		hangmanFrame.getContentPane().add(lblGuessesRemaining);
//		
//		JLabel lblGuessCount = new JLabel("6");
//		lblGuessCount.setForeground(new Color(255, 128, 128));
//		lblGuessCount.setHorizontalAlignment(SwingConstants.CENTER);
//		lblGuessCount.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
//		lblGuessCount.setBounds(770, 74, 37, 14);
//		hangmanFrame.getContentPane().add(lblGuessCount);
	} // initializeGameFrame()
} // class
