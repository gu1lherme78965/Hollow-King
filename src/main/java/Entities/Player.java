package Entities;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    private int x, y;
    private BufferedImage sprite;
    String sprite_path;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        sprite_path = System.getProperty("user.dir") + "\\sprites\\player\\Player.png";


        try {
            sprite = javax.imageio.ImageIO.read(new File(sprite_path));

        } catch (IOException e) {
            System.out.println("Error loading image: " + sprite_path);
            e.printStackTrace();
        }
    }

    // general getters and setter for character position
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    // character movement methods
    public void moveUp() {
        y -= 1;
    }
    public void moveDown() {
        y += 1;
    }
    public void moveLeft() {
        x -= 1;
    }
    public void moveRight() {
        x += 1;
    }

    @Override
    public void draw(Screen screen) {


        for (int x = 0; x < sprite.getWidth(); x++){
            for (int y = 0; y < sprite.getHeight(); y++){
                int a = sprite.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                if (alpha != 0) {
                    TextColor pixel_color = new TextColor.RGB(red, green, blue);
                screen.setCharacter(x+this.x, y+this.y, new TextCharacter(' ', pixel_color, pixel_color));
                }
            }
        }

    }
}
