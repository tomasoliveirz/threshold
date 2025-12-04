package entity;

import java.awt.Graphics2D;

/**
 * Interface para objetos renderizáveis.
 */
public interface Renderable {
    
    void render(Graphics2D g);
    
    default void renderShadow(Graphics2D g) {}
    
    double getSortY();
    
    default boolean isVisible() { return true; }
}
