package com.IB.SL.entity.projectile;

import java.util.List;

import com.IB.SL.Game;
import com.IB.SL.entity.inventory.effects.Stun;
import com.IB.SL.entity.mob.PlayerMP;
import com.IB.SL.entity.spawner.WallParticleSpawner;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;


public class CopperShockwave extends Projectile{
	
	public static int FIRE_RATE = 30;
	public static int time = 0;
	
	public CopperShockwave(double x, double y, double dir) {
		super(x, y, dir);
		range = random.nextInt(80) + 260;
		speed = 0.4;
		damage = 0;
		sprite = Sprite.CopperShockwave;
		

		this.id = 1;

	}
	
	public void update() {
		time++;
		List<PlayerMP> players = level.players;
		PlayerCollision(players, this); 
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 4, -2, 8)) {		
			level.add(new WallParticleSpawner((int) (x + nx), (int) (y + ny), 2, 2, level));
		//	level.add(new ParticleSpawner((int) level.getPlayerAt(0).getX(), (int)level.getPlayerAt(0).getY(), 2, 2, level));
			 remove();
	}
		
		double px = players.get(0).x;
		double py = players.get(0).y;
		
		angle = Math.atan((py - this.y) / (px - this.x));
		move();
		}

	public void addEffect(PlayerMP player) {
		player.effects.addEffect(new Stun(player, 40));
	}
	
	protected void move() {
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		
		x += nx;
		y += ny;
		if (distance() > range) remove();
		
		sprite = Sprite.rotate(Sprite.CopperShockwave, Math.PI / 20.0 + random.nextInt());
		//sprite = Sprite.rotate(sprite, Math.PI / 20.0 + random.nextInt());
		if (Game.devModeInfoOn) {
		System.out.println("Distance (In Tiles): " + distance() / 16);
	}
	}

	public double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin -y) * (yOrigin - y)));
		return dist;
	}
int time2= 0;
	public void render(Screen screen) {
		time2++;
		if (time2 % 40 == 0) { 
			
		}
		
		
		screen.renderProjectile((int)x - 8,(int)y - 14, this);
	}
}


/*	for (int i = 0; i < Level.players.size(); i++) {
	if (x < Level.players.get(i).getX() + 5
        && x > Level.players.get(i).getX() - 5
        && y <  Level.players.get(i).getY() + 5
        && y >  Level.players.get(i).getY() - 5
        ) {
        remove();
        Level.players.get(i).mobhealth -= 1;
        Game.Health =  Level.players.get(i).mobhealth;
        level.add(new WallParticleSpawner((int) (x + nx), (int) (y + ny), 6, 2, level));
        level.add(new BleedSpawner((int) (x + nx), (int) (y + ny), 10, 8, level));
        
		if (Level.players.get(i).mobhealth <= 0){
			Level.players.get(i).remove();
			level.add(new ParticleSpawner((int) (x + nx), (int) (y + ny), 300000, 200, level));
				System.out.println("YOU DIED");
    			Game.Dead = false;
    			time++;
    			if (Level.players.get(i).isRemoved()) {
    				Player.respawn = true;
    		}
    	}
	}
}*/