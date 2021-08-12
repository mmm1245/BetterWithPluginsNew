package me.MaY.spigot.betterwithplugins;

import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.MaY.spigot.betterwithplugins.customItems.list.ExplosivePickaxe;
import me.MaY.spigot.betterwithplugins.util.NamespacedKeyStorage;

import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;

public class TestListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.GREEN + " has joined the game!");
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.RED + " has left the game!");
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        ItemStack is = event.getPlayer().getItemInHand();
        if(is == null)
        	return;
        if(is.getItemMeta() == null)
        	return;
        String str = is.getItemMeta().getPersistentDataContainer().get(NamespacedKeyStorage.getCustomItemKey(), PersistentDataType.STRING);
        if(str == null)
        	return;
        if(!str.equals(new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "explosive_pickaxe").toString()))
        	return;
        pickExplode(event.getBlock().getLocation(), is);
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        ItemStack is = event.getPlayer().getInventory().getItemInOffHand();
        if(is == null)
        	return;
        if(is.getItemMeta() == null)
        	return;
        String str = is.getItemMeta().getPersistentDataContainer().get(NamespacedKeyStorage.getCustomItemKey(), PersistentDataType.STRING);
        if(str == null)
        	return;
        if(!str.equals(NamespacedKeyStorage.getDevNullItemKey().toString()))
        	return;
        if(!event.getItem().getItemStack().getType().name().equals(is.getItemMeta().getPersistentDataContainer().get(NamespacedKeyStorage.getScrapKey(), PersistentDataType.STRING)))
        	return;
        event.setCancelled(true);
        event.getItem().remove();
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
