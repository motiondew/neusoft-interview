package BusinessLogic;

import Entities.Doctor;
import Entities.Patient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PatientGenerator implements Serializable {

    public static final String PATH_TO_JSON = "patients.json";

    private List<Patient> patientList;
    private int[] histogram = new int[4];

    public PatientGenerator() {
        patientList = new ArrayList<Patient>();
    }

    public void generate(){

        for (int i = 0; i < 100; i++) {

            if(i<15){
                generatePatientWithAge(1);
            }
            else if(i>=15 && i<=40){
                generatePatientWithAge(7);
            }
            else if(i>=40 && i<=65){
                generatePatientWithAge(18);
            }
            else if(i>65){
                generatePatientWithAge(85);
            }
        }
    }

    public void printPatientList() {
        for (Patient p: patientList) {
            System.out.println("Firstame: "+p.getFirstName()+
                                "\nLastname: "+p.getLastName()+
                                "\nAge: "+p.getAge()+
                                "\nReason: "+p.getReason()+"\n");
        }
    }
    public void printHistogram(){
        System.out.println("Children(0-1): "+histogram[0] +" patients"+
                            "\nPupil(1-7): "+histogram[1] +" patients"+
                            "\nStudent(7-18): "+histogram[2] +" patients"+
                            "\nAdult(>18): "+histogram[3] +" patients\n");


    }
    public void countHistogram(int patientAge){

        if(patientAge < 1){
            histogram[0]++;
        }else if(patientAge >=1 && patientAge <=7 ){
            histogram[1]++;
        }else if(patientAge >=8 && patientAge <=18 ){
            histogram[2]++;
        }else if(patientAge > 18 ) {
            histogram[3]++;
        }
    }
    public void saveToDisk(){
        String json = new Gson().toJson(patientList);

        try (PrintStream out = new PrintStream(new FileOutputStream("patients.json"))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<Patient> readFromDisk(){

        String jsonString="";

        try {
            InputStream jsonStream = new FileInputStream(PATH_TO_JSON);
            BufferedReader buffer = new BufferedReader( new InputStreamReader(jsonStream));

            String line= buffer.readLine();
            StringBuilder sb  =  new StringBuilder();

            sb.append(line);

            jsonString =  sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type listType = new TypeToken<ArrayList<Patient>>(){}.getType();
        List<Patient> patList = new Gson().fromJson(jsonString, listType);

        this.patientList = patList;
        return patList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    private void generatePatientWithAge(int maxAge){
        Patient newPatient = new Patient(maxAge);
        patientList.add(newPatient);
        int patientAge = Integer.parseInt(newPatient.getAge());
        countHistogram(patientAge);
    }
}
