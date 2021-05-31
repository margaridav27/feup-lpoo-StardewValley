package org.lpoo.g55.controller.game.elements;

import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.utils.Position;

public interface PlayerControllerInteraction {
    Player getPlayer();

    void setPlayer(Player player);

    boolean canPlayerMove(Position playerCurrentPosition);
}
