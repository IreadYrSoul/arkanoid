package display;

import io.Input;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import javax.swing.*;

/**
 * Class that response for drawing graphics,
 * construct and configure frame.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public class Display {

    private static boolean created = false; // defines that is the class is created of not.
    private static JFrame window; // frame (window) on which will be placed all components.

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;

    private static BufferStrategy bufferStrategy;

    public static void create(int width, int height, String title, int _clearColor, int numBuffers) {

        if (created){
            return; // if this class already created.
        }

        window = new JFrame(title);
        window.setIconImage(new ImageIcon(Thread.currentThread()
                .getContextClassLoader().getResource("images/logo.png")).getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas content = new Canvas();

        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        JMenu game = new JMenu("Game");
        JMenu help = new JMenu("Help");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem about = new JMenuItem("About");
        game.add(newGame);
        game.add(exit);
        help.add(about);
        menuBar.add(game);
        menuBar.add(help);

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearColor;

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public static void destroy() {
        if (!created)
            return;
        window.dispose();
    }

    // add Input (JComponent) into JFrame

    public static void addInput(Input input){
        window.add(input);
    }
}