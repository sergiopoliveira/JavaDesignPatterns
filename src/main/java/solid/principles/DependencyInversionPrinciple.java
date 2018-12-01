package solid.principles;
// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

// Abstraction can mean abstract class or interface

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Triplet<T, U, V> {

    private final T first;
    private final U second;
    private final V third;

    public Triplet(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }
}

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;
    // dob


    public Person(String name) {
        this.name = name;
    }
}

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

// low-level module because it is related to data storage
class Relationships implements RelationshipBrowser {
    private List<Triplet<Person, Relationship, Person>> relations
            = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getFirst().name, name)
                        && x.getSecond() == Relationship.PARENT)
                .map(Triplet::getThird)
                .collect(Collectors.toList());
    }
}

// High-level module because it allows us to perform some operation
// on those low-level constructs
class Research {
//    public solid.principles.Research(solid.principles.Relationships relationships) {
//
//        List<solid.principles.Triplet<solid.principles.Person, solid.principles.Relationship, solid.principles.Person>> relations
//                = relationships.getRelations();
//
//        relations.stream()
//                .filter(x -> x.getFirst().name.equals("John")
//                        && x.getSecond() == solid.principles.Relationship.PARENT)
//                .forEach(ch -> System.out.println("John has a child called " +
//                        ch.getThird().name
//                ));
//    }

    public Research(RelationshipBrowser browser) {
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child : children) {
            System.out.println("John has a child called: " + child.name);
        }

    }
}

    class DependencyInversionPrinciple {
        public static void main(String[] args) {
            Person parent = new Person("John");
            Person child1 = new Person("Chris");
            Person child2 = new Person("Matt");

            Relationships relationships = new Relationships();
            relationships.addParentAndChild(parent, child1);
            relationships.addParentAndChild(parent, child2);

            new Research(relationships);

        }

    }
