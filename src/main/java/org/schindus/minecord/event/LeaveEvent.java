package org.schindus.minecord.event;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.schindus.minecord.Minecord;
import org.schindus.minecord.discord.bot.DiscordBot;
import org.schindus.minecord.discord.webhook.WebhookManager;

import java.io.IOException;

public class LeaveEvent implements Listener {
    final FileConfiguration config = Minecord.getPlugin(Minecord.class).getConfig();
    private final boolean noQuitMessage = config.getBoolean("noQuitMessage");
    private final boolean useBot = config.getBoolean("useBot");
    private final String webhook = config.getString("webhook");
    private final String channelID = config.getString("channelID");

    @EventHandler
    public void quit(PlayerQuitEvent event) throws IOException {
        if (!noQuitMessage){
            final String user = event.getPlayer().getDisplayName();
            if (useBot){
                if (channelID != null && DiscordBot.getBot() != null){
                    DiscordBot.getBot().getTextChannelById(channelID).sendMessage(user+" hat den Server verlassen").queue();
                }
            } else if (webhook != null) {
                WebhookManager.setWebhook(user+" hat den Server verlassen");
            }
        }
    }

}
