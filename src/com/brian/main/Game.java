package com.brian.main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -8993431008488886099L;
    public final static int WIDTH = 640, HEIGHT = WIDTH * 9 / 12;

    public Game() {
        new Window(WIDTH, HEIGHT, "Waves", this);
    }

    public synchronized void start() {

    }

    public void run() {

    }

    public static void main(String[] args) {
        new Game();
    }
}