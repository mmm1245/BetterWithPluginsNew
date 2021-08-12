package me.MaY.spigot.betterwithplugins.customItems.list;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.MaY.spigot.betterwithplugins.BetterWithPluginsMain;
import me.MaY.spigot.betterwithplugins.customItems.CustomItem;
import me.MaY.spigot.betterwithplugins.util.CustomItemBuilder;
import me.MaY.spigot.betterwithplugins.util.NamespacedKeyStorage;

public class ExplosivePickaxe extends CustomItem{
	@Override
	public ItemStack create(int amount) {
		return CustomItemBuilder.createCustomItemStack(Material.DIAMOND_PICKAXE, amount, "Explosive pickaxe", NamespacedKeyStorage.getExplosivePickaxeKey(), "BOOM!!!");
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean checkType(ItemStack is) {
		if(is == null)
			return false;
		if(is.getItemMeta() == null)
			return false;
		return is.getItemMeta().getPersistentDataContainer().getOrDefault(NamespacedKeyStorage.getCustomItemKey(), PersistentDataType.STRING, "abc").equals(NamespacedKeyStorage.getExplosivePickaxeKey().toString());
	}
	
	public static void pickExplode(Location loc, ItemStack stack) {
		loc.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, loc, 1);
		loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
		int xL = loc.getBlockX();
		int yL = loc.getBlockY();
		int zL = loc.getBlockZ();
		for(int x = -1;x < 2;x++) {
			for(int y = -1;y < 2;y++) {
				for(int z = -1;z < 2;z++) {
					Block block = loc.getWorld().getBlockAt(xL+x, yL+y, zL+z);
					if(block == null)
						continue;
					if(block.getType().getBlastResistance() <= 6f) {
						block.breakNaturally(stack);
					}
				}
			}
		}
	}

}
