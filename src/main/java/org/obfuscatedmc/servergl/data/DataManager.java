package org.obfuscatedmc.servergl.data;

import org.bukkit.entity.Player;

/**
 * Represents a data manager.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class DataManager
{

    public BinarySave of(Player player) {
        return new BinarySave(player.getUniqueId().toString());
    }

}
