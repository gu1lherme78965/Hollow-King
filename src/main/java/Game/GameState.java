package Game;

import java.io.IOException;

public abstract class GameState {

    public abstract void handleInput() throws IOException;
    public abstract void draw() throws IOException;
    public abstract void update();
}
