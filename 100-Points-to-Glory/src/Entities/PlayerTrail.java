package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import Game.EntityManager;
import Game.ResourceManager;

public class PlayerTrail extends Component{

	private int timer;
	private int count;
	
	public PlayerTrail(String id, ResourceManager rm, EntityManager em, int timer) {
		super(id, rm, em);
		this.timer = timer;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		count += delta;
		owner.getImage().setAlpha(1-count/(float)timer);
		if(count >= timer){
			em.removeEntity(owner);
		}
	}
	
}
