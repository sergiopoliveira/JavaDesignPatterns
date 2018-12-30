package behavioral.iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature {
    private int strength, agility, inteligence;

    public int max() {
        return Math.max(strength, Math.max(agility, inteligence));
    }

    public int sum() {
        return strength + agility + inteligence;
    }

    public double average() {
        return sum() / 3.0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getInteligence() {
        return inteligence;
    }

    public void setInteligence(int inteligence) {
        this.inteligence = inteligence;
    }
}

class Creature implements Iterable<Integer> {

    private int[] stats = new int[3];

    private final int STR = 0;

    public int getStrength() {
        return stats[STR];
    }

    public void setStrength(int value) {
        stats[STR] = value;
    }

    public int getAgility() {
        return stats[1];
    }

    public void setAgility(int value) {
        stats[1] = value;
    }

    public int sum() {
        return IntStream.of(stats).sum();
    }

    public int max() {
        return IntStream.of(stats).max().getAsInt();
    }

    public double average() {
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for (int x : stats) {
            action.accept(x);
        }
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();
    }
}

public class ArrayBackedProperties {

    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setStrength(12);
        creature.setAgility(10);
        System.out.println("AVG: " + creature.average());
        System.out.println("MAX: " + creature.max());
    }
}
