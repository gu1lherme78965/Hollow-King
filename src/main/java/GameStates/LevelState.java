package GameStates;

import Entities.Entity;
import Entities.Player;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class LevelState implements GameState {
    private Entity entities;
    private Player player;
    Screen screen;

    public LevelState(Screen screen) {
        this.player = new Player(10, 10);
        this.screen = screen;
    }

    @Override
    public void handleInput() throws IOException, QuitGameException {
        // user input reading
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.EOF) {throw new QuitGameException();}
        processKey(key);
    }

    @Override
    public void draw() throws IOException {
        screen.clear();
        player.draw(screen);
        screen.refresh();
    }

    @Override
    public void update() {

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
