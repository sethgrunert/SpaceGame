package module.armor;


public class SteelArmor extends Armor{

	public SteelArmor(int size) {
		super(size);
		mass = size*10;
		health = size*6;
		damageReduction = 1.0*size;
	}
	
}
