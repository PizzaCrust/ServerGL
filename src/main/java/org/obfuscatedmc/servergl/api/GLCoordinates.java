package org.obfuscatedmc.servergl.api;

/**
 * Represents coordinates in GL in a 2D plane.
 * (immutable)
 *
 * @author PizzaCrust
 * @since 1.0-SNAPSHOT
 */
public class GLCoordinates
{
    public final int x;
    public final int y;

    private GLCoordinates(int x,
                          int y) {
        this.x = x;
        this.y = y;
    }

    public static GLCoordinates of(int x,
                                   int y) {
        return new GLCoordinates(x, y);
    }

    public GLCoordinates add(int x,
                    int y) {
        return new GLCoordinates(this.x + x, this.y + y);
    }

    public GLCoordinates subtract(int x,
                                  int y) {
        return new GLCoordinates(this.x - x, this.y - y);
    }

}
