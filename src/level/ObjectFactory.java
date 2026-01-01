package level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.GameConstants;

public class ObjectFactory {

    private Map<Character, String> charMap = new HashMap<>();

    public ObjectFactory() {
        loadMapping();
    }

    private void loadMapping() {
        try {
            // Simple JSON parser for flat structure { "key": "value" }
            List<String> lines = Files.readAllLines(Paths.get("src/level/charactermap.json"));
            StringBuilder jsonBuilder = new StringBuilder();
            for (String line : lines) {
                jsonBuilder.append(line.trim());
            }
            String json = jsonBuilder.toString();

            // Remove brackets
            json = json.replace("{", "").replace("}", "");

            // Split by comma
            String[] pairs = json.split(",");

            for (String pair : pairs) {
                String[] entry = pair.split(":");
                if (entry.length == 2) {
                    String keyStr = entry[0].trim().replace("\"", "");
                    String valStr = entry[1].trim().replace("\"", "");

                    if (!keyStr.isEmpty()) {
                        charMap.put(keyStr.charAt(0), valStr);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            // Fallback defaults
            charMap.put('#', "level.levelObjects.SolidWall");
            charMap.put('P', "entity.Player");
        }
    }

    public Object createObject(char c, int x, int y) {
        String className = charMap.get(c);
        if (className == null)
            return null;

        try {
            Class<?> clazz = Class.forName(className);

            // Check if it is Player
            if (className.equals("entity.Player")) {
                return clazz.getConstructor(double.class, double.class)
                        .newInstance(x * (double) GameConstants.TILE_SIZE, y * (double) GameConstants.TILE_SIZE);
            }

            // Check if it is SolidWall
            if (className.equals("level.levelObjects.SolidWall")) {
                // Return a marker or special object, or we can handle it in LevelLoader
                // For now, let's return an instance if distinct logic is needed,
                // but LevelLoader needs to know it's a wall to set the TileMap.
                // We can return a specific type.
                return new level.levelObjects.SolidWall(x, y);
            }

            return null;

        } catch (Exception e) {
            System.err.println("Error creating object for char '" + c + "': " + e.getMessage());
            return null;
        }
    }

    public boolean isSolidWall(char c) {
        String className = charMap.get(c);
        return "level.levelObjects.SolidWall".equals(className);
    }
}
