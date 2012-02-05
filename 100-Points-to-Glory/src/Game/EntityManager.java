package Game;

import java.util.ArrayList;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import Entities.Enemy007;
import Entities.Enemy1;
import Entities.EnemyXplode;
import Entities.Entity;
import Entities.LineMovement;
import Entities.PlayerWeapon2;
import Entities.PlayerWeapon3;
import Entities.PlayerWeapon4;
import Entities.TrailMaker;

public class EntityManager {
	
	private ResourceManager rm;
	
	private ArrayList<Entity> entities;
	private ArrayList<Entity> toAdd;
	private ArrayList<Entity> toRemove;
	
	private int score;
	private int suicide;
	private int level;
	private int lives;
	private boolean glory;
	
	private static int egInterval1 = 3000;
	private static int egInterval2 = 2500;
	private static int egInterval3 = 2000;
	private static int egInterval4 = 1750;
	private static int egInterval5 = 1250;
	private int egTimeSince;
	
	private boolean displayMessage;
	private int messageIterator;
	private int timeToDisplay;
	private int timeDisplayed;
	private static String[] messages = {
		"Loser",
		"You are so bad",
		"You Mom could do this better than you",
		"Again?!?!"
	};
	
	public EntityManager(){
		entities = new ArrayList<Entity>();
		toAdd 	 = new ArrayList<Entity>();
		toRemove = new ArrayList<Entity>();
		
		score = 99;
		suicide = 0;
		level = 4;
		lives = 100;
		glory = false;
		
		egTimeSince = 0;
		
		displayMessage = false;
		messageIterator = 0;
		timeToDisplay = 1000;
		timeDisplayed = 0;
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
	
	public int getSuicide() {
		return suicide;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta, Camera cam) {
		checkCollisions(cam);
		enemyGeneratorUpdate(gc, delta, cam);
		
		entities.addAll(toAdd);
		toAdd.clear();
		entities.removeAll(toRemove);
		toRemove.clear();
		
		if(displayMessage) {
			if(timeDisplayed >= timeToDisplay) {
				displayMessage = false;
				timeDisplayed = 0;
				if(messageIterator == messages.length-1){
					messageIterator = 0;
				} else {
					messageIterator++;
				}
			} else {
				timeDisplayed += delta;
			}
		}
		
		for(Entity e : entities) {
			e.update(gc, sb, delta, cam);
		}
	}
	
	public void enemyGeneratorUpdate(GameContainer gc, int delta, Camera cam) {
		egTimeSince += delta;
		//if(egTimeSince >= egInterval) {
		double rnd = Math.random();
		
		switch(level){
		case 1: if(egTimeSince >= egInterval1){
					egTimeSince -= egInterval1;
						 if(rnd <= 0.90) addEnemy1(cam);
					else if(rnd <= 1.00) addEnemyXPlode(cam);
					else if(rnd <= 0.00) addEnemyMetroid(cam);
					else if(rnd <= 0.00) addEnemy007(cam);
				}
				break;	 
		case 2: if(egTimeSince >= egInterval2){
					egTimeSince -= egInterval2;
						 if(rnd <= 0.70) addEnemy1(cam);
					else if(rnd <= 0.90) addEnemyXPlode(cam);
					else if(rnd <= 0.00) addEnemyMetroid(cam);
					else if(rnd <= 1.00) addEnemy007(cam);
				}
				break;	
		case 3: if(egTimeSince >= egInterval3){
					egTimeSince -= egInterval3;
						 if(rnd <= 0.50) addEnemy1(cam);
					else if(rnd <= 0.50) addEnemyXPlode(cam);
					else if(rnd <= 0.80) addEnemyMetroid(cam);
					else if(rnd <= 1.00) addEnemy007(cam);
				}
				break;	
		case 4: if(egTimeSince >= egInterval4){
					egTimeSince -= egInterval4;
						 if(rnd <= 0.40) addEnemy1(cam);
					else if(rnd <= 0.70) addEnemyXPlode(cam);
					else if(rnd <= 0.80) addEnemyMetroid(cam);
					else if(rnd <= 1.00) addEnemy007(cam);
				}
				break;	
		case 5: if(egTimeSince >= egInterval5){
					egTimeSince -= egInterval5;
					 	 if(rnd <= 0.20) addEnemy1(cam);
					else if(rnd <= 0.40) addEnemyXPlode(cam);
					else if(rnd <= 0.60) addEnemyMetroid(cam);
					else if(rnd <= 0.80) addEnemy007(cam);
					else if(rnd <= 1.00) addGlory(cam);
				}
				break;	

		}
	}
	
	public void addEnemy1(Camera cam) {
		Entity enemy = new Entity("Enemy");
		enemy.setImage(rm.getImage32(0, 2));
		double angle = Math.random()*Math.PI;
		float x = cam.getWidth() *0.75f*(float)Math.sin(angle)+cam.getWidth()/2;
		float y = cam.getHeight()*0.75f*(float)Math.cos(angle)+cam.getHeight()/2;
		enemy.setPosition(new Vector2f(x,y));
		enemy.addComponent(new Enemy1("Enemy1Component", rm, this, 0.1f));
		enemy.addComponent(new TrailMaker("Enemy1 Trail Maker", rm, this));
		this.addEntity(enemy);
	}
	
	public void addEnemyXPlode(Camera cam) {
		Entity enemy = new Entity("Enemy");
		enemy.setImage(rm.getImage32(0, 3));
		double angle = Math.random()*Math.PI;
		float x = cam.getWidth() *0.75f*(float)Math.sin(angle)+cam.getWidth()/2;
		float y = cam.getHeight()*0.75f*(float)Math.cos(angle)+cam.getHeight()/2;
		enemy.setPosition(new Vector2f(x,y));
		enemy.addComponent(new Enemy1("Enemy1Component", rm, this, 0.05f));
		enemy.addComponent(new EnemyXplode("EnemyXplodeComponent", rm, this, 10000));
		this.addEntity(enemy);
	}
	
	public void addEnemyMetroid(Camera cam) {
		Entity enemy = new Entity("Metroid");
		enemy.setImage(rm.getImage(32, 32, 64, 64));
		double angleOfScreen = Math.random()*Math.PI;
		float x = cam.getWidth() *0.75f*(float)Math.sin(angleOfScreen)+cam.getWidth()/2;
		float y = cam.getHeight()*0.75f*(float)Math.cos(angleOfScreen)+cam.getHeight()/2;
		Vector2f pos = new Vector2f(x, y);
		Vector2f posPlayer = new Vector2f(this.getEntity("Player").getPosition());
		float angleToPlayer = (float)posPlayer.sub(pos).getTheta() + 90;
		enemy.setPosition(pos);
		enemy.addComponent(new LineMovement("Enemy Metroid Movement", 0.02f, angleToPlayer, true));
		this.addEntity(enemy);
	}
	
	public void addEnemy007(Camera cam) {
		Entity enemy = new Entity("Enemy");
		enemy.setImage(rm.getImage32(1, 0));
		double angle = Math.random()*Math.PI;
		float x = cam.getWidth() *0.75f*(float)Math.sin(angle)+cam.getWidth()/2;
		float y = cam.getHeight()*0.75f*(float)Math.cos(angle)+cam.getHeight()/2;
		enemy.setPosition(new Vector2f(x,y));
		enemy.addComponent(new Enemy1("Enemy1Component", rm, this, 0.05f));
		enemy.addComponent(new Enemy007("Enemy007Component", rm, this, 5));
		enemy.addComponent(new TrailMaker("Enemy007 Trail Maker", rm, this));
		this.addEntity(enemy);
	}
	
	public void addGlory(Camera cam) {
		Entity glory = new Entity("Glory");
		glory.setImage(rm.getImage32(2, 0));
		double angleOfScreen = Math.random()*Math.PI;
		float x = cam.getWidth() *0.75f*(float)Math.sin(angleOfScreen)+cam.getWidth()/2;
		float y = cam.getHeight()*0.75f*(float)Math.cos(angleOfScreen)+cam.getHeight()/2;
		Vector2f pos = new Vector2f(x, y);
		Vector2f posPlayer = new Vector2f(this.getEntity("Player").getPosition());
		float angleToPlayer = (float)posPlayer.sub(pos).getTheta() + 90;
		glory.setPosition(pos);
		glory.addComponent(new LineMovement("Glory Movement", 0.1f, angleToPlayer, true));
		this.addEntity(glory);
	}
	
	public void checkCollisions(Camera cam) {
		if(lives <= 0) return;
		
		//Player to Player and Enemy Bullets, Enemies, Metroids
		Entity player = this.getEntity("Player");
		for(Entity e : entities) {
			if(e.getId().equals("Enemy Bullet") || e.getId().equals("Player Bullet") 
					|| e.getId().equals("Enemy") || e.getId().equals("Metroid") || e.getId().equals("Glory")) {
				if(checkCollision(player, e)){
					lives--;
					if(e.getId().equals("Player Bullet")) {
						displayMessage = true;
						suicide++;
					}
					if(e.getId().equals("Glory")) {
						glory = true;
					}
					this.removeEntity(e);
					if(lives == 0) {
						player.removeComponents();
						return;
					}
				}
			}
		}
		//Enemies to Player Bullets
		for(Entity e : entities) {
			if(e.getId().equals("Enemy")) {
				for(Entity f : entities) {
					if(f.getId().equals("Player Bullet")) {
						if(checkCollision(e,f)) {
							score++;
							this.removeEntity(e);
							this.removeEntity(f);
							this.checkLevelUp(cam);
						}
					}
					if(f.getId().equals("Enemy Bullet")) {
						if(checkCollision(e,f)) {
							this.removeEntity(e);
							this.removeEntity(f);
						}
					}
				}
			}
		}
	}
	
	public boolean checkCollision(Entity e1, Entity e2) {
		// This method detects to see if the images overlap at all. If they do, collision is possible
		float ax1 = e1.getPosition().getX();
		float ay1 = e1.getPosition().getY();;
		float ax2 = ax1 + e1.getWidth();
		float ay2 = ay1 + e1.getHeight();
		float bx1 = e2.getPosition().getX();
		float by1 = e2.getPosition().getY();;
		float bx2 = bx1 + e2.getWidth();
		float by2 = by1 + e2.getHeight();
		
		if(by2 < ay1 || ay2 < by1 || bx2 < ax1 || ax2 < bx1)
		{
			return false; // Collision is impossible.
		}
		
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
	
	public void checkLevelUp(Camera cam){
		Entity player = this.getEntity("Player");
		switch(score){
		case 25: level = 2;
				 rm.getLevelUp().play();
				 player.removeComponents("Weapon1");
				 player.addComponent(new PlayerWeapon2("Weapon2", rm, this));
				 break;
		case 50: level = 3;
				 rm.getLevelUp().play();
				 player.removeComponents("Weapon2");
				 player.addComponent(new PlayerWeapon3("Weapon3", rm, this));
				 break;
		case 75: level = 4;
				 rm.getLevelUp().play();
				 player.removeComponents("Weapon3");
				 player.addComponent(new PlayerWeapon4("Weapon4", rm, this));
				 break;
		case 100:level = 5;
				 rm.getLevelUp().play();
				 addGlory(cam);
				 addGlory(cam);
				 addGlory(cam);
				 break;
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr, Camera cam) {
		if(glory) {
			Image gloryImage = rm.getImage32(2, 0).getScaledCopy(cam.getScale()*4);
			gloryImage.draw((gc.getWidth()-gloryImage.getWidth())/2, (gc.getHeight()-gloryImage.getHeight())/2);
			gr.drawString("You have achieved Glory!", (gc.getWidth()/2)-115, (gc.getHeight()/2)+120);
		}
		if(lives <= 0) {
			gr.drawString("100 lives and you still lose.", (gc.getWidth()/2)-130, (gc.getHeight()/2)+150);
			gr.drawString("score: " + score + " and " + suicide + " suicides", (gc.getWidth()/2)-120, (gc.getHeight()/2)+180);
			gr.drawString("  Press 'p' to restart  ", (gc.getWidth()/2)-115, (gc.getHeight()/2)+210);
		}
		
		switch(level){
		case 1: gr.drawString("Get 100 Points for Glory", gc.getWidth()/2 - 115, 10);
				break;
		case 2: gr.drawString("Weapon Upgrade! Shoot those Enemies!", gc.getWidth()/2 - 170, 10);
				break;
		case 3: gr.drawString("More Bullets = More Awesome", gc.getWidth()/2 - 130, 10);
				break;
		case 4: gr.drawString("Let me help you with another Weapon Upgrade", gc.getWidth()/2 - 200, 10);
				break;
		case 5: gr.drawString("Catch that Glory!", gc.getWidth()/2 - 80, 10);
				break;
		}
		
		if(displayMessage && lives > 0){
			Vector2f pos = new Vector2f(this.getEntity("Player").getPosition());
			pos.scale(cam.getScale());
			pos.add(cam.getOffset());
			gr.drawString(messages[messageIterator], pos.getX() + 25, pos.getY() - 25);
		}
		
		for(Entity e : entities) {
			e.render(gc, sb, gr, cam);
		}
	}

}
