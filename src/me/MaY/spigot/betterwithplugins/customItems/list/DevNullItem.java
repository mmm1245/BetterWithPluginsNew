package me.MaY.spigot.betterwithplugins.customItems.list;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.MaY.spigot.betterwithplugins.BetterWithPluginsMain;
import me.MaY.spigot.betterwithplugins.customItems.CustomItem;
import me.MaY.spigot.betterwithplugins.util.CustomItemBuilder;
import me.MaY.spigot.betterwithplugins.util.NamespacedKeyStorage;

public class DevNullItem extends CustomItem{
	@Override
	public ItemStack create(int amount) {
		ItemStack is = CustomItemBuilder.createCustomItemStack(Material.NETHER_STAR, amount, "/dev/null", NamespacedKeyStorage.getDevNullItemKey(), "Scraps picked items");
		ItemMeta meta = is.getItemMeta();
		meta.getPersistentDataContainer().set(NamespacedKeyStorage.getScrapKey(), PersistentDataType.STRING, Material.COBBLESTONE.name());
		is.setItemMeta(meta);
		return is;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean checkType(ItemStack is) {
		if(is == null)
			return false;
		if(is.getItemMeta() == null)
			return false;
		return is.getItemMeta().getPersistentDataContainer().getOrDefault(NamespacedKeyStorage.getCustomItemKey(), PersistentDataType.STRING, "abc").equals(NamespacedKeyStorage.getDevNullItemKey().toString());
	}
}
