package Entities;

import Utilities.DataGeneratorSingleton;

public class Patient {

    private String firstName;
    private String lastName;
    private String age;
    private String reason;

    private static DataGeneratorSingleton randomGeneratorInstance = DataGeneratorSingleton.getInstance();

    public Patient() {
        this.firstName = randomGeneratorInstance.generateRandomString(5);
        this.lastName = randomGeneratorInstance.generateRandomString(4);
        this.age = randomGeneratorInstance.generateRandomAge(0,85);
        this.reason = randomGeneratorInstance.generateReason();
    }
    public Patient(int maxAge) {
        this.firstName = randomGeneratorInstance.generateRandomString(5);
        this.lastName = randomGeneratorInstance.generateRandomString(4);
        this.age = randomGeneratorInstance.generateRandomAge(0,maxAge);
        this.reason = randomGeneratorInstance.generateReason();
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

    public String getReason() {
        return reason;
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

    public void setReason(String reason) {
        this.reason = reason;
    }


}
