package module.weapon;
import particle.LaserParticle;

public class BasicLaser extends Weapon{

	public BasicLaser(int size,double shipOffsetX, double shipOffsetY, double shipOffsetTheta) {
		super(size, shipOffsetX, shipOffsetY, shipOffsetTheta);
		mass = size*2;
		health = size*1;
		fireRate = 250.0*Math.sqrt(size);
		damage = 2.0*size;
		burstPower =.25*size;
		particle = new LaserParticle(size,0,0,0,damage,faction);
	}
	
}
