// TODO: comment this file

import acm.program.*;
import acm.util.*;
import java.io.*;    // for File
import java.util.*;  // for Scanner

public class Snowman extends SnowmanProgram {
	
	public void run() {
		String word="", filename="";
		int gameWon, gamesCount=0, gamesWon=0 ,best=-1,temp;
		boolean rep=true;
		int option;
		
		intro();
		
		option = readInt("Please enter option number");
		while(option>5 && option <1)
			{
			println("Options must be between 1 and 5");
			//println("Please enter option again");
			option = readInt("Please enter option number");
		}
		
		switch(option) { 
		case 1:
			filename = "res/oneword.txt";
			break;
		case 2:
			filename="res/twowords.txt";
			break;
		case 3:
			filename="res/small.txt";
			break;
		case 4:
			filename="res/dict.txt";
			break;
		case 5:
			filename="res/large.txt";
			break;
		}
				
		while(rep==true)
		{
			word = getRandomWord(filename);
			gameWon = playOneGame(word);
			gamesCount++;
			if(gameWon!=0)
			{
				gamesWon++;
				temp = gameWon - 1000;
				if (temp > best)
					best = temp;
			}
			println("Result is:" + gameWon);
			
			
			rep = readBoolean("Wanna play again? (y/n):","y","n");
			
		}
		
		stats(gamesCount, gamesWon, best);
		
	}
	
	// DONE: Contains welcome banner and dictionary options
	private void intro() {
		println("CS 106A Snowman!");
		println("I will think of a random word.");
		println("You'll try to guess its letters.");
		println("Every time you guess a letter");
		println("that isn't in my word, a new");
		println("piece of the snowman appears.");
		println("Guess correctly to avoid");
		println("bringing him to life in the sun!");
		println("");
		
		println("Chose a Dictionary size:");
		println("1) very very small (1 word)");
		println("2) very small (2 words)");
		println("3) small (5 words)");
		println("4) medium (73 words)");
		println("5) large (121,806 words)");
		
		
		
	}
	
	// DONE: Returns: 
	// Won game: number of guesses left + 1000
	// Lost game: 0
	private int playOneGame(String secretWord) {
		String guessedLetters="", correct="";
		int count = 8;
		
		print("Secret Word: ");
		for(int i=0; i<=secretWord.length()-1 ; i++)
			print("-");
		println("");
		println("Your guesses:");
		//println("size guesses: " + guessedLetters.length());
		println("Guesses left: " + count);
		
		while(count>0) {
			
			guessedLetters = guessedLetters + readGuess(guessedLetters);
			
			correct = createHint(secretWord, guessedLetters);
			if(correct=="win")
				return count+999;
			//println("");
			print("Your guesses:");
			for(int i=0; i<guessedLetters.length(); i++)
				print(guessedLetters.charAt(i));
			println("");
			//println("size guesses: " + guessedLetters.length());
			
			//println("");
			print("Guesses left: ");
			println(count-1);
			
//			displaySnowman(count);
			
			count--;

		}
		
		if(count==0)
		{
			println("You Lost!");
			displaySnowman(8);
			return 0;

		}
		
		return 0;
	}
	
	
	// DONE: Creates hints and returns flag of wheter the word has been 
	// guessed (correct) or not (incorrect)
	private String createHint(String secretWord, String guessedLetters) {
		
		int lword, lguess, index;
		char c;
		
		String hint ="", flag= "Incorrect";
		lword = secretWord.length();
		lguess = guessedLetters.length();
		
		/*println("secret word:");
		for(int i=0; i<lword ; i++)
			print(secretWord.charAt(i));
		println("");*/
		
		/*println("guessed letters:");
		for(int i=0; i<lguess ; i++)
			print(guessedLetters.charAt(i));
		println("");*/

		
		for(int i=0; i<lword ; i++)
			hint = hint + "-";
		
		/*println("initial hint:");
		for(int i=0; i<lword ; i++)
			print(hint.charAt(i));
		println("");*/

		
		for (int i=0; i<lguess; i++)
			{
					
					c = guessedLetters.charAt(i);
					index = secretWord.indexOf(c);
					if (index!=-1) {
							if(i==lguess-1)
								flag = "Correct";
							while(index!=-1) {
							//println("index: "+index);
							if(index==0)
								hint = guessedLetters.charAt(i) + hint.substring(1);
							if(index>0 && index<lword-1)
								hint = hint.substring(0, index) + guessedLetters.charAt(i) + hint.substring(index+1);
							if(index==lword-1)
								hint = hint.substring(0,lword-1) + guessedLetters.charAt(i) ; 

							index = index+1;
							index = secretWord.indexOf(c,index);

							}

				}
					
				/*println("hint#"+ i);
				for(int k=0; k<hint.length() ; k++)
					print(hint.charAt(k));
				println("");*/
			}
				
		
		println(flag);
		
		displaySnowman(guessedLetters.length()-1);
		println("");


		if(secretWord.equals(hint))
		{
			println("");
			print("You Win! My word was \"");
			for(int i=0; i<lword ; i++)
				print(secretWord.charAt(i));
			println("\"");
			return "win";
		}
		else
		{
			print("Secret Word: ");
			for(int k=0; k<hint.length() ; k++)
				print(hint.charAt(k));
			println("");
		}
		
		return "";
		/*if (hint.equals(secretWord))
			return "yes";
		else
			return "no";*/
		/*if(guessedLetters.equals(""))
			return "";
		else
			return flag;*/
		
	}
	
