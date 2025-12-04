package tiles;

import core.GameConstants;
import core.Vector2;
import geometry.CollisionBox;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Tile individual.
 */
public class Tile {
    
    private final int gridX, gridY;
    private final TileType type;
    private final Color color;
    
    public Tile(int gridX, int gridY, TileType type) {
        this(gridX, gridY, type, null);
    }
    
    public Tile(int gridX, int gridY, TileType type, Color colorOverride) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.type = type;
        this.color = colorOverride != null ? colorOverride : type.getDefaultColor();
    }
    
    // =====================================================
    // POSITION
    // =====================================================
    
    public int getGridX() { return gridX; }
    public int getGridY() { return gridY; }
    
    public int getWorldX() {
        return gridX * GameConstants.TILE_SIZE;
    }
    
    public int getWorldY() {
        return gridY * GameConstants.TILE_SIZE;
    }
    
    public Vector2 getWorldPosition() {
        // TODO
        return null;
    }
    
    // =====================================================
    // PROPERTIES
    // =====================================================
    
    public TileType getType() { return type; }
    public Color getColor() { return color; }
    public boolean isSolid() { return type.isSolid(); }
    public boolean isVisible() { return color != null; }
    
    // =====================================================
    // COLLISION
    // =====================================================
    
    public CollisionBox getCollisionBox() {
        // TODO
        return null;
    }
    
    // =====================================================
    // RENDERING
    // =====================================================
    
    public void render(Graphics2D g) {
        // TODO
    }
    
    public void renderDebug(Graphics2D g) {
        // TODO
    }
    
    @Override
    public String toString() {
        return String.format("Tile[%d,%d]=%s", gridX, gridY, type);
    }
}
