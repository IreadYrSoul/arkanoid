package display;

import graphics.Texture;
import io.Input;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Display {

    private static boolean created = false; // defines that is the class is created of not.
    private static JFrame window; // frame (window) on which will be placed all components.
    private static Canvas content;

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static int[] temp;
    private static Graphics bufferGraphics;
    private static int clearColor;

    private static BufferStrategy bufferStrategy;

    public static void create(int width, int height, String title, int _clearColor, int numBuffers) {

        if (created){
            return; // if this class already created.
        }

        window = new JFrame(title);
        window.setIconImage(new ImageIcon("images/logo.png").getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // temp

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();

        // temp

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

    public static void setTitle(String title) {
        window.setTitle(title);
    }

    // add Input (JComponent) into JFrame

    public static void addInput(Input input){
        window.add(input);
    }
}
