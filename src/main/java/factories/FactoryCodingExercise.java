package factories;

class PersonE {
    public int id;
    public String name;

    public PersonE(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory {
    static int id;

    public PersonE createPerson(String name) {
        return new PersonE(id++, name);
    }
}

class FactoryCodingExercise {

    public static void main(String[] args) {

        PersonFactory pf = new PersonFactory();
        PersonE sergio = pf.createPerson("Sergio");
        System.out.println(sergio.id + sergio.name);
    }
}
