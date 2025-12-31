
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
                GameConstants.SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        setDoubleBuffered(true);

        init();
    }

    // =====================================================
    // INIT
    // =====================================================

    // =====================================================
    // INIT
    // =====================================================

    private void init() {
        entities = new ArrayList<>();
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);

        createTestMap();
        collisionSystem = new CollisionSystem(tileMap);

        // Initialize Player if not created by map
        if (player == null) { // Should have been created in createTestMap if P was found
            player = new Player(100, 100);
            addEntity(player);
        }
        player.setInputHandler(inputHandler);
        player.setCollisionSystem(collisionSystem);

        gameTimer = new Timer(1000 / 60, this);
    }

    public void start() {
        gameTimer.start();
    }

    public void stop() {
        gameTimer.stop();
    }

    // =====================================================
    // GAME LOOP
    // =====================================================

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        inputHandler.update();

        // Update all entities
        for (int i = 0; i < entities.size(); i++) {
            Entity ent = entities.get(i);
            if (ent.isActive()) {
                ent.update();
            }
        }

        // Camera follow logic (Simple centering)
        // No explicit camera class yet, handled in paintComponent

        frameCount++;
    }

    // =====================================================
    // RENDERING
    // =====================================================

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Camera Translation
        int camX = 0;
        int camY = 0;

        if (player != null) {
            camX = (int) player.getX() - getWidth() / 2 + player.getWidth() / 2;
            camY = (int) player.getY() - getHeight() / 2 + player.getHeight() / 2;

            // Map bounds clamping
            if (tileMap != null) {
                int mapW = tileMap.getPixelWidth();
                int mapH = tileMap.getPixelHeight();
                if (camX < 0)
                    camX = 0;
                if (camY < 0)
                    camY = 0;
                if (camX > mapW - getWidth())
                    camX = mapW - getWidth();
                if (camY > mapH - getHeight())
                    camY = mapH - getHeight();

                // If map is smaller than screen, center it
                if (mapW < getWidth())
                    camX = -(getWidth() - mapW) / 2;
                if (mapH < getHeight())
                    camY = -(getHeight() - mapH) / 2;
            }
        }

        g2.translate(-camX, -camY);

        // Render Map Below
        if (tileMap != null) {
            tileMap.renderBelow(g2);
        }

        // Render Entities
        for (Entity ent : entities) {
            ent.render(g2);
            // ent.renderDebug(g2); // Uncomment for debug
        }

        // Render Map Above
        if (tileMap != null) {
            tileMap.renderAbove(g2);
        }

        g2.translate(camX, camY); // Restore for HUD

        renderHUD(g2);
    }

    private void renderHUD(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString("Entities: " + entities.size(), 10, 20);
    }

    private void renderDebug(Graphics2D g) {
        // TODO
    }

    // =====================================================
    // MAP CREATION
    // =====================================================

    private void createTestMap() {
        level.LevelLoader.LevelData data = level.LevelLoader.loadLevel("res/maps/start_area.txt");
        if (data != null) {
            this.tileMap = data.tileMap;
            // Handle player if loaded from map
            if (data.player != null) {
                this.player = data.player;
                addEntity(this.player);
            }
        } else {
            // Fallback or error handling
            System.err.println("Failed to load level.");
        }
    }

    private void createTestItems() {
        // TODO
    }

    // =====================================================
    // ENTITY MANAGEMENT
    // =====================================================

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    // =====================================================
    // GETTERS
    // =====================================================

    public TileMap getTileMap() {
        return tileMap;
    }

    public CollisionSystem getCollisionSystem() {
        return collisionSystem;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public long getFrameCount() {
        return frameCount;
    }

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
