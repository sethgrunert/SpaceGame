package ui;

import game.MainThread;

import javax.swing.JFrame;



/**
 * 
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class GameWindow extends JFrame {
	/**
	 * Constructor
	 */
	public GameWindow(){
		super();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize((int)MainThread.windowSize.getX(), (int)MainThread.windowSize.getY());
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.add(new Canvas());
		addKeyListener(new KeyboardInput());
		addMouseListener(new MouseInput());
		addMouseMotionListener(new MouseInput());
		addMouseWheelListener(new MouseInput());
	}
}
