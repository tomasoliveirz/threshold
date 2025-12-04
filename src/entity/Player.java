package entity;

import core.GameConstants;
import core.Vector2;
import collision.CollisionSystem;
import input.InputHandler;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Entidade do jogador.
 */
public class Player extends Entity {
    
    private double speed = GameConstants.PLAYER_SPEED;
    private CollisionSystem collisionSystem;
    private InputHandler inputHandler;
    
    private int facingX = 0;
    private int facingY = 1;
    private boolean moving = false;
    
    public Player(double x, double y) {
        super(x, y, 24, 32, 20, 12, 20);
        this.tag = "player";
    }
    
    // =====================================================
    // DEPENDENCIES
    // =====================================================
    
    public void setCollisionSystem(CollisionSystem cs) {
        this.collisionSystem = cs;
    }
    
    public void setInputHandler(InputHandler ih) {
        this.inputHandler = ih;
    }
    
    // =====================================================
    // UPDATE
    // =====================================================
    
    @Override
    public void update() {
        // TODO
    }
    
    private Vector2 processInput() {
        // TODO
        return Vector2.ZERO;
    }
    
    // =====================================================
    // RENDERING
    // =====================================================
    
    @Override
    public void render(Graphics2D g) {
        // TODO
    }
    
    @Override
    public void renderShadow(Graphics2D g) {
        // TODO
    }
    
    // =====================================================
    // GETTERS
    // =====================================================
    
    public double getSpeed() { return speed; }
    public void setSpeed(double s) { this.speed = s; }
    public int getFacingX() { return facingX; }
    public int getFacingY() { return facingY; }
    public boolean isMoving() { return moving; }
}
