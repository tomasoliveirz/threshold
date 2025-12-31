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

    // =====================================================
    // TILE MANAGEMENT
    // =====================================================

    public void setTile(int x, int y, Tile tile) {
        if (isValidPosition(x, y)) {
            tiles[y][x] = tile;
        }
    }

    public void setTile(int x, int y, TileType type) {
        if (isValidPosition(x, y)) {
            setTile(x, y, new Tile(x, y, type));
        }
    }

    public Tile getTile(int x, int y) {
        if (isValidPosition(x, y)) {
            return tiles[y][x];
        }
        return null;
    }

    public void removeTile(int x, int y) {
        if (isValidPosition(x, y)) {
            tiles[y][x] = null;
        }
    }

    public void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = null;
            }
        }
    }

    public void fill(TileType type) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setTile(x, y, type);
            }
        }
    }

    // =====================================================
    // QUERIES
    // =====================================================

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isSolidAt(int x, int y) {
        Tile tile = getTile(x, y);
        return tile != null && tile.isSolid();
    }

    // =====================================================
    // RENDERING
    // =====================================================

    public void render(Graphics2D g) {
        render(g, 0, 0, width - 1, height - 1);
    }

    public void render(Graphics2D g, int startX, int startY, int endX, int endY) {
        startX = Math.max(0, startX);
        startY = Math.max(0, startY);
        endX = Math.min(width - 1, endX);
        endY = Math.min(height - 1, endY);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                Tile tile = tiles[y][x];
                if (tile != null) {
                    tile.render(g);
                }
            }
        }
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isRenderAboveEntities() {
        return renderAboveEntities;
    }

    @Override
    public String toString() {
        return String.format("TileLayer[%s, %dx%d]", name, width, height);
    }
}
