package Game;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LevelFactory {
    public static Level load(String level_file_path) {
        JSONParser parser = new JSONParser();
        level_file_path = System.getProperty("user.dir") +  "\\src\\main\\resources\\data\\levels\\" + level_file_path;

        // parse JSON file with level data
        try {
            FileReader file = new FileReader(level_file_path);
            Object obj = parser.parse(file);
            return construct((JSONObject) obj);
        } catch (FileNotFoundException e) {
            System.out.println("File not found under :" + level_file_path);
            throw new RuntimeException(e);
        } catch (IOException ignored) {

        } catch (ParseException e) {
            System.out.println("Error while parsing file :" + level_file_path);
            throw new RuntimeException(e);
        }
        return null;
    }

    private static Level construct(JSONObject JSON_obj) {
        // Retrieving basic Level data
        int width = Integer.parseInt(JSON_obj.get("width").toString());
        int height = Integer.parseInt(JSON_obj.get("height").toString());
        String name = (String) JSON_obj.get("name");
        JSONArray tiles = (JSONArray) JSON_obj.get("terrain_tilesID");

        // parse the tile data from the JSONArray
        int[][] terrain_tiles = new int[height][width];
        for (int i = 0; i < height; i++) {
            terrain_tiles[i] = JSONArrayToIntArray((JSONArray) tiles.get(i), width);
        }
        Layer terrain_tilesID = new Layer(width, height, terrain_tiles);

        return new Level(name, width, height, terrain_tilesID);
    }

    // auxiliary function to aid in map tile id parsing
    private static int[] JSONArrayToIntArray(JSONArray array, int length) {
        int[] IDs = new int[length];
        for (int i = 0; i < length; i++) {
            IDs[i] = Integer.parseInt(array.get(i).toString());
        }
        return IDs;
    }
}
