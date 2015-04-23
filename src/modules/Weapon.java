package modules;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Particles.Particle;



public class Weapon extends Module{
	protected double fireRate;
	private double cooldown=0;
	protected double burstPower=0;
	protected double damage;
	protected Particle particle;
	private ArrayList<Particle> bullets = new ArrayList<Particle>();
	protected double shipOffsetX = 0,shipOffsetY = 0;
	protected double shipOffsetTheta;
	
	
	Weapon(int size,double shipOffsetX, double shipOffsetY,double shipOffsetTheta) {
		super(size);
		this.shipOffsetX = shipOffsetX;
		this.shipOffsetY = shipOffsetY;
		this.shipOffsetTheta = shipOffsetTheta;
	}
	
	@Override
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

	private void fire(Double theta, Double shipX, Double shipY) {
		double x = shipX+(shipOffsetX*Math.sin(theta-Math.PI/2)+shipOffsetY*Math.sin(theta));
		double y = shipY+(shipOffsetX*Math.cos(theta-Math.PI/2)+shipOffsetY*Math.cos(theta));
		bullets.add(particle.clone(theta+shipOffsetTheta,x,y));
	}
	
	private void updateParticles(){
		for(int i=0; i<bullets.size(); i++){
			if(bullets.get(i)!=null){
				if(!bullets.get(i).move())
					bullets.remove(i);
			}		
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		for(int i=0; i<bullets.size(); i++){
			bullets.get(i).draw(g2d);
		}
		
	}

}
