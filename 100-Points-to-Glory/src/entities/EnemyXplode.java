package entities;

import game.Camera;
import game.EntityManager;
import game.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class EnemyXplode extends Component {


	private int time;
	private int pictime;
	private boolean pictoggle;
	private int bullets;
	
	public EnemyXplode(String id, ResourceManager rm, EntityManager em, int time) {
		super(id, rm, em);
		this.time=time;
		bullets = 6;
		time=pictime=0;
		pictoggle=true;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Camera cam) {
        
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
        
        if(time <= 0) {
        	rm.getExplosion().play();
        	for(int i = 0; i < bullets; i++) {
            	Entity bullet = new Entity("Enemy Bullet");
            	Vector2f pos = owner.getPosition();
            	float angle = 360*i/(float)bullets;
            	bullet.setImage(rm.getImage04(2, 9));
            	float addXBullet =  20 * (float)Math.sin(Math.toRadians(angle));
            	float addYBullet = -20 * (float)Math.cos(Math.toRadians(angle));
            	bullet.setPosition(new Vector2f(pos.getX()+addXBullet, pos.getY()+addYBullet));
            	bullet.addComponent(new LineMovement("BulletMovement", 0.5f, angle, true));
            	em.addEntity(bullet);
        	}
        	em.removeEntity(owner);
        } else if(time <= 500) {
        	owner.setImage(rm.getImage32(3, 3));
        } else if(time <= 1500) {
        	owner.setImage(rm.getImage32(2,3));
        }
	}
}
