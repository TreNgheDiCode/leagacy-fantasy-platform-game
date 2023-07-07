package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class LoadResources {

    // Backgrounds
    public static final String MENU_BACKGROUND = "Background.png";
    public static final String TREE_BACKGROUND = "Tree_Background.png";

    // Player Animations
    public static final String PLAYER_IDLE = "Idle-Sheet.png";
    public static final String PLAYER_RUNNING = "Run-Sheet.png";
    public static final String PLAYER_ATTACK = "Attack-Sheet.png";
    public static final String PLAYER_DEAD = "Dead-Sheet.png";
    public static final String PLAYER_JUMP = "Jump-Sheet.png";
    public static final String PLAYER_FALLING = "Falling-Sheet.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        try (InputStream is = LoadResources.class.getResourceAsStream("/" + fileName)) {
            img = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static BufferedImage[] GetAllLevels() {
        URL url = LoadResources.class.getResource("/level");
        File file = null;

        try {
            assert url != null;
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assert file != null;
        File[] files = file.listFiles();
        assert files != null;
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++)
            for (File value : files) {
                if (value.getName().equals((i + 1) + ".png"))
                    filesSorted[i] = value;

            }

        BufferedImage[] images = new BufferedImage[filesSorted.length];

        for (int i = 0; i < images.length; i++)
            try {
                images[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return images;
    }
}
