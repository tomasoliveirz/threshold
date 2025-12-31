package level;

import core.GameConstants;
import entity.Player;
import tiles.TileMap;
import tiles.TileType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LevelLoader {

    public static class LevelData {
        public TileMap tileMap;
        public Player player;

        public LevelData(TileMap tileMap, Player player) {
            this.tileMap = tileMap;
            this.player = player;
        }
    }

    public static LevelData loadLevel(String filePath) {
        TileMap tileMap = null;
        Player player = null;

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                System.err.println("Map file not found: " + path.toAbsolutePath());
                return null;
            }

            List<String> lines = Files.readAllLines(path);

            // Determine dimensions
            int height = 0;
            int width = 0;

            // Filter out metadata or empty lines if any (for now assuming raw map or simple
            // header skipping)
            // The user's file has headers "WIDTH: 20", "HEIGHT: 15". We should handle that
            // or ignore it.
            // The previous code treated everything as map. The new file has headers.
            // Let's parse properly.

            // Simple robust parser:
            // 1. Calculate max width of lines that start with '#' or '.'
            // 2. Count such lines for height

            int startLineIndex = 0;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.startsWith("#") || line.startsWith(".")) {
                    if (line.length() > width)
                        width = line.length();
                    height++;
                } else if (width == 0) {
                    // Still looking for map start
                    startLineIndex++;
                }
            }

            tileMap = new TileMap(width, height);

            int mapY = 0;
            for (int i = startLineIndex; i < lines.size(); i++) {
                String line = lines.get(i);
                // Basic check if it's a map line
                if (!line.startsWith("#") && !line.startsWith("."))
                    continue;

                for (int x = 0; x < line.length(); x++) {
                    char c = line.charAt(x);
                    if (c == '#') {
                        tileMap.setTile(TileMap.LAYER_WALL, x, mapY, TileType.WALL);
                    } else if (c == 'P') {
                        player = new Player(x * GameConstants.TILE_SIZE, mapY * GameConstants.TILE_SIZE);
                    }
                }
                mapY++;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new LevelData(tileMap, player);
    }
}
