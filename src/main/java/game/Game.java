package game;

import components.*;
import display.Display;
import io.Input;
import level.Level;
import util.CollisionHandler;
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

    public static final int WIDTH = 800; // width of game window.
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
    private static Background background; // game component which represent game background.
    private static Platform platform; // game component representing platform which user manage.
    private static Ball ball; // game component that represent running ball.
    private static Level lvl; // current level game.
    private static GamePoint gamePoint;
    private static Live[] lives;

    // creates instance and initialize display of game.

    public Game(){
        running = false;
        initComponents();
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        Display.addInput(input);
        graphics = Display.getGraphics();
    }

    /* init all game components */

    private static void initComponents(){
        input = new Input();
        background = new Background(0, 0);
        platform = new Platform(375, 500);
        ball = new Ball(0, 0, platform);
        lvl = new LevelLoader().createLevel();
        gamePoint = new GamePoint();
        lives = new Live[3];
        int liveX = 80;
        for (int i = 0; i < lives.length; i++){
            lives[i] = new Live(liveX, 575);
            liveX += Live.getImage().getWidth() + 5;
        }
    }

    // method which start the game.

    public synchronized void start(){
        if (running){
            return; // if the game already running.
        }
        running = true; // mean that the game is starting now.
        gameThread = new Thread(this); // initialize game thread and put in it THIS task to executing.
        gameThread.start();
    }

    // method which stop the game.

    public synchronized void stop(){
        if (!running){
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

    private void update(){
        lvl.update();

        int activeBlocks = 0;
        for(int[] cellLine: Level.getLevelMap()){
            for (int cell: cellLine){
                if (cell != 0) activeBlocks++;
            }
        }
        if (activeBlocks == 0){
            lvl = new LevelLoader().createLevel();
            ball.startPosition();
        }

        ball.update(input);
        boolean collision = CollisionHandler.blockCollision(lvl, ball);

        if (collision){
            gamePoint.increment();
        }

        CollisionHandler.platformCollision(platform, ball);
        platform.update(input);
    }

    // method which draw all after when update method calculated all.

    private void render(){
        Display.clear();

        background.render(graphics);
        lvl.render(graphics);
        ball.render(graphics);
        platform.render(graphics);
        renderStatistics();

        Display.swapBuffers();
    }

    // method which renders game statistics in black panel.

    private void renderStatistics(){
        Font font = new Font("Arial", Font.BOLD, 18);
        graphics.setFont(font);
        graphics.drawString(gamePoint.getTotal(), 5,590);
        graphics.setColor(Color.white);
        for (Live live: lives){
            if (live != null) {
                live.render(graphics);
            }
        }
        graphics.drawString("Level: " + LevelLoader.getCurrentLevelFile(), WIDTH - 100,590);
    }

    public static Live[] getLives() {
        return lives;
    }

    public static void setLives(Live[] lives) {
        Game.lives = lives;
    }

    // method which close unhelpful resources.

    private void cleanUp(){
        Display.destroy(); // destroy display.
    }

    // main game loop.
    @Override
    public void run() {
        int fps = 0; // number frame per second.
        int upd = 0; // number of updating.
        int updl = 0; // amount loops of updating.
        long counter = 0; // elapsed time in nanoseconds since start game.
        long lastTime = Time.get(); // time of last iteration. first initializing.
        float delta = 0;

        while (running){  // while game is running.
            long now  = Time.get(); // current time in nanoseconds.
            long elapsedTime = now - lastTime; // time that elapse of last iteration.
            lastTime = now; // rewrite time of last iteration for current time.
            counter += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL); // the ratio of elapsed time to time between updates.
            while (delta > 1){
                update(); // while delta >= 1 the game must updated.
                upd++;
                delta--;
                if (render){
                    updl++; // if render already true (it's not first update in current branch of main loop).
                }
                else {
                    render = true; // else it is first update and render must be equals true.
                }
            }

            if (render){
                render(); // if game must redrawn execute render.
                fps++;
            }
            else {
                try {
                    Thread.sleep(IDLE_TIME); // do pause for CPU and resources at 1 millisecond.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (counter >= Time.SECOND){
                Display.setTitle(TITLE + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t| fps:" + fps + " | upd:" + upd + " | updl:" + updl + " |"); // if elapsed time after start the game equals or more than 1 seconds.
                fps = 0;
                upd = 0;
                updl = 0;
                counter = 0;
            }
        }
    }
}