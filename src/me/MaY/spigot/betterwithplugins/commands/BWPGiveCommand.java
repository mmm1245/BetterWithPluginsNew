package me.MaY.spigot.betterwithplugins.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.MaY.spigot.betterwithplugins.BetterWithPluginsMain;
import me.MaY.spigot.betterwithplugins.customItems.CustomItem;

public class BWPGiveCommand implements CommandExecutor, TabCompleter{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.isOp()) {
            	player.sendMessage("Invalid permissions");
        		return true;
            }
            if(args.length == 1) {
            	CustomItem item = BetterWithPluginsMain.getINSTANCE().getCustomItemRegistry().get(NamespacedKey.fromString(args[0]));
            	if(item == null) {
            		player.sendMessage("Invalid item");
            		return true;
            	}
            	player.getInventory().addItem(item.create());
            	return true;
            }
            
        }
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] args) {
		if(args.length == 1) {
			return BetterWithPluginsMain.getINSTANCE().getCustomItemRegistry().asList();
		}
		return null;
	}

}
