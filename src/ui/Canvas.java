package ui;

import game.MainThread;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;

import javax.swing.JPanel;


/**
 * 
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class Canvas extends JPanel {
	/**
	 * Constructor
	 */
	public Canvas(){
		setBackground(Color.WHITE);		
	}
	
	/**
	 * Draws everything to the screen
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		MainThread.map.draw((int)MainThread.view.getX(), (int)MainThread.view.getY(), g2d);
		MainThread.playerShip.draw(g2d);
		for(int i=0; i<MainThread.enemies.size(); i++){
			System.out.println(i);
			MainThread.enemies.get(i).draw(g2d);
		}
		drawDebug(g2d);
		MainThread.playerShip.drawHitboxes(g2d);
		repaint();
	}
	
	/**
	 * Draws debug information to the screen
	 * @param g2d graphics to draw to
	 */
	public void drawDebug(Graphics2D g2d){
		g2d.setColor(Color.BLUE);
		g2d.drawLine((int)MainThread.playerShip.getPos().screenX(),(int)MainThread.playerShip.getPos().screenY(), (int)MouseInput.mousePos.getX(), (int)MouseInput.mousePos.getY());
		g2d.setColor(Color.ORANGE);
		g2d.setStroke ( new BasicStroke ( 5.0f, BasicStroke.JOIN_BEVEL, BasicStroke.JOIN_MITER ) );
		g2d.drawLine((int)MainThread.playerShip.getPos().screenX(),(int)MainThread.playerShip.getPos().screenY(),
		(int)(MainThread.playerShip.getVelX()*10+(int)MainThread.playerShip.getPos().screenX()),(int)(MainThread.playerShip.getVelY()*-10+(int)MainThread.playerShip.getPos().screenY()));
	}
}
