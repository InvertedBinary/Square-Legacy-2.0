package com.IB.SL.entity.projectile;

import java.util.List;
import java.util.Random;

import com.IB.SL.Game;
import com.IB.SL.Game.gameState;
import com.IB.SL.entity.Entity;
import com.IB.SL.entity.inventory.item.Item;
import com.IB.SL.entity.mob.Mob;
import com.IB.SL.entity.spawner.BleedSpawner;
import com.IB.SL.graphics.Screen;
import com.IB.SL.graphics.Sprite;
import com.IB.SL.util.Sound;


public class Flare extends Projectile {
	public static int FIRE_RATE = 25;
	public int xa, ya;
	int ExpV = (random.nextInt(1) + 3);
	Random dropChance = new Random();
	int drop;
	Item item;

	protected static Random Random = new Random();
	
	public Flare(double x, double y, double dir) {
		super(x, y, dir);
		damage = 1;
		basicInit();
	}
	
	public Flare(double x, double y, double dir, Mob mob) {
		super(x, y, dir);
		damage = 1 + mob.stat_ATC / 5;

		basicInit();
	}
	
	void basicInit() {
		range = random.nextInt(80) + 260;
		drop = (dropChance.nextInt((6 - 1) + 1) + 1);
		speed = 3;
		sprite = Sprite.rotate(Sprite.Flare, angle);	
		manaCost = 0;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		item = new Item();
		this.id = 14;
		Sound.Play(Sound.Spell2,  false);
	}
	
	public void update() {
		List<Entity> entities = level.entities;
		this.Collision(this, entities);

		level.add(new BleedSpawner((int) (x + nx), (int) (y + ny - 4), 4, 4, level));
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 1, 0, 6)) {
			remove();
		}
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();
	}

	public double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin -y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 8,(int)y - 14, this);
		if (Game.getGame().gameState == gameState.INGAME_A) screen.drawRect((int)x - 3, (int)y - 9, 5, 5, 0x0093FF, true);

	}
}