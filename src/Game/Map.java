package Game;
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


/**
 * 
 * @author Seth Grunert sethgrunert@my.ccsu.edu
 *
 */
public class Map {
	private int sizeX=0, sizeY=0,tileSize=0;
	private BufferedImage background;
	private Image resizedImage;
	static final int GRID = 0;
	
	/**
	 * Map constructor
	 * @param sizeX number of tiles in the horizontal direction
	 * @param sizeY number of tiles in the vertical direction
	 * @param tileSize size of a tile in pixels (tiles must be square)
	 * @param background integer constant representing a background
	 */
	Map(int sizeX,int sizeY,int tileSize,int background){
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.tileSize=tileSize;
		this.background = new BufferedImage(sizeX*tileSize,sizeY*tileSize,BufferedImage.TYPE_INT_ARGB);
		if(background==GRID)
			makeGrid();
		resizedImage = this.background.getScaledInstance((int)(sizeX*tileSize*MainThread.scale),(int)(sizeY*tileSize*MainThread.scale), BufferedImage.SCALE_AREA_AVERAGING);
	}
	
	/**
	 * zooms the backround image(way to slow right now)
	 * @param scale zoom factor (.5 = half size)
	 */
	public void changeScale(double scale){
		 resizedImage = this.background.getScaledInstance((int)(sizeX*tileSize*scale),(int)(sizeY*tileSize*scale), BufferedImage.SCALE_AREA_AVERAGING);
	}
	
	/**
	 * creates a grid to test with
	 */
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
	
	/**
	 * draws the map to the screen
	 * @param x offset from the origin
	 * @param y offset from the origin
	 * @param g2d graphics to draw to
	 */
	public void draw(int x,int y,Graphics2D g2d){
		g2d.drawImage(resizedImage, 0, 0, (int)MainThread.windowSize.getX(), (int)MainThread.windowSize.getY(), x,y,(int)(x+MainThread.windowSize.getX()),(int)(y+MainThread.windowSize.getY()),null);	
	}
	
	/**
	 * @return number of tiles horizontally
	 */
	public int getSizeX(){
		return sizeX;
	}
	
	/**
	 * @return number of tiles vertically
	 */
	public int getSizeY(){
		return sizeY;
	}
	
	/**
	 * @return number of pixels per tile
	 */
	public int getTileSize(){
		return tileSize;
	}
}
