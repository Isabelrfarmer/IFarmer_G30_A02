package hangman;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Point;

/**
 * <p>Title: HangmanRulesFrame </p>
 * <p>Description: HangmanRulesFrame.java frame displays a frame containing the rules of a hangman game.</p>
 * <p>Course: 420-G30 Programming III</p>
 * @author Isabel Farmer
 */
public class HangmanRulesFrame {

	protected JFrame frmHangmanRules;

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
		frmHangmanRules = new JFrame();
		frmHangmanRules.setTitle("Hangman Rules");
		frmHangmanRules.setBounds(100, 100, 483, 308);
		frmHangmanRules.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmHangmanRules.getContentPane().setBackground(new Color(231, 223, 198));
		frmHangmanRules.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Learn to play!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 26));
		lblNewLabel.setBounds(105, 11, 256, 24);
		frmHangmanRules.getContentPane().add(lblNewLabel);

		Border border = BorderFactory.createLineBorder(new Color(34, 116, 165), 1);

		JTextPane txtpnTheAimOf = new JTextPane();
		txtpnTheAimOf.setLocation(new Point(2, 2));
		txtpnTheAimOf.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		txtpnTheAimOf.setText(
				"The aim of the game is to guess the all the missing letter and reveal the word.\r\n\r\nFor each incorrect guess, a part of the hangman will be added, and once you create a full stick figure you lose! This allows for a total of 6 incorrect guesses.\r\n\r\nIf you guess all the letters of the word before your 6 attempts are used, you win!\r\n\r\n");
		txtpnTheAimOf.setBounds(105, 92, 335, 155);
		txtpnTheAimOf.setBorder(border);
		txtpnTheAimOf.setMargin(new Insets(30, 30, 30, 30));
		frmHangmanRules.getContentPane().add(txtpnTheAimOf);

		JLabel hangmanImgLabel = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/hangmanImages/rulesImg.png")).getImage()
				.getScaledInstance(200, 211, Image.SCALE_SMOOTH);
		hangmanImgLabel.setIcon(new ImageIcon(img));
		hangmanImgLabel.setBounds(10, 52, 249, 213);
		frmHangmanRules.getContentPane().add(hangmanImgLabel);
	} // initialize
} // class
