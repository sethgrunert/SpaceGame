package Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import Utilities.Vec2;

import Exceptions.OutOfPowerException;
import Exceptions.OutOfRoomException;

import modules.Module;


/**
 * @author Seth Grunert sethgrunert@ccsu.edu
 * 
 */
public class Ship {
	private Vec2 pos = null;
	private Vec2 vel = new Vec2(0,0);
	private Vec2 accel = new Vec2(0,0);
	private double faceng = 0;
	private double powerRemaining = 0;
	private double healthRemaining = 0;
	private int remainingModules =0;
	private Path2D.Double path = null;
	private ArrayList<Module> modules = new ArrayList<Module>();
	private double shieldRemaining = 10;
	
	private Vec2 size = null;
	private double accelMax = 0;
	private double rotationSpeed = 0;
	private int numModules;
	private ArrayList<HitBox> hitboxes = new ArrayList<HitBox>();
	private double mass = 0;
	private double health = 0;
	private double damageReduction = 0;
	private double thrust = 0;
	private double powerCap = 0;
	private double powerRate = 0;
	private double shieldCap = 10;
	
	/**
	 * Ship constructor
	 * @param numModules total space for modules
	 * @param sizeX width of the ships model in pixels
	 * @param sizeY length of the ships sprite in pixels
	 * @param posX initial horizontal position of ship (pixels from the origin)
	 * @param posY initial vertical position of ship (pixels from the origin)
	 */
	Ship(int numModules,double sizeX,double sizeY,int posX, int posY){
		this.numModules = numModules;
		remainingModules = this.numModules;
		size = new Vec2(sizeX,sizeY);
		pos = new Vec2(posX,posY);
		hitboxes.add(new HitBox((int)sizeX, 0, (int)(sizeY/3)));
		hitboxes.add(new HitBox((int)sizeX, 0, 0));
		hitboxes.add(new HitBox((int)sizeX, 0, (int)(-sizeY/3)));
		hitboxes.add(new HitBox((int)(sizeX/2), 0, (int)(sizeY*2/3)));
	}
	
	/**
	 * @return an ArrayList of HitBox objects the make up the ship
	 */
	public ArrayList<HitBox> getHitboxes(){
		return hitboxes;
	}
	
	/**
	 * rotates the ship towards theta at the max rotation speed of the ship
	 * @param theta direction to rotate towards in radians
	 */
	public void setFaceing(double theta){
		boolean clockwise = true;
		if(faceng<Math.PI){
			if(theta<faceng)
				clockwise = false;
			if(theta>faceng+Math.PI)
				clockwise = false;
		}
		else{
			if(theta<=faceng)
				clockwise = false;
			if(theta<(faceng-Math.PI))
				clockwise = true;
		}
		double spin = rotationSpeed;
		if(Math.abs(faceng-theta)<rotationSpeed)
			spin = Math.abs(faceng-theta);
		
		
		if(clockwise)
			faceng+=spin;
		else
			faceng-=spin;
		
		if(faceng<0)
			faceng+=Math.PI*2;
		else
			faceng=faceng%(Math.PI*2);
		
	}
	
	/**
	 * @return facing of the ship in radians
	 */
	public double getFacing(){
		return Math.PI-faceng;
	}
	
	/**
	 * @return horizontal position of the ship in pixels from the origin
	 */
	public double getPosX(){
		return pos.getX();
	}
	
	/**
	 * @return vertical position of the ship in pixels from the origin
	 */
	public double getPosY(){
		return pos.getY();
	}
	
	/**
	 * Changes the direction of thrust based on 8 directional input
	 * @param x x-control axis
	 * @param y y-control axis
	 */
	public void setAccel(int x, int y){
		if(y==1)
			accel.setY(accelMax);
		if(y==-1)
			accel.setY(-.35*accelMax);
		if(y==0)
			accel.setY(0);
		if(x==1)
			accel.setX(.5*accelMax);
		if(x==-1)
			accel.setX(-.5*accelMax);
		if(x==0)
			accel.setX(0);
	}
	
