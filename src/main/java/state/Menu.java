package state;

import main.Game;
import utils.LoadResources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethod {
    BufferedImage backgroundImage;
    public Menu(Game game) {
        super(game);
        loadBackground();
    }

    private void loadBackground() {
        backgroundImage = LoadResources.GetSpriteAtlas(LoadResources.MENU_BACKGROUND);
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            setGameState(Gamestate.PLAYING);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
