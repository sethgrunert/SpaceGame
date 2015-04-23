package Particles;

import java.awt.Color;
import java.awt.Graphics2D;

import Game.MainThread;



public class RailGunParticle extends Particle {

	public RailGunParticle(int numModules, double theta, double x, double y) {
		super(theta, x, y);
		this.numModules = numModules;
		this.color = Color.GRAY;
		this.speed = 20;
		this.radius = (int) (Math.sqrt(numModules)*15);
		this.range = (int) (Math.sqrt(numModules)*1200);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillOval((int)((posX*MainThread.scale-MainThread.viewX)-radius*MainThread.scale/2),(int)((posY*MainThread.scale-MainThread.viewY)-radius*MainThread.scale/2),(int)(radius*MainThread.scale),(int)(radius*MainThread.scale));
	}

	@Override
	public Particle clone(double theta, double x, double y) {
		return new RailGunParticle(numModules,theta,x,y);
	}

}
