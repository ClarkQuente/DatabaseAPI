package me.clarkquente;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static ConnectionManager connectionManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        connectionManager = new ConnectionManager();

    }

    @Override
    public void onDisable() {

    }

    public static void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage("§a[DatabaseAPI] §e- " + message);
    }

    public static Main getInstance() {
        return instance;
    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }
}
