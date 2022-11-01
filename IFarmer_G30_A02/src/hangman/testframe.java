package hangman;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testframe {

	private JFrame frame;
	private JTextField txtFieldPlayerGuess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testframe window = new testframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 222, 173));
		frame.setBounds(100, 100, 980, 712);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHaveFunMsg = new JLabel("have fun playing!");
		lblHaveFunMsg.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHaveFunMsg.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblHaveFunMsg.setBounds(297, 37, 657, 18);
		frame.getContentPane().add(lblHaveFunMsg);
		
		txtFieldPlayerGuess = new JTextField();
		txtFieldPlayerGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtFieldPlayerGuess.setBounds(693, 145, 86, 31);
		frame.getContentPane().add(txtFieldPlayerGuess);
		txtFieldPlayerGuess.setColumns(10);
		
		JLabel lblPlayerGuess = new JLabel("Guess:");
		lblPlayerGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPlayerGuess.setBounds(620, 143, 96, 31);
		frame.getContentPane().add(lblPlayerGuess);
		
		JTextPane previousGuessPane = new JTextPane();
		previousGuessPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		previousGuessPane.setBounds(552, 413, 342, 100);
		frame.getContentPane().add(previousGuessPane);
		previousGuessPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		

		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(138, 43, 226));
		separator.setBounds(552, 367, 342, 18);
		frame.getContentPane().add(separator);
		
		JLabel lblPreviousGuess = new JLabel("Previous Guesses");
		lblPreviousGuess.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblPreviousGuess.setBounds(552, 382, 164, 20);
		frame.getContentPane().add(lblPreviousGuess);
		
		JPanel wordGuessingPane = new JPanel();
		wordGuessingPane.setBackground(new Color(128, 128, 192));
		wordGuessingPane.setBounds(552, 212, 342, 134);
		frame.getContentPane().add(wordGuessingPane);
		wordGuessingPane.setLayout(null);
		
		JTextArea txtrSdasd = new JTextArea();
		txtrSdasd.setForeground(new Color(255, 0, 128));
		txtrSdasd.setText("A");
		txtrSdasd.setBackground(new Color(192, 192, 192));
		txtrSdasd.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		txtrSdasd.setEditable(false);
		txtrSdasd.setBounds(123, 52, 95, 30);
		wordGuessingPane.add(txtrSdasd);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(3, 86, 40, 2);
		wordGuessingPane.add(separator_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(127, 11, 47, 31);
		wordGuessingPane.add(textArea_1);
		
		JButton btnNewButton = new JButton("Guess");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(789, 147, 73, 27);
		frame.getContentPane().add(btnNewButton);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Main Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("View Scoreboard");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Start New Game");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Quit");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Get Hint");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Game Rules");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		
		JLabel label = new JLabel(); //JLabel Creation
		Image img = new ImageIcon(this.getClass().getResource("/hangmanImages/hangmanImg0.png")).getImage().getScaledInstance(560, 560, Image.SCALE_SMOOTH);;
		label.setIcon(new ImageIcon(img));
		label.setBounds(10,37,520,565);
//		Image newImg = img
		frame.getContentPane().add(label);
		
		JLabel lblYouHave = new JLabel("You have");
		lblYouHave.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblYouHave.setBounds(588, 71, 158, 20);
		frame.getContentPane().add(lblYouHave);
		
		JLabel lblGuessesRemaining = new JLabel("guesses remaining");
		lblGuessesRemaining.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblGuessesRemaining.setBounds(813, 66, 151, 31);
		frame.getContentPane().add(lblGuessesRemaining);
		
		JLabel lblGuessCount = new JLabel("6");
		lblGuessCount.setForeground(new Color(255, 128, 128));
		lblGuessCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessCount.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblGuessCount.setBounds(770, 74, 37, 14);
		frame.getContentPane().add(lblGuessCount);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(608, 547, 199, 22);
		frame.getContentPane().add(textArea);
//		frame.add(new JLabel(new ImageIcon(this.getClass().getResource("hangmanImg0.png")).getImage()));
		
//		JLabel label = new JLabel(); //JLabel Creation
//        label.setIcon(new ImageIcon("../../hangmanImages/hangmanImg0.png")); //Sets the image to be displayed as an icon
//        Dimension size = label.getPreferredSize(); //Gets the size of the image
//        label.setBounds(50, 30, size.width, size.height); 
//        frame.getContentPane().add(label);
		
	}
}
