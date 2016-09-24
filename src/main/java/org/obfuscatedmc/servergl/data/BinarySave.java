package org.obfuscatedmc.servergl.data;

import com.google.common.base.Optional;

import org.obfuscatedmc.servergl.PluginCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Represents a binary save.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
public class BinarySave
{

    private final File file;

    public BinarySave(String name) {
        file = new File(PluginCore.DIRECTORY, name);
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new HashMap<String, Object>());
                oos.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<HashMap<String, Object>> get() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<String, Object> object = (HashMap<String, Object>) ois.readObject();
            ois.close();
            fis.close();
            return Optional.of(object);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    public void set(HashMap<String, Object> object) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
