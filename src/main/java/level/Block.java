package level;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Game component class that represents
 * single block {@link Block}.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public class Block {

    private BufferedImage image;
    private BlockType type;


    Block(BufferedImage image, BlockType type) {
        this.image = image;
        this.type = type;
    }

    public void render(Graphics2D g, int x, int y){
        if (!type.equals(BlockType.EMPTY)){
            g.drawImage(image, x, y, null);
        }
    }
}
