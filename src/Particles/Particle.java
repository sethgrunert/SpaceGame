package Particles;
import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Particle {
	protected int numModules=0;
	protected int radius=0;
	protected int speed=0;
	private double theta=0;
	protected double posX=0;
	protected double posY=0;
	protected int range =0;
	protected Color color =  null;
	
	Particle(double theta, double x, double y){
		this.theta=theta;
		posX=x;
		posY=y;
	}
	
	/**
	 * Moves a particle
	 * @return returns false if the particle is past its max range and true otherwise
	 */
	public boolean move(){
		posX+=speed*Math.sin(theta);
		posY+=speed*Math.cos(theta);
		range-=speed;
		if(range<0)
			return false;
		return true;
	}
	
	public abstract void draw(Graphics2D g2d);
	public abstract Particle clone(double theta, double x, double y);
}
