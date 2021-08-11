package me.MaY.spigot.betterwithplugins;

import org.bukkit.plugin.java.JavaPlugin;

public class BetterWithPluginsMain extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println(this.getClass().getSimpleName() + ": Working...");
    }

    @Override
    public void onDisable() {
        
    }

	public BetterWithPluginsMain getINSTANCE() {
		return JavaPlugin.getPlugin(BetterWithPluginsMain.class);
	}
}
