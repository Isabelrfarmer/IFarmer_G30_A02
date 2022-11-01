package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import linked_data_structures.SinglyLinkedList;

public class Dictionary {
	private String filename = "word_db.txt";
	private FileWriter output;
	protected SinglyLinkedList<String> wordsList = new SinglyLinkedList<String>();
	protected SinglyLinkedList<String> currentWord = new SinglyLinkedList<String>();
	
	protected Dictionary() {
		
		readFile();
	} // Dictionary()
	
	protected boolean readFile() {
		boolean isValid = true;
		try {
			
			File wordsFile = new File(filename);
			Scanner reader = new Scanner(wordsFile);
			String word = "";
			while (reader.hasNext()) {
				word = reader.nextLine().trim();
				
				if (fileIsValid(word)) {
					addWord(word);
//					System.out.println(word);
				} else {
					isValid = false;
				}
			}
		} catch (FileNotFoundException e) {
			isValid = false;
		}
		return isValid;
	} //readFile()
	
	private boolean fileIsValid(String line) {
		return true;
	}

	
	protected void addWord(String word) {
		wordsList.add(word);
	} // addWord(String
	
	protected void splitWord(String word) {
		String wordArr[] = word.split(word);
		

//		
		for(int i = 0; i < word.length();i++) {
//			System.out.println(word.charAt(i));
			String letter =String.valueOf(word.charAt(i));
			System.out.println(letter);
			currentWord.add(letter);
		}
		
//		return null;
	} // splitWord(String word)

} // class
