package core;

/**
 * Vetor 2D imutável.
 */
public class Vector2 {

    public static final Vector2 ZERO = new Vector2(0, 0);
    public static final Vector2 UP = new Vector2(0, -1);
    public static final Vector2 DOWN = new Vector2(0, 1);
    public static final Vector2 LEFT = new Vector2(-1, 0);
    public static final Vector2 RIGHT = new Vector2(1, 0);

    public final double x;
    public final double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this(0, 0);
    }

    // =====================================================
    // OPERATIONS
    // =====================================================

    // =====================================================
    // OPERATIONS
    // =====================================================

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 add(double dx, double dy) {
        return new Vector2(x + dx, y + dy);
    }

    public Vector2 sub(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public Vector2 mul(double scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public Vector2 div(double scalar) {
        if (scalar == 0)
            return ZERO;
        return new Vector2(x / scalar, y / scalar);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public Vector2 normalize() {
        double len = length();
        if (len == 0)
            return ZERO;
        return div(len);
    }

    public double dot(Vector2 other) {
        return x * other.x + y * other.y;
    }

    public double distanceTo(Vector2 other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Vector2 negate() {
        return new Vector2(-x, -y);
    }

    // =====================================================
    // GRID CONVERSION
    // =====================================================

    public int toGridX() {
        return (int) Math.floor(x / GameConstants.TILE_SIZE);
    }

    public int toGridY() {
        return (int) Math.floor(y / GameConstants.TILE_SIZE);
    }

    public static Vector2 fromGrid(int gridX, int gridY) {
        return new Vector2(
                gridX * GameConstants.TILE_SIZE + GameConstants.TILE_SIZE / 2.0,
                gridY * GameConstants.TILE_SIZE + GameConstants.TILE_SIZE / 2.0);
    }

    // =====================================================
    // OBJECT OVERRIDES
    // =====================================================

    @Override
    public String toString() {
        return String.format("Vector2(%.2f, %.2f)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Vector2))
            return false;
        Vector2 other = (Vector2) obj;
        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y);
    }
}
