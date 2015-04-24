package ai;

/**
 * Defines AI behavior
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public abstract class Behavior {
	static final int PASSIVE = 0;
	static final int TURRET = 1;
	static final int CHASE = 3;
	
	public Behavior(){	}
	
	/**
	 * ???
	 */
	public abstract void takeAction();
	
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
		case CHASE:
			return new ChaseBehavior();
		default:
			return null;
		}
	}
}
