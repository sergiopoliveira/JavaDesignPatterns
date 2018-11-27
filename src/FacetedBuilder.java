//use multiple builders in tandem


class PersonFB {
    // address
    public String streetAddress, postcode, city;

    // employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "PersonFB{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

// builder facade
class PersonFBBuilder {
    protected PersonFB person = new PersonFB();


    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }

    public PersonFB build() {
        return person;
    }
}

class PersonAddressBuilder extends PersonFBBuilder {
    public PersonAddressBuilder(PersonFB person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress) {
        person.streetAddress = streetAddress;
        return this;
    }
}

class PersonJobBuilder extends PersonFBBuilder {
    public PersonJobBuilder(PersonFB person) {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName) {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position) {
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome) {
        person.annualIncome = annualIncome;
        return this;
    }
}

class FacetedBuilder {

    public static void main(String[] args) {
        PersonFBBuilder pb = new PersonFBBuilder();
        PersonFB person = pb
                .lives()
                .at("123 London Road")
                .works()
                .at("Fabrikam")
                .asA("Engineer")
                .earning(10)
                .build();

        System.out.println(person);
    }
}
