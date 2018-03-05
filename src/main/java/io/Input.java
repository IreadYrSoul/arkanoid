package io;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class Input extends JComponent{

    private boolean[] map; // array which persist buttons state (false = released, true = pressed).

    public Input(){
        map = new boolean[256]; // length equals 256 for 128 different keys multiply by 2 state (is pressed/ is released).

        for (int i = 0; i < map.length; i++) {

            final int KEY_CODE = i;

            // gets InputMap of THIS JComponent, and put on it unique keys.
            // i = some unique value of key in keyboard.
            // 0 = means that action will be simple press without Ctrl + / Shift +
            // false = state of the button (key) (is press / is release).
            // i * 2 = value of the map that means some unique button in unique state.
            // gets ActionMap of THIS JComponent, and put on it uniques value of the map
            // and Action object which discribe event which this button (in this state) will do.
            // All this actions will be use, when JFrame of THIS JComponent be in focus.

            // for each pressed key (button).

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, false), i * 2);
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = true;
                }
            });

            // similar process for each released key (button).

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, true), i * 2 + 1);
            getActionMap().put(i * 2 + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    map[KEY_CODE] = false;
                }
            });
        }


    }

    // get copy of model of keyboard state.

    public boolean[] getMap(){
        return Arrays.copyOf(map, map.length);
    }

    // get state for special key.
    // when map[special_key] return true, it mean special button was pressed,
    // and special Action for this button have to execute.

    public boolean getKey(int KeyCode){
        return map[KeyCode];
    }
}
