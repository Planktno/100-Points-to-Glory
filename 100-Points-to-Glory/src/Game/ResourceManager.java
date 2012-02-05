package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class ResourceManager {

	Image sprites;
	Image background;
	Music nyan;

	public ResourceManager() {
		try {
			sprites = new Image("data/sprites.png");
			background = new Image("data/background.png");
			nyan= new Music("data/nyc.ogg", false);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public Image getImage32(int x, int y) {
		return sprites.getSubImage(x * 32, y * 32, 32, 32);
	}

	public Image getImage04(int x, int y) {
		return sprites.getSubImage(x * 4, y * 4, 4, 4);
	}

	public Music getNyan() {
		return nyan;
	}

	public Image getBackground() {
		return background;
	}
}
