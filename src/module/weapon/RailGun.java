package module.weapon;
import particle.RailGunParticle;

public class RailGun extends Weapon{

	public RailGun(int size, double shipOffsetX, double shipOffsetY, double shipOffsetTheta) {
		super(size, shipOffsetX, shipOffsetY, shipOffsetTheta);
		mass = 2.0*size;
		health = 1.0*size;
		fireRate = 1000*Math.sqrt(size);
		damage = 3.5*size;
		burstPower =1*size;
		particle = new RailGunParticle(size,0,0,0,damage,faction);
	}

}
