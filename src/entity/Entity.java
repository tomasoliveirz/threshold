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
        return new CollisionBox(
                position.x + (width - collisionWidth) / 2.0,
                position.y + collisionOffsetY,
                collisionWidth,
                collisionHeight);
    }

    public void setPositionFromCollisionBox(CollisionBox box) {
        double newX = box.x - (width - collisionWidth) / 2.0;
        double newY = box.y - collisionOffsetY;
        setPosition(newX, newY);
    }

    // =====================================================
    // MOVEMENT
    // =====================================================

    public void move(double dx, double dy) {
        this.position = this.position.add(dx, dy);
    }

    public void move(Vector2 delta) {
        this.position = this.position.add(delta);
    }

    public void setPosition(double x, double y) {
        this.position = new Vector2(x, y);
    }

    public void setPosition(Vector2 pos) {
        this.position = pos;
    }

    public void setX(double x) {
        this.position = new Vector2(x, this.position.y);
    }

    public void setY(double y) {
        this.position = new Vector2(this.position.x, y);
    }

    // =====================================================
    // RENDERING
    // =====================================================

    @Override
    public void renderShadow(Graphics2D g) {
        // Optional shadow impl
    }

    public void renderDebug(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawRect((int) position.x, (int) position.y, width, height);

        CollisionBox box = getCollisionBox();
        if (box != null) {
            box.renderDebug(g, Color.RED);
        }
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

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getVisualBounds() {
        return new Rectangle((int) position.x, (int) position.y, width, height);
    }

    // =====================================================
    // PROPERTIES
    // =====================================================

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean s) {
        this.solid = s;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean a) {
        this.active = a;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return String.format("%s[%.1f,%.1f]", getClass().getSimpleName(), position.x, position.y);
    }
}
