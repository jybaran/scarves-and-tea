// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public class Johnlock extends Character {
    public Johnlock() {
	_hp = 125;
	_str = 40;
	_def = 30;
	_altdef = 15;
	_attack = 1.8;
	_altattack = 3.6;
    }

    public int attack(Character opponent) {
	return super.attack(opponent);
    }


    public static String about() {
	String retStr = "Sherlock. Does Sherlock things.  ";
	retStr += "I don't know what those are yet.";
	retStr += "John is also there.";
	return retStr;
    }

    public String ascii() {
	String retStr = "";
	/*	retStr +=" \n  O"; 
	retStr +=" \n \|/";
	retStr +=" \n  |";
	retStr +=" \n / \";*/
	return retStr;
    }


}
