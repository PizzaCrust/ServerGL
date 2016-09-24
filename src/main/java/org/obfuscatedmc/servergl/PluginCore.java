package org.obfuscatedmc.servergl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.obfuscatedmc.servergl.data.DataManager;
import org.obfuscatedmc.servergl.event.ClientInfoPacketReceiveEvent;

import java.io.File;
import java.util.logging.Logger;

/**
 * Represents the core of the plugin.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class PluginCore
    extends JavaPlugin
{

    private final Logger logger = this.getLogger();

    public static File PARENT_DIRECTORY;
    public static File DIRECTORY;

    public static DataManager DATA;

    public static Listener[] LISTENERS = new Listener[] {
            new ResolutionHandler()
    };

    @Override
    public void onEnable() {
        DATA = new DataManager();
        PARENT_DIRECTORY = this.getDataFolder().getParentFile();
        DIRECTORY = new File(PARENT_DIRECTORY, "ServerGL-Data");
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdir();
        }
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "ClientInfo", new
                ClientInfoPacketReceiveEvent.Listener());
        for (Listener listener : LISTENERS) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public static boolean isCapableOfCustomRendering(Player player) {
        return ResolutionHandler.getResolution(player).isPresent();
    }

}