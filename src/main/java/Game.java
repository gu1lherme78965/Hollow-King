import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;

public class Game {
    private Screen screen;
    private int x, y;

    Game() {
        TerminalSize size = new TerminalSize(150, 50);

        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(size).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (Exception e) {
            e.printStackTrace();
        }

        x=10;
        y=10;
    }

    public void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
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
                    x--;
                    break;
                case KeyType.ArrowRight:
                    x++;
                    break;
                case KeyType.ArrowUp:
                    y--;
                    break;
                case KeyType.ArrowDown:
                    y++;
                    break;
            }
        }
    }
}
