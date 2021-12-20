package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private final String path;
    public final int SIZE;
    public BufferedImage image;

    public static final SpriteSheet TILES = new SpriteSheet("res\\textures\\sprite.png", 256);

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        load();
    }

    private void load() {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
