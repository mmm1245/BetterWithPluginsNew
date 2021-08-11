package me.MaY.spigot.betterwithplugins.customItems;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.NamespacedKey;

public class CustomItemRegistry {
	private HashMap<NamespacedKey, CustomItem> registry;
	public CustomItemRegistry() {
		this.registry = new HashMap<>();
	}
	public void register(NamespacedKey key, CustomItem item) {
        this.registry.put(key, item);
	}
	public CustomItem get(NamespacedKey key) {
		return registry.get(key);
	}
	public ArrayList<String> asList(){
		ArrayList<String> toReturn = new ArrayList<String>();
		for(NamespacedKey key : registry.keySet()) {
			toReturn.add(key.toString());
		}
		return toReturn;
	}
}
