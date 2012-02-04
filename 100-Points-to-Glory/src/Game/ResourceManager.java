package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ResourceManager {

	Image sprites;
	Image background;
	
	public ResourceManager(){
		try {
			sprites = new Image("data/sprites.png");
			background = new Image("data/background.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage32(int x, int y) {
		return sprites.getSubImage(x*32, y*32, 32, 32);
	}
	
	public Image getImage04(int x, int y) {
		return sprites.getSubImage(x*4, y*4, 4, 4);
	}
	
	public Image getBackground() {
		return background;
	}
}
