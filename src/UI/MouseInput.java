package UI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import Utilities.Vec2;

import Game.MainThread;



public class MouseInput implements MouseListener,MouseMotionListener,MouseWheelListener {
	public static boolean mouseDown = false;
	public static Vec2 mousePos = new Vec2(0,0);
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==e.BUTTON1)
			mouseDown = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==e.BUTTON1)
			mouseDown = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos.setX(e.getX()-5);
		mousePos.setY(e.getY()-25);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.setX(e.getX()-5);
		mousePos.setY(e.getY()-25);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation()<0)
			MainThread.scale/=(1+ (-.01* e.getWheelRotation()));
		else
			MainThread.scale*=(1+ (.01* e.getWheelRotation()));
		MainThread.map.changeScale(MainThread.scale);
	}

}
