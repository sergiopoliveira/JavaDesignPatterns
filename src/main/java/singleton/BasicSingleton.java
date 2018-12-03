package singleton;

// Basic Singleton
//1) Make Constructor private
//2) Make static final instance of Singleton
//3) Provide public getter for instance

import java.io.*;

public class BasicSingleton implements Serializable {

    private BasicSingleton() {
    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // Serialization will create instances behind the scenes...
    // need this to avoid that
    //The readResolve method is called when ObjectInputStream
    // has read an object from the stream and is preparing to return it
    // to the caller. ObjectInputStream checks whether the class of the object defines
    // the readResolve method. If the method is defined, the readResolve method is
    // called to allow the object in the stream to designate the object to be returned.
    // The object returned should be of a type that is compatible with all uses.
    // If it is not compatible, a ClassCastException will be thrown when the type mismatch is discovered.
    protected Object readResolve() {
        return INSTANCE;
    }
}

class Demo {

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception {

        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {

//        BasicSingleton singleton = BasicSingleton.getInstance();
//        singleton.setValue(123);
//        System.out.println(singleton.getValue());

        // 1. reflection
        // 2. serialization

        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(111);

        String fileName = "singleton.bin";
        saveToFile(singleton, fileName);

        singleton.setValue(222);

        BasicSingleton singleton2 = readFromFile(fileName);

        System.out.println(singleton == singleton2);

        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());


    }
}
