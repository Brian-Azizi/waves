package com.brian.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.brian.main.Game.STATE;

public class Menu extends MouseAdapter {
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
            // Play
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
                        handler));
            }
            // Help
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = STATE.Help;
            }
            // Quit
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(0);
            }
        }

        if (game.gameState == STATE.Help) {
            // Back button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                return;
            }
        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        return (mx > x && mx < x + width && my > y && my < y + height);
    }

    public void tick() {
    }

    public void render(Graphics g) {
        Font fontLarge = new Font("arial", 1, 50);
        Font fontSmall = new Font("arial", 1, 20);
        Font fontMedium = new Font("arial", 1, 30);
        g.setFont(fontLarge);
        g.setColor(Color.white);

        if (game.gameState == STATE.Menu) {
            g.drawString("Menu", 240, 70);

            g.setFont(fontMedium);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        } else if (game.gameState == STATE.Help) {
            g.drawString("Help", 240, 70);

            g.setFont(fontSmall);
            g.drawString("Use WSDA to move player and dodge enemies.", 100, 190);

            g.setFont(fontMedium);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }

    }
}