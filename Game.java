// Game.java provides the game logic

public class Game {
	public static final int MAX_MISSES = 7;
	// init the answer
	private String mAnswer;
	// keep track of letters that are hits
	private String mHits;
	// keep track of letters that are misses
	private String mMisses;

	// the game constructor
	public Game(String answer) {
		// let Hangman.java decide the answer
		mAnswer = answer;
		// start from no hits
		mHits = "";
		// start from no misses
		mMisses = "";
	}

	// private method for validatong guesses
	private char validateGuess(char letter) {
		// validates if the guess is a letter
		if (! Character.isLetter(letter)) {
			throw new IllegalArgumentException("A letter is required,");
		}
		// ruling out uppercase guesses
		letter = Character.toLowerCase(letter);
		// validates if the letter has already been guessed
		// validates the letter if it is inside mMisses or MHits
		if (mMisses.indexOf(letter) >= 0 || mHits.indexOf(letter) >= 0) {
			throw new IllegalArgumentException("'" + letter + "' has already been guessed,");
		}
		return letter;
	}

	public boolean applyGuess(String letters) {
		if (letters.length() == 0) {
			throw new IllegalArgumentException("No letter found");
		}
		return applyGuess(letters.charAt(0));
	}

	// check if the guess of the user is a hit or a mis
	public boolean applyGuess(char letter) {
		// validate the guess first from private method here above
		letter = validateGuess(letter);
		// if the letter does not exist in the answer, the indexOf the guess will be -1
		// if the letter does exist in the answer, the indexOf the guess will be the correspondig index starting from 0 ofcourse
		// check if the guess is 0 or above, or -1 --> true or false
		boolean isHit = mAnswer.indexOf(letter) >= 0;
		if (isHit) {
			// store the guess inside mHits if its a hit
			mHits += letter;
		} else {
			// store the guess inside mMisses if its NOT a hit
			mMisses += letter;
		}
		// return the boolean
		return isHit;
	}

	public String getCurrentProgress() {
		// starting progress is nothing...
		String progress = "";
		// loop trough each of the letters in the answer
		for (char letter: mAnswer.toCharArray()) {
			// hide the answer with dashes
			char display = '_';
			// if the guess is a hit, the _ becomes the guessed letter
			if (mHits.indexOf(letter) >= 0) {
				display = letter;
			}
			progress += display;
		}
		return progress;
	}

	public int getRemainingTries() {
		return MAX_MISSES - mMisses.length();
	}

	public boolean isSolved() {
		return getCurrentProgress().indexOf('_') == -1 ;
	}

	public String getAnswer() {
		return mAnswer;
	}

}

















