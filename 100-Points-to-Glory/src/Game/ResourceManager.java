package Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class ResourceManager {

	Image sprites;
	Image background;
	Music nyan;
	Sound laser;
	Sound xplode;
	Sound levelUp;

	public ResourceManager() {
		try {
			sprites = new Image("data/sprites.png");
			background = new Image("data/background.png");
			nyan = new Music("data/nyc.ogg", false);
			laser = new Sound("data/Laser.wav");
			xplode = new Sound("data/Explosion.ogg");
			levelUp = new Sound("data/LevelUp.ogg");
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
	
	public Image getImage(int x, int y, int width, int height){
		return sprites.getSubImage(x, y, width, height);
	}

	public Music getNyan() {
		return nyan;
	}
	
	public Sound getLaser() {
		return laser;
	}
	
	public Sound getExplosion() {
		return xplode;
	}
	
	public Sound getLevelUp() {
		return levelUp;
	}

	public Image getBackground() {
		return background;
	}
}
