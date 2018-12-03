package singleton;

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("Initializating a lazy singleton");
    }

//    public static synchronized LazySingleton getInstance() {
//        if(instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }
//}

    // double-checked locking
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}

//  This is called the initialization-on-demand holder idiom.
//  In Java, encapsulating classes do not automatically initialize inner classes.
//  So the inner class only gets initialized by getInstance().
//  Then again, class initialization is guaranteed to be sequential in Java,
//  so the JVM implicitly renders it thread-safe.
class InnerStaticSingleton {
    private InnerStaticSingleton() {
    }

    private static class Impl {

        // Inner classes can access private members of outer-classes
        private static final InnerStaticSingleton INSTANCE
                = new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }

}

public class LazinessAndThreadSafety {


}
