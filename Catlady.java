// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public class Catlady extends Character {
    public Catlady() {
	_hp = 125;
	_str = 40;
	_def = 100;
	_altdef = _def/2;
	_attack = 0.4;
	_altattack = _attack*2;
    }

    public int attack(Character opponent) {
	return super.attack(opponent);
    }

    public void specialize() {
	if (_attack < _altattack) {
	    double tempA = _attack;
	    _attack = _altattack;
	    _altattack = tempA;
	    int tempD = _def;
	    _def = _altdef;
	    _altdef = tempD;
	}
	/*if the attack stat is larger than the alt attack, the warrior is
	  already prepped for a special attack, so nothing changes*/
    }
    
    public String getName() {
	return "";
    }

    public void normalize() {
	if (_attack > _altattack) {
	    double tempA = _attack;
	    _attack = _altattack;
	    _altattack = tempA;
	    int tempD = _def;
	    _def = _altdef;
	    _altdef = tempD;
	}
	/*if the attack stat is smaller than the alt attack, the warrior is
	  already prepped for a normal attack, so nothing changes*/
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
