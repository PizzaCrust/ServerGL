package org.obfuscatedmc.servergl;

import com.google.common.base.Optional;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.obfuscatedmc.servergl.api.Resolution;
import org.obfuscatedmc.servergl.data.BinarySave;
import org.obfuscatedmc.servergl.event.ClientInfoPacketReceiveEvent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.HashMap;

/**
 * Handles {@link Resolution}.
 *
 * @author PizzaCrust
 * @since 1.0-SNAPSHOT
 */
public class ResolutionHandler
    implements Listener
{

    @EventHandler
    public void onReceivePacket(ClientInfoPacketReceiveEvent e) {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(e.message));
        try {
            String method = dataInputStream.readUTF();
            if (method.equals("resolution")) {
                int width = dataInputStream.readInt();
                int height = dataInputStream.readInt();
                Resolution resolution = new Resolution(width, height);
                BinarySave playerSave = PluginCore.DATA.of(e.player);
                Optional<HashMap<String, Object>> playerData = playerSave.get();
                if (playerData.isPresent()) {
                    HashMap<String, Object> hashMap = playerData.get();
                    hashMap.put("resolution", resolution);
                    playerSave.set(hashMap);
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

}
