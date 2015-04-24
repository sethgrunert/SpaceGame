package ui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class KeyboardInput implements KeyListener{
	public static boolean[] pressed = new boolean[256];
	
	/**
	 * Activates when a key is pressed, stores in the pressed array
	 */
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()]=true;
	}

	/**
	 * Activates when a key is released, stores in the pressed array
	 */
	public void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
