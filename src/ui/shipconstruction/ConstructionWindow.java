package ui.shipconstruction;

import game.MainThread;
import game.Ship;

import javax.swing.JFrame;

import ui.Canvas;

public class ConstructionWindow extends JFrame{
	public ConstructionWindow(Ship s){
		super();
		setVisible(true);
		setResizable(false);
		setSize(300, 400);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		add(new ConstructionCanvas(s));
		addMouseListener(new MouseInput());
		addMouseMotionListener(new MouseInput());
	}
}
