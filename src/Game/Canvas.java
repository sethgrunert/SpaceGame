package Game;

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
		repaint();
	}
}
