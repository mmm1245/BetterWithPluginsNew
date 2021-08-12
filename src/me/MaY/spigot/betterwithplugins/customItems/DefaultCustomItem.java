package me.MaY.spigot.betterwithplugins.customItems;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.MaY.spigot.betterwithplugins.util.NamespacedKeyStorage;

public class DefaultCustomItem extends CustomItem{
	Material material;
	String customName;
	NamespacedKey key;
	String lore[];
	IOnCreate createEvent;
	public DefaultCustomItem(Material material, String customName, NamespacedKey key, String... lore) {
		this.material = material;
		this.customName = customName;
		this.key = key;
		this.lore = lore;
	}
	public DefaultCustomItem(Material material, String customName, NamespacedKey key) {
		this.material = material;
		this.customName = customName;
		this.key = key;
	}
	public DefaultCustomItem(Material material, String customName, NamespacedKey key, IOnCreate createEvent, String... lore) {
		this.material = material;
		this.customName = customName;
		this.key = key;
		this.lore = lore;
		this.createEvent = createEvent;
	}
	public DefaultCustomItem(Material material, String customName, NamespacedKey key, IOnCreate createEvent) {
		this.material = material;
		this.customName = customName;
		this.key = key;
		this.createEvent = createEvent;
	}
	@Override
	public ItemStack create(int amount) {
		if(createEvent == null)
			return CustomItemBuilder.createCustomItemStack(material, amount, customName, key, lore);
		else 
			return createEvent.create(CustomItemBuilder.createCustomItemStack(material, amount, customName, key, lore));
	}

	@Override
	public boolean checkType(ItemStack is) {
		if(is == null)
			return false;
		if(is.getItemMeta() == null)
			return false;
		return is.getItemMeta().getPersistentDataContainer().getOrDefault(NamespacedKeyStorage.getCustomItemKey(), PersistentDataType.STRING, "abc").equals(key.toString());
	}
	
}
