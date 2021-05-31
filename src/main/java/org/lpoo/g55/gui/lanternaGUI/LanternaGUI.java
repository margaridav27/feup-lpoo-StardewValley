package org.lpoo.g55.gui.lanternaGUI;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.lpoo.g55.gui.GUI;
import org.lpoo.g55.model.buildings.Barn;
import org.lpoo.g55.model.buildings.Coop;
import org.lpoo.g55.model.elements.Player;
import org.lpoo.g55.model.elements.animals.Animal;
import org.lpoo.g55.model.elements.animals.barn.BarnAnimal;
import org.lpoo.g55.model.elements.animals.coop.CoopAnimal;
import org.lpoo.g55.model.elements.items.Item;
import org.lpoo.g55.model.farm.Farm;
import org.lpoo.g55.model.farm.Field;
import org.lpoo.g55.model.market.Market;
import org.lpoo.g55.model.market.shops.AnimalShop;
import org.lpoo.g55.model.market.shops.CropShop;
import org.lpoo.g55.model.menus.Menu;
import org.lpoo.g55.model.menus.*;
import org.lpoo.g55.model.utils.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;

    public LanternaGUI(TerminalSize terminalSize) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfiguration = loadCustomFont();
        Terminal terminal = createTerminal(terminalSize, fontConfiguration);
        this.screen = createTerminalScreen(terminal);
    }

    // Only for testing Lanterna Implementation, it isn't meant to be used in production.
    public LanternaGUI(TerminalScreen stubScreen) {
        screen = stubScreen;
    }

    private AWTTerminalFontConfiguration loadCustomFont() throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("fonts/Topaz-8-1-2.ttf");
        if (resource == null)
            throw new IllegalArgumentException();

        File fileFont = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fileFont);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 15);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    private Terminal createTerminal(TerminalSize terminalSize, AWTTerminalFontConfiguration fontConfiguration) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

        terminalFactory.setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfiguration);

        return terminalFactory.createTerminal();
    }

    private TerminalScreen createTerminalScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke input = screen.pollInput();

        if (input == null)
            return ACTION.NONE;

        switch (input.getKeyType()) {
            case EOF:
                return ACTION.QUIT;
            case Enter:
                return ACTION.SELECT;
            case Backspace:
                return ACTION.BACKSPACE;
            case ArrowDown:
                return ACTION.DOWN;
            case ArrowUp:
                return ACTION.UP;
            case ArrowLeft:
                return ACTION.LEFT;
            case ArrowRight:
                return ACTION.RIGHT;
            case Escape:
                return ACTION.ESC;
            case Character:
                if (input.getCharacter().equals('c') || input.getCharacter().equals('C'))
                    return ACTION.CHAR_C;
                if (input.getCharacter().equals('w') || input.getCharacter().equals('W'))
                    return ACTION.CHAR_W;
                if (input.getCharacter().equals('h') || input.getCharacter().equals('H'))
                    return ACTION.CHAR_H;
                if (input.getCharacter().equals('q') || input.getCharacter().equals('Q'))
                    return ACTION.QUIT;
                if (input.getCharacter().equals('i') || input.getCharacter().equals('I'))
                    return ACTION.CHAR_I;
            default:
                return ACTION.NONE;
        }
    }

    @Override
    public void clearGUI() {
        screen.clear();
    }

    @Override
    public void refreshGUI() throws IOException {
        screen.refresh();
    }

    @Override
    public void closeGUI() throws IOException {
        screen.close();
        screen.getTerminal().close();
    }

    @Override
    public void drawDialog(String message, TerminalPosition lanternaPosition) {
        TextGraphics textGraphics = screen.newTextGraphics();

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        lanternaPosition = lanternaPosition.withRelativeColumn(12).withRelativeRow(3);
        textGraphics.putString(lanternaPosition, message, SGR.BOLD);
    }

    @Override
    public void drawMenu(Menu menu) {
        if (menu instanceof AnimalShopMenu) {
            drawAnimalShopMenu((AnimalShopMenu) menu);
            return;
        }
        if (menu instanceof CropShopMenu) {
            drawCropShopMenu((CropShopMenu) menu);
            return;
        }
        if (menu instanceof InventoryMenu) {
            drawInventoryMenu((InventoryMenu) menu);
            return;
        }

        TerminalPosition lanternaPosition = new TerminalPosition(menu.getPosition().getX(), menu.getPosition().getY());
        TerminalSize lanternaSize = new TerminalSize(menu.getSize().getWidth(), menu.getSize().getHeight());

        TextGraphics textGraphics = screen.newTextGraphics();

        TextColor background = TextColor.Factory.fromString(menu.getBackground());
        TextCharacter[] c = TextCharacter.fromCharacter(' ', background, background);
        textGraphics.fillRectangle(lanternaPosition, lanternaSize, c[0]);

        lanternaPosition = lanternaPosition.withRelativeColumn(1).withRelativeRow(1);
        textGraphics.putString(lanternaPosition, menu.getTitle(), SGR.BOLD);
        lanternaPosition = lanternaPosition.withRelativeRow(3);

        if (menu instanceof InstructionsMenu) {
            for (String string : ((InstructionsMenu) menu).getInstructions()) {
                textGraphics.putString(lanternaPosition, string);
                lanternaPosition = lanternaPosition.withRelativeRow(2);
            }

            lanternaPosition = lanternaPosition.withRelativeRow(1);
        }

        for (int i = 0; i < menu.getEntries().size(); i++) {
            if (menu.getSelectedEntry() == i)
                textGraphics.putString(lanternaPosition, menu.getEntries().get(i).getText(), SGR.REVERSE);
            else
                textGraphics.putString(lanternaPosition, menu.getEntries().get(i).getText());

            lanternaPosition = lanternaPosition.withRelativeRow(2);
        }
    }

    @Override
    public void drawAnimalShopMenu(AnimalShopMenu menu) {
        Map<Animal, Integer> chart = menu.getChart();

        TerminalPosition lanternaPosition = new TerminalPosition(menu.getPosition().getX(), menu.getPosition().getY());
        TerminalSize lanternaSize = new TerminalSize(menu.getSize().getWidth(), menu.getSize().getHeight());
        TextGraphics textGraphics = screen.newTextGraphics();

        TextColor background = TextColor.Factory.fromString(menu.getBackground());
        TextCharacter[] c = TextCharacter.fromCharacter(' ', background, background);
        textGraphics.fillRectangle(lanternaPosition, lanternaSize, c[0]);

        lanternaPosition = lanternaPosition.withRelativeColumn(13).withRelativeRow(1);
        textGraphics.putString(lanternaPosition, menu.getTitle(), SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(-13).withRelativeRow(3);
        textGraphics.putString(lanternaPosition, "ANIMALS", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(21);
        textGraphics.putString(lanternaPosition, "PRICE", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(21);
        textGraphics.putString(lanternaPosition, "QUANTITY", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(-42).withRelativeRow(3);

        int balance = menu.getShop().getMarket().getPlayer().getBalance();
        int quantity = 0, totalBarnQuantity = 0, totalCoopQuantity = 0;
        int price = 0, totalPrice = 0;
        int entryCounter = 0;

        int barnCapacity = menu.getShop().getMarket().getFarm().getBarn().getCapacity();
        int coopCapacity = menu.getShop().getMarket().getFarm().getCoop().getCapacity();

        for (Animal animal : chart.keySet()) {
            if (menu.getSelectedEntry() == entryCounter)
                textGraphics.putString(lanternaPosition, menu.getEntries().get(entryCounter).getText(), SGR.REVERSE);
            else
                textGraphics.putString(lanternaPosition, menu.getEntries().get(entryCounter).getText());

            lanternaPosition = lanternaPosition.withRelativeColumn(21);

            price = animal.getPrice();
            textGraphics.putString(lanternaPosition, String.valueOf(price));
            lanternaPosition = lanternaPosition.withRelativeRow(2);

            if (animal.toString().equals(menu.getEntries().get(entryCounter).getText())) {
                quantity = chart.get(animal);

                if (animal instanceof BarnAnimal) {
                    totalBarnQuantity += quantity;
                    totalPrice += price * quantity;
                } else if (animal instanceof CoopAnimal) {
                    totalCoopQuantity += quantity;
                    totalPrice += price * quantity;
                }
            }

            lanternaPosition = lanternaPosition.withRelativeColumn(24).withRelativeRow(-2);
            textGraphics.putString(lanternaPosition, String.valueOf(quantity));

            lanternaPosition = lanternaPosition.withRelativeColumn(-45).withRelativeRow(2);

            entryCounter += 2;
        }

        lanternaPosition = lanternaPosition.withRelativeRow(2);

        int checkoutEntry = menu.getEntries().size() - 2;
        if (menu.getSelectedEntry() == checkoutEntry)
            textGraphics.putString(lanternaPosition, menu.getEntries().get(checkoutEntry).getText(), SGR.REVERSE);
        else
            textGraphics.putString(lanternaPosition, menu.getEntries().get(checkoutEntry).getText());

        lanternaPosition = lanternaPosition.withRelativeColumn(21);
        textGraphics.putString(lanternaPosition, String.valueOf(totalPrice));

        lanternaPosition = lanternaPosition.withRelativeColumn(24);
        textGraphics.putString(lanternaPosition, String.valueOf(totalBarnQuantity));

        lanternaPosition = lanternaPosition.withRelativeColumn(-45).withRelativeRow(2);

        int leaveShopEntry = menu.getEntries().size() - 1;
        if (menu.getSelectedEntry() == leaveShopEntry)
            textGraphics.putString(lanternaPosition, menu.getEntries().get(leaveShopEntry).getText(), SGR.REVERSE);
        else
            textGraphics.putString(lanternaPosition, menu.getEntries().get(leaveShopEntry).getText());

        if (balance < totalPrice) {
            String string = "You have " + (totalPrice - balance) + " less than you need!";
            drawDialog(string, lanternaPosition);
        }
        if (totalBarnQuantity > barnCapacity) {
            String string = "You have exceed the Barn capacity!";
            drawDialog(string, lanternaPosition.withRelativeRow(2));
        }
        if (totalCoopQuantity > coopCapacity) {
            String string = "You have exceed the Coop capacity!";
            drawDialog(string, lanternaPosition.withRelativeRow(4));
        }
    }

    @Override
    public void drawCropShopMenu(CropShopMenu menu) {
        Map<Item, Integer> chart = menu.getChart();

        TerminalPosition lanternaPosition = new TerminalPosition(menu.getPosition().getX(), menu.getPosition().getY());
        TerminalSize lanternaSize = new TerminalSize(menu.getSize().getWidth(), menu.getSize().getHeight());
        TextGraphics textGraphics = screen.newTextGraphics();

        TextColor background = TextColor.Factory.fromString(menu.getBackground());
        TextCharacter[] c = TextCharacter.fromCharacter(' ', background, background);
        textGraphics.fillRectangle(lanternaPosition, lanternaSize, c[0]);

        lanternaPosition = lanternaPosition.withRelativeColumn(13).withRelativeRow(1);
        textGraphics.putString(lanternaPosition, menu.getTitle(), SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(-13).withRelativeRow(3);
        textGraphics.putString(lanternaPosition, "CROPS", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(21);
        textGraphics.putString(lanternaPosition, "PRICE", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(21);
        textGraphics.putString(lanternaPosition, "QUANTITY", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(-42).withRelativeRow(3);

        int balance = menu.getShop().getMarket().getPlayer().getBalance();
        int quantity = 0, totalQuantity = 0;
        int price = 0, totalPrice = 0;
        int entryCounter = 0;

        for (Item item : chart.keySet()) {
            if (menu.getSelectedEntry() == entryCounter)
                textGraphics.putString(lanternaPosition, menu.getEntries().get(entryCounter).getText(), SGR.REVERSE);
            else
                textGraphics.putString(lanternaPosition, menu.getEntries().get(entryCounter).getText());

            lanternaPosition = lanternaPosition.withRelativeColumn(21);

            price = item.getPrice();
            textGraphics.putString(lanternaPosition, String.valueOf(price));
            lanternaPosition = lanternaPosition.withRelativeRow(2);

            if (item.toString().equals(menu.getEntries().get(entryCounter).getText())) {
                quantity = chart.get(item);

                totalQuantity += quantity;
                totalPrice += price * quantity;
            }

            lanternaPosition = lanternaPosition.withRelativeColumn(24).withRelativeRow(-2);
            textGraphics.putString(lanternaPosition, String.valueOf(quantity));

            lanternaPosition = lanternaPosition.withRelativeColumn(-45).withRelativeRow(2);

            entryCounter += 2;
        }

        lanternaPosition = lanternaPosition.withRelativeRow(3);

        int checkoutEntry = menu.getEntries().size() - 2;
        if (menu.getSelectedEntry() == checkoutEntry)
            textGraphics.putString(lanternaPosition, menu.getEntries().get(checkoutEntry).getText(), SGR.REVERSE);
        else
            textGraphics.putString(lanternaPosition, menu.getEntries().get(checkoutEntry).getText());

        lanternaPosition = lanternaPosition.withRelativeColumn(21);
        textGraphics.putString(lanternaPosition, String.valueOf(totalPrice));

        lanternaPosition = lanternaPosition.withRelativeColumn(24);
        textGraphics.putString(lanternaPosition, String.valueOf(totalQuantity));

        lanternaPosition = lanternaPosition.withRelativeColumn(-45).withRelativeRow(2);

        int leaveShopEntry = menu.getEntries().size() - 1;
        if (menu.getSelectedEntry() == leaveShopEntry)
            textGraphics.putString(lanternaPosition, menu.getEntries().get(leaveShopEntry).getText(), SGR.REVERSE);
        else
            textGraphics.putString(lanternaPosition, menu.getEntries().get(leaveShopEntry).getText());

        if (balance < totalPrice) {
            String string = "You have " + (totalPrice - balance) + " less than you need!";
            drawDialog(string, lanternaPosition);
        }
    }

    @Override
    public void drawInventoryMenu(InventoryMenu menu) {
        Map<Item, Integer> chart = menu.getChart();
        Map<Item, Integer> inventory = menu.getInventory();

        TerminalPosition lanternaPosition = new TerminalPosition(menu.getPosition().getX(), menu.getPosition().getY());
        TerminalSize lanternaSize = new TerminalSize(menu.getSize().getWidth(), menu.getSize().getHeight());
        TextGraphics textGraphics = screen.newTextGraphics();

        TextColor background = TextColor.Factory.fromString(menu.getBackground());
        TextCharacter[] c = TextCharacter.fromCharacter(' ', background, background);
        textGraphics.fillRectangle(lanternaPosition, lanternaSize, c[0]);

        lanternaPosition = lanternaPosition.withRelativeColumn(25).withRelativeRow(1);
        textGraphics.putString(lanternaPosition, menu.getTitle(), SGR.BOLD);

        if (menu.getEntries().isEmpty()) {
            drawDialog("NO ITEMS AVAILABLE IN THE INVENTORY!", lanternaPosition.withRelativeColumn(-25));
            return;
        }

        lanternaPosition = lanternaPosition.withRelativeColumn(-24).withRelativeRow(3);
        textGraphics.putString(lanternaPosition, "ITEMS", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(12);
        textGraphics.putString(lanternaPosition, "SELLING PRICE", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(20);
        textGraphics.putString(lanternaPosition, "AVAILABLE", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(16);
        textGraphics.putString(lanternaPosition, "TO SELL", SGR.BOLD);

        lanternaPosition = lanternaPosition.withRelativeColumn(-48).withRelativeRow(3);

        int quantityToSell = 0, totalQuantityToSell = 0;
        int sellingPrice = 0, totalEarnedMoney = 0;
        int quantityInInventory = 0;
        int entryCounter = 0;

        for (Item item : chart.keySet()) {
            if (menu.getSelectedEntry() == entryCounter)
                textGraphics.putString(lanternaPosition, menu.getEntries().get(entryCounter).getText(), SGR.REVERSE);
            else
                textGraphics.putString(lanternaPosition, menu.getEntries().get(entryCounter).getText());

            sellingPrice = item.getSellingPrice();
            lanternaPosition = lanternaPosition.withRelativeColumn(18);
            textGraphics.putString(lanternaPosition, String.valueOf(sellingPrice));

            if (item.toString().equals(menu.getEntries().get(entryCounter).getText())) {
                quantityToSell = chart.get(item);

                totalQuantityToSell += quantityToSell;
                totalEarnedMoney += sellingPrice * quantityToSell;
            }

            quantityInInventory = inventory.get(item);
            lanternaPosition = lanternaPosition.withRelativeColumn(18);
            textGraphics.putString(lanternaPosition, String.valueOf(Math.max(0, quantityInInventory - quantityToSell)));

            lanternaPosition = lanternaPosition.withRelativeColumn(15);
            textGraphics.putString(lanternaPosition, String.valueOf(Math.min(quantityToSell, quantityInInventory)));

            lanternaPosition = lanternaPosition.withRelativeColumn(-51).withRelativeRow(2);

            entryCounter += 2;
        }

        lanternaPosition = lanternaPosition.withRelativeRow(3);

        int sellEntry = menu.getEntries().size() - 1;
        if (menu.getSelectedEntry() == sellEntry)
            textGraphics.putString(lanternaPosition, menu.getEntries().get(sellEntry).getText(), SGR.REVERSE);
        else
            textGraphics.putString(lanternaPosition, menu.getEntries().get(sellEntry).getText());

        lanternaPosition = lanternaPosition.withRelativeColumn(51);
        textGraphics.putString(lanternaPosition, String.valueOf(totalQuantityToSell));

        lanternaPosition = lanternaPosition.withRelativeColumn(-10).withRelativeRow(2);
        String price = "Total: " +  totalEarnedMoney;
        textGraphics.putString(lanternaPosition, price);

    }

    /* Elements Draw Functions */

    @Override
    public void drawElement(Position position, Character representation, String foreground, String background) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString(foreground));
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(background));
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), representation.toString());
    }

    @Override
    public void drawPlayerName(Player player) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(TerminalPosition.OFFSET_1x1, player.getName(), SGR.BOLD);
    }

    @Override
    public void drawBalance(Player player) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalPosition terminalPosition = TerminalPosition.OFFSET_1x1.withRelativeRow(2);

        TextColor balanceColor = new TextColor.Indexed(226);
        TextCharacter[] balance = TextCharacter.fromCharacter('C', balanceColor, null, SGR.CIRCLED);
        textGraphics.setCharacter(terminalPosition, balance[0]);
        textGraphics.putString(terminalPosition.withRelativeColumn(2), String.valueOf(player.getBalance()));
    }

    @Override
    public void drawFarm(Farm farm) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalSize farmSize = new TerminalSize(farm.getSize().getWidth(), farm.getSize().getHeight());

        TerminalPosition initial = TerminalPosition.TOP_LEFT_CORNER.withRelative(4, 5);
        TerminalPosition finish = initial.withRelativeColumn(farm.getSize().getWidth() - 2);

        for (int i = 1; i < farm.getSize().getHeight(); i++) {
            int colorIndex = (i % 2 == 0) ? 2 : 28;
            TextColor color = new TextColor.Indexed(colorIndex);
            TextCharacter[] ch = TextCharacter.fromCharacter(' ', color, color);

            textGraphics.drawLine(initial, finish, ch[0]);
            initial = initial.withRelativeRow(1);
            finish = finish.withRelativeRow(1);
        }

        TerminalPosition topLeftCornerBorder = TerminalPosition.TOP_LEFT_CORNER.withRelative(3, 4);
        TextColor borderColor = new TextColor.Indexed(242);
        TextCharacter[] border = TextCharacter.fromCharacter(' ', borderColor, borderColor);
        textGraphics.drawRectangle(topLeftCornerBorder, farmSize, border[0]);
    }

    @Override
    public void drawCoop(Coop coop) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalSize coopSize = new TerminalSize(coop.getSize().getWidth(), coop.getSize().getHeight());

        TerminalPosition coopTopLeftCorner = TerminalPosition.TOP_LEFT_CORNER.withRelative(3, 4);

        TextColor innerColor = new TextColor.Indexed(220);
        TextCharacter[] border = TextCharacter.fromCharacter(' ', innerColor, innerColor);
        TextColor borderColor = new TextColor.Indexed(136);
        TextCharacter[] inner = TextCharacter.fromCharacter(' ', borderColor, borderColor);

        textGraphics.fillRectangle(coopTopLeftCorner, coopSize, inner[0]);
        textGraphics.drawRectangle(coopTopLeftCorner, coopSize, border[0]);

        TerminalPosition exitPosition = new TerminalPosition(coop.getExitPosition().getX(), coop.getExitPosition().getY());
        TextColor exitForegroundColor = new TextColor.Indexed(190);
        TextColor exitBackgroundColor = new TextColor.Indexed(245);
        TextCharacter[] exitChar = TextCharacter.fromCharacter('_', exitForegroundColor, exitBackgroundColor, SGR.BOLD, SGR.UNDERLINE);
        textGraphics.setCharacter(exitPosition, exitChar[0]);
    }

    @Override
    public void drawCoopOutside(Position position) {
        TextGraphics textGraphics = screen.newTextGraphics();

        // Coop Walls
        TerminalPosition coopOutsidePosition = new TerminalPosition(position.getX(), position.getY());
        coopOutsidePosition = coopOutsidePosition.withRelative(-2, -3);
        TerminalSize coopOutsideSize = new TerminalSize(5, 4);

        TextColor innerBackgroundColor = new TextColor.Indexed(136);
        TextColor innerForegroundColor = new TextColor.Indexed(130);
        TextCharacter[] inner = TextCharacter.fromCharacter('_', innerForegroundColor, innerBackgroundColor, SGR.BOLD);
        textGraphics.fillRectangle(coopOutsidePosition, coopOutsideSize, inner[0]);

        // Coop Roof
        TerminalPosition p1 = new TerminalPosition(position.getX(), position.getY() - 6);
        TerminalPosition p2 = new TerminalPosition(position.getX() + 3, position.getY() - 3);
        TerminalPosition p3 = new TerminalPosition(position.getX() - 3, position.getY() - 3);

        TextColor roofForegroundColor = new TextColor.Indexed(203);
        TextColor roofBackgroundColor = new TextColor.Indexed(208);
        TextCharacter[] roof = TextCharacter.fromCharacter('"', roofForegroundColor, roofBackgroundColor, SGR.BOLD);
        textGraphics.fillTriangle(p1, p2, p3, roof[0]);

        // Coop Door
        TerminalPosition doorPosition = new TerminalPosition(position.getX(), position.getY() - 1);
        TextColor doorColor = new TextColor.Indexed(52);
        TextCharacter[] door = TextCharacter.fromCharacter(' ', doorColor, doorColor);
        textGraphics.drawLine(doorPosition, doorPosition.withRelativeRow(1), door[0]);
    }

    @Override
    public void drawBarn(Barn barn) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalSize barnSize = new TerminalSize(barn.getSize().getWidth(), barn.getSize().getHeight());

        TerminalPosition barnTopLeftCorner = TerminalPosition.TOP_LEFT_CORNER.withRelative(3, 4);

        TextColor innerColor = new TextColor.Indexed(100);
        TextCharacter[] border = TextCharacter.fromCharacter(' ', innerColor, innerColor);
        TextColor borderColor = new TextColor.Indexed(130);
        TextCharacter[] inner = TextCharacter.fromCharacter(' ', borderColor, borderColor);

        textGraphics.fillRectangle(barnTopLeftCorner, barnSize, inner[0]);
        textGraphics.drawRectangle(barnTopLeftCorner, barnSize, border[0]);

        TerminalPosition exitPosition = new TerminalPosition(barn.getExitPosition().getX(), barn.getExitPosition().getY());
        TextColor exitForegroundColor = new TextColor.Indexed(190);
        TextColor exitBackgroundColor = new TextColor.Indexed(245);
        TextCharacter[] exitChar = TextCharacter.fromCharacter('_', exitForegroundColor, exitBackgroundColor, SGR.BOLD, SGR.UNDERLINE);
        textGraphics.setCharacter(exitPosition, exitChar[0]);
    }

    @Override
    public void drawBarnOutside(Position position) {
        TextGraphics textGraphics = screen.newTextGraphics();

        // Barn Walls
        TerminalPosition barnOutsidePosition = new TerminalPosition(position.getX(), position.getY());
        barnOutsidePosition = barnOutsidePosition.withRelative(-3, -5);
        TerminalSize barnOutsideSize = new TerminalSize(7, 6);

        TextColor innerColor = new TextColor.Indexed(124);
        TextCharacter[] inner = TextCharacter.fromCharacter(' ', innerColor, innerColor);
        textGraphics.fillRectangle(barnOutsidePosition, barnOutsideSize, inner[0]);

        // Small Decoration
        TextColor decorationColor = new TextColor.Indexed(15);
        TextCharacter[] decoration = TextCharacter.fromCharacter(' ', decorationColor, decorationColor);

        TerminalPosition topDecorationPosition = new TerminalPosition(position.getX(), position.getY() - 4);
        TerminalPosition initialDecorationPosition = topDecorationPosition.withRelativeColumn(-3).withRelativeRow(4);
        TerminalPosition finalDecorationPosition = topDecorationPosition.withRelativeColumn(3).withRelativeRow(4);

        textGraphics.drawLine(initialDecorationPosition, topDecorationPosition, decoration[0]);
        textGraphics.drawLine(topDecorationPosition, finalDecorationPosition, decoration[0]);

        // Barn Roof
        TerminalPosition p1 = new TerminalPosition(position.getX(), position.getY() - 7);
        TerminalPosition p2 = new TerminalPosition(position.getX() + 4, position.getY() - 5);
        TerminalPosition p3 = new TerminalPosition(position.getX() - 4, position.getY() - 5);

        TextColor roofColor = new TextColor.Indexed(15);
        TextCharacter[] roofBorder = TextCharacter.fromCharacter(' ', roofColor, roofColor);
        textGraphics.fillTriangle(p1, p2, p3, roofBorder[0]);

        p2 = p2.withRelativeColumn(1);
        p3 = p3.withRelativeColumn(-1);

        TextColor roofBorderColor = new TextColor.Indexed(0);
        TextCharacter[] roof = TextCharacter.fromCharacter(' ', roofBorderColor, roofBorderColor);
        textGraphics.drawTriangle(p1, p2, p3, roof[0]);

        // Coop Door
        TerminalPosition doorPosition = new TerminalPosition(position.getX(), position.getY() - 1);
        TextColor doorColor = new TextColor.Indexed(0);
        TextCharacter[] door = TextCharacter.fromCharacter(' ', doorColor, doorColor);
        textGraphics.drawLine(doorPosition, doorPosition.withRelativeRow(1), door[0]);
    }

    @Override
    public void drawField(Field field) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalPosition fieldPosition = new TerminalPosition(field.getTopLeftCorner().getX(), field.getTopLeftCorner().getY());
        TerminalSize fieldSize = new TerminalSize(field.getSize().getWidth(), field.getSize().getHeight());

        TextColor innerColor = new TextColor.Indexed(94);
        TextCharacter[] inner = TextCharacter.fromCharacter('_', innerColor, innerColor);
        TextColor borderColor = new TextColor.Indexed(102);
        TextCharacter[] border = TextCharacter.fromCharacter(' ', borderColor, borderColor);

        textGraphics.fillRectangle(fieldPosition, fieldSize, inner[0]);
        textGraphics.drawRectangle(fieldPosition, fieldSize, border[0]);

        TextColor gateColor = new TextColor.Indexed(52);
        TextCharacter[] gateChar = TextCharacter.fromCharacter(' ', gateColor, gateColor);
        for (int deltaX = 0; deltaX <= Field.getGateSize(); deltaX++)
            textGraphics.setCharacter(field.getGate().getX() + deltaX, field.getGate().getY(), gateChar[0]);
    }

    @Override
    public void drawMarket(Market market) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalSize farmSize = new TerminalSize(market.getSize().getWidth(), market.getSize().getHeight());

        TerminalPosition initial = TerminalPosition.TOP_LEFT_CORNER.withRelative(4, 5);
        TerminalPosition finish = initial.withRelativeColumn(market.getSize().getWidth() - 2);

        for (int i = 1; i < market.getSize().getHeight(); i++) {
            int colorIndex = (i % 2 == 0) ? 102 : 145;
            TextColor color = new TextColor.Indexed(colorIndex);
            TextCharacter[] ch = TextCharacter.fromCharacter(' ', color, color);

            textGraphics.drawLine(initial, finish, ch[0]);
            initial = initial.withRelativeRow(1);
            finish = finish.withRelativeRow(1);
        }

        TerminalPosition topLeftCornerBorder = TerminalPosition.TOP_LEFT_CORNER.withRelative(3, 4);
        TextColor borderColor = new TextColor.Indexed(88);
        TextCharacter[] border = TextCharacter.fromCharacter(' ', borderColor, borderColor);
        textGraphics.drawRectangle(topLeftCornerBorder, farmSize, border[0]);
    }

    @Override
    public void drawCropShop(CropShop shop) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalPosition terminalPosition = new TerminalPosition(shop.getPosition().getX(), shop.getPosition().getY());
        TerminalSize terminalSize = new TerminalSize(7, 3);

        TextCharacter[] inner = TextCharacter.fromCharacter(' ', TextColor.ANSI.GREEN, TextColor.ANSI.GREEN);
        textGraphics.fillRectangle(terminalPosition, terminalSize, inner[0]);

        terminalPosition = terminalPosition.withRelative(1, 1);
        textGraphics.putString(terminalPosition, "CROPS", SGR.BOLD);
    }

    @Override
    public void drawAnimalShop(AnimalShop shop) {
        TextGraphics textGraphics = screen.newTextGraphics();
        TerminalPosition terminalPosition = new TerminalPosition(shop.getPosition().getX(), shop.getPosition().getY());
        TerminalSize terminalSize = new TerminalSize(9, 3);

        TextCharacter[] inner = TextCharacter.fromCharacter(' ', TextColor.ANSI.RED_BRIGHT, TextColor.ANSI.RED_BRIGHT);
        textGraphics.fillRectangle(terminalPosition, terminalSize, inner[0]);

        terminalPosition = terminalPosition.withRelative(1, 1);
        textGraphics.putString(terminalPosition, "ANIMALS", SGR.BOLD);
    }
}
