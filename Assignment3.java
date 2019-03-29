package all_code;


/**
 * This class creates a simple word search puzzle, using the Java programming language
 *
 * @author Jeehyuep Kwon
 * @version Dec. 6, 2018
 */


import java.util.*;

public class Assignment3 {
	
	//constants 
	static Scanner input = new Scanner(System.in);
	static Random random = new Random();
	static final int MAXIMUM_SIZE_OF_ARRAY = 10;
	static final int MINIMUM_SIZE_OF_ARRAY = 5;
	
	
	public static void main(String[] args) {
		
		// declare the size of puzzle
		int numberOfWords = 0;
		// declare two dimensional array
		char[][] wordsFromUser; // no memory allocated
		// declare a dimensional array of answer list
		String[] searchList;
		//declare random letters to fill the random spot of puzzle
		char[] randomLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		
		//display the purpose of the program
		displayPurpose();
		
		//call method to get the size of puzzle and return it
		numberOfWords = getNumberOfWords(numberOfWords);
		
		//allocate memory space to the puzzle
		wordsFromUser = new char[numberOfWords][numberOfWords];
		
		//call method to get the words from user, store it in puzzle and return the search list
		searchList = getWordsFromUser(wordsFromUser, numberOfWords, randomLetters);
		
		//call method to fill the random letter inside the puzzle
		fillRandomLetter(wordsFromUser, numberOfWords, randomLetters);
		
		//display the puzzle
		displayWords(wordsFromUser, numberOfWords);
		//display the search List
		WordsForSearch(searchList, numberOfWords);
		
	}
	
	
	private static void displayPurpose() {
		
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("-------------This program creates a simple word search puzzle---------------");
		System.out.println("----------------------------------------------------------------------------");
	}
	
	
	private static int getNumberOfWords(int numberOfWords) {
		
		//ask user for the size of the puzzle from the user
		do {
			System.out.print("Please, input the number of words you want to create(length >= 5 and <= 10) :");
			numberOfWords = input.nextInt();
			input.nextLine();
			
			//the size of the puzzle should be >= 5 and <= 10 - if the range is not matched, ask for the size again
			
			if(numberOfWords < MINIMUM_SIZE_OF_ARRAY || numberOfWords > MAXIMUM_SIZE_OF_ARRAY) {
				System.out.println("Invalid number!! Try again!! ");
			}
		} while(numberOfWords < MINIMUM_SIZE_OF_ARRAY || numberOfWords > MAXIMUM_SIZE_OF_ARRAY);
		System.out.println("----------------------------------------------------------------------------");
		 System.out.printf("You created %d X %d size of puzzle! Enter %d words(only %d words available)%n", numberOfWords, numberOfWords, numberOfWords, numberOfWords);
		System.out.println("----------------------------------------------------------------------------");
		
		// return the size of the puzzle
		return numberOfWords;
	}
	
	private static String[] getWordsFromUser(char[][] wordsFromUser, int numberOfWords, char[] randomLetters) {
		
		//declare one dimensional array for storing data of search list
		String[] searchList = new String[numberOfWords];
		
		//declare variable
		String wordFromUser;
		int rows = 0;
		int columns = 0;
		int startColumn = 0;
		int matchWord = 0;
		int alphabetCount = 26;
		
		//iterate through all rows in the 2d array (wordsFromUser.length == size of the puzzle) 
		for(rows = 0; rows < wordsFromUser.length; rows++) {
			
			//ask user for word of puzzle
			do {
				
				System.out.printf("Please, enter word %d : ", rows + 1);
				wordFromUser = input.nextLine();
				
				//initialize the matchWord variable
				matchWord = 0;
				//validate whether the input is letter or not
				for(int i = 0; i < wordFromUser.length(); i++) {
					for(int j = 0; j < alphabetCount; j++) {
						if(wordFromUser.charAt(i) == randomLetters[j]) {
							//count the number of correct letters
							matchWord++;
						} 
					}
				}
			
			//if the validation of letters are not correct, it gives an error message.
			if(!(matchWord == wordFromUser.length())) {
					System.out.println("Invalid!! You have to input letters from A to Z!!");
					
			//the length of a word should be less than or equals to the size of the puzzle
			//if the range is not matched, ask for the word again	
			} else if (wordFromUser.length() > numberOfWords) {
				System.out.printf("Invalid!! Your words should be less than %d words!!%n", numberOfWords);
				
			} else {
				//store search list through user
				searchList[rows] = wordFromUser;
			}		
				
			} while (wordFromUser.length() > numberOfWords || !(matchWord == wordFromUser.length()));
			
			//use Random to put the word on random spot
			//the random spot should be selected by using this formula((size of puzzle + 1) - the length of the word)
			//if you use Random, last number doesn't include. so add one to it
			startColumn = random.nextInt((numberOfWords + 1) - wordFromUser.length());
			
			//iterate through all columns in each row
			for(columns = 0; columns < wordFromUser.length(); columns++, startColumn++) {
				//put the word on random spot on column 
				wordsFromUser[rows][startColumn] = wordFromUser.charAt(columns);
			}
		}
		//return search list
		return searchList;
	}
	
	private static void fillRandomLetter(char[][] wordsFromUser, int numberOfWords, char[] randomLetters) {
		
		//declare variables
		int rows = 0;
		int columns = 0;
		int randomLetter;
		
		//store the size of random letters
		int size = randomLetters.length;
		
		//iterate through all rows
		for(rows = 0; rows < numberOfWords; rows++) {
			//iterate through all columns
			for(columns = 0; columns < numberOfWords; columns++) {
				//if the element is the null character, replace it with random letters
				if(wordsFromUser[rows][columns] == '\u0000') {
					randomLetter = random.nextInt(size);
					wordsFromUser[rows][columns] = randomLetters[randomLetter];
				}
			}
		}
	}
	
	private static void displayWords(char[][] wordsFromUser, int numberOfWords) {
		
		//declare variables
		int rows = 0;
		int columns = 0;
		
		System.out.println("-------------------------------Words Puzzle--------------------------------");
		
		
		for(rows = 0; rows < numberOfWords; rows++) {
			for(columns = 0; columns < numberOfWords; columns++) {
				//display the words 
				System.out.printf("%-10c", wordsFromUser[rows][columns]);
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------");
	}
	
	private static void WordsForSearch(String[] searchList, int numberOfWords) {
		//declare variables
		int rows = 0;
		
		System.out.println("------------------------------Words to search------------------------------");
		
		for(rows = 0; rows < numberOfWords; rows++) {
			//display the search list
			System.out.println(searchList[rows]);

		}
		System.out.println("---------------------------------------------------------------------------");
	}

}
