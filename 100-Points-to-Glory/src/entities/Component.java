package entities;

import game.Camera;
import game.EntityManager;
import game.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;


public abstract class Component {

	protected String id;
	protected Entity owner;
	protected ResourceManager rm;
	protected EntityManager em;
	
	public Component(String id) {
		this.id = id;
	}
	
	public Component(String id, ResourceManager rm, EntityManager em) {
		this.id = id;
		this.rm = rm;
		this.em = em;
	}
	
	public String getId() {
		return id;
	}
	
	public void setOwnerEntity(Entity owner) {
		this.owner = owner;
	}
	
	public abstract void update(GameContainer gc, StateBasedGame sb, int delta, Camera cam);
	
}
