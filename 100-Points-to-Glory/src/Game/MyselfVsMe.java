package Game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import Entities.Entity;
import Entities.PlayerComponent;
import Entities.PlayerWeapon1;
import Entities.TrailMaker;

public class MyselfVsMe extends BasicGame {

	private static int WIDTH = 1024;
	private static int HEIGHT = 800;
	
	private Camera cam;
	private ResourceManager rm;
	private EntityManager em;
	
	public MyselfVsMe() {
		super("Myself Vs. Me - A Game by Planktno & Diko");
	}
	
	public void setCamera(int normalWidth, int normalHeight, int screenWidth, int screenHeight) {
		cam = new Camera(normalWidth, normalHeight, screenWidth, screenHeight);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		rm = new ResourceManager();
		em = new EntityManager();
		em.setResourceManager(rm);
		
		//Init player
		Entity player = new Entity("Player");
		player.setImage(rm.getImage32(0, 0));
		player.setPosition(new Vector2f((WIDTH-player.getWidth())/2, (HEIGHT-player.getHeight())/2));
		player.addComponent(new PlayerComponent("PlayerComponent", rm, em));
		player.addComponent(new TrailMaker("Player Trail Maker", rm, em));
		player.addComponent(new PlayerWeapon1("Weapon1", rm, em));
		em.addEntity(player);
		rm.getNyan().play();	
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		//Draw the background
		rm.getBackground().getScaledCopy(cam.getScale()).draw(cam.getOffset().getX(), 0);
		
		//Draw all Entities
		em.render(gc, null, gr, cam);
		
		//Draw Strings of relevant information
		gr.drawString("Score: " + em.getScore() , cam.getOffset().getX()+cam.getWidth()*cam.getScale()-120, 10);
		gr.drawString("Level: " + em.getLevel() , cam.getOffset().getX()+cam.getWidth()*cam.getScale()-220, 10);
		gr.drawString("Lives: " + em.getLives() , cam.getOffset().getX()+10, 10);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		em.update(gc, null, delta, cam);
	}
	
	
	
	/**
	 * @param args
	 * @throws SlickException 
	 */
	public static void main(String[] args) throws SlickException {
		MyselfVsMe game = new MyselfVsMe();
		AppGameContainer app = new AppGameContainer( game );
		game.setCamera(WIDTH, HEIGHT, app.getScreenWidth(), app.getScreenHeight());
        app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
        app.setTargetFrameRate(60);
        //app.setShowFPS(false);
        app.start();
	}

}
