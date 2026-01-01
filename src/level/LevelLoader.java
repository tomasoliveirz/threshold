package level;

import entity.Entity;
import entity.Player;
import level.levelObjects.SolidWall;
import tiles.TileMap;
import tiles.TileType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {

    public static class LevelData {
        public TileMap tileMap;
        public Player player;
        public List<Entity> entities;

        public LevelData(TileMap tileMap, Player player, List<Entity> entities) {
            this.tileMap = tileMap;
            this.player = player;
            this.entities = entities;
        }
    }

    public static LevelData loadLevel(String filePath) {
        TileMap tileMap = null;
        Player player = null;
        List<Entity> entities = new ArrayList<>();

        ObjectFactory objectFactory = new ObjectFactory();

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                System.err.println("Map file not found: " + path.toAbsolutePath());
                return null;
            }

            List<String> lines = Files.readAllLines(path);

            int startLineIndex = 0;
            int width = 0;
            int height = 0;

            // Pre-calculate dimensions
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                // Treat any line with mapped chars (e.g. #, ., G, P) as map line.
                // Simple heuristic: if line contains keys from our factory?
                // Or just keep existing heuristics: start with # or .
                // But now we have G and P.
                // Let's assume if it's not a header (key: value), it's a map line?
                // Safest is to just check max length.
                if (line.isEmpty())
                    continue;

                // If it looks like a map line (contains map characters)
                if (line.contains("#") || line.contains(".") || line.contains("P")) {
                    if (line.length() > width)
                        width = line.length();
                    height++;
                } else {
                    // Maybe metadata lines before map
                    if (height == 0)
                        startLineIndex++;
                }
            }

            tileMap = new TileMap(width, height);

            int mapY = 0;
            for (int i = startLineIndex; i < lines.size(); i++) {
                String line = lines.get(i);
                // Basic skip empty or pure metadata lines inside body if needed
                if (line.trim().isEmpty())
                    continue;

                for (int x = 0; x < line.length(); x++) {
                    char c = line.charAt(x);

                    // Factory Logic
                    Object obj = objectFactory.createObject(c, x, mapY);

                    if (obj instanceof SolidWall) {
                        tileMap.setTile(TileMap.LAYER_WALL, x, mapY, TileType.WALL);
                    } else if (obj instanceof Player) {
                        player = (Player) obj;
                    } else if (obj instanceof Entity) {
                        entities.add((Entity) obj);
                    }
                    // Handle default/empty char '.' or unknown?
                    // if c == '#', factory returns SolidWall, we set Tile.
                    // If c == '.', factory returns null (default).
                }
                mapY++;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new LevelData(tileMap, player, entities);
    }
}
