package org.obfuscatedmc.servergl.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Represents the event when the client info packet arrives.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class ClientInfoPacketReceiveEvent
    extends Event
{
    private static final HandlerList listeningClasses = new HandlerList();

    public final Player player;
    public final byte[] message;

    public ClientInfoPacketReceiveEvent(Player players,
                                        byte[] message) {
        this.player = players;
        this.message = message;
    }

    public static HandlerList getHandlerList() {
        return listeningClasses;
    }

    public HandlerList getHandlers() {
        return listeningClasses;
    }

    public static class Listener implements PluginMessageListener {

        public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
            Bukkit.getPluginManager().callEvent(new ClientInfoPacketReceiveEvent(player, bytes));
        }

    }

}
