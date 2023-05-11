package GameInfo;

public class PlayerConstants {

    public static final float PLAYER_SPEED = 3.0f;
    public static final int WALK_HITBOX = 16;
    public static final int DAMAGE_HITBOX_X = 16;
    public static final int DAMAGE_HITBOX_Y = 24;


    public static class PlayerAction{
        public static final int STAY = 0;
        public static final int GO_UP = 1;
        public static final int GO_DOWN = 2;
        public static final int GO_LEFT = 3;
        public static final int GO_RIGHT = 4;
        public static final int DIE = 5;
        public static final int GO_NEXT_LEVEL = 6;
        public static final int BOSS_DEFEATED = 7;
    }


}
