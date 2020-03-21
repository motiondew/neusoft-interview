package BusinessLogic;

import Entities.Doctor;
import Entities.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scheduler {

    private static final  int NUMBER_OF_SERVERS = 4;

    private List<Server> serverList;
    private List<Doctor> doctorList;
    private List<Patient> patientList;
    private  List<Doctor> workingDoctorList;
    private  List<Patient> assignedPatientList;
    private int simulationSpeed = 1;

    public Scheduler(List<Doctor> doctorList, List<Patient> patientList){
        serverList = new ArrayList<Server>();
        this.doctorList = doctorList;
        this.patientList = patientList;
        this.workingDoctorList = new ArrayList<Doctor>();
        this.assignedPatientList = new ArrayList<Patient>();
        startWork(false);
    }
    public Scheduler(List<Doctor> doctorList, List<Patient> patientList,int simulationSpeed){
        serverList = new ArrayList<Server>();
        this.doctorList = doctorList;
        this.patientList = patientList;
        this.workingDoctorList = new ArrayList<Doctor>();
        this.assignedPatientList = new ArrayList<Patient>();
        this.simulationSpeed = simulationSpeed;
        startWork(true);
    }

    private void startWork(boolean speedFlag){
        for(int i=0;i<NUMBER_OF_SERVERS;i++){

            Random r = new Random();
            int availableDoctors = doctorList.size()-1;
            int doctorIndex = r.nextInt(availableDoctors);
            Doctor selectedDoctor = doctorList.get(doctorIndex);
            workingDoctorList.add(selectedDoctor);
            doctorList.remove(selectedDoctor);

            Server newServer;

            if(speedFlag == true) {
                 newServer = new Server(selectedDoctor,simulationSpeed);
            }else{
                 newServer = new Server(selectedDoctor);
            }
            serverList.add(newServer);
        }
        assignPatients();
        startThreads();
    }

    private void assignPatients(){
        for (Patient patient: patientList) {
            Random r = new Random();
            int availableDoctors = serverList.size();
            int assignedServerIndex = r.nextInt(availableDoctors);

            Server assignedServer= serverList.get(assignedServerIndex);
            assignedServer.assignPatient(patient);

        }
    }
    private void startThreads(){
        for (Server server: serverList) {
            Thread serverThread = new Thread(server);
            serverThread.start();
        }
    }
}
