package ai;

import utility.Vec2;

/**
 * Defines AI behavior
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public abstract class Behavior {
	public static final int PASSIVE = 0;
	public static final int TURRET = 1;
	public static final int CHASE = 3;
	
	public Behavior(){	}
	
	/**
	 * @param behavior integer constant representing a type of behavior
	 * @return an instance of the type of behavior defined by the constant
	 */
	public static Behavior getType(int behavior){
		switch(behavior){
		case PASSIVE:
			return new PasiveBehavior();
		case TURRET:
			return new TurretBehavior();
		/*case CHASE:
			return new ChaseBehavior();*/
		default:
			return null;
		}
	}

	public abstract void scan(Vec2 pos);
	
	/**
	 * @return direction the ship should try to face
	 */
	public abstract double getNewFaceing(double currentFaceing);

	/**
	 * @return 1 if the ship should accelerate left
	 * -1 if the ship should accelerate right
	 * 0 if the ship shouldn't move horizontally
	 */
	public abstract int getAccelX();
	
	/**
	 * @return 1 if the ship should accelerate forwards
	 * -1 if the ship should accelerate backwards
	 * 0 if the ship shouldn't move vertically
	 */
	public abstract int getAccelY();

	/**
	 * @return true if the ship should be fireing
	 */
	public abstract boolean getFireing();
}
