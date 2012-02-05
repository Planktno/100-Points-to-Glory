package entities;

import game.Camera;
import game.EntityManager;
import game.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class PlayerComponent extends Component{

	private float speed;
	private static float MAXSPEED = 0.2f;
	private static float ACC = 0.001f;
	private float angle;
	private static float ANGLESPEED = 0.2f;
	
	public PlayerComponent(String id, ResourceManager rm, EntityManager em) {
		super(id, rm, em);
		
		speed = 0;
		angle = 0;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Camera cam) {
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
            if(pos.getX() >= cam.getWidth() && angle > 0) 	angle = -angle;

            if(pos.getY() <= 0 && angle >= 0 && angle < 90) angle = -(angle - 180);
            if(pos.getY() <= 0 && angle < 0 && angle > -90) angle = -(angle + 180);
            if(pos.getY() >= cam.getHeight() && angle > 90) 	angle = -(angle - 180);
            if(pos.getY() >= cam.getHeight() && angle < -90) angle = -(angle + 180);
        } else {
           	if(pos.getX() <= 0 && angle > 0) 				angle = -angle;
            if(pos.getX() >= cam.getWidth() && angle < 0) 	angle = -angle;

            if(pos.getY() <= 0 && angle > 90) angle = -(angle - 180);
            if(pos.getY() <= 0 && angle < -90) angle = -(angle + 180);
            if(pos.getY() >= cam.getHeight() && angle >= 0 && angle < 90) angle = -(angle - 180);
            if(pos.getY() >= cam.getHeight() && angle < 0 && angle > -90) angle = -(angle + 180);
        }       
	}

}
