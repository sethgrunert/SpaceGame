/**
 * 
 */
package ai;

import utility.Vec2;
import game.MainThread;
import game.Ship;

/**
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class TurretBehavior extends Behavior {
	Ship target = null;
	int range = 1000;
	Vec2 currentLocation = null;
	double currentFacing = 0;
	/* (non-Javadoc)
	 * @see ai.Behavior#getNewFaceing(double)
	 */
	@Override
	public double getNewFaceing(double currentFacing) {
		this.currentFacing =  currentFacing;
		if(target==null)
			return currentFacing;
		else
			return target.getPos().getAngle(currentLocation, true);
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#getAccelX()
	 */
	@Override
	public int getAccelX() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#getAccelY()
	 */
	@Override
	public int getAccelY() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#getFireing()
	 */
	@Override
	public boolean getFireing() {
		if(target!=null && Math.abs(target.getPos().getAngle(currentLocation, true)-currentFacing)<Math.PI/8)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#scan()
	 */
	@Override
	public void scan(Vec2 pos) {
		currentLocation=pos;
		if(MainThread.playerShip.getPos().getDistance(currentLocation)<range)
			target=MainThread.playerShip;
		else
			target=null;
			
	}

}
