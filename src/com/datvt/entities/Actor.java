package com.datvt.entities;

import java.awt.Image;

public abstract class Actor {

	protected Image image;

	protected int width;
	protected int height;
	protected int attack;
	protected int defense;
	protected int health;
	protected int healthMax;
	protected int orient;

	protected float positionX;
	protected float positionY;
	protected float speedX;
	protected float speedY;

	protected boolean alive;

	protected abstract void move();
	protected abstract void attack(Actor actor);
	protected abstract void takeDamage(int damage);
	protected abstract boolean collision(Actor actor);
}
