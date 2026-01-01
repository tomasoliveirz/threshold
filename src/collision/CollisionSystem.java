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
        return moveAndSlide(entity, movement.x, movement.y);
    }

    public CollisionResult moveAndSlide(Entity entity, double dx, double dy) {
        double movedX = moveX(entity, dx);
        double movedY = moveY(entity, dy);

        // Determine collision flags
        boolean collidedX = Math.abs(movedX) < Math.abs(dx);
        boolean collidedY = Math.abs(movedY) < Math.abs(dy);

        return CollisionResult.withTile(
                new Vector2(movedX, movedY),
                new Vector2(movedX, movedY), // Applied
                new Vector2(dx - movedX, dy - movedY), // Blocked
                null, // Tile info simplified for now
                collidedX,
                collidedY);
    }

    protected double moveX(Entity entity, double dx) {
        if (dx == 0)
            return 0;

        CollisionBox box = entity.getCollisionBox();
        CollisionBox targetBox = box.offset(dx, 0);

        List<CollisionBox> walls = tileMap.getSolidTilesIn(targetBox);

        double originalBoxX = targetBox.x;
        double adjustedBoxX = originalBoxX;

        for (CollisionBox wall : walls) {
            // Re-check overlap with potentially adjusted box?
            // Simple sweeping AABB often checks if 'targetBox' intersects.
            // But if we have multiple walls, we need the "most restrictive" logic.
            // For now, simple iteration.

            // Re-create target box at current adjusted pos to see if we still collide?
            // Or just check against the intended target.
            // Proper way: find first collision time.
            // Simplified way (discrete): check intersection.

            if (targetBox.intersects(wall)) {
                // Determine corrections
                if (dx > 0) {
                    // Moving Right: Snap to Left of Wall
                    double wallLeft = wall.x;
                    double possibleBoxX = wallLeft - box.width;
                    if (possibleBoxX < adjustedBoxX) {
                        adjustedBoxX = possibleBoxX;
                    }
                } else {
                    // Moving Left: Snap to Right of Wall
                    double wallRight = wall.x + wall.width;
                    double possibleBoxX = wallRight;
                    if (possibleBoxX > adjustedBoxX) { // We want the right-most limit
                        adjustedBoxX = possibleBoxX;
                    }
                }
            }
        }

        // Update targetBox to final X to set entity
        targetBox.x = adjustedBoxX;

        entity.setPositionFromCollisionBox(targetBox);

        // Return actual moved distance
        return adjustedBoxX - box.x;
    }

    protected double moveY(Entity entity, double dy) {
        if (dy == 0)
            return 0;

        CollisionBox box = entity.getCollisionBox();
        CollisionBox targetBox = box.offset(0, dy);

        List<CollisionBox> walls = tileMap.getSolidTilesIn(targetBox);

        double originalBoxY = targetBox.y;
        double adjustedBoxY = originalBoxY;

        for (CollisionBox wall : walls) {
            if (targetBox.intersects(wall)) {
                if (dy > 0) {
                    // Falling: Snap to Top of Wall
                    double wallTop = wall.y;
                    double possibleBoxY = wallTop - box.height;
                    // We want the HIGHEST valid ground (lowest Y value)
                    if (possibleBoxY < adjustedBoxY) {
                        adjustedBoxY = possibleBoxY;
                    }
                } else {
                    // Jumping: Snap to Bottom of Wall
                    double wallBottom = wall.y + wall.height;
                    double possibleBoxY = wallBottom;
                    if (possibleBoxY > adjustedBoxY) {
                        adjustedBoxY = possibleBoxY;
                    }
                }
            }
        }

        targetBox.y = adjustedBoxY;

        entity.setPositionFromCollisionBox(targetBox);

        return adjustedBoxY - box.y;
    }

    // =====================================================
    // DETECTION
    // =====================================================

    public boolean checkCollision(CollisionBox box) {
        return tileMap.getSolidTilesIn(box).size() > 0;
    }

    public boolean canMove(Entity entity, double dx, double dy) {
        CollisionBox target = entity.getCollisionBox().offset(dx, dy);
        return !checkCollision(target);
    }

    public boolean checkEntityCollision(Entity a, Entity b) {
        return a.getCollisionBox().intersects(b.getCollisionBox());
    }

    // =====================================================
    // QUERIES
    // =====================================================

    public List<Tile> getCollidingTiles(CollisionBox box) {
        return tileMap.getSolidTileObjectsIn(box);
    }

}