	// DONE: Reads and validates guesses
	private char readGuess(String guessedLetters) {

		
		String in = readLine("? ");
		print(in);
		char c = in.charAt(0);
		c = Character.toUpperCase(c);
		int repeat = guessedLetters.indexOf(c);
		
		while(in.length()!=1 || (c<'A' || c>'Z') || repeat!=-1)
			{
			if(in.length()!=1)
				println("Please Enter EXACTLY ONE character: ");
			else if(c<'A' || c>'Z')
				println("Pleeeeease enter a CHARACTER: ");
			else //if((c<'A' || c>'Z') && repeat!=-1)
				println("You ALREADY enetered that character. Enter a NEW character: ");
			
			in = readLine("?");
			c = in.charAt(0);
			c = Character.toUpperCase(c);
			repeat = guessedLetters.indexOf(c);

			}
		
		
		return c;
	}
	
	// DONE: Displays snowman in 9 stages
	private void displaySnowman(int guessCount) {
		// TODO: write this method
		try {
			String n, file="res/display";
			int sum =0, counter=8-guessCount;
			
			file = file + counter + ".txt";
			//println(file);

					
				Scanner input = new Scanner(new File(file));

				while(input.hasNextLine()) {
					n = input.nextLine();
					println(n);
				}
				
				input.close();

		} catch (FileNotFoundException e){
			println("File Not Found.");
			e.printStackTrace();
		}		
	}
	
	// DONE: Show stats after the session is over
	private void stats(int gamesCount, int gamesWon, int best) {
		// TODO: write this method
		double rate= 100.0*gamesWon/gamesCount;
		
		println();
		println("*******************");
		println("Overall statistics:");
		println("Games played: " + gamesCount);
		println("Games won: " + gamesWon);
		println("Win percent: " + rate + "%");
		if (best!=-1)
			println("Best game: " + best + " guess(es) remaining");
		
		println("Thanks for playing!");
		println("*******************");

		
	}
	
	// DONE: Return a random word from the dictionary files
	private String getRandomWord(String filename) {
		// TODO: write this method
		String n, word="";
		int index =0, counter=0;
		
		try {
			

				Scanner input = new Scanner(new File(filename));

				while(input.hasNextLine()) {
					input.nextLine();
					counter++;
					//println(counter);
				}
				input.close();

				input = new Scanner(new File(filename));
				index = RandomGenerator.getInstance().nextInt(2,counter);
				print(index);
				for(int i=0; i<index; i++)
					{
					word = input.nextLine();
				}
				
				println(word);
				
				input.close();
			

		} catch (FileNotFoundException e){
			println("File Not Found.");
			e.printStackTrace();
		}
		
		return word;
	}
}

	// That's It!!
