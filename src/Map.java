import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;


public class Map {
	private int sizeX=0, sizeY=0,tileSize=10;
	private BufferedImage background;
	private Image resizedImage;
	
	Map(int sizeX,int sizeY,int tileSize,int background){
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.tileSize=tileSize;
		this.background = new BufferedImage(sizeX*tileSize,sizeY*tileSize,BufferedImage.TYPE_INT_ARGB);
		if(background==0)
			makeGrid();
		 resizedImage = this.background.getScaledInstance((int)(sizeX*tileSize),(int)(sizeY*tileSize), BufferedImage.SCALE_FAST);
	}
	public void changeScale(double scale){
		 resizedImage = this.background.getScaledInstance((int)(sizeX*tileSize*scale),(int)(sizeY*tileSize*scale), BufferedImage.SCALE_AREA_AVERAGING);
	}
	private void makeGrid(){
		Graphics g = background.getGraphics();
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				g.setColor(new Color(255-128*i/4,255-128*j/4,255));
				g.fillRect(sizeX/4*tileSize*i, sizeX/4*tileSize*j, sizeX/4*tileSize, sizeX/4*tileSize);
			}
		}
		g.setColor(Color.BLACK);
		for(int i=0; i<sizeX; i++){
			for(int j=0; j<sizeY; j++){
				g.drawRect(i*tileSize, j*tileSize, tileSize, tileSize);
			}
		}
	}
	public void draw(int x,int y,Graphics2D g2d){
		g2d.drawImage(resizedImage, 0, 0, 800, 600, x,y,(int)((x+800)),(int)((y+600)),null);	
	}
	public int getSizeX(){
		return sizeX;
	}
	public int getSizeY(){
		return sizeY;
	}
	public int getTileSize(){
		return tileSize;
	}
}
