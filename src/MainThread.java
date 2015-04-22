import java.awt.event.KeyEvent;

public class MainThread implements Runnable{
	public static Ship playerShip = new Ship(10,100,50,100);
	private static int keyboardX = 0, keyboardY=0;
	public static int mouseX=0,mouseY=0;
	public static int boundingBoxX=300,boundingBoxY=150; 
	public static double viewX=0,viewY=0;
	public static Map map = new Map(100,100,25,0);
	public static double scale =.5;
	
	public static void main(String[] args) 
	{
		Thread t1 = new Thread(new MainThread());
		t1.start();
	}
	
	MainThread(){}

	@Override
	public void run() {
		GameWindow window = new GameWindow();
		try {
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
				playerShip.move();
				//scrolling
				if(playerShip.getPosX()>(boundingBoxX+(800-boundingBoxX)/2+viewX*scale)){
					viewX+=(playerShip.getPosX()-(boundingBoxX+(800-boundingBoxX)/2+viewX*scale));
				}
				if(playerShip.getPosX()<((800-boundingBoxX)/2+viewX)){
					viewX+=(playerShip.getPosX()-((800-boundingBoxX)/2+viewX));
				}
				if(playerShip.getPosY()>(boundingBoxY+(600-boundingBoxY)/2+viewY)){
					viewY+=(playerShip.getPosY()-(boundingBoxY+(600-boundingBoxY)/2+viewY));
				}
				if(playerShip.getPosY()<((600-boundingBoxY)/2+viewY)){
					viewY+=(playerShip.getPosY()-((600-boundingBoxY)/2+viewY));
				}
				System.out.println(viewX + " " + viewY);
				Thread.sleep(1000/60);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