	/**
	 * Attempts to add a given module to the ship
	 * Updates various ship statistics based on the properties of the given mod
	 * @param newMod module to add
	 * @throws OutOfRoomException ship doesn't have enough space to fit the chosen module
	 * @throws OutOfPowerException adding the module would lead to a negative power balance
	 */
	public void addModule(Module newMod) throws OutOfRoomException,OutOfPowerException{
		if(remainingModules-newMod.getSize()<0)
			throw new OutOfRoomException();
		else if(powerRate+newMod.getPower()<0)
			throw new OutOfPowerException();
		else{
			modules.add(newMod);
			remainingModules-=newMod.getSize();
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
	}
	
	/**
	 * Moves the ship one frame(1/60s) forward
	 * @param fireing true if the ship should attempt to fire weapons
	 * @return true if ship is dead, false if ship still has health left
	 */
	public boolean move(boolean fireing){
		vel.setX(vel.getX()+accel.getX()*Math.cos(faceng)+accel.getY()*Math.sin(faceng));
		vel.setY(vel.getY()+accel.getX()*Math.sin(faceng)+accel.getY()*Math.cos(faceng));
		vel.setX(vel.getX()*.99);
		vel.setY(vel.getY()*.99);
		if(Math.abs(vel.getX())<(accelMax/10))
			vel.setX(0);
		if(Math.abs(vel.getY())<(accelMax/10))
			vel.setY(0);
		pos.setX(pos.getX()+vel.getX());
		pos.setY(pos.getY()-vel.getY());
		
		if(pos.getX()>MainThread.map.getSizeX()*MainThread.map.getTileSize())
			pos.setX(MainThread.map.getSizeX()*MainThread.map.getTileSize());
		if(pos.getX()<0)
			pos.setX(0);
		if(pos.getY()>MainThread.map.getSizeY()*MainThread.map.getTileSize())
			pos.setY(MainThread.map.getSizeY()*MainThread.map.getTileSize());
		if(pos.getY()<0)
			pos.setY(0);
		
		updateModules(fireing);
		return (healthRemaining!=0);
	}
	
	/**
	 * called from the move method, updates all modules one frame(1/60s) forward
	 * @param fireing true if the ship should attempt to fire weapons
	 */
	private void updateModules(boolean fireing){
		for(int i=0; i<modules.size(); i++){
			powerRemaining+=modules.get(i).update(getFacing(), pos.getX(), pos.getY(),fireing,powerRemaining);
			if(powerRemaining>powerCap)
				powerRemaining=powerCap;
		}
		
		shieldRemaining=shieldRemaining*1.01+shieldCap*.001;
		if(shieldRemaining>shieldCap)
			shieldRemaining = shieldCap;
	}
	
	/**
	 * Subtracts remaining health based on the incoming damage and the
	 * damage reduction of the ship
	 * If the damage is less then 1 there is a chance the ship will take 1 damage
	 * @param damage amount of incoming damage
	 */
	public void takeDamage(double damage){
		System.out.println(shieldRemaining + " " );
		shieldRemaining-=damage;
		double efectiveDamage = 0;
		if(shieldRemaining<0){
			efectiveDamage=-1*shieldRemaining-damageReduction;
			shieldRemaining=0;
		}
		else
			return;
		
		if(efectiveDamage<1){
			if(Math.random()<1.0/Math.pow(2, 2-efectiveDamage))
				efectiveDamage=1;
			else
				efectiveDamage=0;
		}
		healthRemaining-=efectiveDamage;
		if(healthRemaining<0){
			healthRemaining=0;
			death();
		}
	}
	
	/**
	 * placeholder for ship death animation
	 */
	private void death(){
		System.out.println("RIP");
	}
	
	/**
	 * draws the ship and hud (power/hp/shields)
	 * @param g2d Graphics to draw to
	 */
	public void draw(Graphics2D g2d){
		double f=faceng;
		AffineTransform t = new AffineTransform();
		path = new Path2D.Double();
		Rectangle r = new Rectangle((int)(pos.getX()-size.getX()/2),(int)(pos.getY()-size.getY()/2),(int)size.getX(),(int)size.getY());
		path.append(r, false);
		r = new Rectangle((int)(pos.getX()-size.getX()/4),(int)(pos.getY()-size.getY()*3/4),(int)size.getX()/2,(int)size.getY()/4);
		path.append(r, false);
		t.translate(-1*MainThread.view.getX(),-1*MainThread.view.getY());
		t.scale(MainThread.scale, MainThread.scale);
		t.rotate(f,pos.getX(),pos.getY());
		path.transform(t);
		
		//power bar
		g2d.setColor(new Color(210,210,0,128));
		g2d.fillArc((int)pos.screenX()-50,(int)pos.screenY()-50, 100, 100, 180, (int)(180*powerRemaining/powerCap));
		g2d.setColor(new Color(255,255,255,128));
		g2d.fillArc((int)pos.screenX()-40,(int)pos.screenY()-40, 80, 80, 180, (int)(180*powerRemaining/powerCap));
		//shield bar
		g2d.setColor(new Color(0,255,255,150));
		g2d.fillArc((int)pos.screenX()-50,(int)pos.screenY()-50, 100, 100, 180, (int)(-180*shieldRemaining/shieldCap));
		//hp bar
		g2d.setColor(new Color(0,128,0,128));
		g2d.fillArc((int)pos.screenX()-40,(int)pos.screenY()-40, 80, 80, 180, (int)(-180*healthRemaining/health));
		
		
		g2d.setColor(Color.BLACK);
		g2d.drawString((int)healthRemaining+ "(" +(int)shieldRemaining + ") / " + (int)(health+shieldCap), (int)pos.screenX(),(int)pos.screenY()-50);
		g2d.drawString(Math.round(powerRemaining)+ " / " + (int)powerCap, (int)pos.screenX(),(int)pos.screenY()+62);
		for(int i=0; i<modules.size(); i++){
			modules.get(i).draw(g2d);
		}
		g2d.setColor(Color.BLACK);
		g2d.fill(path);
	}
	
	/**
	 * Draws a visual representation of the ships hitbox
	 * @param g2d Graphics to draw to
	 */
	public void drawHitboxes(Graphics2D g2d){
		for(int i=0; i<hitboxes.size(); i++){
			hitboxes.get(i).draw(g2d, pos.getX(), pos.getY(), getFacing());
		}
	}
	
	/**
	 * @return the x component of the ships velocity
	 */
	public double getVelX(){
		return vel.getX();
	}
	
	/**
	 * @return the y component of the ships velocity
	 */
	public double getVelY(){
		return vel.getY();
	}
	
	public Vec2 getPos(){
		return pos;
	}
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getAngle(double x1, double y1, double x2, double y2){
		Double theta = Math.atan(-1*(x1-x2)/(y1-y2));
		if(y1>=y2)
			theta+=Math.PI;
		return (theta+Math.PI*2)%(Math.PI*2);
	}
}
