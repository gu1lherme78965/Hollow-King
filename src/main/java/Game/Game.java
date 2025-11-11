package Game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.io.IOException;

public class Game {
    private Terminal terminal;
    private Screen screen;
    private GameState current_game_state;
    private boolean running;

    public Game() {
        // The terminal window size was chosen to a 1:3 ratio to keep a landscape feel.
        TerminalSize size = new TerminalSize(300, 100);

        try {
            /* Creates a default terminal and changes its normal font size and increases the default terminal size to
             simulate better screen resolution. 12 was chosen to be an appropriate "pixel" size.
             */
            int pixel_size = 8;
            this.terminal = new DefaultTerminalFactory()
                    .setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.getDefaultOfSize(pixel_size))
                    .setInitialTerminalSize(size).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (Exception e) {
            e.printStackTrace();
        }

        running = true;
    }

    public void run() {

        // main loop
        try {
            while(running) {
                // tells whatever state it is in to handle the logic.
                current_game_state.draw();
                current_game_state.handleInput();
                current_game_state.update();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setState(GameState game_state) {
        current_game_state = game_state;
    }

    public GameState getState() {
        return current_game_state;
    }

    public void quit() {
        running = false;
    }

    public Screen getScreen() {
        return screen;
    }

    public Terminal getTerminal() {
        return terminal;
    }
}

