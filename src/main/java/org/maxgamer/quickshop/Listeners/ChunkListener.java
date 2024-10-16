package org.maxgamer.quickshop.Listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.Shop.Shop;

import java.util.HashMap;

public class ChunkListener implements Listener {
	private QuickShop plugin;

	public ChunkListener(QuickShop plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChunkLoad(ChunkLoadEvent e) {
		Chunk c = e.getChunk();
		if (plugin.getShopManager().getShops() == null)
			return;
		HashMap<Location, Shop> inChunk = plugin.getShopManager().getShops(c);
		if (inChunk == null)
			return;
		for (Shop shop : inChunk.values()) {
			shop.onLoad();
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChunkUnload(ChunkUnloadEvent e) {
		Chunk c = e.getChunk();
		HashMap<Location, Shop> inChunk = plugin.getShopManager().getShops(c);
		if (inChunk == null)
			return;
		for (Shop shop : inChunk.values()) {
			shop.onUnload();
		}
	}
}