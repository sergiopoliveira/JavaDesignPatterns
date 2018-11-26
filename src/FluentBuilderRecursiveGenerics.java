
class PersonFBRG {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {
    protected PersonFBRG person = new PersonFBRG();

    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    public PersonFBRG build() {
        return person;
    }

    protected SELF self() {
        return (SELF) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    @Override
    protected EmployeeBuilder self() {
        return this;
    }

    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return this;
    }
}

class FluentBuilderRecursiveGenerics {
    public static void main(String[] args) {
        EmployeeBuilder pb = new EmployeeBuilder();
        PersonFBRG sergio = pb
                .withName("Sergio")
                .worksAt("Developer")
                .build();
        System.out.println(sergio);
    }
}
