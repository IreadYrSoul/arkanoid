import game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Main class that run application.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(() -> new Game().start());
    }
}
