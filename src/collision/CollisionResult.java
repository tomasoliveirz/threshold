package collision;

import core.Vector2;
import tiles.Tile;

/**
 * Resultado de colisão.
 */
public class CollisionResult {

    public final boolean collided;
    public final Vector2 resolvedPosition;
    public final Vector2 blockedMovement;
    public final Vector2 appliedMovement;
    public final Tile collidedTile;
    public final Vector2 collisionNormal;
    public final boolean collidedX;
    public final boolean collidedY;

    public CollisionResult(
            boolean collided,
            Vector2 resolvedPosition,
            Vector2 appliedMovement,
            Vector2 blockedMovement,
            Tile collidedTile,
            Vector2 collisionNormal,
            boolean collidedX,
            boolean collidedY) {
        this.collided = collided;
        this.resolvedPosition = resolvedPosition;
        this.appliedMovement = appliedMovement;
        this.blockedMovement = blockedMovement;
        this.collidedTile = collidedTile;
        this.collisionNormal = collisionNormal;
        this.collidedX = collidedX;
        this.collidedY = collidedY;
    }

    public static CollisionResult noCollision(Vector2 finalPos, Vector2 movement) {
        return new CollisionResult(
                false,
                finalPos,
                movement,
                Vector2.ZERO,
                null,
                Vector2.ZERO,
                false,
                false);
    }

    public static CollisionResult withTile(
            Vector2 resolvedPos,
            Vector2 applied,
            Vector2 blocked,
            Tile tile,
            boolean colX,
            boolean colY) {
        return new CollisionResult(
                true,
                resolvedPos,
                applied,
                blocked,
                tile,
                Vector2.ZERO, // Simplified normal for now
                colX,
                colY);
    }

    @Override
    public String toString() {
        return collided
                ? String.format("CollisionResult[x=%b,y=%b]", collidedX, collidedY)
                : "CollisionResult[none]";
    }
}
