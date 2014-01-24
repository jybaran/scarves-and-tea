// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

import java.io.*;
import java.util.*;

public class YoRPG {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    //each round, a Warrior and a Monster will be instantiated
    private Character pat;   //Is it man or woman?
    private Monster smaug; //Friendly generic monster name, eh?

    private int moveCount;
    private int roundCount;
    private int levelCount;
    private int wins;
    private int losses;
    private boolean gameOver;
    private int difficulty;

    private InputStreamReader isr;
    private BufferedReader in;

    private String[][] charas;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public YoRPG() {
	moveCount = 0;
	gameOver = false;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );

	charas = new String[2][2];
	charas[0][0] = "John (Sherlock's flatmate & loyal blogger)";
	charas[0][1] = "Lestrade (detective inspector for Scotland Yard)";
	charas[1][0] = "Ron (red hair and a hand-me-down robe, he must be a Weasley)";
	charas[1][1] = "Crookshanks (either a very large cat or quite a small tiger)";

	newGame();
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~~~

    /*=============================================
      void newGame() -- facilitates info gathering to begin a new game
      pre:  
      post: according to user input, modifies instance var for difficulty 
      and instantiates a Warrior
      =============================================*/
    public void newGame() {

	levelCount = 1;
	String s;
	int heroType = 0;
	int sidekickType = 0;
	String name = "";
	int instructions = 0;
	String input = "1";
	s = "\n\nWelcome to Scarf Wars!\n";

	s+= "Would you like to read the rules before you begin? \n";
	s+="\t1: Sure\n \t2: Nah, I know what I'm doing\n";
	System.out.print (s) ;

	try {
	    input = in.readLine();
	}
	catch (IOException e ) { }

	if ( !( input.equals("1") ) && !( input.equals("2") ) ) {
	    input = "1";
	}

	instructions = Integer.parseInt( input );

	if (instructions == 1) {
	    String rules = "\n~~~~~~~~~~~~~~~~~~\nHow to play:\n";
	    rules += "First, select your hero character. The character you choose will determine your sidekick options.\n\n";
	    rules += "Next, pick your sidekick. Each sidekick affects your hero's stats in different ways.\n\n";
	    rules += "Each level, you will face off against a different type of monster. You must complete three rounds to continue to the next level, but choosing to play more rounds give you the chance to increase your stats.\n\n";
	    rules += "You can choose to specialize or attack normally at the beginning of each turn. Specializing increases your attack, but lowers your defense, so choose wisely.\n\n";
	    //add "do you want to know more about character options?"
	    rules += "Now play! \n~~~~~~~~~~~~~~~~~~\n";
	    System.out.println(rules);
	}

	s = "\nChoose your hero: \n";
	s+= "\t1: Sherlock (consulting detective & high-functioning sociopath)\n";
	s+= "\t2: Hermione (cleverest witch of her age)\n";
	s+= "Selection: ";
	System.out.print(s);
    
	try {
	    input = in.readLine();
	}
	catch ( IOException e ) { }

	if ( !( input.equals("1") ) && !( input.equals("2") ) ) {
	    input = "1";
	}

	heroType = Integer.parseInt( input );

	s = "Choose your sidekick: \n";
	s+= "\t1: " + charas[heroType-1][0] + "\n";
	s+= "\t2: " + charas[heroType-1][1] + "\n";
	s+= "Selection: ";
	System.out.print(s);

	try {
	    input = in.readLine();
	}
	catch ( IOException e ) { }

	if ( !( input.equals("1") ) && !( input.equals("2") ) ) {
	    input = "1";
	}

	sidekickType = Integer.parseInt( input );

	if (heroType == 1 && sidekickType == 1) {
	    pat = new Johnlock();
	}

	else if (heroType == 1 && sidekickType == 2 ) { 
	    pat = new Sherstrade();
	}
	
	else if (heroType == 2 && sidekickType == 1) {
	    pat = new Ronmione();
	}

	else if (heroType == 2 && sidekickType == 2) {
	    pat = new Catlady();
	}

    }//end newGame()


