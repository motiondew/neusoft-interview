package BusinessLogic;

import Entities.Doctor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.print.Doc;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoctorGenerator implements Serializable {

    public static final String PATH_TO_JSON = "doctors.json";

    private List<Doctor> doctorList;

    public DoctorGenerator() {
        this.doctorList = new ArrayList<Doctor>();
    }

    public void generate(int numberOfDoctors){

        if(numberOfDoctors >= 8) {
            for (int i = 0; i < numberOfDoctors; i++) {

                Doctor newDoctor = new Doctor();
                doctorList.add(newDoctor);
            }
        }else{
            System.out.println("BusinessLogic.DoctorGenerator::generate -> constraint [ numberOfDoctors must le at least 8]");
        }
    }

    public void printDoctorList(){
        for (Doctor element :doctorList) {
            System.out.println("Firstname: "+element.getFirstName()+
                    "\nLastname: "+ element.getLastName()+
                    "\nAge: "+element.getAge()+
                    "\nID: "+element.getIDNumber()+"\n");
        }
    }

    public void saveToDisk(){
        String json = new Gson().toJson(doctorList);
        try (PrintStream out = new PrintStream(new FileOutputStream("doctors.json"))) {
            out.print(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> readFromDisk(){

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

        Type listType = new TypeToken<ArrayList<Doctor>>(){}.getType();
        List<Doctor> docList = new Gson().fromJson(jsonString, listType);

        this.doctorList = docList;
        return docList;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }



}
