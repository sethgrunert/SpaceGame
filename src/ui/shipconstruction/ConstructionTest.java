package ui.shipconstruction;

import game.PlayerShip;

public class ConstructionTest {
	public static void main(String[] args){
		ConstructionWindow window = new ConstructionWindow(new PlayerShip(3,4, 50, 100, 0, 0));
	}
}
