package ai;

import utility.Vec2;


public class PasiveBehavior extends Behavior {

	/* (non-Javadoc)
	 * @see ai.Behavior#getNewFaceing()
	 */
	@Override
	public double getNewFaceing(double currentFaceing) {
		return currentFaceing;
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#getAccelX()
	 */
	@Override
	public int getAccelX() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#getAccelY()
	 */
	@Override
	public int getAccelY() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#getFireing()
	 */
	@Override
	public boolean getFireing() {
		return false;
	}

	/* (non-Javadoc)
	 * @see ai.Behavior#scan()
	 */
	@Override
	public void scan(Vec2 pos) {		
	}

	

}
