package modules;
import java.awt.Graphics2D;


public abstract class Module{
	protected int size = 0;
	protected double mass = 0;
	protected double health = 0;
	protected double damageReduction = 0;
	protected double thrust = 0;
	protected double power = 0;
	protected double powerCap = 0;
	
	Module(int size){
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public double update(Double theta, Double x, Double y,boolean fireing,double powerRemaining){
		return power/60;
	}
	
	public abstract void draw(Graphics2D g2d);

	public double getPowerCap() {
		return powerCap;
	}

	public double getThrust() {
		return thrust;
	}

	public double getDamageReduction() {
		return damageReduction;
	}

	public double getHealth() {
		return health;
	}

	public double getMass() {
		return mass;
	}

	public double getPower() {
		return power;
	}
}
