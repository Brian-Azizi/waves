package com.brian.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

    public static int HEALTH = 100;

    private int score = 0;
    private int level = 1;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);
        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.getHSBColor((1f * HEALTH) / 360, 1f, 1f));
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    public void score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}