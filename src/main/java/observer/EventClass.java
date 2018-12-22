package observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs> {
    private int count = 0;
    private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args) {
        for (Consumer<TArgs> handler : handlers.values()) {
            handler.accept(args);
        }
    }

    public class Subscription implements AutoCloseable {

        private Event<TArgs> event;
        private int id;

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() {
            event.handlers.remove(id);
        }
    }
}

class PropChangedEventArgs {
    public Object source;
    public String propertyName;

    public PropChangedEventArgs(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }
}

class Personn {
    public Event<PropChangedEventArgs> propertyChanged = new Event<>();

    private int age;

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChanged.fire(new PropChangedEventArgs(this, "age"));
    }
}

public class EventClass {

    public static void main(String[] args) {
        Personn personn = new Personn();

        Event<PropChangedEventArgs>.Subscription sub =
                personn.propertyChanged.addHandler(
                        x -> System.out.println("Person's " + x.propertyName + " has changed"));

        personn.setAge(17);
        personn.setAge(18);
        sub.close();
        personn.setAge(20);
    }
}
