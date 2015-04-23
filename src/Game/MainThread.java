package Game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import modules.*;

public class MainThread implements Runnable{
	private static int keyboardX = 0, keyboardY=0;
	public static int mouseX=0,mouseY=0;
	public static int windowX=800,windowY=600;
	public static double boundingBox=.5; 
	public static double viewX=0,viewY=0;
	public static double scale =.5;
	public static Map map = new Map(100,100,50,0);
	public static int frame = 0;
	
	public static Ship playerShip = new Ship(12,50,100,map.getSizeX()*map.getTileSize()/8,map.getSizeY()*map.getTileSize()/8);
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
		enemies.add(new Ship(12,50,100,100,100));
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
				enemies.get(0).addModule(new BasicEngine(4));
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
				playerShip.setFaceing(Ship.getAngle((double)mouseX,(double)mouseY,(playerShip.getPosX()*scale-viewX),(playerShip.getPosY()*scale-viewY)));
				playerShip.setAccel(keyboardX, keyboardY);
				if(!playerShip.move(MouseInput.mouseDown))
					playerShip =null;
				for(int i=0; i<enemies.size(); i++){
					if(!enemies.get(i).move(false))
						enemies.remove(i);
				}
				
				//scrolling
				if(playerShip.getPosX()>((.5+boundingBox/2)*windowX+viewX)/scale){
					viewX+=(playerShip.getPosX()-((.5+boundingBox/2)*windowX+viewX)/scale)*scale;
				}
				if(playerShip.getPosX()<((.5-boundingBox/2)*windowX+viewX)/scale){
					viewX+=(playerShip.getPosX()-((.5-boundingBox/2)*windowX+viewX)/scale)*scale;
				}
				if(playerShip.getPosY()>((.5+boundingBox/2)*windowY+viewY)/scale){
					viewY+=(playerShip.getPosY()-((.5+boundingBox/2)*windowY+viewY)/scale)*scale;
				}
				if(playerShip.getPosY()<((.5-boundingBox/2)*windowY+viewY)/scale){
					viewY+=(playerShip.getPosY()-((.5-boundingBox/2)*windowY+viewY)/scale)*scale;
				}
				
				Thread.sleep(1000/60);
				frame++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

