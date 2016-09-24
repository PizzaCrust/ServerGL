package org.obfuscatedmc.servergl.api;

import com.google.common.base.Preconditions;

/**
 * Represents OpenGL.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class OpenGL
{

    private static GLProvider PROVIDER = null;

    private OpenGL() {}

    public static GLProvider getProvider() {
        return PROVIDER;
    }

    public static void setProvider(GLProvider glProvider) {
        if (PROVIDER != null) {
            return;
        }
        Preconditions.checkNotNull(glProvider);
        PROVIDER = glProvider;
    }

}
