package level;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Block {

    private BufferedImage image;
    private BlockType type;


    public Block(BufferedImage image, BlockType type) {

        this.image = image;
        this.type = type;
    }

    public void render(Graphics2D g, int x, int y){
        if (!type.equals(BlockType.EMPTY)){
            g.drawImage(image, x, y, null);
        }
    }

    public BlockType getType() {
        return type;
    }
}
