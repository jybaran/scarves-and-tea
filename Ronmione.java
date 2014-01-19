// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public class Ronmione extends Character {
    public Ronmione() {
	_hp = 125;
	_str = 50;
	_def = 50;
	_altdef = 30;
	_attack = 1.4;
	_altattack = 2.6;
    }

    public int attack(Character opponent) {
	return super.attack(opponent);
    }

    public static String about() {
	String retStr = "Hermoine is a smart person. \n";
	retStr += "She has a cat.\n";
	retStr += "They love each other.";
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
