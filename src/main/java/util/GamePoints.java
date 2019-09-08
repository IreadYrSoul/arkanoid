package components;

/**
 * Simple class that represents points of game.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class GamePoints {

    private static int total  = 0;

    /**
     * Adds 10 points to total point balance
     * after each collision.
     */
    public void increment(){
        total += 10;
    }

    /**
     * Gets total point balance.
     */
    public String getTotal(){
        if (total == 0) {
            return "0000";
        } else if (total < 100) {
            return "00" + total;
        } else if (100 < total && total < 1000) {
            return "0" + total;
        }
        return "0" + total;
    }
}