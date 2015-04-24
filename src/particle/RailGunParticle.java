package particle;

import game.MainThread;

import java.awt.Color;
import java.awt.Graphics2D;



/**
 * To be used with railgun class weapons
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class RailGunParticle extends Particle {

	/**
	 * Particle Constructor
	 * @param numModules size of the module that fired the particle
	 * @param theta direction of movement in radians
	 * @param x x component of the position
	 * @param y y component of the position
	 * @param damage amount of damage the particle will do when it hits with something
	 */
	public RailGunParticle(int numModules, double theta, double x, double y, double d, int faction) {
		super(theta, x, y,d,faction);
		this.numModules = numModules;
		this.color = Color.GRAY;
		this.speed = (int) (20.0 / Math.sqrt(numModules));
		this.radius = (int) (Math.sqrt(numModules)*15.0);
		this.range = (int) (Math.sqrt(numModules)*1200.0);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillOval((int)((posX*MainThread.scale-MainThread.view.getX())-radius*MainThread.scale/2),(int)((posY*MainThread.scale-MainThread.view.getY())-radius*MainThread.scale/2),(int)(radius*MainThread.scale),(int)(radius*MainThread.scale));
	}

	public Particle clone(double theta, double x, double y) {
		return new RailGunParticle(numModules,theta,x,y,damage,faction);
	}

}
