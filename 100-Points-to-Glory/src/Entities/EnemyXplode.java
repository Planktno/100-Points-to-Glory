package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Game.EntityManager;
import Game.ResourceManager;

public class EnemyXplode extends Component {

	private static float SPEED = 0.1f;
	private double angle;
	private int time=0, pictime=0;
	private boolean pictoggle=true;
	
	public EnemyXplode(String id, ResourceManager rm, EntityManager em, time) {
		super(id, rm, em);
		this.time=time;
		angle = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Vector2f pos = owner.getPosition();
		Vector2f posPlayer = new Vector2f(em.getEntity("Player").getPosition());
		angle = posPlayer.sub(pos).getTheta() + 90;
		
		//Setting new Coordinates
        pos.x += SPEED * delta * (float)Math.sin(Math.toRadians(angle));
        pos.y -= SPEED * delta * (float)Math.cos(Math.toRadians(angle));
        owner.setRotation((float)angle + 180);
        
        //special addings for exploding MOFOers
        pictime+=delta;
        if (pictime>=500){
        	pictime=0;
        	pictoggle=!pictoggle;
        	if (pictoggle)
        		owner.setImage(rm.getImage32(911, 911));	//toggle pic1
        	else
        		owner.setImage(rm.getImage32(911, 911));	//toggle pic2
        }
        
        time-=delta;
        if (time<=0){
        	
        	em.removeEntity(owner);
        }
        else
        	if (time<=1500){
        		owner.setImage(rm.getImage32(301073, 301073)); 	//1,5 sec before explosion pic
        	}
        
		
	}

}
