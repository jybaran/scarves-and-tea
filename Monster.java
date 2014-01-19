// JENNY BARAN, MARIA VASILKIN
// pd 8
// Final Project 
// 2014-01-21

public class Monster extends Character {
    public Monster() {
	_hp = 150;
	_str = 35 + (int)( Math.random() * 10 );
	_def = 20;
	_attack = 1.5;
	_altattack = 2.5;
	_altDefense = 10;
    }

 
    public Monster(int i) {

	_hp = 150;
	_str = 20 + (int)( Math.random() * 45 );
	_def = 20;
	_attack = 1;
	String _name;
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

    public static String about() {
	String retStr = "All we know about this monster is that it will probably ";
	retStr += "bite your head off. That's really all that matters though, right?";
	return retStr;
    }

}
