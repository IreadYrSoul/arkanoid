package components;

import io.Input;
import java.awt.Graphics2D;

/**
 * Basic abstract class that represent a basic component of game.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public abstract class GameComponent {

     int x; // coordinate x
     int y; // coordinate y

    GameComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Update location of component when its state was changed.
     * @param input input action.
     */
    
    public abstract void update(Input input);

    /**
     * Redraw component on display after its updating.
     * @param g common  component.
     */
    
    public abstract void render(Graphics2D g);

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
