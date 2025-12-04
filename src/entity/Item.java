package entity;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Item coletável.
 */
public class Item extends Entity {
    
    private String itemId;
    private String displayName;
    private Color color;
    private int quantity = 1;
    private boolean collected = false;
    
    private double bobTimer = 0;
    private double bobSpeed = 0.1;
    private double bobAmplitude = 3.0;
    
    public Item(double x, double y, String itemId, String displayName, Color color) {
        super(x, y, 16, 16, 14, 14, 8);
        this.itemId = itemId;
        this.displayName = displayName;
        this.color = color;
        this.solid = false;
        this.bobTimer = Math.random() * Math.PI * 2;
    }
    
    // =====================================================
    // UPDATE
    // =====================================================
    
    @Override
    public void update() {
        // TODO
    }
    
    // =====================================================
    // PICKUP
    // =====================================================
    
    public boolean tryPickup(Entity picker) {
        // TODO
        return false;
    }
    
    public void collect() {
        // TODO
    }
    
    // =====================================================
    // RENDERING
    // =====================================================
    
    @Override
    public void render(Graphics2D g) {
        // TODO
    }
    
    @Override
    public void renderShadow(Graphics2D g) {
        // TODO
    }
    
    // =====================================================
    // GETTERS
    // =====================================================
    
    public String getItemId() { return itemId; }
    public String getDisplayName() { return displayName; }
    public Color getColor() { return color; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    public boolean isCollected() { return collected; }
    
    // =====================================================
    // FACTORIES
    // =====================================================
    
    public static Item createGold(double x, double y) {
        return new Item(x, y, "gold", "Gold", new Color(255, 215, 0));
    }
    
    public static Item createGem(double x, double y) {
        return new Item(x, y, "gem", "Gem", new Color(100, 200, 255));
    }
    
    public static Item createPotion(double x, double y) {
        return new Item(x, y, "potion", "Potion", new Color(255, 100, 150));
    }
    
    public static Item createKey(double x, double y) {
        return new Item(x, y, "key", "Key", new Color(180, 180, 180));
    }
}
