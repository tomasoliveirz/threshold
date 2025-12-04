package collision;

import core.Vector2;
import geometry.CollisionBox;
import tiles.Tile;
import tiles.TileMap;
import entity.Entity;
import java.util.List;

/**
 * Sistema de colisões.
 */
public class CollisionSystem {
    
    private TileMap tileMap;
    
    public CollisionSystem(TileMap tileMap) {
        this.tileMap = tileMap;
    }
    
    public CollisionSystem() {
        this(null);
    }
    
    public void setTileMap(TileMap tileMap) {
        this.tileMap = tileMap;
    }
    
    public TileMap getTileMap() {
        return tileMap;
    }
    
    // =====================================================
    // MAIN COLLISION
    // =====================================================
    
    public CollisionResult moveAndSlide(Entity entity, Vector2 movement) {
        // TODO
        return null;
    }
    
    public CollisionResult moveAndSlide(Entity entity, double dx, double dy) {
        // TODO
        return null;
    }
    
    protected double moveX(Entity entity, double dx) {
        // TODO
        return 0;
    }
    
    protected double moveY(Entity entity, double dy) {
        // TODO
        return 0;
    }
    
    // =====================================================
    // DETECTION
    // =====================================================
    
    public boolean checkCollision(CollisionBox box) {
        // TODO
        return false;
    }
    
    public boolean canMove(Entity entity, double dx, double dy) {
        // TODO
        return false;
    }
    
    public boolean checkEntityCollision(Entity a, Entity b) {
        // TODO
        return false;
    }
    
    // =====================================================
    // QUERIES
    // =====================================================
    
    public List<Tile> getCollidingTiles(CollisionBox box) {
        // TODO
        return null;
    }
    
    public boolean isPointSolid(double x, double y) {
        // TODO
        return false;
    }
    
    public boolean isPointSolid(Vector2 point) {
        return isPointSolid(point.x, point.y);
    }
    
    public Tile raycast(Vector2 origin, Vector2 direction, double maxDist) {
        // TODO (avançado)
        return null;
    }
}
