package org.schindus.minecord;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.schindus.minecord.discord.bot.DiscordBot;
import org.schindus.minecord.event.JoinEvent;
import org.schindus.minecord.event.MessageEvent;

public final class Minecord extends JavaPlugin {
    private final FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getLogger().info("-------------------------------");
        getLogger().info(" ");
        getLogger().info("Minecord wurde gestartet");
        getLogger().info(" ");
        DiscordBot.setUpBot(config.getString("token"), OnlineStatus.ONLINE, Activity.playing("Minecord"));
        initEvents();
        getLogger().info(" ");
        getLogger().info("-------------------------------");
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
    private void initEvents(){
        getServer().getPluginManager().registerEvents(new MessageEvent(),this);
        getServer().getPluginManager().registerEvents(new JoinEvent(),this);
    }
}
