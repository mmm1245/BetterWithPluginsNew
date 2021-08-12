package me.MaY.spigot.betterwithplugins.util;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.MaY.spigot.betterwithplugins.BetterWithPluginsMain;

public class CustomItemBuilder {
	public static ItemStack createCustomItemStack(Material material, int amount, String name, NamespacedKey id, String...lore) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta meta = itemStack.getItemMeta();
		if(name != null)
			meta.setDisplayName("§f" + name);
		meta.getPersistentDataContainer().set(NamespacedKeyStorage.getCustomItemKey(), PersistentDataType.STRING, id.toString());
		if(lore != null && lore.length > 0) {
			ArrayList<String> lores = new ArrayList<>();
			for(int i = 0;i < lore.length;i++) {
				lores.add(lore[i]);
			}
			meta.setLore(lores);
		}
		itemStack.setItemMeta(meta);
		return itemStack;
	}
	public static ItemStack createCustomItemStack(Material material, int amount, String name, int customModelData, NamespacedKey id, String...lore) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta meta = itemStack.getItemMeta();
		if(name != null)
			meta.setDisplayName("§f" + name);
		meta.getPersistentDataContainer().set(NamespacedKeyStorage.getCustomItemKey(), PersistentDataType.STRING, id.toString());
		if(lore != null && lore.length > 0) {
			ArrayList<String> lores = new ArrayList<>();
			for(int i = 0;i < lore.length;i++) {
				lores.add(lore[i]);
			}
			meta.setLore(lores);
		}
		meta.setCustomModelData(customModelData);
		itemStack.setItemMeta(meta);
		return itemStack;
	}
}
