package org.lpoo.g55.commands.shopping;

import org.lpoo.g55.Game;
import org.lpoo.g55.commands.Command;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.states.MarketState;

public final class LeaveShopCommand implements Command {
    private static final String TEXT = "Leave Shop";

    private final Game GAME;
    private final Farm FARM;

    public LeaveShopCommand(Game GAME, Farm FARM) {
        this.GAME = GAME;
        this.FARM = FARM;
    }

    @Override
    public String getText() {
        return LeaveShopCommand.TEXT;
    }

    @Override
    public void execute() {
        Position playerExitPosition = this.FARM.getPlayer().getPosition().getLeft();
        this.FARM.getPlayer().setPosition(playerExitPosition);
        this.GAME.setState(new MarketState(this.FARM.getMarket()));
    }
}
