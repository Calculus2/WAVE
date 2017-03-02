package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, (int) 0, Game.WIDTH - 35); // can't exit the screen
														// right or left
		y = Game.clamp(y, (int) 0, Game.HEIGHT - 60); // can't exit the screen
														// up or down
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));

		collision();

	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
					|| tempObject.getId() == ID.SmartEnemy) { // tempOject is
																// now
																// BasicEnemy
				if (getBounds().intersects(tempObject.getBounds())) {
					// collision code

					HUD.HEALTH -= 1.5;

				}
			}
		}

	}

	public void render(Graphics g) {

		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
	}

}
