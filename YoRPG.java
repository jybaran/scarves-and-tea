// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

import java.io.*;
import java.util.*;

public class YoRPG {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    //change this constant to set number of encounters in a game
    public final static int MAX_ENCOUNTERS = 3; //should represent levels now, right?

    //each round, a Warrior and a Monster will be instantiated
    private Character pat;   //Is it man or woman?
    private Monster smaug; //Friendly generic monster name, eh?

    private int moveCount;
    private int roundCount;
    private int levelCount;
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
	charas[0][0] = "John (description here)";
	charas[0][1] = "Lestrade (description here)";
	charas[1][0] = "Ron (description here)";
	charas[1][1] = "Crookshanks (description here)";

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
	s = "Welcome to Ye Olde RPG!\n";

	s += "\nChoose your difficulty: \n";
	s += "\t1: Easy\n";
	s += "\t2: Not so easy\n";
	s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
	s += "Selection: ";
	System.out.print( s );

	try {
	    difficulty = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }

	s = "Choose your hero: \n";
	s+= "\t1: Sherlock (description here)\n";
	s+= "\t2: Hermione (description here)\n";
	s+= "Selection: ";
	System.out.print(s);

	try {
	    heroType = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }

	s = "Choose your sidekick: \n";
	s+= "\t1: " + charas[heroType-1][0] + "\n";
	s+= "\t2: " + charas[heroType-1][1] + "\n";
	s+= "Selection: ";
	System.out.print(s);

	try {
	    sidekickType = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }

	if (heroType == 1 && sidekickType == 1) {
	    pat = new Johnlock();
	}

	else if (heroType == 1 && sidekickType == 2 ) { 
	    pat = new Sherstrade();
	}
	
	else if (heroType == 2 && sidekickType == 1) {
	    pat = new Ronmione();
	}

	else if (heroType == 1 && sidekickType == 2) {
	    pat = new Catlady();
	}

    }//end newGame()


    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  hero pat has been initialized
      post: Returns true if player wins (monster dies).
      Returns false if monster wins (player dies).
      =============================================*/
    public boolean playRound() {

	int i = 1;
	int d1, d2;

	if ( Math.random() >= ( difficulty / 3.0 ) )
	    System.out.println( "Nothing to see here. Move along!" );

	else {
	    System.out.println( "Play Round " + roundCount);
	    System.out.println( "Lo, yonder monster approacheth!" );

	    smaug = new Monster( levelCount );
	}

	while( smaug.isAlive() && pat.isAlive() ) {

	    // Give user the option of using a special attack:
	    // If you land a hit, you incur greater damage,
	    // ...but if you get hit, you take more damage.
	    try {
		System.out.println( "Do you feel lucky?" );
		System.out.println( "\t1: Nay.\n\t2: Aye!" );
		i = Integer.parseInt( in.readLine() );
	    }
	    catch ( IOException e ) { }

	    if ( i == 2 )
		pat.specialize();
	    else
		pat.normalize();

	    d1 = pat.attack( smaug );
	    d2 = smaug.attack( pat );

	    /*There was weird looking code here and I need
	      to make sure I didn;t delete something important*/
	    System.out.println("You dealt " + d1 +
					   " points of damage.");

	    System.out.println( "Ye Olde Monster hit back for " + d2
				+ " points of damage.");
	}//end while

	//option 1: you & the monster perish
	if ( !smaug.isAlive() && !pat.isAlive() ) {
	    System.out.println( "'Twas an epic battle, to be sure... " + 
				"You cut ye olde monster down, but " +
				"with its dying breath ye olde monster " +
				"laid a fatal blow upon thy skull." );
	    return false;
	}
	//option 2: you slay the beast
	else if ( !smaug.isAlive() ) {
	    System.out.println( "HuzzaaH! Ye olde monster hath been slain!" );
	    return true;
	}
	//option 3: the beast slays you
	else if ( !pat.isAlive() ) {
	    System.out.println( "Ye olde self hath expired. You got dead." );
	    return false;
	}//end else
	return true;
    }//end playRound()

    //PLAY LEVEL METHOD
    public boolean playLevel() {
	roundCount = 1;
	int wins = 0;
	int losses = 0;
	int i= 1;
	System.out.println("Let's play level " + levelCount + "!");
	//If you haven't won enough to progress and haven't lost enough to lose, you're in this loop
	while (wins < 3 && losses < 3) { 
	    if (playRound()) {
		//adjust hp and stuff
		wins ++;
		roundCount ++;
	    }
	    else { 
		losses ++;
		roundCount ++;
	    }
	}
	if (losses >= 3) {
	    System.out.println( "You fought bravely and lost. Oops.");
	    return false;
	}
	else if (wins >= 3) {
	    if (levelCount < 3) { //bc the last time shouldn't prompt you for the next level
		try {
		    System.out.println( "Good job, man. Now what?" );
		    System.out.println( "\t1: Keep playing level. \n\t2: Proceed to next level" );
		    i = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }
		if (i == 1) {
		    playRound();
		}
		else {
		    roundCount --;
		    levelCount++;
		    return true;
		}
	    }
	}
	return true;
    }


	    
	
	    
	    
	
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

	//loading...
	YoRPG game = new YoRPG();

	int encounters = 0;

	while( encounters <= 3) {//encounters < MAX_ENCOUNTERS ) {
	    if ( !game.playLevel() )//originally playRound
		break;
	    encounters++;
	    System.out.println();
	}

	System.out.println( "Thy game doth be over." );

    }//end main

}//end class YoRPG
