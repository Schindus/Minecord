package org.schindus.ctdc;

import org.bukkit.plugin.java.JavaPlugin;

public final class ChatToDiscord extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("-------------------------------");
        getLogger().info(" ");
        getLogger().info("Minecord wurde gestartet");
        getLogger().info(" ");
        getLogger().info("-------------------------------");
        getServer().getPluginManager().registerEvents(new MessageEvent(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("------------------------------");
        getLogger().info(" ");
        getLogger().info("Minecord wurde gestoppt");
        getLogger().info(" ");
        getLogger().info("------------------------------");
    }
}
