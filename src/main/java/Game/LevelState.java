package Game;


import Entities.Player;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class LevelState extends GameState {
    private Level level;
    private Player player;
    Screen screen;
    Game game;
    private final long fps = 1000 /30;

    public LevelState(Game game) {
        this.screen = game.getScreen();
        this.game = game;
        this.player = new Player(10, 10, 16, 14);
        level = LevelFactory.load("testLevel.json");
    }

    @Override
    public void handleInput() throws IOException{
        // user input reading
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.EOF) {game.quit();}
        processKey(key);
    }

    @Override
    public void draw() throws IOException, InterruptedException {
        screen.clear();
        level.draw(screen);
        player.draw(screen);
        screen.refresh(Screen.RefreshType.DELTA);
        Thread.sleep(fps);
    }

    @Override
    public void update() {
        player.update();
    }

    private void processKey(KeyStroke key) throws IOException{
        if (key.getKeyType() == KeyType.Character) {
            switch (key.getCharacter()) {
                case 'q':
                    screen.close();
                    break;
            }
        } else {
            switch (key.getKeyType()) {
                case KeyType.ArrowLeft:
                    player.moveLeft();
                    break;
                case KeyType.ArrowRight:
                    player.moveRight();
                    break;
                case KeyType.ArrowUp:
                    player.moveUp();
                    break;
                case KeyType.ArrowDown:
                    player.moveDown();
                    break;
            }
        }
    }
}
