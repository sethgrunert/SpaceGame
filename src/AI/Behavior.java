package AI;


public class Behavior {
	static final int PASSIVE = 0;
	static final int TURRET = 1;
	static final int CHASE = 3;
	
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
