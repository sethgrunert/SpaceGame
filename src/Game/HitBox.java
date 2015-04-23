package Game;

import java.awt.Color;
import java.awt.Graphics2D;

import Particles.Particle;

public class HitBox {
	private int radius;
	private int offsetX;
	private int offsetY;
	
	public HitBox(int radius,int offsetX,int offsetY){
		this.radius = radius;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	public void draw(Graphics2D g2d, double shipX, double shipY, double theta){
		double x = shipX+(offsetX*Math.sin(theta-Math.PI/2)+offsetY*Math.sin(theta));
		double y = shipY+(offsetX*Math.cos(theta-Math.PI/2)+offsetY*Math.cos(theta));
		g2d.setColor(Color.GRAY);
		g2d.fillOval((int)((x*MainThread.scale-MainThread.viewX)-radius*MainThread.scale/2),(int)((y*MainThread.scale-MainThread.viewY)-radius*MainThread.scale/2),(int)(radius*MainThread.scale),(int)(radius*MainThread.scale));	
	}
	
	public double getX(double shipX, double shipY, double theta){
		return shipX+(offsetX*Math.sin(theta-Math.PI/2)+offsetY*Math.sin(theta));
	}
	
	public double getY(double shipX, double shipY, double theta){
		return shipY+(offsetX*Math.cos(theta-Math.PI/2)+offsetY*Math.cos(theta));
	}
	
	public int getRadius(){
		return radius;
	}
}
