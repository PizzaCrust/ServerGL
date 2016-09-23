package org.obfuscatedmc.servergl.api;

/**
 * Coordinates defined by quads from constraints.
 * (basically constraints and immutable)
 * 
 * @author PizzaCrust
 * @since 1.0-SNAPSHOT
 */
public class QuadCoordinates
{
    public final int left;
    public final int right;
    public final int top;
    public final int bottom;
    
    private QuadCoordinates(int left,
                           int right, 
                           int top,
                           int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public static QuadCoordinates of(int left,
                                     int right,
                                     int top,
                                     int bottom) {
        return new QuadCoordinates(left, right, top, bottom);
    }


    public QuadCoordinates add(int left,
                    int right,
                    int top,
                    int bottom) {
        return new QuadCoordinates(this.left + left, this.right + right, this.top + top,
                this.bottom + bottom);
    }

    public QuadCoordinates subtract(int left,
                                    int right,
                                    int top,
                                    int bottom) {
        return new QuadCoordinates(this.left - left, this.right - right, this.top - top, this
                .bottom - bottom);
    }
    
}
