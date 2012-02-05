package game;

import org.newdawn.slick.geom.Vector2f;

public class Camera {

	private int width;
	private int height;
	private float scale;
	private Vector2f offset;
	
	public Camera(int normalWidth, int normalHeight, int screenWidth, int screenHeight) {
		this.width = normalWidth;
		this.height = normalHeight;
		this.scale = screenHeight/(float)normalHeight;
		this.offset = new Vector2f((screenWidth-this.scale*normalWidth)/(float)2, 0);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public float getScale(){
		return scale;
	}
	
	public Vector2f getOffset(){
		return offset;
	}
}
