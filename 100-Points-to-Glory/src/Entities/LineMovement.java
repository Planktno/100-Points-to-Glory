package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class LineMovement extends Component {

	private float speed;
	private float angle;
	private boolean bounceOnEdge;
	private boolean deleteOnEdge;
	
	public LineMovement(String id, float speed, float angle, boolean bounceOnEdge) {
		super(id);
		this.speed = speed;
		this.angle = angle;
		this.bounceOnEdge = bounceOnEdge;
		this.deleteOnEdge = deleteOnEdge;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Vector2f pos = owner.getPosition();
        pos.x += speed * delta * (float)Math.sin(Math.toRadians(angle));
        pos.y -= speed * delta * (float)Math.cos(Math.toRadians(angle));
        
        if(bounceOnEdge) {
        	if(pos.getX() <= 0 && angle < 0) {
            	angle = -angle;
            }
            if(pos.getX() >= gc.getWidth() && angle > 0) {
            	angle = -angle;
            }
            
            if(pos.getY() <= 0 && angle >= 0 && angle < 90) {
            	angle = -(angle - 180);
            }
            if(pos.getY() <= 0 && angle < 0 && angle > -90) {
            	angle = -(angle + 180);
            }
            if(pos.getY() >= gc.getHeight() && angle > 90) {
            	angle = -(angle - 180);
            }
            if(pos.getY() >= gc.getHeight() && angle < -90) {
            	angle = -(angle + 180);
            }
        }
	}

}
