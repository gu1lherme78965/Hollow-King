package GameStates;

import java.io.IOException;

public interface GameState {
    void handleInput() throws IOException, QuitGameException;
    void draw() throws IOException;
    void update();
}
