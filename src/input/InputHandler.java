package input;

import core.Vector2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handler de input do teclado.
 */
public class InputHandler implements KeyListener {

    private boolean[] keys = new boolean[256];
    private boolean[] keysPressed = new boolean[256];

    // Key constants
    public static final int KEY_UP = KeyEvent.VK_W;
    public static final int KEY_DOWN = KeyEvent.VK_S;
    public static final int KEY_LEFT = KeyEvent.VK_A;
    public static final int KEY_RIGHT = KeyEvent.VK_D;
    public static final int KEY_UP_ALT = KeyEvent.VK_UP;
    public static final int KEY_DOWN_ALT = KeyEvent.VK_DOWN;
    public static final int KEY_LEFT_ALT = KeyEvent.VK_LEFT;
    public static final int KEY_RIGHT_ALT = KeyEvent.VK_RIGHT;
    public static final int KEY_ACTION = KeyEvent.VK_SPACE;
    public static final int KEY_CANCEL = KeyEvent.VK_ESCAPE;
    public static final int KEY_DEBUG_COLLISION = KeyEvent.VK_F1;
    public static final int KEY_DEBUG_GRID = KeyEvent.VK_F2;

    // =====================================================
    // UPDATE
    // =====================================================

    public void update() {
        System.arraycopy(keys, 0, keysPressed, 0, keys.length);
    }

    // =====================================================
    // QUERIES
    // =====================================================

    public boolean isKeyDown(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length)
            return false;
        return keys[keyCode];
    }

    public boolean isKeyPressed(int keyCode) {
        // Simple state check for now, can be expanded for "just pressed"
        return isKeyDown(keyCode);
    }

    public boolean isKeyReleased(int keyCode) {
        return !isKeyDown(keyCode);
    }

    public boolean isUp() {
        return isKeyDown(KEY_UP) || isKeyDown(KEY_UP_ALT);
    }

    public boolean isDown() {
        return isKeyDown(KEY_DOWN) || isKeyDown(KEY_DOWN_ALT);
    }

    public boolean isLeft() {
        return isKeyDown(KEY_LEFT);
    }

    public boolean isRight() {
        return isKeyDown(KEY_RIGHT);
    }

    public boolean isJump() {
        return isKeyDown(KEY_ACTION);
    }

    public Vector2 getMovementVector() {
        double x = 0;
        double y = 0;

        if (isLeft())
            x -= 1;
        if (isRight())
            x += 1;
        // if (isUp()) y -= 1; // Top-down only
        // if (isDown()) y += 1; // Top-down only

        return new Vector2(x, y);
    }

    // =====================================================
    // KEY LISTENER
    // =====================================================

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code >= 0 && code < keys.length) {
            keys[code] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code >= 0 && code < keys.length) {
            keys[code] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
