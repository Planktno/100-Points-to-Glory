package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Game.EntityManager;
import Game.ResourceManager;

public class EnemyXplode extends Component {

	private int time=0, pictime=0;
	private boolean pictoggle=true;
	
	public EnemyXplode(String id, ResourceManager rm, EntityManager em, int time) {
		super(id, rm, em);
		this.time=time;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
        
        //special addings for exploding MOFOers
        pictime+=delta;
        if (pictime>=500){
        	pictime=0;
        	pictoggle=!pictoggle;
        	if (pictoggle)
        		owner.setImage(rm.getImage32(0, 3));	//toggle pic1
        	else
        		owner.setImage(rm.getImage32(1, 3));	//toggle pic2
        }
        
        time-=delta;
        if (time<=0){
        	
        	em.removeEntity(owner);
        }
        else
        	if (time<=1500){
        		owner.setImage(rm.getImage32(2, 3)); 	//1,5 sec before explosion pic
        	}
        
		
	}

}
