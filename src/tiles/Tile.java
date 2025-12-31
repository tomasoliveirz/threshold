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

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public int getWorldX() {
        return gridX * GameConstants.TILE_SIZE;
    }

    public int getWorldY() {
        return gridY * GameConstants.TILE_SIZE;
    }

    public Vector2 getWorldPosition() {
        return new Vector2(getWorldX(), getWorldY());
    }

    // =====================================================
    // PROPERTIES
    // =====================================================

    public TileType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSolid() {
        return type.isSolid();
    }

    public boolean isVisible() {
        return color != null;
    }

    // =====================================================
    // COLLISION
    // =====================================================

    public CollisionBox getCollisionBox() {
        if (!isSolid())
            return null;
        return new CollisionBox(getWorldX(), getWorldY(), GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
    }

    // =====================================================
    // RENDERING
    // =====================================================

    public void render(Graphics2D g) {
        if (!isVisible())
            return;

        g.setColor(color);
        g.fillRect(getWorldX(), getWorldY(), GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);

        // Optional: Draw border for better visibility
        g.setColor(color.darker());
        g.drawRect(getWorldX(), getWorldY(), GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
    }

    public void renderDebug(Graphics2D g) {
        if (isSolid()) {
            g.setColor(Color.RED);
            g.drawRect(getWorldX(), getWorldY(), GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        }
        g.setColor(Color.WHITE);
        g.drawString(gridX + "," + gridY, getWorldX() + 2, getWorldY() + 10);
    }

    @Override
    public String toString() {
        return String.format("Tile[%d,%d]=%s", gridX, gridY, type);
    }
}
