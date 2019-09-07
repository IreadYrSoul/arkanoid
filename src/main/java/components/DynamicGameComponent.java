package components;

import io.Input;


public abstract class DynamicGameComponent extends GameComponent {

    DynamicGameComponent(int x, int y) {
        super(x, y);
    }

    /**
     * Update location of component when its state was changed.
     * @param input input action.
     */
    public abstract void update(Input input);
}
