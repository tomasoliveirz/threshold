package core;

/**
 * Constantes globais do jogo.
 */
public final class GameConstants {

    // =====================================================
    // TILE SYSTEM
    // =====================================================

    public static final int TILE_SIZE = 64;

    // =====================================================
    // SCREEN
    // =====================================================

    public static final int SCREEN_TILES_X = 25;
    public static final int SCREEN_TILES_Y = 19;
    public static final int SCREEN_WIDTH = SCREEN_TILES_X * TILE_SIZE;
    public static final int SCREEN_HEIGHT = SCREEN_TILES_Y * TILE_SIZE;

    // =====================================================
    // GAME LOOP
    // =====================================================

    public static final int FPS = 60;
    public static final double FRAME_TIME_MS = 1000.0 / FPS;

    // =====================================================
    // PHYSICS
    // =====================================================

    public static final double PLAYER_SPEED = 5.0; // Increased for platformer feel

    public static final double GRAVITY = 0.5;
    public static final double JUMP_FORCE = 12.0;
    public static final double MAX_FALL_SPEED = 15.0;

    public static final double DIAGONAL_FACTOR = 0.70710678118;

    // =====================================================
    // DEBUG FLAGS
    // =====================================================

    public static boolean DEBUG_COLLISIONS = false;
    public static boolean DEBUG_GRID = false;
    public static boolean DEBUG_ENTITIES = false;

    private GameConstants() {
    }
}
