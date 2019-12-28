package com.brian.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -8993431008488886099L;

    public final static int WIDTH = 640, HEIGHT = WIDTH * 9 / 12;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawn;

    public Game() {
        handler = new Handler();
        r = new Random();
        this.addKeyListener(new KeyInput(handler));
        hud = new HUD();
        spawn = new Spawn(handler, hud);

        new Window(WIDTH, HEIGHT, "Waves", this);

        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
        handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy, handler));
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
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // num of frames that were supposed to have passed?
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
        spawn.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) {
        if (var > max)
            return max;
        else if (var < min)
            return min;
        return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}