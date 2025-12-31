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
        layers[LAYER_FLOOR] = new TileLayer("Floor", width, height);
        layers[LAYER_WALL] = new TileLayer("Wall", width, height);
        layers[LAYER_DECORATION] = new TileLayer("Decoration", width, height);
        layers[LAYER_WALL_TOP] = new TileLayer("WallTop", width, height, true);
    }

    // =====================================================
    // LAYER ACCESS
    // =====================================================

    public TileLayer getLayer(int index) {
        if (index >= 0 && index < LAYER_COUNT) {
            return layers[index];
        }
        return null;
    }

    public TileLayer getFloorLayer() {
        return getLayer(LAYER_FLOOR);
    }

    public TileLayer getWallLayer() {
        return getLayer(LAYER_WALL);
    }

    public TileLayer getDecorationLayer() {
        return getLayer(LAYER_DECORATION);
    }

    // =====================================================
    // TILE ACCESS
    // =====================================================

    public void setTile(int layer, int x, int y, TileType type) {
        TileLayer tl = getLayer(layer);
        if (tl != null && isValidPosition(x, y)) {
            tl.setTile(x, y, new Tile(x, y, type));
        }
    }

    public Tile getTile(int layer, int x, int y) {
        TileLayer tl = getLayer(layer);
        if (tl != null && isValidPosition(x, y)) {
            return tl.getTile(x, y);
        }
        return null;
    }

    public void removeTile(int layer, int x, int y) {
        TileLayer tl = getLayer(layer);
        if (tl != null && isValidPosition(x, y)) {
            tl.setTile(x, y, (Tile) null);
        }
    }

    // =====================================================
    // COLLISION
    // =====================================================

    public boolean isSolidAt(double worldX, double worldY) {
        int gx = worldToGrid(worldX);
        int gy = worldToGrid(worldY);

        if (!isValidPosition(gx, gy))
            return true; // Bounds are solid

        // Check all layers for solid tiles
        for (TileLayer layer : layers) {
            Tile tile = layer.getTile(gx, gy);
            if (tile != null && tile.isSolid()) {
                return true;
            }
        }
        return false;
    }

    public boolean isSolidAtGrid(int gridX, int gridY) {
        if (!isValidPosition(gridX, gridY))
            return true;

        for (TileLayer layer : layers) {
            Tile tile = layer.getTile(gridX, gridY);
            if (tile != null && tile.isSolid()) {
                return true;
            }
        }
        return false;
    }

    public List<CollisionBox> getSolidTilesIn(CollisionBox area) {
        List<CollisionBox> boxes = new ArrayList<>();

        int startX = worldToGrid(area.getLeft());
        int endX = worldToGrid(area.getRight());
        int startY = worldToGrid(area.getTop());
        int endY = worldToGrid(area.getBottom());

        // Clamp to map bounds + 1 buffer
        startX = Math.max(0, startX);
        startY = Math.max(0, startY);
        endX = Math.min(width - 1, endX);
        endY = Math.min(height - 1, endY);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                if (isSolidAtGrid(x, y)) {
                    boxes.add(new CollisionBox(
                            x * GameConstants.TILE_SIZE,
                            y * GameConstants.TILE_SIZE,
                            GameConstants.TILE_SIZE,
                            GameConstants.TILE_SIZE));
                }
            }
        }

        return boxes;
    }

    public List<Tile> getSolidTileObjectsIn(CollisionBox area) {
        // Not strictly needed for basic physics, skipping for now to save space
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
        renderLayer(g, LAYER_FLOOR);
        renderLayer(g, LAYER_WALL);
        renderLayer(g, LAYER_DECORATION);
    }

    public void renderAbove(Graphics2D g) {
        renderLayer(g, LAYER_WALL_TOP);
    }

    public void renderLayer(Graphics2D g, int layerIndex) {
        TileLayer layer = getLayer(layerIndex);
        if (layer == null)
            return;

        // Simple render loop, could be optimized for view frustum
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = layer.getTile(x, y);
                if (tile != null) {
                    tile.render(g);
                }
            }
        }
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPixelWidth() {
        return width * GameConstants.TILE_SIZE;
    }

    public int getPixelHeight() {
        return height * GameConstants.TILE_SIZE;
    }

    @Override
    public String toString() {
        return String.format("TileMap[%dx%d]", width, height);
    }
}
