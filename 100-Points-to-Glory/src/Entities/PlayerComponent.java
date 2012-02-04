package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Game.EntityManager;
import Game.ResourceManager;

public class PlayerComponent extends Component{

	private float speed;
	private static float MAXSPEED = 0.2f;
	private static float ACC = 0.001f;
	private float angle;
	private static float ANGLESPEED = 0.2f;
	
	private static int COOLDOWN = 200;
	private int timeSinceLastShot;
	
	private static int TRAILCOOLDOWN = 10;
	private int timeSinceLastTrail;
	
	public PlayerComponent(String id, ResourceManager rm, EntityManager em) {
		super(id, rm, em);
		
		speed = 0;
		angle = 0;
		timeSinceLastShot = 0;
		timeSinceLastTrail = 0;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Input input = gc.getInput();
		
		//ACCeleration and braking
		if(input.isKeyDown(Input.KEY_W)) { //ACCeleration
			if(speed < MAXSPEED) {
				speed += ACC*delta;
			} else {
				speed = MAXSPEED;
			}
		}
		if(input.isKeyDown(Input.KEY_S)) { //Braking
			if(speed > -MAXSPEED) {
				speed -= ACC*delta;
			} else {
				speed = -MAXSPEED;
			}
		}
		
		//Angle
		if(input.isKeyDown(Input.KEY_A)) {
			angle -= delta*ANGLESPEED;
			if(angle < -180f) {
				angle += 360;
			}
		}
		if(input.isKeyDown(Input.KEY_D)) {
			angle += delta*ANGLESPEED;
			if(angle > 180f) {
				angle -= 360;
			}
		}
		
		//Setting of new Coordinates
        Vector2f pos = owner.getPosition();
        pos.x += speed * delta * (float)Math.sin(Math.toRadians(angle));
        pos.y -= speed * delta * (float)Math.cos(Math.toRadians(angle));
        owner.setRotation(angle);
        
		//If against Wall
        if(speed > 0) {
        	if(pos.getX() <= 0 && angle < 0) 				angle = -angle;
            if(pos.getX() >= gc.getWidth() && angle > 0) 	angle = -angle;

            if(pos.getY() <= 0 && angle >= 0 && angle < 90) angle = -(angle - 180);
            if(pos.getY() <= 0 && angle < 0 && angle > -90) angle = -(angle + 180);
            if(pos.getY() >= gc.getHeight() && angle > 90) 	angle = -(angle - 180);
            if(pos.getY() >= gc.getHeight() && angle < -90) angle = -(angle + 180);
        } else {
           	if(pos.getX() <= 0 && angle > 0) 				angle = -angle;
            if(pos.getX() >= gc.getWidth() && angle < 0) 	angle = -angle;

            if(pos.getY() <= 0 && angle > 90) angle = -(angle - 180);
            if(pos.getY() <= 0 && angle < -90) angle = -(angle + 180);
            if(pos.getY() >= gc.getHeight() && angle >= 0 && angle < 90) angle = -(angle - 180);
            if(pos.getY() >= gc.getHeight() && angle < 0 && angle > -90) angle = -(angle + 180);
        }

        
        //Trail
        if(timeSinceLastTrail < TRAILCOOLDOWN) timeSinceLastTrail += delta;
        else {
        	timeSinceLastTrail = 0;
        	Entity trailLeft = new Entity("Player Trail Left");
        	Entity trailRight = new Entity("Player Trail Right");
        	trailLeft.setImage(rm.getImage04(4, 9));
        	trailRight.setImage(rm.getImage04(4, 9));
        	float addXTrailLeft = -16 * (float)Math.sin(Math.toRadians(angle + 25));
        	float addYTrailLeft = 16 * (float)Math.cos(Math.toRadians(angle  + 25));
        	float addXTrailRight = -16 * (float)Math.sin(Math.toRadians(angle- 25));
        	float addYTrailRight = 16 * (float)Math.cos(Math.toRadians(angle - 25));
        	trailLeft.setPosition(new Vector2f(pos.getX()+addXTrailLeft, pos.getY()+addYTrailLeft));
        	trailRight.setPosition(new Vector2f(pos.getX()+addXTrailRight, pos.getY()+addYTrailRight));
        	trailLeft.AddComponent(new PlayerTrail("Player Trail Timer", rm, em, 100));
        	trailRight.AddComponent(new PlayerTrail("Player Trail Timer", rm, em, 100));
        	em.addEntity(trailLeft);
        	em.addEntity(trailRight);
        }
 
        //Attacking
        if(timeSinceLastShot < COOLDOWN) timeSinceLastShot += delta;
        
        if(input.isKeyDown(Input.KEY_SPACE) && timeSinceLastShot >= COOLDOWN) {
        	timeSinceLastShot = 0;
        	
        	Entity bullet = new Entity("Bullet");
        	bullet.setImage(rm.getImage04(0, 9));
        	float addXBullet =  20 * (float)Math.sin(Math.toRadians(angle));
        	float addYBullet = -20 * (float)Math.cos(Math.toRadians(angle));
        	bullet.setPosition(new Vector2f(pos.getX()+addXBullet, pos.getY()+addYBullet));
        	bullet.AddComponent(new LineMovement("BulletMovement", 0.5f, angle, true));
        	em.addEntity(bullet);
        }
	}

}
