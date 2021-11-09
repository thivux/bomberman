package oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {
    private final String _path;
    public final int SIZE;
    public BufferedImage image;

    public static SpriteSheet tiles = new SpriteSheet("res\\textures\\sprite.png", 256);

    public SpriteSheet(String path, int size) {
        _path = path;
        SIZE = size;
        load();
    }

    private void load() {
        try {
            image = ImageIO.read(new File(_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
