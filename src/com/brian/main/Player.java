package com.brian.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    public Player(int x, int y, ID id) {
        super(x, y, id);
        setVolX(1);
        setVolY(1);
    }

    public void tick() {
        x += volX;
        y += volY;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}