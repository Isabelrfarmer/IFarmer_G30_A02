package hangman;

import linked_data_structures.SinglyLinkedList;

public class Player {
	private String player;
	private int numGamesPlayed;
	private int numGamesWon;
	protected Dictionary words;
	private Scoreboard scoreboard;
	protected String gameWord;
	protected int incorrectGuessCount;
	protected SinglyLinkedList<String> previousGuesses = new SinglyLinkedList<String>();
	protected boolean gotHint;

	protected Player() {
		setPlayerName("Unknown");
		setNumGamesPlayed(0);
		setNumGamesWon(0);
		incorrectGuessCount = 6;
		gotHint = false;
	} // Player()

	protected Player(String name) {
		setPlayerName(name);
		setNumGamesPlayed(0);
		setNumGamesWon(0);
		words = new Dictionary();
		incorrectGuessCount = 6;
		choseWord();
		gotHint = false;
	} // Player(String)
	
	protected void incrementIncorrectGuessCount() {
		incorrectGuessCount--;
	}
	
	protected int getIncorrectGuessCount() {
		return incorrectGuessCount;
	}

	protected void setPlayerName(String name) {
		player = name;
	} // setPlayerName(String)

	protected String getPlayerName() {
		return player;
	} // getPlayerName()
	
	protected void previousGuesses(String letter) {
		previousGuesses.add(letter);
	} // previousGuesses(String)
	
	protected SinglyLinkedList<String> getPreviousGuesses(){
		return previousGuesses;
	} // getPreviousGuesses()
	
	protected void setNumGamesPlayed(int num) {
		numGamesPlayed = num;
	} // setNumGamesPlayed(int)

	protected int getNumGamesPlayed() {
		return numGamesPlayed;
	} // getNumGamesPlayed()
	
	protected void setNumGamesWon(int num) {
		numGamesWon = num;
	} // setNumGamesWon(int)

	protected int getNumGamesWon() {
		return numGamesWon;
	} // getNumGamesWon()
	
	protected void choseWord() {
        int max = words.wordsList.getLength() - 1;
        int min = 0;
        int range = max - min + 1;
        int randomNum = (int)(Math.random() * range) + min;
        gameWord = words.wordsList.getElementAt(randomNum);
        words.splitWord(gameWord);
        System.out.println(gameWord);
	} // getNumGamesWon()
	
	protected String getWord() {
		return gameWord;
	} // getNumGamesWon()

	public boolean takeTurn(String x) {
		boolean wordIsFound = false;
		char pAns = x.charAt(0);
		for (int i = 0; i < gameWord.length(); ++i) {
			char current = gameWord.charAt(i);
			if(pAns== current) {
				wordIsFound = true;
			}
		}
	return wordIsFound;
	} // takeTurn(String)
	

} // player class
