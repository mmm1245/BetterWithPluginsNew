package me.MaY.spigot.betterwithplugins.util;

import org.bukkit.NamespacedKey;

import me.MaY.spigot.betterwithplugins.BetterWithPluginsMain;

public class NamespacedKeyStorage {
	private NamespacedKey customItemKey;
	private NamespacedKey explosivePickaxeItemKey;
	private NamespacedKey devNullItemKey;
	private NamespacedKey flyingThingItemKey;
	private NamespacedKey scrapKey;
	public NamespacedKeyStorage() {
		customItemKey = new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "customItem");
		explosivePickaxeItemKey = new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "explosive_pickaxe");
		devNullItemKey = new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "dev_null");
		scrapKey = new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "scrap");
		flyingThingItemKey = new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "flying_thing");
	}
	public static NamespacedKey getCustomItemKey() {
		return BetterWithPluginsMain.getNamespacedKeyStorage().customItemKey;
	}
	public static NamespacedKey getExplosivePickaxeKey() {
		return BetterWithPluginsMain.getNamespacedKeyStorage().explosivePickaxeItemKey;
	}
	public static NamespacedKey getDevNullItemKey() {
		return BetterWithPluginsMain.getNamespacedKeyStorage().devNullItemKey;
	}
	public static NamespacedKey getScrapKey() {
		return BetterWithPluginsMain.getNamespacedKeyStorage().scrapKey;
	}
	public static NamespacedKey getFlyingThingItemKey() {
		return BetterWithPluginsMain.getNamespacedKeyStorage().flyingThingItemKey;
	}
}
