package org.lpoo.g55.model.market;

import org.lpoo.g55.controller.game.elements.PlayerControllerInteraction;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.market.shops.Shop;

import java.util.List;

public class Market implements PlayerControllerInteraction {
    final private Position entrance;
    final private Position exit;

    final private Size size;

    private Farm farm;
    private List<Shop> shops;
    private Player player;

    public Market(Size size, Position entrance, Position exit) {
        this.size = size;
        this.entrance = entrance;
        this.exit = exit;
    }

    public Size getSize() {
        return size;
    }

    public Farm getFarm() {
        return farm;
    }
    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Shop> getShops() {
        return shops;
    }
    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public Position getEntrancePosition() {
        return entrance;
    }
    public Position getExitPosition() {
        return exit;
    }

    private static boolean between(int min, int target, int max) {
        return min <= target && target <= max;
    }

    @Override
    public boolean canPlayerMove(Position playerCurrentPosition) {
        return between(4, playerCurrentPosition.getX(), size.getWidth() + 1) &&
               between(5, playerCurrentPosition.getY(), size.getHeight() + 2);
    }
}
