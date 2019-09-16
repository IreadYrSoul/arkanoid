package game;

import components.*;
import display.Display;
import io.Input;
import level.Level;
import util.GamePoints;
import util.LevelLoader;
import util.Time;

import java.awt.*;


/**
 * The Main game class that handles game logic and
 * lifecycle all game components.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Game implements Runnable {

    public static final int WIDTH = 300; // width of game window.
    public static final int HEIGHT = 600; // height of game window.
    private static final String TITLE = "Arkanoid v 1.0"; // title of game window.
    private static final int CLEAR_COLOR = 0xff000000; // color which clean game background.
    private static final int NUM_BUFFERS = 3; // amount of image buffers.

    private static final float UPDATE_RATE = 60.0f; // number of times a second when game will be recalculated and redraw.
    private static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE; // amount of time which must pass between each update.
    private static final long IDLE_TIME = 1; //the time within which the game process will be idle in milliseconds.

    private static boolean running; // game running or not.
    private Thread gameThread; // the thread which rule the game process.
    private static Input input; // a component which encapsulate state of keyboard keys and handle them.

    private static Graphics2D graphics; // game component which represent shared game graphics.

    /**
     * Game component that represent managed platform.
     */
    private static Platform platform; // game component representing platform which user manage.

    /**
     * Game component that represent ball.
     */
    private static Ball ball;

    /**
     * Game component that represent game level.
     */
    private static Level lvl;

    /**
     *
     */
    private static GamePoints gamePoints;

    private static LevelLoader levelLoader;

    private static Live[] lives;

    // creates instance and initialize display of game.

    public Game() {
        running = false;
        initComponents();
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        Display.addInput(input);
        graphics = Display.getGraphics();
    }

    /**
     * Init all game components.
     */
    private static void initComponents() {
        input = new Input();
        platform = new Platform(175, 500);
        ball = new Ball();
        ball.startPosition(platform.getBound().x, platform.getBound().y);
        levelLoader = new LevelLoader();
        lvl = levelLoader.createLevel();
        gamePoints = new GamePoints();
        lives = new Live[3];
        int liveX = 80;
        for (int i = 0; i < lives.length; i++) {
            lives[i] = new Live(liveX, 575);
            liveX += Live.getImage().getWidth() + 5;
        }
    }

    // method which start the game.

    public synchronized void start() {
        if (running) {
            return; // if the game already running.
        }
        running = true; // mean that the game is starting now.
        gameThread = new Thread(this); // initialize game thread and put in it THIS task to executing.
        gameThread.start();
    }

    // method which stop the game.

    public synchronized void stop() {
        if (!running) {
            return; // if the game already stop.
        }
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cleanUp(); // flush all resources.
    }

    // method which compute all logic and physics of game.

    /**
     * Main game logic.
     */
    private void update() {

        ball.update(input);

        for (Block block : lvl.getBlocks()) {
            if (ball.getCircle().intersects(block.getBound())) {
                boolean minW = ball.getCircle().getCenterX() >= block.getBound().x;
                boolean maxW = ball.getCircle().getCenterX() <= block.getBound().x + block.getBound().width;
                boolean downIntersect = ball.getCircle().getCenterY() < block.getBound().y;
                boolean upIntersect = ball.getCircle().getCenterY() > block.getBound().y + block.getBound().height;
                if (((minW && maxW) && downIntersect) || ((minW && maxW) && upIntersect)){
                ball.changeDy();
                } else {
                ball.changeDx();
                }
                lvl.removeBlock(block);
                return;
            }
        }

        if (ball.getCircle().intersects(platform.getBound())) {
            ball.changeDy();
            ball.changeDx();
        }

        if (ball.getCircle().intersects(platform.getBound())){
            boolean maxWidth = ball.getCircle().getMaxX() >= platform.getBound().x;
            boolean minWidth = ball.getCircle().getMinX() <= platform.getBound().x + platform.getBound().width;
            boolean downIntersect = ball.getCircle().getCenterY() < platform.getBound().y;
            boolean upIntersect = ball.getCircle().getCenterY() > platform.getBound().y + platform.getBound().height;
            if (((minWidth && maxWidth) && downIntersect) || ((minWidth && maxWidth) && upIntersect)) {
                ball.changeDy();
            } else {
                ball.changeDx();
            }
        }

        platform.update(input);

    }


    /**
     * Main render method.
     * Render all game components.
     */
    private void render() {
        Display.clear();
        lvl.render(graphics);
        ball.render(graphics);
        platform.render(graphics);
        renderStatistics();
        Display.swapBuffers();
    }

    // method which renders game statistics in black panel.

    private void renderStatistics() {
        Font font = new Font("Arial", Font.BOLD, 18);
        graphics.setFont(font);
        graphics.drawString(gamePoints.getTotal(), 5, 590);
        graphics.setColor(Color.white);
        for (Live live : lives) {
            if (live != null) {
                live.render(graphics);
            }
        }
        graphics.drawString("lvl : " + levelLoader.getCurrLevel(), WIDTH - 60, 590);
    }

    public static Live[] getLives() {
        return lives;
    }

    public static void setLives(Live[] lives) {
        Game.lives = lives;
    }

    // method which close unhelpful resources.

    private void cleanUp() {
        Display.destroy(); // destroy display.
    }

    /**
     * Game engine.
     */
    @Override
    public void run() {
        long counter = 0;
        long lastTime = Time.get();
        float delta = 0;
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;
            counter += elapsedTime;
            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                delta--;
                if (!render) {
                    render = true;
                }
            }
            if (render) {
                render();
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (counter >= Time.SECOND) {
                counter = 0;
            }
        }
    }
}