package tiles;

import core.GameConstants;
import geometry.CollisionBox;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

/**
 * Mapa de tiles com múltiplas camadas.
 */
public class TileMap {
    
    public static final int LAYER_FLOOR = 0;
    public static final int LAYER_WALL = 1;
    public static final int LAYER_DECORATION = 2;
    public static final int LAYER_WALL_TOP = 3;
    public static final int LAYER_COUNT = 4;
    
    private final int width, height;
    private final TileLayer[] layers;
    
    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.layers = new TileLayer[LAYER_COUNT];
        initializeLayers();
    }
    
    private void initializeLayers() {
        // TODO
    }
    
    // =====================================================
    // LAYER ACCESS
    // =====================================================
    
    public TileLayer getLayer(int index) {
        // TODO
        return null;
    }
    
    public TileLayer getFloorLayer() { return getLayer(LAYER_FLOOR); }
    public TileLayer getWallLayer() { return getLayer(LAYER_WALL); }
    public TileLayer getDecorationLayer() { return getLayer(LAYER_DECORATION); }
    
    // =====================================================
    // TILE ACCESS
    // =====================================================
    
    public void setTile(int layer, int x, int y, TileType type) {
        // TODO
    }
    
    public Tile getTile(int layer, int x, int y) {
        // TODO
        return null;
    }
    
    public void removeTile(int layer, int x, int y) {
        // TODO
    }
    
    // =====================================================
    // COLLISION
    // =====================================================
    
    public boolean isSolidAt(double worldX, double worldY) {
        // TODO
        return false;
    }
    
    public boolean isSolidAtGrid(int gridX, int gridY) {
        // TODO
        return false;
    }
    
    public List<CollisionBox> getSolidTilesIn(CollisionBox area) {
        // TODO
        return new ArrayList<>();
    }
    
    public List<Tile> getSolidTileObjectsIn(CollisionBox area) {
        // TODO
        return new ArrayList<>();
    }
    
    // =====================================================
    // COORDINATES
    // =====================================================
    
    public static int worldToGrid(double worldCoord) {
        return (int) Math.floor(worldCoord / GameConstants.TILE_SIZE);
    }
    
    public static int gridToWorld(int gridCoord) {
        return gridCoord * GameConstants.TILE_SIZE;
    }
    
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    
    // =====================================================
    // RENDERING
    // =====================================================
    
    public void renderBelow(Graphics2D g) {
        // TODO
    }
    
    public void renderAbove(Graphics2D g) {
        // TODO
    }
    
    public void renderLayer(Graphics2D g, int layerIndex) {
        // TODO
    }
    
    // =====================================================
    // GETTERS
    // =====================================================
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getPixelWidth() { return width * GameConstants.TILE_SIZE; }
    public int getPixelHeight() { return height * GameConstants.TILE_SIZE; }
    
    @Override
    public String toString() {
        return String.format("TileMap[%dx%d]", width, height);
    }
}
