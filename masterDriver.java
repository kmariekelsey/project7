import java.util.Random;
import java.util.Scanner;


package project7;

public class masterDriver {
	static guess userGuess;
	static guess keyCode;
	static boolean gameOver=false;
	static boolean winner=false;
	static int guessesLeft = 12;
	static guess[] answerHistory=new guess[12];

	public static void main(String[] args){
		printGreeting();
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		
		while(!answer.equals("Y")&&!answer.equals("N")){
			System.err.println("\nPlease type a valid answer");
			answer = scanner.nextLine();
		}
		
		if(answer.equals("Y")){
			initializeGame();				//initialize key
			System.out.println("Type in the characters for your guess and press enter.");
			System.out.println("Enter guess: ");
			answer=scanner.nextLine();
			if (checkValid(answer)) {	
				userGuess=new guess(answer);
				guessesLeft--;
				playRound(answer);			//process guess
			}
			else if(answer.equals("history")) printHistory();
			else System.err.println(" --> INVALID GUESS\n");
		}
		
		else {
			System.out.println("Thank you for playing mastermind");
			scanner.close();
			return;
		}
		
		while(true){
			System.out.println("\nType in the characters for your guess and press enter.");
			System.out.println("You have "+guessesLeft+ " guesses left.");
			System.out.println("Enter next guess: ");
			answer=scanner.nextLine();
			if (checkValid(answer)) {
				guessesLeft--;
				if(userGuess==null) userGuess=new guess(answer);
				playRound(answer);				//process guess
			}
			else if(answer.equals("history")) printHistory();
			else System.err.println(" --> INVALID GUESS\n");
			if(gameOver==true||guessesLeft==0) break;		//the game is over when you're out of guesses or guess the correct code	
		}
		
		if(winner) System.out.println("\nCongratulations, You Won!");
		else {
			System.out.println("\nSorry, You Lose");
			System.out.println("Correct Combination: " +keyCode.getCombination());
		}
	
	scanner.close();
}

//checks for a valid color input
	private static boolean checkValid(String answer) {
		if (answer.length() != 4) return false;
		for (int i=0; i<4; i++) {
			if (answer.charAt(i) != 'R' && answer.charAt(i) != 'B' 
					&& answer.charAt(i) != 'O' && answer.charAt(i) != 'Y' 
					&& answer.charAt(i) != 'G' && answer.charAt(i) != 'P') return false;
		}
		return true;
	}



//method updates the state of the game based on the user input
	private static void playRound(String answer) {
		userGuess.updateCombination(answer);		//update the current guess, number of pegs, win/lose
		
		System.out.println("Number of Black Pegs: "+ userGuess.getBlackPegs());
		System.out.println("Number of White Pegs: "+ userGuess.getWhitePegs());
		answerHistory[guessesLeft]=new guess(userGuess);		//add to try history
		if(userGuess.getBlackPegs()==4){						//win condition~4 black pegs
			gameOver=true;
			winner=true;
		}
	}


	
//method initializes the winning key using the Random class
	private static void initializeGame(){
		String key = "";
		Random randomKey=new Random();
		
		System.out.println("\nGenerating secret code ....");
		
		for(int i=0; i<4;i++){
			
			int randomColor= randomKey.nextInt(6); //random number from 0-5
			
			if(randomColor==0) key=key+'B';        //0=B
			else if(randomColor==1) key=key+'G';   //1=G
			else if(randomColor==2) key=key+'O';   //2=O
			else if(randomColor==3) key=key+'P';   //3=P
			else if(randomColor==4) key=key+'R';   //4=R
			else key=key+'Y';			 //5=Y
		}
		keyCode =new guess(key);		         //instantiate the code as a guess type
	}

//cycles through the global answer history
	public static void printHistory(){
		System.out.println("History:");
		
		for(int i=11;i>=guessesLeft;i--){
			if(answerHistory[i]!=null) {
				System.out.println("Guess:"+ answerHistory[i].getCombination());
				System.out.println("Number of Black Pegs: "+ answerHistory[i].getBlackPegs());
				System.out.println("Number of White Pegs: "+ answerHistory[i].getWhitePegs() + "\n");
			}	
		}
	}


//method prints the formatted intro greeting
	public static void printGreeting(){
		
		System.out.println("Welcome to Mastermind.  Here are the rules."
			+ "\n\nThis is a text version of the classic board game Mastermind."
			+ "\nThe computer will think of a secret code. The code consists of 4 colored pegs. "
			+ "\nThe pegs may be one of six colors: blue, green, orange, purple, red, or yellow."
			+ "\nA color may appear more than once in the code. "
			+ "\nYou try to guess what colored pegs are in the code and what order they are in. "
			+ "\nAfter you make a valid guess the result (feedback) will be displayed. "
			+ "\nThe result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess.  "
			+ "\nFor each peg in the guess that is the correct color, but is out of position, you get a white peg.  "
			+ "\nFor each peg, which is fully incorrect, you get no feedback."
			+ "\n\nOnly the first letter of the color is displayed. B for Blue, R for Red, and so forth. "
			+ "\nType 'history' so see your valid print hirtory."
			+ "\nWhen entering guesses you only need to enter the first character of each color as a capital letter."
			+ "\nYou have 12 guesses to figure out the secret code or you lose the game.  "
			+ "\n\nAre you ready to play? (Y/N):");
			
	}







}
