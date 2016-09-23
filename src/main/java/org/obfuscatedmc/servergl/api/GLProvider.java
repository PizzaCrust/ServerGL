package org.obfuscatedmc.servergl.api;

import com.google.common.base.Optional;

import org.bukkit.entity.Player;

/**
 * Network-to-object interfacer for the API.
 *
 * @author PizzaCrust
 * @since 1.0-SNAPSHOT
 */
public interface GLProvider
{

    /**
     * Retrieves the GL client.
     * @param player the player
     * @return the GL client
     */
    Optional<GLClient> retrieveClient(Player player);

}
