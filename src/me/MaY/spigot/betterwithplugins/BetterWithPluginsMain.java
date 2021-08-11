package me.MaY.spigot.betterwithplugins;

import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import me.MaY.spigot.betterwithplugins.commands.BWPGiveCommand;
import me.MaY.spigot.betterwithplugins.customItems.CustomItemRegistry;
import me.MaY.spigot.betterwithplugins.customItems.list.ExplosivePickaxe;

public class BetterWithPluginsMain extends JavaPlugin {
	private CustomItemRegistry customItemRegistry;
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new TestListener(), this);
        this.customItemRegistry = new CustomItemRegistry();
        getLogger().info(this.getClass().getSimpleName() + ": Working...");
        
        PluginCommand command = this.getCommand("bwpgive");
		command.setExecutor(new BWPGiveCommand());
		command.setTabCompleter(new BWPGiveCommand());
		
        customItemRegistry.register(new NamespacedKey(this, "explosive_pickaxe"), new ExplosivePickaxe());
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
}
