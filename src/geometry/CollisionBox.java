package geometry;

import core.Vector2;
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

    public double getLeft() {
        return x;
    }

    public double getRight() {
        return x + width;
    }

    public double getTop() {
        return y;
    }

    public double getBottom() {
        return y + height;
    }

    public double getCenterX() {
        return x + width / 2;
    }

    public double getCenterY() {
        return y + height / 2;
    }

    public Vector2 getCenter() {
        return new Vector2(getCenterX(), getCenterY());
    }

    // =====================================================
    // COLLISION
    // =====================================================

    public boolean intersects(CollisionBox other) {
        return this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + this.height > other.y;
    }

    public boolean contains(double px, double py) {
        return px >= this.x && px <= this.x + this.width &&
                py >= this.y && py <= this.y + this.height;
    }

    public boolean contains(Vector2 point) {
        return contains(point.x, point.y);
    }

    // =====================================================
    // TRANSFORMATIONS
    // =====================================================

    public CollisionBox offset(double dx, double dy) {
        return new CollisionBox(x + dx, y + dy, width, height);
    }

    public CollisionBox offset(Vector2 delta) {
        return offset(delta.x, delta.y);
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setCenter(double cx, double cy) {
        this.x = cx - width / 2;
        this.y = cy - height / 2;
    }

    public CollisionBox expand(double amount) {
        return new CollisionBox(x - amount, y - amount, width + amount * 2, height + amount * 2);
    }

    // =====================================================
    // RESOLUTION
    // =====================================================

    public Vector2 getOverlap(CollisionBox other) {
        if (!intersects(other))
            return Vector2.ZERO;

        double overlapX = Math.min(this.x + this.width, other.x + other.width) - Math.max(this.x, other.x);
        double overlapY = Math.min(this.y + this.height, other.y + other.height) - Math.max(this.y, other.y);

        return new Vector2(overlapX, overlapY);
    }

    public Vector2 getMinimumTranslation(CollisionBox other) {
        // Simplified for now, can be added if needed for precise resolution
        return Vector2.ZERO;
    }

    // =====================================================
    // DEBUG
    // =====================================================

    public void renderDebug(Graphics2D g, Color color) {
        g.setColor(color);
        g.drawRect((int) x, (int) y, (int) width, (int) height);
    }

    public void renderDebug(Graphics2D g) {
        renderDebug(g, new Color(255, 0, 0, 100));
    }

    @Override
    public String toString() {
        return String.format("CollisionBox[%.1f, %.1f, %.1f, %.1f]", x, y, width, height);
    }
}
