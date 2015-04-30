package ui.shipconstruction;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import utility.Vec2;

import com.sun.xml.internal.ws.api.server.Module;

public class ModuleGroupButton {
	Color color = null;
	double x = 0;
	double y = 0;
	double size = 0;
	boolean mouseOver = false;
	String name="";
	ArrayList<ModuleButton> subTypes= new ArrayList<ModuleButton>();
	
	ModuleGroupButton(String s,Color color,double x, double y,double size){
		this.name = s;
		this.color=color;
		this.x=x;
		this.y=y;
		this.size=size;
	}
	
	public void addModuleType(String s){
		double newY= y+(subTypes.size()+1)*(size+3);
		int newRed = Math.max(0, color.getRed()-(subTypes.size()+1)*25);
		int newGreen = Math.max(0, color.getGreen()-(subTypes.size()+1)*25);
		int newBlue = Math.max(0, color.getBlue()-(subTypes.size()+1)*25);
		Color newColor = new Color(newRed,newGreen,newBlue);
		subTypes.add(new ModuleButton(s,newColor,x,newY,size));
	}
	
	public void draw(Graphics2D g2d){
		if(mouseOver){
			g2d.setColor(new Color(200,200,200));
			g2d.fillRect((int)x-3, (int)y-3, (int)size+6, (int)(3+(size+3)*(subTypes.size()+1)));
			for(int i=0; i<subTypes.size(); i++){
				subTypes.get(i).checkMouseOver();
				subTypes.get(i).draw(g2d);
			}
		}
		g2d.setColor(color);
		g2d.fillRect((int)x, (int)y, (int)size, (int)size);
		if(mouseOver){
			g2d.setColor(Color.BLACK);
			g2d.drawString(name, (int)(x), (int)(y-5));
		}
	}
	
	public void checkMouseOver(){
		if(!mouseOver){
			if(MouseInput.mousePos.getX()>= x && MouseInput.mousePos.getX()<= (x+size)){
				if(MouseInput.mousePos.getY()>= y && MouseInput.mousePos.getY()<= (y+size)){
					mouseOver=true;
					return;
				}
			}
			mouseOver=false;
		}
		else{
			if(MouseInput.mousePos.getX()>= x && MouseInput.mousePos.getX()<= x+size){
				if(MouseInput.mousePos.getY()>= y && MouseInput.mousePos.getY()<= (y+(3+(size+3)*(subTypes.size()+1)))){
					mouseOver=true;
					return;
				}
			}
			mouseOver=false;
		}
	}
	
	public boolean isMousedOver(){
		return mouseOver;
	}
}
