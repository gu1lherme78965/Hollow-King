package Game;

import Entities.Player;
import com.googlecode.lanterna.screen.Screen;

import Renderer.Renderer;

public class Level {
    // foreground and background are being ignored at this stage of development
    String name;
    private final Player player;
    private Layer background;
    private Layer terrain;
    private Layer foreground;
    private int tiled_width, tiled_height;

    public  Level(String name, int tiled_width, int tiled_height, Layer terrain) {
        this.tiled_width = tiled_width;
        this.tiled_height = tiled_height;
        this.player = new Player(10, 10, 16, 14);

        this.terrain = terrain;
    }

    public int getTiledWidth() {
        return tiled_width;
    }

    public int getTiledHeight() {
        return tiled_height;
    }

    public void draw(Screen screen) {
        // background and foreground rendering are skipped for this stage of development
        Renderer.renderLayer(screen, terrain);
    }
}
