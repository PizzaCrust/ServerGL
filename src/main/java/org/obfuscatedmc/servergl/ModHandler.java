package org.obfuscatedmc.servergl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.obfuscatedmc.servergl.api.GLCoordinates;
import org.obfuscatedmc.servergl.api.OpenGL;

public class ModHandler
        implements Listener
{

    @EventHandler
    public void onJoinEvent(final PlayerJoinEvent e) {
        if (PluginCore.MOD_REQ) {
            e.getPlayer().sendMessage(ChatColor.GOLD + "This server requires ServerGL-Client mod.");
            e.getPlayer().sendMessage(ChatColor.RED + "Waiting for client to send mod verification...");
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(PluginCore.INSTANCE, new Runnable() {
                public void run() {
                    if (PluginCore.CACHED_LOADED_MOD_PLAYERS.contains(e.getPlayer())) {
                        e.getPlayer().sendMessage(ChatColor.GREEN + "Verified!");
                        OpenGL.getProvider().retrieveClient(e.getPlayer()).get().drawString
                                ("ServerGL", GLCoordinates.of(0, 0));
                    } else {
                        e.getPlayer().kickPlayer(ChatColor.RED + "Failed to verify" +
                                "client.");
                    }
                }
            }, 40L);
        }
    }

}
