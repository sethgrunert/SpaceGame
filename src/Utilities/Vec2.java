package Utilities;

import Game.MainThread;

public class Vec2 {
	private double x;
	private double y;

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public Vec2(int x, int y){
		this.x=x;
		this.y=y;
	}
	public Vec2(double x, int y){
		this.x=x;
		this.y=y;
	}
	public Vec2(int x, double y){
		this.x=x;
		this.y=y;
	}
	public Vec2(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public double getAngle(Vec2 p2, boolean scaled){
		double x2=p2.getX();
		double y2=p2.getY();
		if(!scaled){
			x2=p2.screenX();
			y2=p2.screenY();
		}
			
		Double theta = Math.atan(-1*(x-x2)/(y-y2));
		if(y>=y2)
			theta+=Math.PI;
		return (theta+Math.PI*2)%(Math.PI*2);
	}
	
	public double getDistance(Vec2 p2){
		return Math.sqrt((x-p2.getX())*(x-p2.getX())+(y-p2.getY())*(y-p2.getY()));
	}
	
	public double screenX(){
		return x*MainThread.scale-MainThread.view.getX();
	}
	public double screenY(){
		return y*MainThread.scale-MainThread.view.getY();
	}
}
