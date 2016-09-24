package org.obfuscatedmc.servergl;

import com.google.common.base.Optional;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.obfuscatedmc.servergl.api.OpenGL;
import org.obfuscatedmc.servergl.api.Resolution;
import org.obfuscatedmc.servergl.data.BinarySave;
import org.obfuscatedmc.servergl.data.DataManager;
import org.obfuscatedmc.servergl.impl.GL11Provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static List<Player> CACHED_LOADED_MOD_PLAYERS = new ArrayList<Player>();

    public static File PARENT_DIRECTORY;
    public static File DIRECTORY;
    public static File CONFIG_FILE;

    public static DataManager DATA;
    public static Plugin INSTANCE;

    public static boolean MOD_REQ = false;

    public static Listener[] LISTENERS = new Listener[] {
            //new ResolutionHandler(),
            new ModHandler(),
            //new JoinModRegistar(),
    };

    @Override
    public void onEnable() {
        INSTANCE = this;
        DATA = new DataManager();
        PARENT_DIRECTORY = this.getDataFolder().getParentFile();
        DIRECTORY = new File(PARENT_DIRECTORY, "ServerGL-Data");
        CONFIG_FILE = new File(DIRECTORY, "configuration.yml");
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdir();
            this.createConfig();
        }
        if (!CONFIG_FILE.exists()) {
            this.createConfig();
        }
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "ClientInfo", new
                 ClientInfoHandler());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "OrderChannel");
        for (Listener listener : LISTENERS) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
        getLogger().info("Using GL11 API implementation!");
        OpenGL.setProvider(new GL11Provider());
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        if (configuration.getBoolean("requiredClient")) {
            MOD_REQ = true;
            getLogger().info("Configuration: Client is required to have client-side mod!");
        }
    }

    public static boolean isCapableOfCustomRendering(Player player) {
        return getResolution(player).isPresent();
    }

    public static Optional<Resolution> getResolution(Player player) {
        BinarySave binarySave = PluginCore.DATA.of(player);
        Optional<HashMap<String, Object>> optional = binarySave.get();
        if (optional.isPresent()) {
            HashMap<String, Object> objectHashMap = optional.get();
            if (objectHashMap.containsKey("resolution")) {
                return Optional.of((Resolution) objectHashMap.get("resolution"));
            } else {
                return Optional.absent();
            }
        } else {
            return Optional.absent();
        }
    }

    private void createConfig() {
        try {
            CONFIG_FILE.createNewFile();
            YamlConfiguration configuration = new YamlConfiguration();
            configuration.set("requiredClient", false);
            configuration.save(CONFIG_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
