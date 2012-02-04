package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Game.EntityManager;
import Game.ResourceManager;

public class Enemy1 extends Component {

	private double angle;
	private float speed;
	
	public Enemy1(String id, ResourceManager rm, EntityManager em, float speed) {
		super(id, rm, em);
		angle = 0;
		this.speed = speed;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Vector2f pos = owner.getPosition();
		Vector2f posPlayer = new Vector2f(em.getEntity("Player").getPosition());
		angle = posPlayer.sub(pos).getTheta() + 90;
		
		//Setting new Coordinates
        pos.x += speed * delta * (float)Math.sin(Math.toRadians(angle));
        pos.y -= speed * delta * (float)Math.cos(Math.toRadians(angle));
        owner.setRotation((float)angle + 180);
		
		
	}

}
