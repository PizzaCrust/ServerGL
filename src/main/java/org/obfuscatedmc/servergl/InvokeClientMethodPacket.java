package org.obfuscatedmc.servergl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The packet that invokes a client method.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class InvokeClientMethodPacket
{

    private final String name;
    private final String[] strings;
    private final int[] ints;

    private InvokeClientMethodPacket(String name,
                                     String[] strings,
                                     int[] ints) {
        this.name = name;
        this.strings = strings;
        this.ints = ints;
    }

    public static InvokeClientMethodPacket create(String name,
                                                  String[] strings,
                                                  int[] ints) {
        return new InvokeClientMethodPacket(name, strings, ints);
    }

    public byte[] asBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        byte[] theBytes = new byte[0];
        try {
            dataOutputStream.writeByte(2);
            dataOutputStream.writeUTF(this.name);
            for (String string : strings) {
                dataOutputStream.writeUTF(string);
            }
            for (int integer : ints) {
                dataOutputStream.writeInt(integer);
            }
            theBytes = byteArrayOutputStream.toByteArray();
            dataOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return theBytes;
    }


}
