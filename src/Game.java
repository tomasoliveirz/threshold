
import core.GameConstants;
import tiles.TileMap;
import collision.CollisionSystem;
import entity.Entity;
import entity.Player;
import input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe principal do jogo.
 */
public class Game extends JPanel implements ActionListener {
    
    private TileMap tileMap;
    private CollisionSystem collisionSystem;
    private InputHandler inputHandler;
    
    private List<Entity> entities;
    private Player player;
    
    private Timer gameTimer;
    private long frameCount = 0;
    
    public Game() {
        setPreferredSize(new Dimension(
            GameConstants.SCREEN_WIDTH,
            GameConstants.SCREEN_HEIGHT
        ));
        setBackground(Color.BLACK);
        setFocusable(true);
        setDoubleBuffered(true);
        
        init();
    }
    
    // =====================================================
    // INIT
    // =====================================================
    
    private void init() {
        // TODO
    }
    
    public void start() {
        // TODO
    }
    
    public void stop() {
        // TODO
    }
    
    // =====================================================
    // GAME LOOP
    // =====================================================
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
    }
    
    private void update() {
        // TODO
    }
    
    // =====================================================
    // RENDERING
    // =====================================================
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO
    }
    
    private void renderHUD(Graphics2D g) {
        // TODO
    }
    
    private void renderDebug(Graphics2D g) {
        // TODO
    }
    
    // =====================================================
    // MAP CREATION
    // =====================================================
    
    private void createTestMap() {
        // TODO
    }
    
    private void createTestItems() {
        // TODO
    }
    
    // =====================================================
    // ENTITY MANAGEMENT
    // =====================================================
    
    public void addEntity(Entity entity) {
        // TODO
    }
    
    public void removeEntity(Entity entity) {
        // TODO
    }
    
    // =====================================================
    // GETTERS
    // =====================================================
    
    public TileMap getTileMap() { return tileMap; }
    public CollisionSystem getCollisionSystem() { return collisionSystem; }
    public Player getPlayer() { return player; }
    public List<Entity> getEntities() { return entities; }
    public long getFrameCount() { return frameCount; }
    
    // =====================================================
    // MAIN
    // =====================================================
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Threshold");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            
            Game game = new Game();
            frame.add(game);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            game.start();
        });
    }
}
