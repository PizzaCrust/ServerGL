package org.obfuscatedmc.servergl.impl;

import com.google.common.base.Optional;

import org.bukkit.entity.Player;
import org.obfuscatedmc.servergl.PluginCore;
import org.obfuscatedmc.servergl.api.GLClient;
import org.obfuscatedmc.servergl.api.GLProvider;

public class GL11Provider implements GLProvider
{

    public Optional<GLClient> retrieveClient(Player player) {
        if (PluginCore.isCapableOfCustomRendering(player))
            return Optional.of((GLClient)new GL11Client(player));
        return Optional.absent();
    }

}
