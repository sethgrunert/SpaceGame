package Particles;

import java.awt.Color;
import java.awt.Graphics2D;

import Game.MainThread;



public class LaserParticle extends Particle{

	public LaserParticle(int numModules,double theta, double x, double y) {
		super(theta, x, y);
		this.numModules = numModules;
		this.color = Color.RED;
		this.speed = 10;
		this.radius = (int) (Math.sqrt(numModules)*7);
		this.range = (int) (Math.sqrt(numModules)*600);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawOval((int)((posX*MainThread.scale-MainThread.viewX)-radius*MainThread.scale*3/4),(int)((posY*MainThread.scale-MainThread.viewY)-radius*MainThread.scale*3/4),(int)(radius*MainThread.scale*1.5),(int)(radius*MainThread.scale*1.5));
		g2d.setColor(color);
		g2d.fillOval((int)((posX*MainThread.scale-MainThread.viewX)-radius*MainThread.scale/2),(int)((posY*MainThread.scale-MainThread.viewY)-radius*MainThread.scale/2),(int)(radius*MainThread.scale),(int)(radius*MainThread.scale));
	}

	@Override
	public Particle clone(double theta, double x, double y) {
		return new LaserParticle(numModules,theta,x,y);
	}
	
}
