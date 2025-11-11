package Game;

public class Level {
    private Layer background;
    private Layer terrain;
    private Layer foreground;
    private int width, height;

    public  Level(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
