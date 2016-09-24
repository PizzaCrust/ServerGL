package org.obfuscatedmc.servergl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.obfuscatedmc.servergl.event.ClientInfoPacketReceiveEvent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class JoinModRegistar
        implements Listener
{

    @EventHandler
    public void onClientInfo(ClientInfoPacketReceiveEvent e) {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(e.message));
        try {
            String method = dataInputStream.readUTF();
            if (method.equals("verify")) {
                PluginCore.CACHED_LOADED_MOD_PLAYERS.add(e.player);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
