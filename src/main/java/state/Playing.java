package state;

import entities.Player;
import main.Game;
import main.GamePanel;
import utils.LoadResources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Playing extends State implements Statemethod {
    // Game Instance
    private Player player;
    // Game Image
    private BufferedImage treeBackground;
    public Playing(Game game) {
        super(game);
        initClasses();

        loadBackground();
    }

    private void loadBackground() {
        treeBackground = LoadResources.GetSpriteAtlas(LoadResources.TREE_BACKGROUND);
    }

    private void initClasses() {
        player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (80 * Game.SCALE), this);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(treeBackground, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            player.setAttacking(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> player.setLeft(true);
            case KeyEvent.VK_D -> player.setRight(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> player.setLeft(false);
            case KeyEvent.VK_D -> player.setRight(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
        }
    }
}
