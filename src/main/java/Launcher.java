import game.Game;

import java.awt.*;

/**
 * Main class that run application.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public class Launcher {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Game().start());
    }
}
