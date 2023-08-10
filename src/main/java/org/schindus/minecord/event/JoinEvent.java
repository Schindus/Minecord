package org.schindus.minecord.event;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.schindus.minecord.Minecord;
import org.schindus.minecord.discord.bot.DiscordBot;
import org.schindus.minecord.discord.webhook.WebhookManager;

import java.io.IOException;

@SuppressWarnings("DataFlowIssue")
public class JoinEvent implements Listener {
    final FileConfiguration config = Minecord.getPlugin(Minecord.class).getConfig();
    private final boolean noJoinMessages = config.getBoolean("noJoinMessages");
    private final boolean useBot = config.getBoolean("useBot");
    private final String webhook = config.getString("webhook");
    private final String channelID = config.getString("channelID");
    @EventHandler
    public void join(PlayerJoinEvent event) throws IOException {
        if (!noJoinMessages){
            final String user = event.getPlayer().getDisplayName();
            if (useBot){
                if (channelID != null && DiscordBot.getBot() != null){
                    DiscordBot.getBot().getTextChannelById(channelID).sendMessage(user+" hat den Server betreten").queue();
                }
            } else if (webhook !=null) {
                WebhookManager.setWebhook(user+" hat den Server betreten");
            }
        }
    }
}
