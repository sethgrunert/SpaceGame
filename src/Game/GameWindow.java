package Game;

import javax.swing.JFrame;


public class GameWindow extends JFrame {
	GameWindow(){
		super();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.add(new Canvas());
		addKeyListener(new KeyboardInput());
		addMouseListener(new MouseInput());
		addMouseMotionListener(new MouseInput());
		addMouseWheelListener(new MouseInput());
	}
}
