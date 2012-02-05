package entities;

import game.Camera;
import game.EntityManager;
import game.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class Enemy007 extends Component {

	private int fireRate;	//in shots/10sec
	private int time;
	
	public Enemy007(String id, ResourceManager rm, EntityManager em, int fireRate) {
		super(id, rm, em);
		this.fireRate=fireRate;
		time=0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Camera cam) {
        time+=delta;
        if (time>=10000/fireRate){
        	time=0;
        	
        	rm.getLaser().play();
        	
        	Entity bullet = new Entity("Enemy Bullet");
        	Vector2f pos = owner.getPosition();
    		Vector2f posPlayer = new Vector2f(em.getEntity("Player").getPosition());
    		float angle = (float)posPlayer.sub(pos).getTheta() + 90;
        	bullet.setImage(rm.getImage04(2, 9));
        	float addXBullet =  20 * (float)Math.sin(Math.toRadians(angle));
        	float addYBullet = -20 * (float)Math.cos(Math.toRadians(angle));
        	bullet.setPosition(new Vector2f(pos.getX()+addXBullet, pos.getY()+addYBullet));
        	bullet.addComponent(new LineMovement("BulletMovement", 0.5f, angle, true));
        	em.addEntity(bullet);
        }
   	}
}
