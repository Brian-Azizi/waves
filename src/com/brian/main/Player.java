package com.brian.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 56);

        collision();

        handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
                    || tempObject.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}