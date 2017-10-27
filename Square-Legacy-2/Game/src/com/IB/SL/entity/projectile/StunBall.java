package com.IB.SL.entity.projectile;

import java.util.List;

import com.IB.SL.Game;
import com.IB.SL.entity.inventory.effects.Stun;
import com.IB.SL.entity.mob.PlayerMP;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;


public class StunBall extends Projectile{
	
	public static int FIRE_RATE = 120;
	public static int time = 0;
	public StunBall(double x, double y, double dir) {
		super(x, y, dir);
		range = random.nextInt(90) + 140;
		speed = 1;
		damage = 3;
		sprite = Sprite.rotate(Sprite.FairyBolt, angle);
		this.breakParticle = 0;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		this.id = 7011;
	}
	
	public void update() {
		List<PlayerMP> players = level.players;
		PlayerCollision(players, this); 
	     
		
		
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 4, -2, 8)) {
			 remove();
	}
	
		move();
		
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) {
			remove();
		}
		
	}
	
	public void addEffect(PlayerMP player) {
		int r = Game.get().getLevel().myRandom(0, 100);
		if (r > 40)
		player.effects.addEffect(new Stun(player, 20));
	}

	
	public double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin -y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 8,(int)y - 14, this);
	}
}