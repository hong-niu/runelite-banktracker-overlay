package com.bankTrackerOverlay;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Bank Tracker Overlay")
public interface BankTrackerOverlayConfig extends Config
{

	@ConfigItem(
			keyName = "trackedItems",
			name = "Tracked Items:",
			description = "Enter items you want to be tracked",
			position =1
	)
	default String getTrackedItems()
	{
		return "";
	}
	@ConfigItem(
			keyName = "trackedItems",
			name = "",
			description = ""
	)
	void setTrackedItems(String trackedItems);

	@ConfigItem(
			keyName = "runeicons",
			name = "Show",
			description = "Show the rune icons next to the number of runes in pouch",
			position = 2
	)
	default boolean showIcons()
	{
		return true;
	}

	/*
	@ConfigItem(
			keyName = "showGEPrice",
			name = "Show Grand Exchange Prices",
			description = "Grand exchange prices should be shown on tooltips",
			position = 7
	)
	default boolean showGEPrice()
	{
		return true;
	}


	@ConfigItem(
			keyName = "showHAValue",
			name = "Show High Alchemy Values",
			description = "High Alchemy values should be shown on tooltips",
			position = 2
	)
	default boolean showHAValue()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showEA",
			name = "Show Price Each on Stacks",
			description = "The price/value of each item should be shown on stacks",
			position = 3
	)
	default boolean showEA()
	{
		return true;
	}

	@ConfigItem(
			keyName = "hideInventory",
			name = "Hide Tooltips on Inventory Items",
			description = "Tooltips should be hidden on items in the inventory",
			position = 4
	)
	default boolean hideInventory()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showAlchProfit",
			name = "Show High Alchemy Profit",
			description = "Show the profit from casting high alchemy on items",
			position = 5
	)
	default boolean showAlchProfit()
	{
		return false;
	}

	@ConfigItem(
			keyName = "showWhileAlching",
			name = "Show prices while alching",
			description = "Show the price overlay while using High Alchemy. Takes priority over \"Hide tooltips on Inventory Items\"",
			position = 6
	)
	default boolean showWhileAlching()
	{
		return true;
	}
	*/

}
