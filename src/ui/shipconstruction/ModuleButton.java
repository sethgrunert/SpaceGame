package ui.shipconstruction;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.xml.internal.ws.api.server.Module;

public class ModuleButton {
	//Module module = null;
	Color color = null;
	double x = 0;
	double y = 0;
	double size = 0;
	boolean mouseOver = false;
	String name ="";
	
	ModuleButton(String s,Color color,double x, double y,double size){
		this.color=color;
		this.x=x;
		this.y=y;
		this.size=size;
		name = s;
	}
	
	public void draw(Graphics2D g2d){
		g2d.setColor(color);
		g2d.fillRect((int)x, (int)y, (int)size, (int)size);
		if(mouseOver){
			g2d.setColor(Color.BLACK);
			g2d.drawString(name, (int)(x+size+5), (int)(y+size/2+5));
		}
	}
	
	public void checkMouseOver(){
		if(MouseInput.mousePos.getX()>= x && MouseInput.mousePos.getX()<= (x+size)){
			if(MouseInput.mousePos.getY()>= y && MouseInput.mousePos.getY()<= (y+size)){
				mouseOver=true;
				return;
			}
		}
		mouseOver=false;
	}
	
	public boolean isMousedOver(){
		return mouseOver;
	}

}
