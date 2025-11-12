package Renderer;

import Game.Layer;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {
    public static void renderLayer(Screen screen, Layer layer) {
        int[][] IDs = layer.getTilesID();
        int width = layer.getTiled_width();
        int height = layer.getTiled_height();

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                int id = IDs[i][j];

                BufferedImage tile = parseID(id);

                for (int x = 0; x < tile.getWidth(); x++){
                    for (int y = 0; y < tile.getHeight(); y++){
                        int a = tile.getRGB(x, y);
                        int alpha = (a >> 24) & 0xff;
                        int red = (a >> 16) & 255;
                        int green = (a >> 8) & 255;
                        int blue = a & 255;

                        if (alpha != 0) {
                            TextColor pixel_color = new TextColor.RGB(red, green, blue);
                            screen.setCharacter(x+j*4, y+i*4, new TextCharacter(' ', pixel_color, pixel_color));
                        }
                    }
                }
            }
        }

    }

    // retrieves the correct tile based on the id provided
    private static BufferedImage parseID(int id) {
        String base_path = System.getProperty("user.dir") + "\\src\\main\\resources\\sprites\\terrain\\";
        String path = base_path + id + ".png";

        try {
            return javax.imageio.ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("Error loading image at: " + path);
            throw new RuntimeException(e);
        }
    }
}
