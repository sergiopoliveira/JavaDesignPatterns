package prototype;

class AddressCC {
    public String streetAddress, city, country;

    public AddressCC(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    // copy constructor
    public AddressCC(AddressCC other) {

        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "prototype.AddressCC{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class EmployeeCC {
    public String name;
    public AddressCC address;

    public EmployeeCC(String name, AddressCC address) {
        this.name = name;
        this.address = address;
    }

    public EmployeeCC(EmployeeCC other) {
        name = other.name;
        address = new AddressCC(other.address);
    }

    @Override
    public String toString() {
        return "prototype.EmployeeCC{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

public class CopyConstructors {

    public static void main(String[] args) {
        EmployeeCC john = new EmployeeCC("John",
                new AddressCC("123 London Road", "London", "UK"));

        // Employee chris = john
        EmployeeCC chris = new EmployeeCC(john);

        chris.name = "Chris";
        System.out.println(john);
        System.out.println(chris);
    }
}
