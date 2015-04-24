package module.weapon;

import java.awt.Graphics2D;
import java.util.ArrayList;

import particle.Particle;

import module.Module;


/**
 * 
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class Weapon extends Module{
	protected double fireRate;
	private double cooldown=0;
	protected double burstPower=0;
	protected double damage;
	protected Particle particle;
	private ArrayList<Particle> bullets = new ArrayList<Particle>();
	protected double shipOffsetX = 0,shipOffsetY = 0;
	protected double shipOffsetTheta;
	
	/**
	 * Weapon Constructor
	 * @param numModules amount of room in the ship the module takes up
	 * @param shipOffsetX distance from the center of the ship
	 * @param shipOffsetY distance from the center of the ship
	 * @param shipOffsetTheta angle in radians from the ships facing 
	 * to the direction the weapon fires
	 */
	Weapon(int numModues,double shipOffsetX, double shipOffsetY,double shipOffsetTheta) {
		super(numModues);
		this.shipOffsetX = shipOffsetX;
		this.shipOffsetY = shipOffsetY;
		this.shipOffsetTheta = shipOffsetTheta;
	}
	
	/**
	 * Attempts to fire the weapon and updates the weapons cooldown
	 */
	public double update(Double theta, Double x, Double y,boolean fireing, double powerRemaining) {
		double powerUsage = 0;
		cooldown+=1000/60;
		if(cooldown>fireRate && fireing && powerRemaining>burstPower){
			fire(theta, x, y);
			cooldown=Math.random()*fireRate/25;
			powerUsage-=burstPower;
		}
		updateParticles();
		return powerUsage;
	}

	/**
	 * fires a new particle from the weapon
	 * @param shipTheta direction the ship
	 * @param shipX position of the ship
	 * @param shipY position of the ship
	 */
	private void fire(Double shipTheta, Double shipX, Double shipY) {
		double x = shipX+(shipOffsetX*Math.sin(shipTheta-Math.PI/2)+shipOffsetY*Math.sin(shipTheta));
		double y = shipY+(shipOffsetX*Math.cos(shipTheta-Math.PI/2)+shipOffsetY*Math.cos(shipTheta));
		bullets.add(particle.clone(shipTheta+shipOffsetTheta,x,y));
	}
	
	/**
	 * moves all particles fired from the weapon and deletes them when they
	 * move past their maximum range
	 */
	private void updateParticles(){
		for(int i=0; i<bullets.size(); i++){
			if(bullets.get(i)!=null){
				if(!bullets.get(i).move())
					bullets.remove(i);
			}		
		}
	}

	
	public void draw(Graphics2D g2d) {
		for(int i=0; i<bullets.size(); i++){
			bullets.get(i).draw(g2d);
		}
		
	}

}
