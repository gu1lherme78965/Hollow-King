package Game;

public class Layer {
    private int[][] tilesID;
    private int width, height, tiled_height, tiled_width;

    Layer (int tiled_width, int tiled_height, int[][] tilesID) {
        this.tiled_width = tiled_width;
        this.tiled_height = tiled_height;
        this.tilesID = tilesID;
        this.width = tiled_width*4;
        this.height = tiled_height*4;
    }

    // Regular getters and setters for class attributes
    public int[][] getTilesID() {
        return tilesID;
    }
    public void setTilesID(int[][] tilesID) {
        this.tilesID = tilesID;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {

    }

    public int getTile(int x, int y) {
        return tilesID[tiled_height-1-y][x];
    }

}
