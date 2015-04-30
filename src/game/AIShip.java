package game;

import ui.MouseInput;
import ai.Behavior;

/**
 * AI controlled ship
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class AIShip extends Ship{
	Behavior ai = null;
	
	/**
	 * Ship constructor
	 * @param numModules total space for modules
	 * @param sizeX width of the ships model in pixels
	 * @param sizeY length of the ships sprite in pixels
	 * @param posX initial horizontal position of ship (pixels from the origin)
	 * @param posY initial vertical position of ship (pixels from the origin)
	 * @param behavior AI the ship should follow
	 */
	AIShip(int numModulesX, int numModulesY,double sizeX, double sizeY, int posX, int posY, int behavior) {
		super(numModulesX,numModulesY, sizeX, sizeY, posX, posY);
		ai = Behavior.getType(behavior);
		faction = ENEMY;
	}




	/* (non-Javadoc)
	 * @see game.Ship#takeTurn()
	 */
	@Override
	public void takeTurn() {
		ai.scan(pos);
		setFaceing(ai.getNewFaceing(facing));
		setAccel(ai.getAccelX(),ai.getAccelY());
		move();
		updateModules(ai.getFireing());
	}
}
