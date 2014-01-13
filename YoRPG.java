// JENNY BARAN
// pd 8
// HW26
// 2013-11-15

import java.io.*;
import java.util.*;

public class YoRPG {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    //change this constant to set number of encounters in a game
    public final static int MAX_ENCOUNTERS = 5;

    //each round, a Warrior and a Monster will be instantiated
    private Character pat;   //Is it man or woman?
    private Monster smaug; //Friendly generic monster name, eh?

    private int moveCount;
    private boolean gameOver;
    private int difficulty;

    private InputStreamReader isr;
    private BufferedReader in;
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public YoRPG() {
	moveCount = 0;
	gameOver = false;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
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

	String s;
	int charType = 0;
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
	s+= "\t1: Sherlock (" + Sherlock.about() + ")";
	s+= "\t2: Hermione (" + Hermione.about() + ")";
	System.out.print(s);

	try {
	    heroType = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }

	s = "Choose your sidekick: \n";
	s+= "\t1: John (" + John.about() + ")";
	s+= "\t2: Dobby (" + Dobby.about() + ")";
	System.out.print(s);

	try {
	    sidekickType = Integer.parseInt( in.readLine() );
	}
	catch ( IOException e ) { }

	s = "Brave adventurer, what doth thy call thyself? (State your name): ";
	System.out.print( s );
	
	try {
	    name = in.readLine();
	}
	catch ( IOException e ) { }

	if (heroType == 1) { //if player chooses Sherlock as hero
	    //instantiate the player's hero
	    pat = new Sherlock( name );
	}

	else if (heroType == 2) { //if player chooses Hermione as hero
	    //instantiate the player's hero
	    pat = new Hermione( name );
	}
	
	else if (sidekickType == 1) {
	    pat = new John( name );
	}

	else if (sidekickType == 2) {
	    pat = new Dobby( name );
	}

    }//end newGame()


    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  Warrior pat has been initialized
      post: Returns true if player wins (monster dies).
            Returns false if monster wins (player dies).
      =============================================*/
    public boolean playTurn() {

	int i = 1;
	int d1, d2;

	if ( Math.random() >= ( difficulty / 3.0 ) )
	    System.out.println( "Nothing to see here. Move along!" );

	else {
	    System.out.println( "Lo, yonder monster approacheth!" );

	    smaug = new Monster();

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

		System.out.println( pat.getName() + " dealt " + d1 +
				    " points of damage.");

		System.out.println( "Ye Olde Monster hit back for " + d2 +
				    " points of damage.");
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
	    }
	}//end else

	return true;
    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args ) {

	//loading...
	YoRPG game = new YoRPG();

	int encounters = 0;

	while( encounters < MAX_ENCOUNTERS ) {
	    if ( !game.playTurn() )
		break;
	    encounters++;
	    System.out.println();
	}

	System.out.println( "Thy game doth be over." );

    }//end main

}//end class YoRPG
