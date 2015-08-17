public class Hangman {

	public static void main(String[] args) {
		// making a game from Game.java
		if (args.length == 0) {
			System.out.println("Please enter a word");
			System.exit(0);
		}
		// allow to give the answer as argument
		// clear && javac Hangman.java && java Hangman "argument"
		Game game = new Game(args[0]);
		// making a prompter from Prompter.java
		Prompter prompter = new Prompter(game);
		prompter.play();
	}

}
