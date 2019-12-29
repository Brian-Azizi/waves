package com.brian.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {
    private final Handler handler;
    private Random r = new Random();

    public EnemyBossBullet(final float x, final float y, final ID id, final Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = r.nextInt(10) - 5;
        velY = 5;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT)
            handler.removeObject(this);

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
    }

    @Override
    public void render(final Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

}