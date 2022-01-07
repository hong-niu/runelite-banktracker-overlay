package com.bankTrackerOverlay;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.Point;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.runepouch.RunepouchConfig;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.tooltip.Tooltip;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.*;
@Slf4j

public class BankTrackerWidgetOverlay extends WidgetItemOverlay {
    private final Client client;
    private final BankTrackerOverlayConfig config;
    private final TooltipManager tooltipManager;
    private final BankTrackerOverlayPlugin plugin;

    @Inject
    BankTrackerWidgetOverlay(Client client, BankTrackerOverlayConfig config, TooltipManager tooltipManager, BankTrackerOverlayPlugin plugin) {
        this.tooltipManager = tooltipManager;
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        showOnBank();
        showOnInventory();
    }

    @Inject
    private ItemManager itemManager;

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
        /*
        if (itemId != ItemID.ARMADYL_HELMET && itemId != ItemID.DRAGON_DART) {
            return;
        }
        */
       if (!plugin.isTrackedItem(client.getItemDefinition(itemId).getName())) {
           return;
       }

        int gePrice = itemManager.getItemPrice(itemId);
        graphics.setFont(FontManager.getRunescapeSmallFont());
        Point location = widgetItem.getCanvasLocation();

        //make tool box
        /*
        StringBuilder tooltipBuilder = new StringBuilder();
        tooltipBuilder
                .append(100)
                .append(" ")
                .append(ColorUtil.wrapWithColorTag("hi", Color.YELLOW))
                .append("</br>");
        */
        //overlays the text onto the thing
        graphics.setColor(Color.black);
        graphics.drawString("" + formatNumber(gePrice), location.getX() + (config.showIcons() ? 12 : 5),
                location.getY() + 13 + (graphics.getFontMetrics().getHeight() - 1) * 0 +11);
        graphics.setColor(Color.GREEN);
        graphics.drawString("" + formatNumber(gePrice), location.getX() + (config.showIcons() ? 11 : 4),
                location.getY() + 12 + (graphics.getFontMetrics().getHeight() - 1) * 0+11);

        //-graphics.getFontMetrics().stringWidth("%")
        graphics.setColor(Color.black);
        graphics.drawString("+" + 25 + "%", location.getX()-graphics.getFontMetrics().stringWidth("%") + (config.showIcons() ? 12 : 5),
                location.getY() + 13 + (graphics.getFontMetrics().getHeight() - 1) * 0 + 22);
        graphics.setColor(Color.GREEN);
        graphics.drawString("+" + 25 + "%", location.getX()-graphics.getFontMetrics().stringWidth("%") + (config.showIcons() ? 11 : 4),
                location.getY() + 12 + (graphics.getFontMetrics().getHeight() - 1) * 0 + 22);


       // OverlayUtil.renderTextLocation(graphics, new Point(location.getX() - 1, location.getY() + graphics.getFontMetrics().getHeight()), "1000k", Color.GREEN);
       // OverlayUtil.renderTextLocation(graphics, new Point(location.getX() - 1, location.getY() + graphics.getFontMetrics().getHeight()*11-1), "+24%", Color.GREEN);

        /*
        //part of toolTip
        String tooltip = tooltipBuilder.toString();

        if (!tooltip.isEmpty())
              //  && widgetItem.getCanvasBounds().contains(client.getMouseCanvasPosition().getX(), client.getMouseCanvasPosition().getY()))
        //&& (config.runePouchOverlayMode() == MOUSE_HOVER || config.runePouchOverlayMode() == BOTH))
        {
            tooltipManager.add(new Tooltip(tooltip));
        }
        */
    }
    private static String formatNumber(int amount)
    {
        double amount2 = (double) amount;
        if (amount < 1000) {
            return amount + " ";
        }
        else if (amount < 1000000) {
            return Double.toString(amount2 / 1000).substring(0,4) + "K";
           // return Integer.toString(amount).substring(0,3) + "K";
        }
        else if (amount < 1000000000) {
            return Double.toString(amount2 / 1000000).substring(0,4) + "M";
            //return Integer.toString(amount).substring(0,3) + "M";
        }


        return amount < 1000 ? String.valueOf(amount) : amount / 1000 + "K";
    }
}
