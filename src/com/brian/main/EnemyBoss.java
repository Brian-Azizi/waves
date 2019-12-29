package com.brian.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {
    private Handler handler;

    private int verticalTimer = 80;
    private int horizontalTimer = 50;

    private Random r = new Random();

    public EnemyBoss(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (verticalTimer <= 0) {
            velY = 0;
            horizontalTimer--;
        } else
            verticalTimer--;

        if (horizontalTimer <= 0) {
            if (velX == 0) {
                velX = 2;
            }
            if (velX > 0)
                velX += 0.005f;
            else if (velX < 0)
                velX -= 0.005f;
            int spawn = r.nextInt(10);
            if (spawn == 0)
                handler.addObject(new EnemyBossBullet(x + 48, y + 96, ID.EnemyBoss, handler));

            velX = Game.clamp(velX, -10, 10);
        }

        if (x <= 0 || x >= Game.WIDTH - 96)
            velX *= -1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 96, 96);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

}