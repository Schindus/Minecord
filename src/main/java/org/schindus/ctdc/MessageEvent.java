package org.schindus.ctdc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;

public class MessageEvent implements Listener {
    final String webhookUrl = "https://discord.com/api/webhooks/1138599434394013807/q0lkeDqyTpOQ7v-tOxZb7CImKElTG7fqxfbEMPhDxsG_qxxxApwzfN_-qWZSwXfV7DNJ";

    @EventHandler
    public void chat(AsyncPlayerChatEvent event){
        final String user = event.getPlayer().getDisplayName();
        final String message = event.getMessage();
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
        webhook.setContent(user+": "+message);
        try {
            webhook.execute();
        } catch (IOException e) {
            System.out.println("Fehler");
        }
    }
}
