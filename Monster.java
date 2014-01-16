// JENNY BARAN
// pd 8
// HW25
// 2013-11-14

public class Monster extends Character {

    public Monster() {

	_hp = 150;
	_str = 20 + (int)( Math.random() * 45 );
	_def = 20;
	_attack = 1;

    }

    public String getName() {
	return "";
    }
    
    public int attack(Character opponent) {
	return super.attack(opponent);
    }

    public void specialize() { }

    public void normalize() { }

    public static String about() {
	String retStr = "All we know about this monster is that it will probably ";
	retStr += "bite your head off. That's really all that matters though, right?";
	return retStr;
    }

}
