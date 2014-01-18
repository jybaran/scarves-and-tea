// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public abstract class Character {

    protected int _hp;
    protected int _str;
    protected int _def;
    protected double _attack;
    
    public boolean isAlive() {
	return _hp > 0;
    }

    public abstract String getName();

    public int getDefense() {
	return _def;
    }

    public void lowerHP(int x) {
	_hp -= x;
    }

    public int attack(Character opponent) {
	int damage = (int)(_str*_attack) - opponent.getDefense();
	
	if (damage < 0)
	    damage = 0;

	opponent.lowerHP(damage);
	
	return damage;
    }

    public abstract void specialize();

    public abstract void normalize();

    public static String about() {
	String retStr = "";
	return retStr;
    }

}
