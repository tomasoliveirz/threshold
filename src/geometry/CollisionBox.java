package geometry;

import core.Vector2;
import core.GameConstants;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Axis-Aligned Bounding Box para colisões.
 */
public class CollisionBox {
    
    public double x, y;
    public double width, height;
    
    public CollisionBox(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public CollisionBox() {
        this(0, 0, 0, 0);
    }
    
    public CollisionBox copy() {
        return new CollisionBox(x, y, width, height);
    }
    
    // =====================================================
    // EDGES
    // =====================================================
    
    public double getLeft()   { return x; }
    public double getRight()  { return x + width; }
    public double getTop()    { return y; }
    public double getBottom() { return y + height; }
    public double getCenterX() { return x + width / 2; }
    public double getCenterY() { return y + height / 2; }
    
    public Vector2 getCenter() {
        // TODO
        return null;
    }
    
    // =====================================================
    // COLLISION
    // =====================================================
    
    public boolean intersects(CollisionBox other) {
        // TODO
        return false;
    }
    
    public boolean contains(double px, double py) {
        // TODO
        return false;
    }
    
    public boolean contains(Vector2 point) {
        // TODO
        return false;
    }
    
    // =====================================================
    // TRANSFORMATIONS
    // =====================================================
    
    public CollisionBox offset(double dx, double dy) {
        // TODO
        return null;
    }
    
    public CollisionBox offset(Vector2 delta) {
        // TODO
        return null;
    }
    
    public void translate(double dx, double dy) {
        // TODO
    }
    
    public void setCenter(double cx, double cy) {
        // TODO
    }
    
    public CollisionBox expand(double amount) {
        // TODO
        return null;
    }
    
    // =====================================================
    // RESOLUTION
    // =====================================================
    
    public Vector2 getOverlap(CollisionBox other) {
        // TODO
        return null;
    }
    
    public Vector2 getMinimumTranslation(CollisionBox other) {
        // TODO
        return null;
    }
    
    // =====================================================
    // DEBUG
    // =====================================================
    
    public void renderDebug(Graphics2D g, Color color) {
        // TODO
    }
    
    public void renderDebug(Graphics2D g) {
        renderDebug(g, new Color(255, 0, 0, 100));
    }
    
    @Override
    public String toString() {
        return String.format("CollisionBox[%.1f, %.1f, %.1f, %.1f]", x, y, width, height);
    }
}
