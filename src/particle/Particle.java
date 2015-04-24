package particle;
import game.MainThread;
import game.Ship;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 * 
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public abstract class Particle {
	protected int numModules=0;
	protected int radius=0;
	protected int speed=0;
	private double theta=0;
	protected double posX=0;
	protected double posY=0;
	protected int range =0;
	protected Color color =  null;
	protected double damage =0;
	
	/**
	 * Particle Constructor
	 * @param theta direction of movement in radians
	 * @param x x component of the position
	 * @param y y component of the position
	 * @param damage amount of damage the particle will do when it hits with something
	 */
	Particle(double theta, double x, double y,double damage){
		this.theta=theta;
		posX=x;
		posY=y;
		this.damage = damage;
	}
	
	/**
	 * Moves a particle and checks collision with all ships
	 * @return returns false if the particle collides with something
	 */
	public boolean move(){
		posX+=speed*Math.sin(theta);
		posY+=speed*Math.cos(theta);
		range-=speed;
		if(range<0)
			return false;
		for(int i=0; i<MainThread.enemies.size(); i++){
			if(checkColision(MainThread.enemies.get(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * Checks if a particle is in contact with any hitbox in a given ship
	 * @param s ship to check the hitboxes of
	 * @return true if there is a collision, false otherwise
	 */
	public boolean checkColision(Ship s){
		double shipX = s.getPosX();
		double shipY = s.getPosY();
		double shipTheta = s.getFacing();
		double hitBoxX = 0, hitBoxY = 0;
		int hitBoxRadius =0;
		for(int i=0; i<s.getHitboxes().size(); i++){
			hitBoxX=s.getHitboxes().get(i).getX(shipX, shipY, shipTheta);
			hitBoxY=s.getHitboxes().get(i).getY(shipX, shipY, shipTheta);
			hitBoxRadius=s.getHitboxes().get(i).getRadius();
			if(Math.sqrt((hitBoxX-posX)*(hitBoxX-posX)+(hitBoxY-posY)*(hitBoxY-posY))<(hitBoxRadius+radius)){
				s.takeDamage(damage);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Draws the particle
	 * @param g2d graphics to draw to
	 */
	public abstract void draw(Graphics2D g2d);
	
	/**
	 * Copies the particle
	 * @param theta direction of movement in radians
	 * @param x x component of the position
	 * @param y y component of the position
	 * @return a copy of the particle with the same properties but
	 * new location and direction
	 */
	public abstract Particle clone(double theta, double x, double y);
}
