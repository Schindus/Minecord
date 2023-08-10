package org.schindus.minecord.event;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.schindus.minecord.Minecord;
import org.schindus.minecord.discord.bot.DiscordBot;
import org.schindus.minecord.discord.webhook.WebhookManager;

import java.io.IOException;

@SuppressWarnings("DataFlowIssue")
public class MessageEvent implements Listener {
    final FileConfiguration config = Minecord.getPlugin(Minecord.class).getConfig();
    private final boolean noChatMessages = config.getBoolean("noChatMessages");
    private final boolean useBot = config.getBoolean("useBot");
    private final String webhook = config.getString("webhook");
    private final String channelID = config.getString("channelID");

    @EventHandler
    public void chat(AsyncPlayerChatEvent event) throws IOException {
        if (!noChatMessages) {
            final String user = event.getPlayer().getDisplayName();
            final String message = event.getMessage();
            if (useBot){
                if (channelID != null && DiscordBot.getBot() != null){
                    DiscordBot.getBot().getTextChannelById(channelID).sendMessage(user+": "+message).queue();
                }
            } else if (webhook != null) {
                WebhookManager.setWebhook(user+": "+message);
            }
        }
    }
}
