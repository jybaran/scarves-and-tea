// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public class Monster extends Character {
    String _name;

    public Monster() {
	_hp = 150;
	_str = 35 + (int)( Math.random() * 10 );
	_def = 20;
	_attack = 1.5;
	_altattack = 2.5;
	_altdef = 10;
	_name = "";
    }

 
    public Monster(int i) {
	this();

	int _type = i;
	
	if (_type == 1)
	    _name = "bear";
	else if (_type == 2)
	    _name = "pterodactyl";
	else
	    _name = "bearodactyl";
    }

    public boolean isAlive() {
	return _hp > 0;
    }
    
    public int attack(Character opponent) {
	return super.attack(opponent);
    }

    public String getName() {
	return _name;
    }


    public static String about() {
	String retStr = "All we know about this monster is that it will probably ";
	retStr += "bite your head off. That's really all that matters though, right?";
	return retStr;
    }

}
