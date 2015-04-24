package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Particles.Particle;
import Utilities.Vec2;

/**
 * A circular area that represents part of a ships hitbox
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class HitBox {
	private int radius;
	private Vec2 offset;
	
	/**
	 * HitBox constructor
	 * @param radius size of the HitBox in pixels
	 * @param offsetX distance from the HitBox to the center of the ship
	 * @param offsetY distance from the HitBox to the center of the ship
	 */
	public HitBox(int radius,int offsetX,int offsetY){
		this.radius = radius;
		offset = new Vec2(offsetX,offsetY);
	}
	
	/**
	 * Draws the HitBox to the screen
	 * @param g2d graphics to draw to
	 * @param shipX absolute position of the ship
	 * @param shipY absolute position of the ship
	 * @param theta direction the ship is facing in radians
	 */
	public void draw(Graphics2D g2d, double shipX, double shipY, double theta){
		double x = shipX+(offset.getX()*Math.sin(theta-Math.PI/2)+offset.getY()*Math.sin(theta));
		double y = shipY+(offset.getX()*Math.cos(theta-Math.PI/2)+offset.getY()*Math.cos(theta));
		g2d.setColor(Color.GRAY);
		g2d.fillOval((int)((x*MainThread.scale-MainThread.view.getX())-radius*MainThread.scale/2),(int)((y*MainThread.scale-MainThread.view.getY())-radius*MainThread.scale/2),(int)(radius*MainThread.scale),(int)(radius*MainThread.scale));	
	}
	
	/**
	 * @param shipX absolute position of the ship
	 * @param shipY absolute position of the ship
	 * @param theta direction the ship is facing in radians
	 * @return the x position of the center of the HitBox
	 */
	public double getX(double shipX, double shipY, double theta){
		return shipX+(offset.getX()*Math.sin(theta-Math.PI/2)+offset.getY()*Math.sin(theta));
	}
	
	/**
	 * @param shipX absolute position of the ship
	 * @param shipY absolute position of the ship
	 * @param theta direction the ship is facing in radians
	 * @return the y position of the center of the HitBox
	 */
	public double getY(double shipX, double shipY, double theta){
		return shipY+(offset.getX()*Math.cos(theta-Math.PI/2)+offset.getY()*Math.cos(theta));
	}
	
	/**
	 * @return the size of the HitBox
	 */
	public int getRadius(){
		return radius;
	}
}
