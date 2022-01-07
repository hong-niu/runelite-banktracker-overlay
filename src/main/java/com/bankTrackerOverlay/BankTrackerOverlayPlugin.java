package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.itemprices.ItemPricesConfig;
import net.runelite.client.plugins.itemprices.ItemPricesOverlay;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class BankTrackerOverlayPlugin extends Plugin {

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ItemPricesOverlay overlay;

	@Provides
	ItemPricesConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ItemPricesConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
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
