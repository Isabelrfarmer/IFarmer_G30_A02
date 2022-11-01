package hangman;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;

public class hangmanFrame {

	private JFrame frame;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hangmanFrame window = new hangmanFrame();
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
	public hangmanFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 635, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mn_pastPlayers = new JMenu("Menu");
		menuBar.add(mn_pastPlayers);
		
		JMenuItem newGame_MenuItem = new JMenuItem("New Game");
		mn_pastPlayers.add(newGame_MenuItem);
		
		JMenuItem scoreboard_MenuItem = new JMenuItem("View Scoreboard");
		mn_pastPlayers.add(scoreboard_MenuItem);
		
		JMenuItem hint_MenuItem = new JMenuItem("Get Hint");
		mn_pastPlayers.add(hint_MenuItem);
		
		JMenuItem rules_MenuItem = new JMenuItem("Rules");
		mn_pastPlayers.add(rules_MenuItem);
		
		JMenuItem quit_NewMenuItem = new JMenuItem("Quit Game");
		mn_pastPlayers.add(quit_NewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Past Players");
		menuBar.add(mnNewMenu_1);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Guessable word here");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(206, 197, 206, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("# of mistakes made here");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(183, 89, 253, 77);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Letters guessed here");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(206, 276, 206, 129);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Player name here");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(471, 11, 115, 14);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
