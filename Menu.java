package fr.heighties.chrichriprototype;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import fr.heighties.chrichriprototype.Game.STATE;

public class Menu extends MouseAdapter {

	final int PANEL_WIDTH = 800;
	final int PANEL_HEIGHT = 600;
	Image backgroundImage;
	private Game game;
	private Handler handler;
	private Random r = new Random();

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Menu) {

			// play button

			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;

				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
						handler));

			}

			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;

			}

			// quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);

			}
		}

		// back button for help
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;

			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	// souris dans rectangles

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;

	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);

			g.setColor(Color.white);
			g.drawString("Menu", 240, 70);

			g.setFont(fnt2);

			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);

			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);

		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);

			g.setFont(fnt3);
			g.drawString("Use ZQSD keys to move player and dodge enemies", 60, 200);

			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);

		}

	}
}
