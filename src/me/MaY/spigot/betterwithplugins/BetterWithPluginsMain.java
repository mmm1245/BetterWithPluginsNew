package me.MaY.spigot.betterwithplugins;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.MaY.spigot.betterwithplugins.commands.BWPGiveCommand;
import me.MaY.spigot.betterwithplugins.customItems.CustomItemRegistry;
import me.MaY.spigot.betterwithplugins.customItems.DefaultCustomItem;
import me.MaY.spigot.betterwithplugins.customItems.IOnCreate;
import me.MaY.spigot.betterwithplugins.customItems.list.DevNullItem;
import me.MaY.spigot.betterwithplugins.customItems.list.ExplosivePickaxe;
import me.MaY.spigot.betterwithplugins.util.NamespacedKeyStorage;

public class BetterWithPluginsMain extends JavaPlugin {
	private CustomItemRegistry customItemRegistry;
	private NamespacedKeyStorage namespacedKeyStorage;
	
	private static DefaultCustomItem flyingThingItem;
    @SuppressWarnings("deprecation")
	@Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new TestListener(), this);
        this.namespacedKeyStorage = new NamespacedKeyStorage();
        this.customItemRegistry = new CustomItemRegistry();
        getLogger().info(this.getClass().getSimpleName() + ": Working...");
        
        PluginCommand command = this.getCommand("bwpgive");
		command.setExecutor(new BWPGiveCommand());
		command.setTabCompleter(new BWPGiveCommand());
		
		flyingThingItem = new DefaultCustomItem(Material.FEATHER, "Flying thing", NamespacedKeyStorage.getFlyingThingItemKey(), "Lets you fly");
		
		customItemRegistry.register(NamespacedKeyStorage.getExplosivePickaxeKey(), new DefaultCustomItem(Material.DIAMOND_PICKAXE, "Explosive pickaxe", NamespacedKeyStorage.getExplosivePickaxeKey(), "Boom!!!"));
		customItemRegistry.register(NamespacedKeyStorage.getFlyingThingItemKey(), flyingThingItem);
        customItemRegistry.register(NamespacedKeyStorage.getDevNullItemKey(), new DefaultCustomItem(Material.NETHER_STAR, "/dev/null", NamespacedKeyStorage.getDevNullItemKey(), new IOnCreate() {
			
			@Override
			public ItemStack create(ItemStack stack) {
				ItemMeta meta = stack.getItemMeta();
				meta.getPersistentDataContainer().set(NamespacedKeyStorage.getScrapKey(), PersistentDataType.STRING, Material.COBBLESTONE.name());
				stack.setItemMeta(meta);
				return stack;
			}
		}, "Scraps selected item"));
        new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player pl : Bukkit.getOnlinePlayers()) {
					if(flyingThingItem.checkType(pl.getItemInHand())) {
						pl.setAllowFlight(true);
						pl.setFlying(true);
						pl.setFlySpeed(1);
					} else {
						pl.setAllowFlight(false);
						pl.setFlying(false);
						pl.setFlySpeed(1);
					}
				}
			}
		}.runTaskTimer(this, 20, 20);
    }

    @Override
    public void onDisable() {

    }
    public CustomItemRegistry getCustomItemRegistry() {
    	return customItemRegistry;
    }
	public static BetterWithPluginsMain getINSTANCE() {
		return JavaPlugin.getPlugin(BetterWithPluginsMain.class);
	}
	public static NamespacedKeyStorage getNamespacedKeyStorage() {
		return getINSTANCE().namespacedKeyStorage;
	}
}
