package me.MaY.spigot.betterwithplugins.customItems;

import org.bukkit.inventory.ItemStack;

public abstract class CustomItem {
	public abstract ItemStack create(int amount);
	public abstract boolean checkType(ItemStack is);
	public ItemStack create() {return create(1);}
}
