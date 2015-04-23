package Game;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import modules.Module;



public class Ship {
	private double posX = 400, posY = 300;
	private double sizeX = 0, sizeY = 0;
	private double velX = 0, velY = 0;
	private double accelMax = 0;
	private double accelX=0, accelY=0;
	private double faceing = 0;
	private Path2D.Double path = null;
	private double rotationSpeed =0;
	private int remainingSize =0;
	private ArrayList<Module> modules = new ArrayList<Module>();
	private int size;
	private double powerRemaining = 0;
	private double healthRemaining = 0;
	private ArrayList<HitBox> hitboxes = new ArrayList<HitBox>();
	
	private double mass = 0;
	private double health = 0;
	private double damageReduction = 0;
	private double thrust = 0;
	private double powerCap = 0;
	private double powerRate = 0;
	
	Ship(int numModules,double sizeX,double sizeY,int posX, int posY){
		this.size = numModules;
		remainingSize = numModules;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.posX = posX;
		this.posY = posY;
		hitboxes.add(new HitBox((int)sizeX, 0, (int)(sizeY/3)));
		hitboxes.add(new HitBox((int)sizeX, 0, 0));
		hitboxes.add(new HitBox((int)sizeX, 0, (int)(-sizeY/3)));
		hitboxes.add(new HitBox((int)(sizeX/2), 0, (int)(sizeY*2/3)));
	}
	
	public ArrayList<HitBox> getHitboxes(){
		return hitboxes;
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
			if(theta<=faceing)
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
			faceing-=spin;//test
		
		if(faceing<0)
			faceing+=Math.PI*2;
		else
			faceing=faceing%(Math.PI*2);
		
	}
	
	public double getFaceing(){
		return Math.PI-faceing;
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
			accelX=accelMax/2;
		if(x==-1)
			accelX=accelMax/2;
		if(x==0)
			accelX=0;
	}
	
	public int addModule(Module newMod) throws OutOfRoomException,OutOfPowerException{
		if(remainingSize-newMod.getSize()<0)
			throw new OutOfRoomException();
		else if(powerRate+newMod.getPower()<0)
			throw new OutOfPowerException();
		else{
			modules.add(newMod);
			remainingSize-=newMod.getSize();
			mass+=newMod.getMass();
			health+=newMod.getHealth();
			healthRemaining=health;
			damageReduction+=newMod.getDamageReduction();
			thrust+=newMod.getThrust();
			powerCap+=newMod.getPowerCap();
			powerRate+=newMod.getPower();
			accelMax = thrust/mass;
			rotationSpeed = Math.PI/10*accelMax;
			System.out.println(thrust + " " + mass);
		}
		return 0;
	}
	
	public boolean move(boolean fireing){
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
		updateModules(fireing);
		return (healthRemaining!=0);
	}
	
	private void updateModules(boolean fireing){
		for(int i=0; i<modules.size(); i++){
			powerRemaining+=modules.get(i).update(getFaceing(), posX, posY,fireing,powerRemaining);
			if(powerRemaining>powerCap)
				powerRemaining=powerCap;
		}
	}
	
	public void takeDamage(double damage){
		double efectiveDamage = damage-damageReduction;
		if(efectiveDamage<1){
			if(Math.random()<1.0/Math.pow(2, 2-efectiveDamage))
				healthRemaining--;
		}
		else
			healthRemaining-=efectiveDamage;
		
		if(healthRemaining<0){
			healthRemaining=0;
			death();
		}
	}
	
	private void death(){
		System.out.println("RIP");
	}
	
	public void draw(Graphics2D g2d){
		double f=faceing;
		AffineTransform t = new AffineTransform();
		path = new Path2D.Double();
		Rectangle r = new Rectangle((int)(posX-sizeX/2),(int)(posY-sizeY/2),(int)sizeX,(int)sizeY);
		path.append(r, false);
		r = new Rectangle((int)(posX-sizeX/4),(int)(posY-sizeY*3/4),(int)sizeX/2,(int)sizeY/4);
		path.append(r, false);
		t.translate(-1*MainThread.viewX,-1*MainThread.viewY);
		t.scale(MainThread.scale, MainThread.scale);
		t.rotate(f,posX,posY);
		path.transform(t);
		
		g2d.setColor(new Color(210,210,0,128));
		g2d.fillArc((int)((posX*MainThread.scale-MainThread.viewX)-50),(int)((posY*MainThread.scale-MainThread.viewY)-50), 100, 100, 180, (int)(180*powerRemaining/powerCap));
		g2d.setColor(new Color(255,255,255,128));
		g2d.fillArc((int)((posX*MainThread.scale-MainThread.viewX)-40),(int)((posY*MainThread.scale-MainThread.viewY)-40), 80, 80, 180, (int)(180*powerRemaining/powerCap));
		g2d.setColor(new Color(0,150,20,128));
		g2d.fillArc((int)((posX*MainThread.scale-MainThread.viewX)-50),(int)((posY*MainThread.scale-MainThread.viewY)-50), 100, 100, 180, (int)(-180*healthRemaining/health));
		g2d.setColor(new Color(255,255,255,128));
		g2d.fillArc((int)((posX*MainThread.scale-MainThread.viewX)-40),(int)((posY*MainThread.scale-MainThread.viewY)-40), 80, 80, 180, (int)(-180*healthRemaining/health));
		g2d.setColor(Color.BLACK);
		g2d.drawString((int)healthRemaining+ " / " + (int)health, (int)((posX*MainThread.scale-MainThread.viewX)),(int)((posY*MainThread.scale-MainThread.viewY)-50));
		g2d.drawString(Math.round(powerRemaining)+ " / " + (int)powerCap, (int)((posX*MainThread.scale-MainThread.viewX)),(int)((posY*MainThread.scale-MainThread.viewY)+62));
		for(int i=0; i<modules.size(); i++){
			modules.get(i).draw(g2d);
		}
		g2d.setColor(Color.BLACK);
		g2d.fill(path);
	}
	
	public void drawHitboxes(Graphics2D g2d){
		for(int i=0; i<hitboxes.size(); i++){
			hitboxes.get(i).draw(g2d, posX, posY, getFaceing());
		}
	}
	
	public double getVelX(){
		return velX;
	}
	
	public double getVelY(){
		return velY;
	}
	
	public static double getAngle(double x1, double y1, double x2, double y2){
		Double theta = Math.atan(-1*(x1-x2)/(y1-y2));
		if(y1>=y2)
			theta+=Math.PI;
		return (theta+Math.PI*2)%(Math.PI*2);
	}
}
