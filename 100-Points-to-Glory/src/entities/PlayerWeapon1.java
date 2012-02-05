package entities;

import game.Camera;
import game.EntityManager;
import game.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class PlayerWeapon1 extends Component {
	
	private static int COOLDOWN = 200;
	private int timeSinceLastShot;

	public PlayerWeapon1(String id, ResourceManager rm, EntityManager em) {
		super(id, rm, em);
		timeSinceLastShot = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Camera cam) {
		Input input = gc.getInput();
		Vector2f pos = owner.getPosition();
		float angle = owner.getRotation();
		
        //Attacking
        if(timeSinceLastShot < COOLDOWN) timeSinceLastShot += delta;
        
        if(input.isKeyDown(Input.KEY_SPACE) && timeSinceLastShot >= COOLDOWN) {
        	timeSinceLastShot = 0;
        	rm.getLaser().play();
        	
        	Entity bullet = new Entity("Player Bullet");
        	bullet.setImage(rm.getImage04(0, 9));
        	float addXBullet =  20 * (float)Math.sin(Math.toRadians(angle));
        	float addYBullet = -20 * (float)Math.cos(Math.toRadians(angle));
        	bullet.setPosition(new Vector2f(pos.getX()+addXBullet, pos.getY()+addYBullet));
        	bullet.addComponent(new LineMovement("BulletMovement", 0.5f, angle, true));
        	em.addEntity(bullet);
        }
	}

}
