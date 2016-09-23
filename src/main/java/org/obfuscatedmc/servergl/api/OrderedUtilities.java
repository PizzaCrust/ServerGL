package org.obfuscatedmc.servergl.api;

/**
 * Some patterns and utilities to order your GL. (method invocation templates)
 *
 * @author PizzaCrust
 * @since 1.0-SNAPSHOT
 */
public class OrderedUtilities
{

    /**
     * Draws a rectangle with a texture.
     * @param glClient the gl client
     * @param quadCoordinates the quad coordinates
     * @param resourceLoc the resource location
     */
    public static void drawRectWithTexture(GLClient glClient,
                                           QuadCoordinates quadCoordinates,
                                           String resourceLoc) {
        glClient.bindTexture(resourceLoc);
        glClient.drawRectangle(quadCoordinates, RGBColor.of(255, 255, 255));
    }

}
