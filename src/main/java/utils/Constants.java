package utils;

import main.Game;

public class Constants {
    public static final float GRAVITY = 0.04f * Game.SCALE;
    public static final int ANI_SPEED = 25;
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int DEAD = 3;
        public static final int JUMP = 4;
        public static final int FALLING = 5;

        public static int GetSpriteAmount(int player_action) {
            return switch (player_action) {
                case FALLING -> 3;
                case IDLE, JUMP -> 4;
                case RUNNING, DEAD, ATTACK -> 8;
                default -> 1;
            };
        }
    }
}