    /*=============================================
      boolean playRound -- simulates a round of combat
      pre:  hero pat has been initialized
      post: Returns true if player wins (monster dies).
      Returns false if monster wins (player dies).
      =============================================*/
    public boolean playRound() {
	String input = "2";
	int d1, d2;
	String monsterType = "";

	if (levelCount == 1)
	    monsterType = "bear";
	else if (levelCount == 2)
	    monsterType = "pterodactyl";
	else
	    monsterType = "bearodactyl";

	/*if ( Math.random() >= ( difficulty / 3.0 ) )
	  System.out.println( "Nothing to see here. Move along!" );

	  else {*/

	System.out.println( "\nRound " + (roundCount + 1));
	System.out.println( "It's a " + monsterType + "!" );

	smaug = new Monster( levelCount );
	smaug.raiseAttack(0.5 * (levelCount - 1));
	smaug.raiseDefense(1 * (levelCount - 1));
	smaug.raiseHP(25 * levelCount);

	while( smaug.isAlive() && pat.isAlive() ) {

	    // Give user the option of using a special attack:
	    // If you land a hit, you incur greater damage,
	    // ...but if you get hit, you take more damage.
	    System.out.println( "Would you like to use a special attack?" );
	    System.out.println( "\t1: Yes!\n\t2: Nope." );
	    try {
		input = in.readLine();
	    }
	    catch (IOException e ) { }

	    if ( !( input.equals("1") ) && !( input.equals("2") ) ) {
		input = "2";
	    }
	    
	    if ( input.equals("1") ) { 
		boolean special = true;
		pat.specialize(special);
	    }
	    else {
		boolean special = false;
		pat.specialize(special);
	    }

	    d1 = pat.attack( smaug );
	    d2 = smaug.attack( pat );

	    System.out.println("\nYou dealt " + d1 +
			       " points of damage.");
	    System.out.println("The " + monsterType + "'s hitpoints are at " 
			       + smaug.getHP() + ".");
	    System.out.println( "The " + monsterType + " hit back for " + d2
				+ " points of damage.");
	    System.out.println( "Your hitpoints are at " + pat.getHP() + ".\n");
	}//end while

	//option 1: you & the monster perish
	if ( !smaug.isAlive() && !pat.isAlive() ) {
	    System.out.println( "Oh wow, you killed the monster " + 
				"but it got you back and you died too. " +
				"That really sucks." );
	    losses ++;
	    roundCount ++;
	    return false;
	}
	//option 2: you slay the beast
	else if ( !smaug.isAlive() ) {
	    roundCount ++;
	    wins ++;
	    System.out.println( "Nice! You killed the " + monsterType + "!" 
				+ "\n\tRounds played: " + roundCount
				+ "\n\tRounds won: " + wins);
	    return true;
	}
	//option 3: the beast slays you
	else if ( !pat.isAlive() ) {
	    roundCount ++;
	    losses ++;
	    System.out.println( "Nice going. You got dead." );
	    return false;
	}//end else
	return true;
    }//end playRound()

    public boolean continuedPlay() { //for playing more than 3 rounds per level
	String input = "2";	

	if (playRound()) {
	    pat.resetHP();
	    pat.raiseAttack(.05 * levelCount);
	    pat.raiseDefense(1);
	    smaug.raiseAttack(.05);
	    smaug.raiseDefense(1);
	}
	else pat.resetHP();

	if (losses >= 3) {
	    System.out.println( "Well, it appears you're out of lives. Oops.");
	    return false;
	}

	System.out.println( "Good job, man. \n You have " 
			    + (3- losses) + " lives remaining. Now what?" );
	System.out.println( "\t1: Keep playing level. "
			    + "\n\t2: Proceed to next level" );
	
	try {
	    input = in.readLine();
	}
	catch (IOException e ) { }

	if ( !( input.equals("1") ) && !( input.equals("2") ) ) {
	    input = "2";
	}

	if ( input.equals("1") ) {
	    if (losses == 2) {
		System.out.println("You only have 1 life left. "
				   + "Are you sure you want to continue?");
		System.out.println("\t1:I changed my mind. \n \t2: Positive.");
		
		try {
	    	    input = in.readLine();
		}
		catch (IOException e ) { }

		if ( !( input.equals("1") ) && !( input.equals("2") ) ) {
	    	    input = "2";
		}

		if ( input.equals("1") ) return true;
		else continuedPlay();
	    }
		
	    continuedPlay();
	}
	else {
	    return true; 	    
	}
	return true;
    }	

    //PLAY LEVEL METHOD
    public boolean playLevel() {
	roundCount = 0;
	wins = 0;
	losses = 0;
	String input = "2";
	System.out.println("\nLet's play level " + levelCount + "!\n~~~~~~~~~~~~~~~~~~");
	//If you haven't won enough to progress and haven't lost enough to lose, you're in this loop
	while (wins < 3 && losses < 3) { 
	    if ( playRound() ) {
		pat.resetHP();
		pat.raiseAttack(.1);
		pat.raiseDefense(1 * levelCount);
		smaug.raiseAttack(.1);
		smaug.raiseDefense(1);
	    }
	    else pat.resetHP();
	}
	if (losses >= 3) {
	    System.out.println( "Well, it appears you're out of lives. Oops.");
	    return false;
	}
	else if (wins >= 3) {
	    if (levelCount < 3) { 
		System.out.println( "Good job, man. \n You played " +
				    roundCount
				    + " rounds, and have " + (3- losses) +
				    " lives remaining. Now what?" );
		System.out.println( "\t1: Keep playing level. "
				    + "\n\t2: Proceed to next level." );
		try {
	    	    input = in.readLine();
		}
		catch (IOException e ) { }

		if ( !( input.equals("1") ) && !( input.equals("2") ) ) {
	    	    input = "2";
		}  
		
		if ( input.equals("1") ) {
		    if ( continuedPlay() ) {
			levelCount ++;
			pat.raiseHP( roundCount * 5 );
			return true;  
		    }
		    return false;
		}
		else {
		    levelCount++;
		    pat.raiseHP( roundCount * 5 );
		    return true;
		}
	    }
	}
	pat.raiseHP( roundCount * 5 );
	return true;
    }	    
	    
	
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

	//loading...
	YoRPG game = new YoRPG();

	int encounters = 0;

	while( encounters <= 3) {
	    if ( !game.playLevel() )
		break;
	    encounters++;
	    System.out.println();
	}

	System.out.println( "Game over." );

    }//end main

}//end class YoRPG
