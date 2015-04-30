package ui.shipconstruction;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import utility.Vec2;




/**
 * Used for all mouse listeners
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class MouseInput implements MouseListener,MouseMotionListener,MouseWheelListener {
	public static boolean mouseDown = false;
	public static Vec2 mousePos = new Vec2(0,0);
	public static Vec2 mousePressLocation = new Vec2(0,0);
	
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	
	/**
	 * Activates when a mouse button is pressed
	 */
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==e.BUTTON1)
			mouseDown = true;
		if(ConstructionCanvas.e.getX())
	}

	/**
	 * Activates when a mouse button is released
	 */
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==e.BUTTON1)
			mouseDown = false;
		if(ConstructionCanvas.activeModule==null){
			for(int i=0;i<ConstructionCanvas.moduleGroups.size();i++){
				if(ConstructionCanvas.moduleGroups.get(i).isMousedOver()){
					for(int j=0; j<ConstructionCanvas.moduleGroups.get(i).subTypes.size(); j++){
						if(ConstructionCanvas.moduleGroups.get(i).subTypes.get(j).isMousedOver())
							ConstructionCanvas.activeModule = ConstructionCanvas.moduleGroups.get(i).subTypes.get(j);
					}
				}
			}
		}
		else{
			
		}
	}

	/**
	 * Stores the position of the pointer when the 
	 * mouse is moved with a button down
	 */
	public void mouseDragged(MouseEvent e) {
		mousePos.setX(e.getX()-5);
		mousePos.setY(e.getY()-25);
	}

	/**
	 * Stores the position of the pointer when the 
	 * mouse is moved without a button down
	 */
	public void mouseMoved(MouseEvent e) {
		mousePos.setX(e.getX()-5);
		mousePos.setY(e.getY()-25);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
	}

}
