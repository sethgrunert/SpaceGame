package particle;

import game.MainThread;

import java.awt.Color;
import java.awt.Graphics2D;



/**
 * To be used with laser class weapons
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class LaserParticle extends Particle{

	/**
	 * Particle Constructor
	 * @param numModules size of the module that fired the particle
	 * @param theta direction of movement in radians
	 * @param x x component of the position
	 * @param y y component of the position
	 * @param damage amount of damage the particle will do when it hits with something
	 */
	public LaserParticle(int numModules,double theta, double x, double y, double d, int faction) {
		super(theta, x, y, d,faction);
		this.numModules = numModules;
		this.color = Color.RED;
		this.speed = (int) (15.0 / Math.sqrt(numModules));
		this.radius = (int) (Math.sqrt(numModules)*7.0);
		this.range = (int) (Math.sqrt(numModules)*600.0);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawOval((int)((posX*MainThread.scale-MainThread.view.getX())-radius*MainThread.scale*3/4),(int)((posY*MainThread.scale-MainThread.view.getY())-radius*MainThread.scale*3/4),(int)(radius*MainThread.scale*1.5),(int)(radius*MainThread.scale*1.5));
		g2d.setColor(color);
		g2d.fillOval((int)((posX*MainThread.scale-MainThread.view.getX())-radius*MainThread.scale/2),(int)((posY*MainThread.scale-MainThread.view.getY())-radius*MainThread.scale/2),(int)(radius*MainThread.scale),(int)(radius*MainThread.scale));
	}

	@Override
	public Particle clone(double theta, double x, double y) {
		return new LaserParticle(numModules,theta,x,y,damage,faction);
	}
	
}
