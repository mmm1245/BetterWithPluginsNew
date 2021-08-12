package me.MaY.spigot.betterwithplugins;

import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import me.MaY.spigot.betterwithplugins.commands.BWPGiveCommand;
import me.MaY.spigot.betterwithplugins.customItems.CustomItemRegistry;
import me.MaY.spigot.betterwithplugins.customItems.list.DevNullItem;
import me.MaY.spigot.betterwithplugins.customItems.list.ExplosivePickaxe;
import me.MaY.spigot.betterwithplugins.util.NamespacedKeyStorage;

public class BetterWithPluginsMain extends JavaPlugin {
	private CustomItemRegistry customItemRegistry;
	private NamespacedKeyStorage namespacedKeyStorage;
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new TestListener(), this);
        this.namespacedKeyStorage = new NamespacedKeyStorage();
        this.customItemRegistry = new CustomItemRegistry();
        getLogger().info(this.getClass().getSimpleName() + ": Working...");
        
        PluginCommand command = this.getCommand("bwpgive");
		command.setExecutor(new BWPGiveCommand());
		command.setTabCompleter(new BWPGiveCommand());
		
		customItemRegistry.register(NamespacedKeyStorage.getExplosivePickaxeKey(), new ExplosivePickaxe());
        customItemRegistry.register(NamespacedKeyStorage.getDevNullItemKey(), new DevNullItem());
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
