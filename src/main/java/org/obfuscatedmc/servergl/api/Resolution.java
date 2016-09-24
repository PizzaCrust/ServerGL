package org.obfuscatedmc.servergl.api;

import java.io.Serializable;

/**
 * Represents the resolution.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class Resolution
    implements Serializable
{

    public final int width;
    public final int height;

    public Resolution(int width,
                      int height) {
        this.width = width;
        this.height = height;
    }


}
