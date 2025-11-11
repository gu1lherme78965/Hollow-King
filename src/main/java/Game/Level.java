package Game;

import Entities.Player;

public class Level {
    private final Player player;
    private Layer background;
    private Layer terrain;
    private Layer foreground;
    private int width, height;

    public  Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = new Player(10, 10);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
