package Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    Aceasta clasa este un singleton utilizat pentru generarea de date pentru pacienti si doctori.
 */


public class DataGeneratorSingleton {

    private final int MIN = 1000;
    private final int MAX = 9999;
    private static DataGeneratorSingleton singleInstance = null;
    //U4DID stand for Unique 4 Digit Identification
    public static List<Integer> U4DIDlist = new ArrayList<Integer>();


    private DataGeneratorSingleton(){
    }

    public static DataGeneratorSingleton getInstance(){
        if (singleInstance == null){
            singleInstance = new DataGeneratorSingleton();
        }
        return singleInstance;
    }

    //Generates a unique 4 digit ID as int, stores it in the U4DIDlist and returns in as string;
    public String generateNext(){

        Random r = new Random();
        int x = r.nextInt((MAX - MIN)+1) + MIN; //returns a random number in in range[1000,9999]
        while(U4DIDlist.contains(x)){ //checks if already exists
            x = r.nextInt((MAX - MIN)+1) + MIN;; //if it does, generates another random
        }
        U4DIDlist.add(x); // finally unique so it adds it to the list

        return String.valueOf(x);
    }

    public void clearU4DIDlist(){
        U4DIDlist.clear();
    }
    public void printU4DIDlist(){

        System.out.println("U4DIDlist contains:");

        for (Integer element : U4DIDlist) {
            System.out.println(element.intValue());
        }
    }

    public String generateRandomString(int stringLength){

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return  generatedString;
    }
    public String generateRandomAge(int min,int max){

        Random random = new Random();
        Integer generatedAge = random.nextInt((max - min)+1)+min;

        return generatedAge.toString();
    }

    public String generateReason(){

        Random r = new Random();
        int reasonIndex = r.nextInt(3);

        if(reasonIndex == 0){
            return "Consultation";
        }
        if(reasonIndex == 1){
            return "Treatment";
        }
        if(reasonIndex == 2){
            return "Prescriptions";
        }
        return "Neasociat[Error]";
    }
}
