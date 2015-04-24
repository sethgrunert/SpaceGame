package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ai.Behavior;

import exception.OutOfPowerException;
import exception.OutOfRoomException;

import ui.GameWindow;
import ui.KeyboardInput;
import ui.MouseInput;
import utility.Vec2;

import module.*;
import module.armor.SteelArmor;
import module.engine.BasicEngine;
import module.powerplant.NuclearReactor;
import module.weapon.*;
/**
 * 
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class MainThread implements Runnable{
	public static int keyboardX = 0, keyboardY=0;
	public static Vec2 windowSize = new Vec2(1000,800);
	public static double boundingBox=.5; 
	public static Vec2 view = new Vec2(0,0);
	public static double scale =.5;
	public static Map map = new Map(100,100,50,Map.GRID);
	public static int frame = 0;
	public static Ship playerShip = new PlayerShip(12,50,100,map.getSizeX()*map.getTileSize()/8,map.getSizeY()*map.getTileSize()/8);
	public static ArrayList<Ship> enemies = new ArrayList<Ship>();

	public static void main(String[] args) 
	{
		Thread t1 = new Thread(new MainThread());
		t1.start();
	}
	
	public MainThread(){}

	@Override
	public void run() {
		GameWindow window = new GameWindow();
		enemies.add(new AIShip(12,50,100,100,100,Behavior.TURRET));
		try {
			try {
				playerShip.addModule(new BasicLaser(1,-19,25,Math.PI/50));
				playerShip.addModule(new RailGun(2,0,50,0));
				playerShip.addModule(new BasicLaser(1,19,25,-Math.PI/50));
				playerShip.addModule(new NuclearReactor(2));
				playerShip.addModule(new BasicEngine(2));
				playerShip.addModule(new BasicEngine(2));
				playerShip.addModule(new SteelArmor(2));
				enemies.get(0).addModule(new SteelArmor(4));
				enemies.get(0).addModule(new NuclearReactor(4));
				enemies.get(0).addModule(new BasicEngine(2));
				enemies.get(0).addModule(new BasicLaser(2,0,50,0));
			} catch (OutOfRoomException e) {
				System.out.println("not enough room for this module");
			} catch (OutOfPowerException e) {
				System.out.println("not enough power for this module");
			}
			while(true){
				keyboardX=0;
				keyboardY=0;
				if(KeyboardInput.pressed[KeyEvent.VK_D])
					keyboardX+=1;
				if(KeyboardInput.pressed[KeyEvent.VK_A])
					keyboardX-=1;
				if(KeyboardInput.pressed[KeyEvent.VK_W])
					keyboardY+=1;
				if(KeyboardInput.pressed[KeyEvent.VK_S])
					keyboardY-=1;
				
				if(playerShip!=null)
					playerShip.takeTurn();
				if(playerShip.isDead())
					playerShip=null;
				
				for(int i=0; i<enemies.size(); i++){
					enemies.get(i).takeTurn();
					if(enemies.get(i).isDead())
						enemies.remove(i);
				}
				
				
				//scrolling
				if(playerShip.getPosX()>((.5+boundingBox/2)*windowSize.getX()+view.getX())/scale)
					view.setX(view.getX()+(playerShip.getPosX()-((.5+boundingBox/2)*windowSize.getX()+view.getX())/scale)*scale);
				if(playerShip.getPosX()<((.5-boundingBox/2)*windowSize.getX()+view.getX())/scale)
					view.setX(view.getX()+(playerShip.getPosX()-((.5-boundingBox/2)*windowSize.getX()+view.getX())/scale)*scale);
				if(playerShip.getPosY()>((.5+boundingBox/2)*windowSize.getY()+view.getY())/scale)
					view.setY(view.getY()+(playerShip.getPosY()-((.5+boundingBox/2)*windowSize.getY()+view.getY())/scale)*scale);
				if(playerShip.getPosY()<((.5-boundingBox/2)*windowSize.getY()+view.getY())/scale)
					view.setY(view.getY()+(playerShip.getPosY()-((.5-boundingBox/2)*windowSize.getY()+view.getY())/scale)*scale);
				
				/*if(frame%60==0){
					playerShip.takeDamage(5);
				}*/
				Thread.sleep(1000/60);
				frame++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

