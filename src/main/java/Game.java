import Entities.Player;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.io.IOException;

public class Game {
    private Screen screen;
    private final Player player;

    Game() {
        // The terminal window size was chosen to a 1:3 ratio to keep a landscape feel.
        TerminalSize size = new TerminalSize(210, 70);

        try {
            /* Creates a default terminal and changes its normal font size and increases the default terminal size to
             simulate better screen resolution. 12 was chosen to be an appropriate "pixel" size.
             */
            int pixel_size = 12;
            Terminal terminal = new DefaultTerminalFactory()
                    .setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.getDefaultOfSize(pixel_size))
                    .setInitialTerminalSize(size).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (Exception e) {
            e.printStackTrace();
        }

        player = new Player(10, 10);
    }

    public void draw() throws IOException {
        screen.clear();
        player.draw(screen);
        screen.refresh();
    }

    public void run() {
        try {
            while(true) {
                draw();

                // user input reading
                KeyStroke key = screen.readInput();
                if(key.getKeyType() == KeyType.EOF) {break;}
                processKey(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
