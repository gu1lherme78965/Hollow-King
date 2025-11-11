package Game;

import java.io.IOException;

public abstract class GameState {
    private Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public abstract void handleInput() throws IOException;
    public abstract void draw() throws IOException;
    public abstract void update();
}
