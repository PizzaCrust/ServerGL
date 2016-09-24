package org.obfuscatedmc.servergl;

import com.google.common.base.Optional;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.obfuscatedmc.servergl.api.Resolution;
import org.obfuscatedmc.servergl.data.BinarySave;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.HashMap;

public class ClientInfoHandler implements PluginMessageListener
{
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        PluginCore.INSTANCE.getLogger().info("Packet Handler: Received Mod Packet from " +
                player.getName() + ", length: " + bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
           // dataInputStream.readInt();
            dataInputStream.readByte();
            String method = dataInputStream.readUTF();
            if (method.equals("verify"))
                verify(player);
            if (method.equals("resolution"))
                resolveResolution(dataInputStream, player);
            dataInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void verify(Player player) {
        PluginCore.INSTANCE.getLogger().info("Player is verified as mod user: " + player.getName());
        PluginCore.CACHED_LOADED_MOD_PLAYERS.add(player);
    }

    private void resolveResolution(DataInputStream dataInputStream, Player player) {
        try {
            int width = dataInputStream.readInt();
            int height = dataInputStream.readInt();
            PluginCore.INSTANCE.getLogger().info("Player is verified having a resolution of " +
                    width + "px x " + height + "px (" + player.getName() + ")");
            Resolution resolution = new Resolution(width, height);
            BinarySave playerSave = PluginCore.DATA.of(player);
            Optional<HashMap<String, Object>> playerData = playerSave.get();
            if (playerData.isPresent()) {
                HashMap<String, Object> hashMap = playerData.get();
                hashMap.put("resolution", resolution);
                playerSave.set(hashMap);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
