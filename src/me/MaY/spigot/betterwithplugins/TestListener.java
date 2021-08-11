package me.MaY.spigot.betterwithplugins;

import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.MaY.spigot.betterwithplugins.customItems.list.ExplosivePickaxe;

import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;

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
        String str = is.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "customItem"), PersistentDataType.STRING);
        if(str == null)
        	return;
        if(!str.equals(new NamespacedKey(BetterWithPluginsMain.getINSTANCE(), "explosive_pickaxe").toString()))
        	return;
        event.setCancelled(true);
        ExplosivePickaxe.pickExplode(event.getBlock().getLocation(), is);
    }


}