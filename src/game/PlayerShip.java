/**
 * 
 */
package game;

import ui.MouseInput;

/**
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class PlayerShip extends Ship{

	/**
	 * Ship constructor
	 * @param numModules total space for modules
	 * @param sizeX width of the ships model in pixels
	 * @param sizeY length of the ships sprite in pixels
	 * @param posX initial horizontal position of ship (pixels from the origin)
	 * @param posY initial vertical position of ship (pixels from the origin)
	 */
	PlayerShip(int numModules, double sizeX, double sizeY, int posX, int posY) {
		super(numModules, sizeX, sizeY, posX, posY);
		faction = PLAYER;
	}

	/* (non-Javadoc)
	 * @see game.Ship#takeTurn()
	 */
	@Override
	public void takeTurn() {
		setFaceing(MouseInput.mousePos.getAngle(pos,false));
		setAccel(MainThread.keyboardX,MainThread.keyboardY);
		move();
		updateModules(MouseInput.mouseDown);
	}
	
}
