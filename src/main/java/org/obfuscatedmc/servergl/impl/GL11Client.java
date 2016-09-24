package org.obfuscatedmc.servergl.impl;

import org.bukkit.entity.Player;
import org.obfuscatedmc.servergl.InvokeClientMethodPacket;
import org.obfuscatedmc.servergl.PluginCore;
import org.obfuscatedmc.servergl.ResolutionHandler;
import org.obfuscatedmc.servergl.api.GLClient;
import org.obfuscatedmc.servergl.api.GLCoordinates;
import org.obfuscatedmc.servergl.api.QuadCoordinates;
import org.obfuscatedmc.servergl.api.RGBColor;
import org.obfuscatedmc.servergl.api.Resolution;

public class GL11Client implements GLClient {
    private final Player player;

    protected GL11Client(Player player) {
        this.player = player;
    }

    private String[] strings(String... varargs) {
        return varargs;
    }

    private int[] integers(int... integers) {
        return integers;
    }

    private void invoke(String method, String[] strings, int[] array) {
        if (PluginCore.isCapableOfCustomRendering(player))
            player.sendPluginMessage(PluginCore.INSTANCE, "OrderChannel", InvokeClientMethodPacket
                    .create(method, strings, array).asBytes());
        throw new RuntimeException("Not capable of custom rendering.");
    }

    public void bindTexture(String resourceLocation) {
        invoke("glBindTexture", strings(resourceLocation), integers());
    }

    public void drawRectangle(QuadCoordinates coordinates, RGBColor color) {
        // TODO
    }

    public void drawRectangleNatively(QuadCoordinates coordinates, int nmsColor) {
        // TODO
    }

    public void drawString(String string, GLCoordinates coordinates) {
        invoke("fontRendererText", strings(string), integers(coordinates.x, coordinates.y));
    }

    public Resolution getClientResolution() {
        if (PluginCore.isCapableOfCustomRendering(this.player))
            return ResolutionHandler.getResolution(this.player).get();
        throw new RuntimeException("Not capable of custom rendering!");
    }

}
