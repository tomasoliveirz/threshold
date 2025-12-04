package tiles;

import java.awt.Color;

/**
 * Tipos de tiles.
 */
public enum TileType {
    
    EMPTY           (false,   null),
    FLOOR           (false,   new Color(45, 45, 55)),
    FLOOR_ALT       (false,   new Color(40, 40, 50)),
    WALL            (true,    new Color(60, 60, 75)),
    WALL_TOP        (false,   new Color(80, 80, 100)),
    WATER           (true,    new Color(30, 100, 180)),
    GRASS           (false,   new Color(34, 139, 34)),
    DECORATION      (false,   new Color(100, 80, 60));
    
    private final boolean solid;
    private final Color defaultColor;
    
    TileType(boolean solid, Color defaultColor) {
        this.solid = solid;
        this.defaultColor = defaultColor;
    }
    
    public boolean isSolid() { return solid; }
    public Color getDefaultColor() { return defaultColor; }
    public boolean isVisible() { return defaultColor != null; }
}
