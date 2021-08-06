package fr.heighties.chrichriprototype;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Image chrichri;
	Random r = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	// colisions

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
//
	}

	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);

		// pour avoir la "train�e" qui suit le joueur
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
//
		collision();

	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
					|| tempObject.getId() == ID.SmartEnemy) { // tempObject is now basic
				// enemy
				if (getBounds().intersects(tempObject.getBounds())) {
					// collision code
					HUD.HEALTH -= 2;
				}

			}
		}
	}

	// public void render(Graphics g) {
	// g.setColor(Color.white);
	// g.fillRect((int) x, (int) y, 32, 32);

	public void paint(Graphics g) {

		// super.paint(g);

		Graphics2D g2D = (Graphics2D) g;

		g2D.drawImage(chrichri, 0, 0, null);

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

}
