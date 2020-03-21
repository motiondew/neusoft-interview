import BusinessLogic.DoctorGenerator;
import BusinessLogic.PatientGenerator;
import BusinessLogic.Scheduler;
import Entities.Doctor;
import Entities.Patient;

import java.util.List;

public class App {

    public static void main(String[] args) {

        //Point a)
        DoctorGenerator  doctorGenerator = new DoctorGenerator();
        doctorGenerator.generate(8);

        //Point b)
        PatientGenerator patientGenerator = new PatientGenerator();
        patientGenerator.generate();

        //Point c)
        System.out.println("-----------Doctor list:-----------\n");
        doctorGenerator.printDoctorList();
        System.out.println("-----------Patient list:-----------\n");
        patientGenerator.printPatientList();
        patientGenerator.printHistogram();

        //Point d)
        patientGenerator.saveToDisk();
        //Point e)
        doctorGenerator.saveToDisk();

        //Point f)
        //First simulation [Fast mode]
        List<Patient> patientList = patientGenerator.readFromDisk();
        List<Doctor> doctorList = doctorGenerator.readFromDisk();
        System.out.println("[FAST-MODE] Simulation begins:");
        System.out.println("Doctor working time: 420 seconds, Consultation/Prescription/Treatment time: 3/2/4 seconds \n[!]Please wait...\n");
        Scheduler nS = new Scheduler(doctorList,patientList,60);

        //Second simulation [Real-time mode]
        patientList = patientGenerator.readFromDisk();
        doctorList = doctorGenerator.readFromDisk();
        System.out.println("[REAL-TIME-MODE] Simulation begins:");
        System.out.println("Doctor working time: 7 hours, Consultation/Prescription/Treatment time: 30/20/40 minutes \n[!!!]Please don't wait :)... takes a lot of time(but it works)\n");
        Scheduler nS2 = new Scheduler(doctorList,patientList,1);
    }
}
