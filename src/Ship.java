import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;


public class Ship {
	private double posX = 400, posY = 300;
	private double sizeX = 0, sizeY = 0;
	private double velX = 0, velY = 0;
	private double mass = 0;
	private double accelMax = 0;
	private double accelX=0, accelY=0;
	private double faceing = 0;
	private Path2D.Double path = null;
	private double rotationSpeed =0;
	
	Ship(double thrust, double mass,double sizeX,double sizeY){
		accelMax = thrust/mass;
		this.mass = mass;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		rotationSpeed = Math.PI/10*accelMax;
		
	}
	public void setFaceing(double theta){
		boolean clockwise = true;
		if(faceing<Math.PI){
			if(theta<faceing)
				clockwise = false;
			if(theta>faceing+Math.PI)
				clockwise = false;
		}
		else{
			if(theta<faceing)
				clockwise = false;
			if(theta<(faceing-Math.PI))
				clockwise = true;
		}
		double spin = rotationSpeed;
		if(Math.abs(faceing-theta)<rotationSpeed)
			spin = Math.abs(faceing-theta);
		
		
		if(clockwise)
			faceing+=spin;
		else
			faceing-=spin;
		
		if(faceing<0)
			faceing+=Math.PI*2;
		else
			faceing=faceing%(Math.PI*2);
		
	}
	public double getPosX(){
		return posX;
	}
	public double getPosY(){
		return posY;
	}
	public void setAccel(int x, int y){
		if(y==1)
			accelY=accelMax;
		if(y==-1)
			accelY=accelMax/-3;
		if(y==0)
			accelY=0;
		if(x==1)
			accelX=accelMax/3;
		if(x==-1)
			accelX=accelMax/-3;
		if(x==0)
			accelX=0;
	}
	
	public void move(){
		velX+=accelX*Math.cos(faceing);
		velY-=accelX*Math.sin(faceing);
		velX+=accelY*Math.sin(faceing);
		velY+=accelY*Math.cos(faceing);
		velX*=.99;
		velY*=.99;
		if(Math.abs(velX)<(accelMax/10))
			velX=0;
		if(Math.abs(velY)<(accelMax/10))
			velY=0;
		posX+=velX;
		posY-=velY;
		if(posX>MainThread.map.getSizeX()*MainThread.map.getTileSize()){
			posX=MainThread.map.getSizeX()*MainThread.map.getTileSize();
		}
		if(posX<0){
			posX=0;
		}
		if(posY>MainThread.map.getSizeY()*MainThread.map.getTileSize()){
			posY=MainThread.map.getSizeY()*MainThread.map.getTileSize();
		}
		if(posY<0){
			posY=0;
		}
	}
	
	public void draw(Graphics2D g2d){
		double f=faceing;
		AffineTransform t = new AffineTransform();
		path = new Path2D.Double();
		Rectangle r = new Rectangle((int)(posX-sizeX/2),(int)(posY-sizeY/2),(int)sizeX,(int)sizeY);
		path.append(r, false);
		r = new Rectangle((int)(posX-sizeX/4),(int)(posY-sizeY*3/4),(int)sizeX/2,(int)sizeY/4);
		path.append(r, false);
		t.translate(-1*MainThread.viewX, -1*MainThread.viewY);
		t.scale(MainThread.scale, MainThread.scale);
		t.rotate(f,posX,posY);
		
		
		path.transform(t);
		g2d.setColor(Color.BLACK);
		g2d.fill(path);
		g2d.setColor(Color.BLUE);
		g2d.drawLine((int)((posX-MainThread.viewX)*MainThread.scale),(int)((posY-MainThread.viewY)*MainThread.scale), MainThread.mouseX, MainThread.mouseY);
		g2d.setColor(Color.RED);
		g2d.drawLine((int)((posX-MainThread.viewX)*MainThread.scale),(int)((posY-MainThread.viewY)*MainThread.scale), (int)(velX*10+(posX-MainThread.viewX)*MainThread.scale),(int)(velY*-10+(posY-MainThread.viewY)*MainThread.scale));
		g2d.fillOval((int)(velX*10+(posX-MainThread.viewX)*MainThread.scale-5),(int)(velY*-10+(posY-MainThread.viewY)*MainThread.scale-5),10,10);
		
	}
	
	public static double getAngle(double x1, double y1, double x2, double y2){
		Double theta = Math.atan(-1*(x1-x2)/(y1-y2));
		if(y1>y2)
			theta+=Math.PI;
		return (theta+Math.PI*2)%(Math.PI*2);
	}
}
