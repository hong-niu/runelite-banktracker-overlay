package com.bankTrackerOverlay;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.itemprices.ItemPricesConfig;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.Text;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Bank Tracker Overlay"
)
public class BankTrackerOverlayPlugin extends Plugin {

	private List<String> trackedItems = new ArrayList<>();

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private BankTrackerWidgetOverlay overlay;

	@Inject
	private BankTrackerOverlayConfig config;

	@Provides
	BankTrackerOverlayConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankTrackerOverlayConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		trackedItems = Text.fromCSV(config.getTrackedItems());
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		trackedItems = Text.fromCSV(config.getTrackedItems());
	}

	public boolean isTrackedItem(String name)
	{
		return trackedItems.contains(name);
	}
}

/*
	@Inject
	private Client client;

	@Inject
	private BankTrackerOverlayConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
    BankTrackerOverlayConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankTrackerOverlayConfig.class);
	}
}

 */
