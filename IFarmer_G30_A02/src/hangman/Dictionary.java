package hangman;

import java.io.File;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import linked_data_structures.SinglyLinkedList;



/**
 * <p>Title: Dictionary </p>
 * <p>Description: Dictionary.java contains logic to read and validate a file containing words, which stores its data using SinglyLinkedLists.</p>
 * <p>Course: 420-G30 Programming III</p>
 * @author Isabel Farmer
 */

public class Dictionary implements Serializable {
	protected String filename = "word_db.txt";
	private FileWriter output;
	protected SinglyLinkedList<String> wordsList = new SinglyLinkedList<String>();
	protected SinglyLinkedList<String> currentWord = new SinglyLinkedList<String>();

	protected Dictionary() {

		readFile();
	} // Dictionary()

	protected boolean readFile() {
		boolean isValid = true;
		wordsList = new SinglyLinkedList<String>();
		try {

			File wordsFile = new File(filename);
			if (wordsFile.length() == 0)
				isValid = false;
			Scanner reader = new Scanner(wordsFile);
			String word = "";
			while (reader.hasNext()) {
				word = reader.nextLine().trim();

				if (wordIsValid(word)) {
					addWord(word);
				}
			}
		} catch (FileNotFoundException e) {
			isValid = false;
		}

		return isValid;
	} // readFile()

	protected boolean wordIsValid(String line) {
		boolean isValid = true;

		if (line.length() > 15 || line.length() < 1) {
			isValid = false;
		}

		for (int i = 0; i < line.length(); i++) {
			if (!Character.isLetter(line.charAt(i)) && isValid) {
				isValid = false;
			}
		}
		return isValid;
	} // wordIsValid()

	protected void addWord(String word) {
		wordsList.add(word);
	} // addWord(String

	protected void splitWord(String word) {
		String wordArr[] = word.split(word);

		clearCurrentWord();

		for (int i = 0; i < word.length(); i++) {
			String letter = String.valueOf(word.charAt(i));
			currentWord.add(letter);
		}

	} // splitWord(String word)

	private void clearCurrentWord() {
		currentWord = new SinglyLinkedList<String>();
	} // clearCurrentWord()

} // class
