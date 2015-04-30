package ui.shipconstruction;

import game.Ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ConstructionCanvas extends JPanel{
	Ship ship = null;
	double tileSize = 0;
	public static ArrayList<ModuleGroupButton> moduleGroups = new ArrayList<ModuleGroupButton>();
	public static ModuleButton activeModule;
	public static Color[][] moduleMatrix;
	public ConstructionCanvas(Ship s){
		ship = s;
		setBackground(Color.WHITE);		
		if(ship.getNumModulesX()>ship.getNumModulesY())
			tileSize = 250/ship.getNumModulesX();
		else
			tileSize = 250/ship.getNumModulesY();
		moduleMatrix=new Color[ship.getNumModulesX()][ship.getNumModulesY()];
		for(int i=0; i<ship.getNumModulesX();i++){
			for(int j=0; j<ship.getNumModulesY();j++){
				moduleMatrix[i][j]=Color.WHITE;
			}
		}
		ModuleGroupButton temp = new ModuleGroupButton("Power Plants",Color.YELLOW,10+(30+5)*0,20,30);
		temp.addModuleType("Nuclear Reactor");
		moduleGroups.add(temp);
		temp = new ModuleGroupButton("Armor",Color.GRAY,10+(30+5)*1,20,30);
		temp.addModuleType("Steel Armor");
		moduleGroups.add(temp);
		temp = new ModuleGroupButton("Engines",Color.ORANGE,10+(30+5)*2,20,30);
		temp.addModuleType("Basic Engine");
		moduleGroups.add(temp);
		temp = new ModuleGroupButton("Weapons",Color.RED,10+(30+5)*3,20,30);
		temp.addModuleType("Railgun");
		temp.addModuleType("Basic Laser");
		moduleGroups.add(temp);
	}
	
	/**
	 * Draws everything to the screen
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g);
		if(activeModule!=null)
			System.out.println(activeModule.name);
		for(int i=0; i<ship.getNumModulesX(); i++){
			for(int j=0; j<ship.getNumModulesY(); j++){
				g.setColor(moduleMatrix[i][j]);
				g.fillRect((int)((300-tileSize*ship.getNumModulesX())/2+tileSize*i), (int)((400-tileSize*ship.getNumModulesY())/2+tileSize*j), (int)tileSize, (int)tileSize);
				g.setColor(Color.BLACK);
				g.drawRect((int)((300-tileSize*ship.getNumModulesX())/2+tileSize*i), (int)((400-tileSize*ship.getNumModulesY())/2+tileSize*j), (int)tileSize, (int)tileSize);
			}
		}
		
		for(int i=0; i<moduleGroups.size();i++){
			moduleGroups.get(i).checkMouseOver();
			moduleGroups.get(i).draw(g2d);
		}
		repaint();
	}
	
	
}
