package game;

import io.Input;
import java.awt.Graphics2D;


public abstract class GameComponent {

     int x; // coordinate x
     int y; // coordinate y

    public GameComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // update location of GameComponent when its state was changed.
    
    public abstract void update(Input input);
    
    // redraw GameComponent on display after its updating.
    
    public abstract void render(Graphics2D g);

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
