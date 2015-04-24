package Game;

import AI.Behavior;

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
	AIShip(int numModules, double sizeX, double sizeY, int posX, int posY, int behavior) {
		super(numModules, sizeX, sizeY, posX, posY);
		ai = Behavior.getType(behavior);
	}

}
