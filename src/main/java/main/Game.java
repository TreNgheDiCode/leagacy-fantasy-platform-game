package main;

import state.Gamestate;
import state.Menu;
import state.Playing;
import utils.LoadResources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    // Init size GamePanel --> GameWindow
    public final static int TILES_DEFAULT_SIZE = 16;
    public final static float SCALE = 3f;
    public final static int TILES_IN_WIDTH = 30;
    public final static int TILES_IN_HEIGHT = 17;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // Window components
    private final GamePanel gamePanel;

    // Game components
    private Menu menu;
    private Playing playing;

    public Game() {
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);

        initClasses();

        gamePanel = new GamePanel(this);
        new GameWindow(gamePanel);
        gamePanel.requestFocusInWindow();

        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
        }
    }

    private void startGameLoop() {
        // Game Loop
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int FPS_SET = 120, UPS_SET = 200;

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (true) // true = shown, false = hide
                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                    frames = 0;
                    updates = 0;
                }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
