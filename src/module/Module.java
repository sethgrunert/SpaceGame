package module;

import java.awt.Graphics2D;

/**
 * Defines a part of a ship
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public abstract class Module{
	protected int numModules = 0;
	protected double mass = 0;
	protected double health = 0;
	protected double damageReduction = 0;
	protected double thrust = 0;
	protected double power = 0;
	protected double powerCap = 0;
	protected int faction = 0;
	
	/**
	 * Constructor
	 * @param numModules amount of room in the ship the module takes up
	 * 
	 */
	public Module(int numModules){
		this.numModules = numModules;
	}
	
	public void setFaction(int faction){
		this.faction = faction;
	}

	/**
	 * @return size of the module
	 */
	public int getSize() {
		return numModules;
	}
	
	/**
	 * Steps the module forward one frame (1/60s)
	 * @param theta direction the ship using the module is facing
	 * @param x position of the ship using the module
	 * @param y position of the ship using the module
	 * @param fireing true if the module should try to activate
	 * @param powerRemaining power the ship using the module has left
	 * @return power lost or gained during the modules operation
	 */
	public double update(Double theta, Double x, Double y,boolean fireing,double powerRemaining){
		return power/60;
	}
	
	/**
	 * draws the module and any particles the module creates
	 * @param g2d graphics to draw to
	 */
	public abstract void draw(Graphics2D g2d);

	/**
	 * @return power capacity added by the module
	 */
	public double getPowerCap() {
		return powerCap;
	}

	/**
	 * @return thrust added by the module
	 */
	public double getThrust() {
		return thrust;
	}

	/**
	 * @return damage reduction added by the module
	 */
	public double getDamageReduction() {
		return damageReduction;
	}

	/**
	 * @return heath added by the module
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @return mass added by the module
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @return power gain/loss added by the module
	 */
	public double getPower() {
		return power;
	}
}
