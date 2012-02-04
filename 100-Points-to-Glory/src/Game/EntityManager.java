package Game;

import java.util.ArrayList;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Entities.Enemy1;
import Entities.EnemyXplode;
import Entities.Entity;

public class EntityManager {
	
	private ResourceManager rm;
	
	private ArrayList<Entity> entities;
	private ArrayList<Entity> toAdd;
	private ArrayList<Entity> toRemove;
	
	private int score;
	private int level;
	private int lives;
	
	private static int egInterval = 3000;
	private int egTimeSince;
	
	public EntityManager(){
		entities = new ArrayList<Entity>();
		toAdd 	 = new ArrayList<Entity>();
		toRemove = new ArrayList<Entity>();
		
		score = 0;
		level = 0;
		lives = 0;
		
		egTimeSince = 0;
	}
	
	public void setResourceManager(ResourceManager rm) {
		this.rm = rm;
	}
	
	public void addEntity(Entity e) {
		toAdd.add(e);
	}
	
	public void removeEntity(Entity e) {
		if(e.getId().equals("Enemy1")) {
			
		}
		toRemove.add(e);
	}
	
	public Entity getEntity(String id) {
		for(Entity e : entities) {
			if(e.getId().equals(id)) return e;
		}
		return null;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getScore() {
		return score;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		checkCollisions();
		enemyGeneratorUpdate(gc, delta);
		
		entities.addAll(toAdd);
		toAdd.clear();
		entities.removeAll(toRemove);
		toRemove.clear();
		
		for(Entity e : entities) {
			e.update(gc, sb, delta);
		}
	}
	
	public void enemyGeneratorUpdate(GameContainer gc, int delta) {
		egTimeSince += delta;
//		if(egTimeSince >= egInterval) {
//			egTimeSince -= egInterval;
//			Entity enemy = new Entity("Enemy1");
//			enemy.setImage(rm.getImage32(0, 2));
//			double angle = Math.random()*Math.PI;
//			float x = gc.getWidth() *0.75f*(float)Math.sin(angle)+gc.getWidth()/2;
//			float y = gc.getHeight()*0.75f*(float)Math.cos(angle)+gc.getHeight()/2;
//			enemy.setPosition(new Vector2f(x,y));
//			enemy.AddComponent(new Enemy1("Enemy1Component", rm, this, 0.1f));
//			this.addEntity(enemy);
//		}
		if(egTimeSince >= egInterval) {
			egTimeSince -= egInterval;
			Entity enemy = new Entity("Enemy2");
			enemy.setImage(rm.getImage32(0, 3));
			double angle = Math.random()*Math.PI;
			float x = gc.getWidth() *0.75f*(float)Math.sin(angle)+gc.getWidth()/2;
			float y = gc.getHeight()*0.75f*(float)Math.cos(angle)+gc.getHeight()/2;
			enemy.setPosition(new Vector2f(x,y));
			enemy.AddComponent(new Enemy1("Enemy1Component", rm, this, 0.1f));
			enemy.AddComponent(new EnemyXplode("EnemyXplodeComponent", rm, this, 6000));
			this.addEntity(enemy);
		}
	}
	
	public void checkCollisions() {
		//Player to Bullets, Enemy1
		Entity player = this.getEntity("Player");
		for(Entity e : entities) {
			if(e.getId().equals("Bullet") || e.getId().equals("Enemy1")) {
				if(checkCollision(player, e)){
					lives--;
					this.removeEntity(e);
				}
			}
		}
		//Enemies to Bullets
		for(Entity e : entities) {
			if(e.getId().equals("Enemy1")) {
				for(Entity f : entities) {
					if(f.getId().equals("Bullet")) {
						if(checkCollision(e,f)) {
							score++;
							this.removeEntity(e);
							this.removeEntity(f);
						}
					}
				}
			}
		}
	}
	
	public boolean checkCollision(Entity e1, Entity e2) {
		HashSet<String> mask1 = getMask(new Vector2f(-e1.getWidth()/2, -e1.getHeight()/2).add(e1.getPosition()), e1.getImage());
		HashSet<String> mask2 = getMask(new Vector2f(-e2.getWidth()/2, -e2.getHeight()/2).add(e2.getPosition()), e2.getImage());

		mask1.retainAll(mask2);
		
		if(mask1.size() > 0) return true;

		return false;
	}
	
	public HashSet<String> getMask(Vector2f pos, Image img) {
		HashSet<String> mask = new HashSet<String>();
		
		for(int i = 0; i < img.getWidth(); i++) {
			for(int j = 0; j < img.getWidth(); j++) {
				if(img.getColor(i, j).getAlpha() != 0) { //is non transparent
					mask.add((Math.floor(pos.getX())+i) + "," + (Math.round(pos.getY())+j));
				}
			}
		}
		
		return mask;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		for(Entity e : entities) {
			e.render(gc, sb, gr);
		}
	}

}
