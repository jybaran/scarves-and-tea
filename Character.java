// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public abstract class Character {

    protected int _hp;
    protected int _str;
    protected int _def;
    protected int _altdef;
    protected double _attack;
    protected double _altattack;
    protected boolean isSpecial;
    
    public boolean isAlive() {
	return _hp > 0;
    }

    public int getDefense() {
	return _def;
    }

    public int getSpDefense() {
	return _altdef;
    }

    public void lowerHP(int x) {
	_hp -= x;
    }

    public void specialize( boolean b ) {
	isSpecial = b;
    }

    public int attack(Character opponent) {
	
	if ( isSpecial ) {
	    int damage = (int)(_str*_altattack) - opponent.getSpDefense();
	}
	else {
	    int damage = (int)(_str*_attack) - opponent.getDefense();
	}
	
	if (damage < 0)
	    damage = 0;

	opponent.lowerHP(damage);
	
	return damage;
    }

    public static String about() {
	String retStr = "";
	return retStr;
    }

}
