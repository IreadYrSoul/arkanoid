package components;

import java.awt.Graphics2D;

/**
 * Basic abstract class that represent a basic static component of game.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public abstract class GameComponent {

    /**
     * Basic horizontal coordinate X.
     */
    int x;

    /**
     * Basic vertical coordinate Y.
     */
    int y;

    GameComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Redraw component on display after its updating.
     *
     * @param g common graphics component.
     */
    public abstract void render(Graphics2D g);
}
