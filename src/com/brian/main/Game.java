package com.brian.main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -8993431008488886099L;

    public final static int WIDTH = 640, HEIGHT = WIDTH * 9 / 12;

    private Thread thread;
    private boolean running = false;

    public Game() {
        new Window(WIDTH, HEIGHT, "Waves", this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {

    }

    public static void main(String[] args) {
        new Game();
    }
}