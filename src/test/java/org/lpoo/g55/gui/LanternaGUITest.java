package org.lpoo.g55.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lpoo.g55.gui.lanternaGUI.LanternaGUI;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.farm.Field;
import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.market.shops.AnimalShop;
import org.lpoo.g55.model.market.shops.CropShop;
import org.lpoo.g55.model.utils.Position;
import org.lpoo.g55.model.utils.Size;
import org.mockito.Mockito;

public class LanternaGUITest {

    private TextGraphics textGraphics;
    private LanternaGUI gui;

    @BeforeEach
    void setUp() {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);

        gui = new LanternaGUI(screen);
    }

    @Test
    void drawDialogTest() {
        gui.drawDialog("Dialog Test", new TerminalPosition(1, 1));

        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(Mockito.any(TextColor.class));
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(Mockito.any(TerminalPosition.class), Mockito.anyString(), Mockito.eq(SGR.BOLD));
    }

    @Test
    void drawMenuTest() {

    }

    @Test
    void drawElementTest() {
        gui.drawElement(new Position(1, 1), 'C', "#000000", "#000000");

        Mockito.verify(textGraphics, Mockito.times(1))
                .setForegroundColor(Mockito.any(TextColor.class));
        Mockito.verify(textGraphics, Mockito.times(1))
                .setBackgroundColor(Mockito.any(TextColor.class));

        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(Mockito.any(TerminalPosition.class), Mockito.anyString());
    }

    @Test
    void drawPlayerName() {
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getName()).thenReturn("PLAYER");

        gui.drawPlayerName(player);
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(Mockito.any(TerminalPosition.class), Mockito.anyString(), Mockito.eq(SGR.BOLD));
    }

    @Test
    void drawBalanceTest() {
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getBalance()).thenReturn(400);

        gui.drawBalance(player);
        Mockito.verify(textGraphics, Mockito.times(1))
                .setCharacter(Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));
        Mockito.verify(textGraphics, Mockito.times(1))
                .putString(Mockito.any(TerminalPosition.class), Mockito.anyString());
    }

    @Property
    void drawFarmTest(@ForAll @IntRange(min = 1, max = 50) int width, @ForAll @IntRange(min = 1, max = 50) int height) {
        setUp();
        Farm farm = Mockito.mock(Farm.class);
        Size farmSize = new Size(width, height);
        Mockito.when(farm.getSize()).thenReturn(farmSize);

        gui.drawFarm(farm);
        Mockito.verify(textGraphics, Mockito.times(height - 1))
                .drawLine(Mockito.any(TerminalPosition.class), Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(1))
                .drawRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));
    }

    @Property
    void drawCoopTest(@ForAll @IntRange(min = 1, max = 50) int width, @ForAll @IntRange(min = 1, max = 50) int height) {
        setUp();
        Coop coop = Mockito.mock(Coop.class);

        Size coopSize = new Size(width, height);
        Mockito.when(coop.getSize()).thenReturn(coopSize);

        Position coopExitPosition = new Position(width / 2, height / 2);
        Mockito.when(coop.getExitPosition()).thenReturn(coopExitPosition);

        gui.drawCoop(coop);

        Mockito.verify(textGraphics, Mockito.times(1))
                .fillRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));
        Mockito.verify(textGraphics, Mockito.times(1))
                .drawRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(1))
                .setCharacter(Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));
    }

    @Test
    void drawCoopOutsideTest() {
        Coop coop = Mockito.mock(Coop.class);
        Mockito.when(coop.getExitPosition()).thenReturn(new Position(10, 10));

        gui.drawCoopOutside(coop.getExitPosition());

        Mockito.verify(textGraphics, Mockito.times(1)).
                fillRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));
        Mockito.verify(textGraphics, Mockito.times(1)).
                fillTriangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalPosition.class),
                        Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));
        Mockito.verify(textGraphics, Mockito.times(1)).
                drawLine(Mockito.any(TerminalPosition.class), Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));
    }

    @Property
    void drawBarnTest(@ForAll @IntRange(min = 1, max = 50) int width, @ForAll @IntRange(min = 1, max = 50) int height) {
        setUp();
        Barn barn = Mockito.mock(Barn.class);

        Size barnSize = new Size(width, height);
        Mockito.when(barn.getSize()).thenReturn(barnSize);

        Position coopExitPosition = new Position(width / 2, height / 2);
        Mockito.when(barn.getExitPosition()).thenReturn(coopExitPosition);

        gui.drawBarn(barn);

        Mockito.verify(textGraphics, Mockito.times(1))
                .fillRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));
        Mockito.verify(textGraphics, Mockito.times(1))
                .drawRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(1))
                .setCharacter(Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));
    }

    @Test
    void drawBarnOutsideTest() {
        Barn barn = Mockito.mock(Barn.class);
        Mockito.when(barn.getExitPosition()).thenReturn(new Position(10, 10));

        gui.drawBarnOutside(barn.getExitPosition());

        Mockito.verify(textGraphics, Mockito.times(1)).
                fillRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(1)).
                fillTriangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalPosition.class),
                        Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));
        Mockito.verify(textGraphics, Mockito.times(1)).
                drawTriangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalPosition.class),
                        Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(3)).
                drawLine(Mockito.any(TerminalPosition.class), Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));
    }

    @Test
    void drawFieldTest() {
        Field field = Mockito.mock(Field.class);
        Mockito.when(field.getTopLeftCorner()).thenReturn(new Position(10, 10));
        Mockito.when(field.getGate()).thenReturn(new Position(18, 10));
        Mockito.when(field.getSize()).thenReturn(new Size(20, 20));

        gui.drawField(field);

        Mockito.verify(textGraphics, Mockito.times(1)).
                fillRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));
        Mockito.verify(textGraphics, Mockito.times(1)).
                drawRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(Field.getGateSize() + 1)).
                setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(TextCharacter.class));
    }

    @Property
    void drawMarketTest(@ForAll @IntRange(min = 1, max = 50) int width, @ForAll @IntRange(min = 1, max = 50) int height) {
        setUp();
        Market market = Mockito.mock(Market.class);
        Size farmSize = new Size(width, height);
        Mockito.when(market.getSize()).thenReturn(farmSize);

        gui.drawMarket(market);
        Mockito.verify(textGraphics, Mockito.times(height - 1))
                .drawLine(Mockito.any(TerminalPosition.class), Mockito.any(TerminalPosition.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(1))
                .drawRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));
    }

    @Test
    void drawCropShopTest() {
        CropShop cropShop = Mockito.mock(CropShop.class);
        Mockito.when(cropShop.getPosition()).thenReturn(new Position(10, 10));

        gui.drawCropShop(cropShop);

        Mockito.verify(textGraphics, Mockito.times(1)).
                fillRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(1)).
                putString(Mockito.any(TerminalPosition.class), Mockito.eq("CROPS"), Mockito.eq(SGR.BOLD));
    }

    @Test
    void drawAnimalShopTest() {
        AnimalShop animalShop = Mockito.mock(AnimalShop.class);
        Mockito.when(animalShop.getPosition()).thenReturn(new Position(10, 10));

        gui.drawAnimalShop(animalShop);

        Mockito.verify(textGraphics, Mockito.times(1)).
                fillRectangle(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class), Mockito.any(TextCharacter.class));

        Mockito.verify(textGraphics, Mockito.times(1)).
                putString(Mockito.any(TerminalPosition.class), Mockito.eq("ANIMALS"), Mockito.eq(SGR.BOLD));
    }
}
