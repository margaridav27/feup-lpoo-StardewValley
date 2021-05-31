package org.lpoo.g55.states.menus;

import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.states.State;

public abstract class MenuState<T extends Menu> extends State<Menu> {
    public MenuState(Menu menu) {
        super(menu);
    }
}
