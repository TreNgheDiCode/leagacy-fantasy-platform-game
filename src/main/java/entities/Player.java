package entities;

import main.Game;
import main.GamePanel;
import state.Playing;
import utils.LoadResources;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.ANI_SPEED;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {
    // Player animations
    private BufferedImage[][] animations;

    // Player's Attributes
    private boolean moving = false, attacking = false;
    private boolean left, right, jump;
    private int flipX = 0;
    private int flipW = 1;

    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);

        this.state = IDLE;
        this.walkSpeed = Game.SCALE;

        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[state][aniIndex], (int) (x + flipX), (int) (Game.GAME_HEIGHT / 2), width * flipW, height, null);
    }

    private void updatePos() {
        moving = false;

        if (jump)
            jump();

        if ((!left && !right) || (right && left))
            return;

        float xSpeed = 0;

        if (left && !right) {
            xSpeed -= walkSpeed;
            flipX = width;
            flipW = -1;
        }

        if (right && !left) {
            xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }

        updateXPos(xSpeed);

        moving = true;
    }

    private void jump() {
        if (inAir)
            return;
    }

    private void updateXPos(float xSpeed) {
        x += xSpeed;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(state)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    public void setAnimation() {
        int currAni = state;

        if (moving)
            state = RUNNING;
        else
            state = IDLE;

        if (attacking) {
            state = ATTACK;
            if (currAni != ATTACK) {
                aniIndex = 1;
                aniTick = 0;
                return;
            }
        }

        if (currAni != state)
            resetAniTick();
    }

    private void loadAnimations() {
        // Animations
        BufferedImage[] idleAnimation, runningAnimation, attackAnimation, deadAnimation, jumpAnimation, fallingAnimation;

        BufferedImage idleImg = LoadResources.GetSpriteAtlas(LoadResources.PLAYER_IDLE);
        BufferedImage runningImg = LoadResources.GetSpriteAtlas(LoadResources.PLAYER_RUNNING);
        BufferedImage attackImg = LoadResources.GetSpriteAtlas(LoadResources.PLAYER_ATTACK);
        BufferedImage deadImg = LoadResources.GetSpriteAtlas(LoadResources.PLAYER_DEAD);
        BufferedImage jumpImg = LoadResources.GetSpriteAtlas(LoadResources.PLAYER_JUMP);
        BufferedImage fallingImg = LoadResources.GetSpriteAtlas(LoadResources.PLAYER_FALLING);

        idleAnimation = new BufferedImage[GetSpriteAmount(IDLE)];
        runningAnimation = new BufferedImage[GetSpriteAmount(RUNNING)];
        attackAnimation = new BufferedImage[GetSpriteAmount(ATTACK)];
        deadAnimation = new BufferedImage[GetSpriteAmount(DEAD)];
        jumpAnimation = new BufferedImage[GetSpriteAmount(JUMP)];
        fallingAnimation = new BufferedImage[GetSpriteAmount(FALLING)];


        animations = new BufferedImage[6][8];
        for (int j = 0; j < animations.length; j++)
            switch (j) {
                case 0 -> {
                    for (int i = 0; i < idleAnimation.length; i++)
                        animations[j][i] = idleImg.getSubimage(i * 64, 0, 64, 80);
                }
                case 1 -> {
                    for (int i = 0; i < runningAnimation.length; i++)
                        animations[j][i] = runningImg.getSubimage(i * 80, 0, 80, 80);
                }
                case 2 -> {
                    for (int i = 0; i < attackAnimation.length; i++)
                        animations[j][i] = attackImg.getSubimage(i * 96, 0, 96, 80);
                }
                case 3 -> {
                    for (int i = 0; i < deadAnimation.length; i++)
                        animations[j][i] = deadImg.getSubimage(i * 80, 0, 80, 64);
                }
                case 4 -> {
                    for (int i = 0; i < jumpAnimation.length; i++)
                        animations[j][i] = jumpImg.getSubimage(i * 64, 0, 64, 64);
                }
                case 5 -> {
                    for (int i = 0; i < fallingAnimation.length; i++)
                        animations[j][i] = fallingImg.getSubimage(i * 64, 0, 64, 64);
                }
            }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
