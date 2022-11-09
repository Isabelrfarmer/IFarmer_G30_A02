package hangman;

import linked_data_structures.DoublyLinkedList;
import linked_data_structures.SinglyLinkedList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * <p>Title: Player </p>
 * <p>Description: Player.java contains all logic concerning a player and their games.</p>
 * <p>Course: 420-G30 Programming III</p>
 * @author Isabel Farmer
 */

public class Player implements Serializable {
	private String player;
	private int numGamesPlayed;
	protected int numGamesWon;
	protected Dictionary words;
	protected String gameWord;
	protected int incorrectGuessCount;
	protected SinglyLinkedList<String> previousGuesses = new SinglyLinkedList<String>();
	protected boolean gotHint;
	private static final long serialVersionUID = 5401935074893086871L;
	protected String currHangmanImg = "";
	protected DoublyLinkedList<Player> allPlayers = new DoublyLinkedList<Player>();
	protected String storeGames = "previousPlayers.ser";

	protected Player() {
		setPlayerName("Unknown");
		numGamesPlayed = 0;
		numGamesWon = 0;
		incorrectGuessCount = 6;
		gotHint = false;
	} // Player()

	protected Player(String name) {
		setPlayerName(name);
		numGamesPlayed = 0;
		numGamesWon = 0;
		words = new Dictionary();
		incorrectGuessCount = 6;
		gotHint = false;
		deserialize();

	} // Player(String)

	protected void incrementIncorrectGuessCount() {
		incorrectGuessCount--;
	} // incrementIncorrectGuessCount()

	protected void addPlayer(Player player) {
		allPlayers.add(player);
		serialize();
	} // addPlayer()

	protected DoublyLinkedList<Player> getAllPlayers() {
		return allPlayers;
	} // getAllPlayers()

	protected int getIncorrectGuessCount() {
		return incorrectGuessCount;
	} // getIncorrectGuessCount()

	protected void setPlayerName(String name) {
		player = name;
	} // setPlayerName(String)

	protected String getPlayerName() {
		return player;
	} // getPlayerName()

	protected void previousGuesses(String letter) {
		previousGuesses.add(letter);
	} // previousGuesses(String)

	protected SinglyLinkedList<String> getPreviousGuesses() {
		return previousGuesses;
	} // getPreviousGuesses()

	protected void incrementNumGamesPlayed() {
		numGamesPlayed++;
	} // incrementNumGamesPlayed()

	protected int getNumGamesPlayed() {
		return numGamesPlayed;
	} // getNumGamesPlayed()

	protected void incrementNumGamesWon() {
		numGamesWon++;
	} // incrementNumGamesWon()

	protected int getNumGamesWon() {
		return numGamesWon;
	} // getNumGamesWon()

	protected boolean chooseWord() {
		boolean isValid = true;
		try {
			int max = words.wordsList.getLength() - 1;
			int min = 0;
			int range = max - min + 1;
			int randomNum = (int) (Math.random() * range) + min;
			gameWord = words.wordsList.getElementAt(randomNum);
			words.splitWord(gameWord);
			removeWord(randomNum);
		} catch (IndexOutOfBoundsException e) {
			isValid = false;
		}
		return isValid;

	} // choseWord()

	protected void removeWord(int randomNum) {
		words.wordsList.remove(randomNum);
	} // removeWord(int)

	protected String getWord() {
		return gameWord;
	} // getWord()

	public boolean takeTurn(String x) {
		boolean wordIsFound = false;
		char pAns = x.charAt(0);
		for (int i = 0; i < gameWord.length(); ++i) {
			char current = gameWord.charAt(i);

			if (pAns == current) {
				wordIsFound = true;
			}
		}
		return wordIsFound;
	} // takeTurn(String)

	protected boolean serialize() {
		boolean isSaved = true;

		try {
			FileOutputStream fileStream = new FileOutputStream(storeGames);
			ObjectOutputStream out = new ObjectOutputStream(fileStream);

			out.writeObject(allPlayers);
			out.close();
			fileStream.close();

		} catch (Exception ex) {

			isSaved = false;
		}

		return isSaved;

	} // serialize()

	public boolean deserialize() {
		boolean isDec = true;
		try {
			FileInputStream inStream = new FileInputStream(storeGames);
			if (inStream.available() > 0) {
				ObjectInputStream obj = new ObjectInputStream(inStream);
				allPlayers = (DoublyLinkedList<Player>) obj.readObject();

				obj.close();
				inStream.close();
			}
			else {
				isDec = false;
			}

		} catch (FileNotFoundException e) {
			isDec = false;
		} catch (IOException e) {
			isDec = false;
		} catch (ClassNotFoundException e) {
			isDec = false;
		}
return isDec;
	} // deserialize()

	protected Player getMostRecentPlayer() {
		Player recentP = allPlayers.getElementAt(0);
		return recentP;
	} // getMostRecentPlayer()

	public void setUpNewGame(boolean isWon) {
		incrementNumGamesPlayed();
		incorrectGuessCount = 6;
		gotHint = false;
		previousGuesses = new SinglyLinkedList<String>();
		if (isWon) {
			incrementNumGamesWon();
		}

	} // setUpNewGame(boolean)

	protected boolean isPlayerStored(Player player) {
		boolean isPresent = false;
		for (int i = 0; i < allPlayers.getLength() && isPresent == false; i++) {
			Player pListPlayer = allPlayers.getElementAt(i);
			if (player.getPlayerName().equals(pListPlayer.getPlayerName())
					&& player.getNumGamesPlayed() == pListPlayer.getNumGamesPlayed()
					&& player.getNumGamesWon() == pListPlayer.getNumGamesWon()) {
				isPresent = true;
			} // if
		} // for
		return isPresent;
	} // isPlayerStored(Player)

} // player class
