package entity;

import core.Vector2;
import geometry.CollisionBox;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

/**
 * Classe base para entidades.
 */
public abstract class Entity implements Renderable {
    
    protected Vector2 position;
    protected Vector2 velocity;
    
    protected int width, height;
    protected int collisionWidth, collisionHeight;
    protected int collisionOffsetY;
    
    protected boolean solid = true;
    protected boolean active = true;
    protected String tag = "";
    
    public Entity(double x, double y, int width, int height,
                  int colWidth, int colHeight, int colOffsetY) {
        this.position = new Vector2(x, y);
        this.velocity = Vector2.ZERO;
        this.width = width;
        this.height = height;
        this.collisionWidth = colWidth;
        this.collisionHeight = colHeight;
        this.collisionOffsetY = colOffsetY;
    }
    
    public Entity(double x, double y, int width, int height) {
        this(x, y, width, height, width, height, 0);
    }
    
    // =====================================================
    // ABSTRACT
    // =====================================================
    
    public abstract void update();
    
    // =====================================================
    // COLLISION
    // =====================================================
    
    public CollisionBox getCollisionBox() {
        // TODO
        return null;
    }
    
    public void setPositionFromCollisionBox(CollisionBox box) {
        // TODO
    }
    
    // =====================================================
    // MOVEMENT
    // =====================================================
    
    public void move(double dx, double dy) {
        // TODO
    }
    
    public void move(Vector2 delta) {
        // TODO
    }
    
    public void setPosition(double x, double y) {
        // TODO
    }
    
    public void setPosition(Vector2 pos) {
        // TODO
    }
    
    // =====================================================
    // RENDERING
    // =====================================================
    
    @Override
    public void renderShadow(Graphics2D g) {
        // TODO
    }
    
    public void renderDebug(Graphics2D g) {
        // TODO
    }
    
    @Override
    public double getSortY() {
        return position.y;
    }
    
    @Override
    public boolean isVisible() {
        return active;
    }
    
    // =====================================================
    // GETTERS
    // =====================================================
    
    public double getX() { return position.x; }
    public double getY() { return position.y; }
    public Vector2 getPosition() { return position; }
    public Vector2 getVelocity() { return velocity; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public Rectangle getVisualBounds() {
        // TODO
        return null;
    }
    
    // =====================================================
    // PROPERTIES
    // =====================================================
    
    public boolean isSolid() { return solid; }
    public void setSolid(boolean s) { this.solid = s; }
    public boolean isActive() { return active; }
    public void setActive(boolean a) { this.active = a; }
    public String getTag() { return tag; }
    public void setTag(String t) { this.tag = t; }
    
    @Override
    public String toString() {
        return String.format("%s[%.1f,%.1f]", getClass().getSimpleName(), position.x, position.y);
    }
}
