package game;

import display.Display;
import graphics.Textures;
import io.Input;
import util.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    private static final int WIDTH = 400; // width of game window.
    private static final int HEIGHT = 600; // height of game window.
    private static final String TITLE = "Tetris v 1.0"; // title of game window.
    private static final int CLEAR_COLOR = 0xff000000; // color which clean game background.
    private static final int NUM_BUFFERS = 3; // amount of image buffers.

    private static final float UPDATE_RATE = 60.0f; // number of times a second when game will be recalculated and redraw.
    private static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE; // amount of time which must pass between each update.
    private static final long IDLE_TIME = 1; //the time within which the game process will be idle in milliseconds.

    private static boolean running; // game running or not.
    private Thread gameThread; // the thread which rule the game process.
    private Input input; // a component which encapsulate state of keyboard keys and handle them.
    private Graphics2D graphics;
    private Textures textures;

    //temp
    int x = 150;

    // creates instance and initialize display of game.

    public Game(){
        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInput(input);
        // temp
        textures = new Textures("platform.png");
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

        if (input.getKey(KeyEvent.VK_LEFT)){
            x -= 4;
        }

        if (input.getKey(KeyEvent.VK_RIGHT)){
            x += 4;
        }
    }

    // method which draw all after when update method calculated all.

    private void render(){
        Display.clear();

        //temp
        graphics.drawImage(textures.cut(0,0,110,35), x, 500, null);

        Display.swapBuffers();
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
                Display.setTitle(TITLE + "             | fps:" + fps + " | upd:" + upd + " | updl:" + updl + " |"); // if elapsed time after start the game equals or more than 1 seconds.
                fps = 0;
                upd = 0;
                updl = 0;
                counter = 0;
            }
        }
    }
}
