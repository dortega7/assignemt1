/**
 * Name:Daniel Ortega
 * Class: CSIS 2450
 * Assignment: A01 HangMan
 * Took inspiration from https://codereview.stackexchange.com/questions/171369/text-based-hangman-game-in-java (why reinvent the wheel?)
 * I did not copy and paste the code but I used it as a guide. Giving credit where its due to avoid plagiarism.
 */
package hangMan;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HangMan {

    public static void main(String[] args) {
    	
    	String wordToGuess = "attempt".toUpperCase();
    	int wordLength = wordToGuess.length();
    	int attempts = 6;
    	boolean dead = false;
    	
        Set<String> previousGuesses = new HashSet<>();
        Scanner input = new Scanner(System.in);
        
        char[] wordToGuessChars = wordToGuess.toCharArray();
        char[] unguessedWord = wordToGuess.toCharArray();
        
        System.out.println("The secret word has " + wordLength + " letters. You have 6 attempts, fail and you die hanged. Guess correctly and you live.");

        for (int i = 0; i < wordLength; i++) {
            unguessedWord[i] = '_';
        }

        while (!String.valueOf(unguessedWord).equals(wordToGuess) & dead == false) {

            boolean correct = false;
            boolean repeated = false;

            for (int i = 0; i < wordLength; i++) {
                System.out.print("\t " + unguessedWord[i]);
            }
            System.out.println("\n");
           
            System.out.println("Type your guess: ");
            String currentGuess = input.next().toUpperCase().substring(0, 1);
            char currentGuessChar = currentGuess.charAt(0); 

            if (previousGuesses.contains(currentGuess)) {
            	attempts--;
                System.out.println("You already guessed this letter! You have "+ attempts + " attempts. \nYour previous guesses were: ");
                System.out.println(previousGuesses.stream().reduce(" ", String::concat));
                repeated = true;
            }

            previousGuesses.add(currentGuess);

            if (!repeated) {
                int repeatedWords = 0;
                for (int i = 0; i < wordLength; i++) {
                    if (wordToGuessChars[i] == currentGuessChar) {
                        unguessedWord[i] = currentGuessChar;
                        correct = true;
                        repeatedWords++;
                    }
                }
                
                if (correct) {
                    System.out.println("The letter " + currentGuessChar + " is in the secret word! There are " + repeatedWords + " " + currentGuessChar + " 's in the word. ");
                } else {
                	attempts--;
                    System.out.println("Nope, that letter is not in the secret word. You have " + attempts + " attepmts left.");
                }
                
                System.out.println();
            }
           
            if(!String.valueOf(unguessedWord).equals(wordToGuess)& attempts>0) {
            	System.out.println("Guess again\n");
            }else {dead = true;}
        }//end of while loop
        
        if(attempts<=0) {
        	System.out.print("\n\t---You are dead--- \n\n\tThe correct word was:\n");
        	for (int i = 0; i < wordLength; i++) {System.out.print("\t " + wordToGuessChars[i]);}
        }else {
        	System.out.print("\t---You guessed right!--- \n\n");
        	for (int i = 0; i < wordLength; i++) {System.out.print("\t " + unguessedWord[i]);}
        	System.out.println("\n\n\t---You live another day---");
        }
    }
}