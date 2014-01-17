public class Bear extends Monster extends Character {
    public Bear() {
	_hp = 100;
	_str = 25 + (int)( Math.random() * 45 );
	def = 17;
	attack = 1;
    }
    public String getName() {
	
    
