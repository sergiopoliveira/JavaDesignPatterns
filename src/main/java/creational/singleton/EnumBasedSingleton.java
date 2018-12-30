package creational.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum EnumBasedSingletonE {
    INSTANCE;

    // constructors in enum are always private
    EnumBasedSingletonE() {
        value = 42;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class EnumBasedSingleton {

    static void saveToFile(EnumBasedSingletonE singleton, String filename) throws Exception {

        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingletonE readFromFile(String filename) throws Exception {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (EnumBasedSingletonE) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "myfile.bin";

        EnumBasedSingletonE singleton = EnumBasedSingletonE.INSTANCE;
        singleton.setValue(111);
        saveToFile(singleton, filename);

        EnumBasedSingletonE singleton2 = readFromFile(filename);
        System.out.println(singleton2.getValue());

        // you cannot serialize effectly because all the fields
        // dont get serialized and you cannot inherit from enum
    }
}
