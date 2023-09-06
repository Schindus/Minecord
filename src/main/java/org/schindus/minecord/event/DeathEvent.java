package org.schindus.minecord.event;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.schindus.minecord.Minecord;
import org.schindus.minecord.discord.bot.DiscordBot;
import org.schindus.minecord.discord.webhook.DiscordWebhook;
import org.schindus.minecord.discord.webhook.WebhookManager;

import java.awt.*;
import java.io.IOException;

@SuppressWarnings("DataFlowIssue")
public class DeathEvent implements Listener {
    final FileConfiguration config = Minecord.getPlugin(Minecord.class).getConfig();
    private final boolean noDeathMessage = config.getBoolean("noDeathMessage");
    private final boolean useBot = config.getBoolean("useBot");
    private final String webhook = config.getString("webhook");
    private final String channelID = config.getString("channelID");

    @EventHandler
    public void death(PlayerDeathEvent event) throws IOException {
        if (!noDeathMessage){
            String location =
                      event.getEntity().getLocation().getBlockX() + " | "
                    + event.getEntity().getLocation().getBlockY() + " | "
                    + event.getEntity().getLocation().getBlockZ();
            final String url = "https://crafatar.com/avatars/"+event.getEntity().getUniqueId()+"?size=100";

            if (useBot) {
                if (channelID != null && DiscordBot.getBot() != null) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setThumbnail(url);
                    builder.setTitle(event.getDeathMessage(),"");
                    builder.addField("Position:",location,false)
                            .addField("KeepInventory:", String.valueOf(event.getKeepInventory()),false);
                    builder.setColor(Color.RED);
                    DiscordBot.getBot().getTextChannelById(channelID).sendMessageEmbeds(builder.build()).queue();
                }
            } else if (webhook != null) {
                DiscordWebhook.EmbedObject embedObject = new DiscordWebhook.EmbedObject();
                embedObject.setThumbnail(url);
                embedObject.setTitle(event.getDeathMessage());
                embedObject.addField("Position: ",location,false);
                embedObject.addField("KeepInventory: ", String.valueOf(event.getKeepInventory()),true);
                embedObject.setColor(Color.RED);
                WebhookManager.setEmbedWebhook(embedObject);
            }
        }
    }
}
