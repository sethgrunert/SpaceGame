package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	
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
		MainThread.map.draw((int)MainThread.viewX, (int)MainThread.viewY, g2d);
		MainThread.playerShip.draw(g2d);
		for(int i=0; i<MainThread.enemies.size(); i++){
			MainThread.enemies.get(i).draw(g2d);
		}
		drawDebug(g2d);
		MainThread.playerShip.drawHitboxes(g2d);
		repaint();
	}
	public void drawDebug(Graphics2D g2d){
		g2d.setColor(Color.BLUE);
		g2d.drawLine((int)((MainThread.playerShip.getPosX()*MainThread.scale-MainThread.viewX)),(int)((MainThread.playerShip.getPosY()*MainThread.scale-MainThread.viewY)), MainThread.mouseX, MainThread.mouseY);
		g2d.setColor(Color.ORANGE);
		g2d.setStroke ( new BasicStroke ( 5.0f, BasicStroke.JOIN_BEVEL, BasicStroke.JOIN_MITER ) );
		g2d.drawLine((int)((MainThread.playerShip.getPosX()*MainThread.scale-MainThread.viewX)),(int)((MainThread.playerShip.getPosY()*MainThread.scale-MainThread.viewY)), (int)(MainThread.playerShip.getVelX()*10+(MainThread.playerShip.getPosX()*MainThread.scale-MainThread.viewX)),(int)(MainThread.playerShip.getVelY()*-10+(MainThread.playerShip.getPosY()*MainThread.scale-MainThread.viewY)));
	}
}
