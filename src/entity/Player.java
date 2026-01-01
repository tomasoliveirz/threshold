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

    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public void update() {
        if (inputHandler == null || collisionSystem == null)
            return;

        // Input
        // Input Logic: A (Left), D (Right), Space (Jump)
        double moveDir = 0;
        if (inputHandler.isLeft())
            moveDir = -1;
        if (inputHandler.isRight())
            moveDir = 1; // Prioritize right if both pressed or cancel? usually cancel:
        if (inputHandler.isLeft() && inputHandler.isRight())
            moveDir = 0;

        if (moveDir != 0) {
            facingX = (int) moveDir;
            moving = true;
        } else {
            moving = false;
        }

        // Calculate Velocity (Simple: Direct speed set, maybe add acceleration later)
        double targetSpeedX = moveDir * speed;
        // Basic inertia or instant? Let's do a bit of smoothing/acceleration or just
        // instant for tight platformer.
        // Let's do instant X velocity for now based on request "move left/right"

        double vy = velocity.y;

        // Gravity
        vy += GameConstants.GRAVITY;

        // Jump
        // We need to know if on ground. Check CollisionSystem result from previous
        // frame?
        // Or check now. Let's do a check.
        boolean onGround = collisionSystem.canMove(this, 0, 1) == false && collisionSystem.canMove(this, 0, -1) == true;
        // Wait, canMove(0,1) false means something is below.
        // Actually CollisionResult tells us if we are on ground.
        // We need to persist that state or check it.
        // Let's check overlap slightly below.
        onGround = collisionSystem.checkCollision(getCollisionBox().offset(0, 1));

        if (inputHandler.isJump() && onGround) {
            vy = -GameConstants.JUMP_FORCE;
        }

        // Apply Movement
        // We reset X velocity every frame if we want instant stop?
        // Or we accumulate.
        // Platformers usually: apply forces or set velocity.
        // Let's set X, accumulate Y.

        // Max fall speed
        if (vy > GameConstants.MAX_FALL_SPEED)
            vy = GameConstants.MAX_FALL_SPEED;

        collision.CollisionResult res = collisionSystem.moveAndSlide(this, targetSpeedX, vy);

        // Update velocity based on collision (e.g. stop if hit wall/floor)
        double newVx = targetSpeedX;
        double newVy = vy;

        if (res.collidedX)
            newVx = 0;
        if (res.collidedY)
            newVy = 0;

        this.velocity = new Vector2(newVx, newVy);
    }

    // =====================================================
    // RENDERING
    // =====================================================

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) position.x, (int) position.y, width, height);

        // Optional: Eye direction
        g.setColor(Color.WHITE);
        int eyeX = (int) position.x + (facingX > 0 ? 14 : 4);
        g.fillRect(eyeX, (int) position.y + 6, 4, 4);
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double s) {
        this.speed = s;
    }

    public int getFacingX() {
        return facingX;
    }

    public int getFacingY() {
        return facingY;
    }

    public boolean isMoving() {
        return moving;
    }
}
