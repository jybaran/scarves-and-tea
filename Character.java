// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public abstract class Character {

    protected int _hp;
    protected int _fullHP;
    protected int _str;
    protected int _def;
    protected int _altdef;
    protected double _attack;
    protected double _altattack;
    protected boolean isSpecial;
    
    public boolean isAlive() {
	return _hp > 0;
    }

    public void raiseAttack(double x) {
	_attack += x;
	_altattack += x*.5;
    }

    public int getDefense() {
	return _def;
    }

    public void  raiseDefense(int x) {
	_def += x;
	_altdef -= x*.5;
    }

    public int getSpDefense() {
	return _altdef;
    }

    public String getHP() {
	String retStr = "";
	retStr += _hp + "/" + _fullHP;	
	return retStr;
    }

    public void lowerHP(int x) {
	_hp -= x;
	if ( _hp < 0 ) {
	    _hp = 0;
	}
    }

    public void raiseHP(int x) {
	_hp += x;
	_fullHP += x;
    }
    
    public void resetHP() {
	_hp = _fullHP;
    }

    public void specialize( boolean b ) {
	isSpecial = b;
    }

    public int attack(Character opponent) {
	int damage = 0;

	if ( isSpecial ) {
	    damage = (int)(_str*_altattack) - opponent.getSpDefense();
	}
	else {
	    damage = (int)(_str*_attack) - opponent.getDefense();
	}
	damage = (int)(.75*damage) + (int)( Math.random()*(.25*damage) );	

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
