package hangman;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HangmanRulesFrame {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HangmanRulesFrame window = new HangmanRulesFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public HangmanRulesFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("hey bitch");
		lblNewLabel.setBounds(159, 142, 46, 14);
		frame.getContentPane().add(lblNewLabel);
	} // initialize

} // class
