package Particles;
import java.awt.Color;
import java.awt.Graphics2D;

import Game.MainThread;
import Game.Ship;

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
	
	Particle(double theta, double x, double y,double d){
		this.theta=theta;
		posX=x;
		posY=y;
		damage = d;
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
		for(int i=0; i<MainThread.enemies.size(); i++){
			if(checkColision(MainThread.enemies.get(i)))
				return false;
		}
		return true;
	}
	
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
	
	public abstract void draw(Graphics2D g2d);
	public abstract Particle clone(double theta, double x, double y);
}
