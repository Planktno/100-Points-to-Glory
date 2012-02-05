package Entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Game.Camera;

public class Entity {

	private String id;
	
	private Image img;
	private Vector2f pos;
	private float rotation;
	private int width, height;

	private ArrayList<Component> components;
	
	public Entity(String id) {
		this.id = id;
		
		components = new ArrayList<Component>();
		
		pos = new Vector2f(0,0);
		rotation = 0;
		height = 0;
		width = 0;
	}
	
	public void AddComponent(Component comp) {
		comp.setOwnerEntity(this);
		components.add(comp);
	}
	
	public Component getComponent(String id) {
		for(Component comp : components) {
			if(comp.getId().equalsIgnoreCase(id))
				return comp;
		}
		return null;
	}
	
	public Vector2f getPosition() {
		return pos;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public String getId() {
		return id;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Image getImage() {
		return img;
	}
	
    public void setPosition(Vector2f pos) {
    	this.pos = pos;
    }
 
    public void setRotation(float rotate) {
        rotation = rotate;
    }
    
    public void setImage(Image img) {
    	this.img = img;
    	this.width = img.getWidth();
    	this.height = img.getHeight();
    }
    
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
    	for(Component component : components) {
    		component.update(gc, sb, delta);
    	}
    }
    
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr, Camera cam) {
    	Image scaled = img.getScaledCopy(cam.getScale());
    	scaled.setRotation(rotation);
    	scaled.draw((pos.getX()-width/2)*cam.getScale(),(pos.getY()-height/2)*cam.getScale());
//    	img.setRotation(rotation);
//    	img.draw(pos.getX()-width/2, pos.getY()-height/2);
    }
	
}

