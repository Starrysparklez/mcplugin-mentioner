package ru.nellyd3v.mentioner;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Events implements Listener {
    Main plugin;
    FileConfiguration config;
    
    public Events(Main plugin) {
        this.config = plugin.getConfig();
    }
    
    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        Player eventPlayer = event.getPlayer(); 
        for(Player player : eventPlayer.getServer().getOnlinePlayers()) {
            if (event.getMessage().contains(player.getDisplayName())) {
                String message = config.getString("notify.content")
                    .replace("&", "§")
                    .replace("%author%", eventPlayer.getName());
                switch (config.getString("notify.type")) {
                    case "message":
                        player.sendMessage(message);
                        break;
                    case "title":
                        String subtitle = config.getString("notify.subtitle")
                            .replace("&", "§")
                            .replace("%author%", eventPlayer.getName());
                        player.sendTitle(message, subtitle,
                            config.getInt("notify.title.fadeIn"),
                            config.getInt("notify.title.stay"),
                            config.getInt("notify.title.fadeOut")
                        );
                        break;
                }
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100, 100);
            }
        }
    }
}