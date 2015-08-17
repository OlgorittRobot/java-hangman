// Prompter.java does the io --> input and output

// import a package
import java.io.Console;

// make the class prompter
public class Prompter {
	// give the prompter a game object
	private Game mGame;

	// prompter constructor
	public Prompter(Game game) {
		// construct the game object
		mGame = game;
	}

	public void play() {
		while (mGame.getRemainingTries() > 0 && !mGame.isSolved()) {
			displayProgress();
			promptForGuess();
		}
		if (mGame.isSolved()) {
			System.out.printf("Congratulations! you won with %d tries remaining.\n", mGame.getRemainingTries());
		} else {
			System.out.printf("Bummer, the word was %s.  :(\n", mGame.getAnswer());
		}
	}

	//
	public boolean promptForGuess() {
		Console console = System.console();
		// default is false, because evaluating them first.
		boolean isHit = false;
		// keeps the loop going if guess is not valid
		boolean isValidGuess = false;
		// lets evaluate
		while (! isValidGuess) {
			// prompt the user for a guess and store the answer inside guesAsString var
			String guessAsString = console.readLine("Enter a letter:  ");
			// trying otherwise it break when not valid
			try {
				// put it trough applyGuess at Game.java
				isHit = mGame.applyGuess(guessAsString);
				// true if is valid
				isValidGuess = true;
			} catch (IllegalArgumentException ex) {
				console.printf("%s please try again.\n", ex.getMessage());
			}
		}
		// return the guess
		return isHit;
	}

	public void displayProgress() {
		// let the user know the progress and how many tries left
		System.out.printf("You have %d tries left to solve:  %s\n", mGame.getRemainingTries(), mGame.getCurrentProgress());
	}

}