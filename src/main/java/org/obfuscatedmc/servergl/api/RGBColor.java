package org.obfuscatedmc.servergl.api;

/**
 * Represents a RGB color set.
 * (immutable)
 *
 * @author PizzaCrust
 * @since 1.0-SNAPSHOT
 */
public class RGBColor
{
    public final int red;
    public final int blue;
    public final int green;

    private RGBColor(int red,
                    int blue,
                    int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public static RGBColor of(int red,
                              int blue,
                              int green) {
        return new RGBColor(red, blue, green);
    }

    public RGBColor add(int red,
                        int blue,
                        int green) {
        return new RGBColor(this.red + red, this.blue + blue, this.green + green);
    }

    public RGBColor subtract(int red,
                             int blue,
                             int green) {
        return new RGBColor(this.red - red, this.blue - blue, this.green - green);
    }

}
