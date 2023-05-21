package View;

public class ScreenConstants {
    public static final float GAME_SCALE = 3.0f;

    public static class MapSize{
        public static final int TILES_IN_X = 17;
        public static final int TILES_IN_Y = 13;
        public static final int TILES_SIZE = 16;
        public static final int SIZE_X = TILES_IN_X * TILES_SIZE;
        public static final int SIZE_Y = TILES_IN_Y * TILES_SIZE;

        public static final int MAP_X = (int)(SIZE_X * GAME_SCALE);
        public static final int MAP_Y = (int)(SIZE_Y*GAME_SCALE);
    }

}
