package com.brian.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

    public static int HEALTH = 100;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.getHSBColor((1f * HEALTH) / 360, 1f, 1f));
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
    }
}