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
    private boolean[] keysReleased = new boolean[256];
    
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
        // TODO
    }
    
    // =====================================================
    // QUERIES
    // =====================================================
    
    public boolean isKeyDown(int keyCode) {
        // TODO
        return false;
    }
    
    public boolean isKeyPressed(int keyCode) {
        // TODO
        return false;
    }
    
    public boolean isKeyReleased(int keyCode) {
        // TODO
        return false;
    }
    
    public boolean isUp() {
        // TODO
        return false;
    }
    
    public boolean isDown() {
        // TODO
        return false;
    }
    
    public boolean isLeft() {
        // TODO
        return false;
    }
    
    public boolean isRight() {
        // TODO
        return false;
    }
    
    public Vector2 getMovementVector() {
        // TODO
        return Vector2.ZERO;
    }
    
    // =====================================================
    // KEY LISTENER
    // =====================================================
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
