package org.obfuscatedmc.servergl.api;

/**
 * Represents a GL client.
 *
 * @author PizzaCrust
 * @since 1.0-SNAPSHOT
 */
public interface GLClient
{

    /**
     * Binds a texture directly from the client's resource manager.
     * @param resourceLocation the resource location, equals to ResourceLocation in Minecraft.
     */
    void bindTexture(String resourceLocation);

    /**
     * Draws a rectangle using optimized custom method with vertex buffers, constraints, better
     * color selection.
     * @param coordinates the coordinates
     * @param color the color
     */
    void drawRectangle(QuadCoordinates coordinates,
                       RGBColor color);

    /**
     * Draws a rectangle natively using the default #drawRect method.
     * @param coordinates the coordinates
     * @param nmsColor the nms color
     */
    void drawRectangleNatively(QuadCoordinates coordinates, int nmsColor);

    /**
     * Uses native methods to draw a string with Minecraft's default FontRenderer.
     * @param string the message
     * @param coordinates the coordinates
     */
    void drawString(String string, GLCoordinates coordinates);

    /**
     * Retrieve the client resolution to make some objects use client resizing.
     * @return the resolution
     */
    Resolution getClientResolution();

}
