package creational.prototype;

import java.util.Arrays;

class Address implements Cloneable {
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "creational.prototype.Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    // deep copy
    @Override
    public Object clone() throws CloneNotSupportedException {

        return new Address(streetName, houseNumber);
        //return super.clone();
    }
}

class PersonP implements Cloneable {
    public String[] names;
    public Address address;

    public PersonP(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "creational.prototype.PersonP{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    // deep copy
    @Override
    public Object clone() throws CloneNotSupportedException {

        return new PersonP(names.clone()
                , (Address) address.clone());
        //return super.clone();
    }
}

public class DontUseCloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        PersonP john = new PersonP(new String[]{"John", "Smith"}, new
                Address("London Road", 123));

        // shallow copy (same references)
        //creational.prototype.PersonP jane = john;

        PersonP jane = (PersonP) john.clone();
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;

        System.out.println(john);
        System.out.println(jane);


    }
}
