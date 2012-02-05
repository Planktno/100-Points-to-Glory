package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Game.Camera;
import Game.EntityManager;
import Game.ResourceManager;

public class TrailMaker extends Component {

	private static int TRAILCOOLDOWN = 10;
	private int timeSinceLastTrail;
	
	public TrailMaker(String id, ResourceManager rm, EntityManager em) {
		super(id, rm, em);
		timeSinceLastTrail = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta,
			Camera cam) {
		Vector2f pos = owner.getPosition();
		float angle = owner.getRotation();
		
		
        //Trail
        if(timeSinceLastTrail < TRAILCOOLDOWN) timeSinceLastTrail += delta;
        else {
        	if(id.equals("Player Trail Maker") || id.equals("Enemy007 Trail Maker")){
            	timeSinceLastTrail = 0;
            	Entity trailLeft = new Entity("Trail Left");
            	Entity trailRight = new Entity("rail Right");
            	if(id.equals("Player Trail Maker")){
            		trailLeft.setImage(rm.getImage04(4, 9));
                	trailRight.setImage(rm.getImage04(4, 9));
            	} else {
            		trailLeft.setImage(rm.getImage04(5, 9));
                	trailRight.setImage(rm.getImage04(5, 9));
            	}
            	float addXTrailLeft = -16 * (float)Math.sin(Math.toRadians(angle + 25));
            	float addYTrailLeft = 16 * (float)Math.cos(Math.toRadians(angle  + 25));
            	float addXTrailRight = -16 * (float)Math.sin(Math.toRadians(angle- 25));
            	float addYTrailRight = 16 * (float)Math.cos(Math.toRadians(angle - 25));
            	trailLeft.setPosition(new Vector2f(pos.getX()+addXTrailLeft, pos.getY()+addYTrailLeft));
            	trailRight.setPosition(new Vector2f(pos.getX()+addXTrailRight, pos.getY()+addYTrailRight));
            	trailLeft.addComponent(new PlayerTrail("Trail Timer", rm, em, 300));
            	trailRight.addComponent(new PlayerTrail("Trail Timer", rm, em, 300));
            	em.addEntity(trailLeft);
            	em.addEntity(trailRight);
        	}
        	if(id.equals("Enemy1 Trail Maker")){
        		timeSinceLastTrail = 0;
            	Entity trail = new Entity("Trail");
            	trail.setImage(rm.getImage(0, 44, 8, 8));
            	float addXTrail = -10 * (float)Math.sin(Math.toRadians(angle));
            	float addYTrail = 10 * (float)Math.cos(Math.toRadians(angle));
            	trail.setPosition(new Vector2f(pos.getX()+addXTrail, pos.getY()+addYTrail));
            	trail.addComponent(new PlayerTrail("Trail Timer", rm, em, 300));
            	em.addEntity(trail);
        	}
        }
	}

}
