// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public class Ronmione extends Character {
    public Ronmione() {
	_hp = 125;
	_fullHP = _hp;
	_str = 30;
	_def = 30;
	_altdef = 15;
	_attack = 1.4;
	_altattack = 1.8;
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
