package entities;

import game.Camera;
import game.EntityManager;
import game.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class PlayerWeapon3 extends Component {
	
	private static int COOLDOWN = 200;
	private int timeSinceLastShot;

	public PlayerWeapon3(String id, ResourceManager rm, EntityManager em) {
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
        	
        	Entity bullet1 = new Entity("Player Bullet");
        	bullet1.setImage(rm.getImage04(0, 9));
        	float addXBullet1 =  20 * (float)Math.sin(Math.toRadians(angle));
        	float addYBullet1 = -20 * (float)Math.cos(Math.toRadians(angle));
        	bullet1.setPosition(new Vector2f(pos.getX()+addXBullet1, pos.getY()+addYBullet1));
        	bullet1.addComponent(new LineMovement("BulletMovement", 0.5f, angle, true));
        	em.addEntity(bullet1);
        	
        	Entity bullet2 = new Entity("Player Bullet");
        	bullet2.setImage(rm.getImage04(0, 9));
        	float addXBullet2 =  20 * (float)Math.sin(Math.toRadians(angle + 15));
        	float addYBullet2 = -20 * (float)Math.cos(Math.toRadians(angle + 15));
        	bullet2.setPosition(new Vector2f(pos.getX()+addXBullet2, pos.getY()+addYBullet2));
        	bullet2.addComponent(new LineMovement("BulletMovement", 0.5f, angle+15, true));
        	em.addEntity(bullet2);
        	
        	Entity bullet3 = new Entity("Player Bullet");
        	bullet3.setImage(rm.getImage04(0, 9));
        	float addXBullet3 =  20 * (float)Math.sin(Math.toRadians(angle - 15));
        	float addYBullet3 = -20 * (float)Math.cos(Math.toRadians(angle - 15));
        	bullet3.setPosition(new Vector2f(pos.getX()+addXBullet3, pos.getY()+addYBullet3));
        	bullet3.addComponent(new LineMovement("BulletMovement", 0.5f, angle-15, true));
        	em.addEntity(bullet3);
        }
	}

}
