package tiles;

import java.awt.Graphics2D;

/**
 * Uma camada de tiles.
 */
public class TileLayer {
    
    private final String name;
    private final Tile[][] tiles;
    private final int width, height;
    private final boolean renderAboveEntities;
    
    public TileLayer(String name, int width, int height, boolean renderAboveEntities) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.renderAboveEntities = renderAboveEntities;
        this.tiles = new Tile[height][width];
    }
    
    public TileLayer(String name, int width, int height) {
        this(name, width, height, false);
    }
    
    // =====================================================
    // TILE MANAGEMENT
    // =====================================================
    
    public void setTile(int x, int y, Tile tile) {
        // TODO
    }
    
    public void setTile(int x, int y, TileType type) {
        // TODO
    }
    
    public Tile getTile(int x, int y) {
        // TODO
        return null;
    }
    
    public void removeTile(int x, int y) {
        // TODO
    }
    
    public void clear() {
        // TODO
    }
    
    public void fill(TileType type) {
        // TODO
    }
    
    // =====================================================
    // QUERIES
    // =====================================================
    
    public boolean isValidPosition(int x, int y) {
        // TODO
        return false;
    }
    
    public boolean isSolidAt(int x, int y) {
        // TODO
        return false;
    }
    
    // =====================================================
    // RENDERING
    // =====================================================
    
    public void render(Graphics2D g) {
        // TODO
    }
    
    public void render(Graphics2D g, int startX, int startY, int endX, int endY) {
        // TODO
    }
    
    // =====================================================
    // GETTERS
    // =====================================================
    
    public String getName() { return name; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isRenderAboveEntities() { return renderAboveEntities; }
    
    @Override
    public String toString() {
        return String.format("TileLayer[%s, %dx%d]", name, width, height);
    }
}
