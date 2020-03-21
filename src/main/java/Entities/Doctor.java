package Entities;

import Utilities.DataGeneratorSingleton;

public class Doctor {

    //name, last name, age and identification number.
    private String firstName;
    private String lastName;
    private String age;
    private String IDNumber;

    private static DataGeneratorSingleton randomGeneratorInstance = DataGeneratorSingleton.getInstance();

    public Doctor() {
        this.firstName = randomGeneratorInstance.generateRandomString(3); //generate here when created
        this.lastName = randomGeneratorInstance.generateRandomString(2);
        this.age = randomGeneratorInstance.generateRandomAge(30,65);
        this.IDNumber = randomGeneratorInstance.generateNext();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }


}



