package components;

import io.Input;

/**
 * Basic abstract class extended {@link GameComponent} that represent a basic dynamic component of game.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public abstract class DynamicGameComponent extends GameComponent {

    DynamicGameComponent(int x, int y) {
        super(x, y);
    }

    /**
     * Update component state.
     *
     * @param input input action.
     */
    public abstract void update(Input input);
}
