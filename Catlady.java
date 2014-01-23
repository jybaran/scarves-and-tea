// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public class Catlady extends Character {
    public Catlady() {
	_hp = 125;
	_fullHP = _hp;
	_str = 20;
	_def = 30;
	_altdef = 30;
	_attack = 1.7;
	_altattack = 2.3;
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
}
