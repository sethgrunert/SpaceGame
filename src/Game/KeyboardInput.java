package Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInput implements KeyListener{
	static boolean[] pressed = new boolean[256];
	
	@Override
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()]=true;
		//System.out.println(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()]=false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
