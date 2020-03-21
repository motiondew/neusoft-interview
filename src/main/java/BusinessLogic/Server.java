package BusinessLogic;

import Entities.Doctor;
import Entities.Patient;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private static final int WORKING_TIME = 25200; // 25200 seconds == 7 hours

    private List<Patient> patientsWaiting;
    private List<Patient> patientsFinished;
    private AtomicInteger amountBilled;
    private Doctor assignedDoctor;
    Integer currentWorkedHours;
    private int simulationSpeed;

    public Server(Doctor assignedDoctor){
        patientsWaiting = new ArrayList<Patient>(100);
        patientsFinished = new ArrayList<Patient>(100);
        amountBilled = new AtomicInteger(0);
        this.assignedDoctor = assignedDoctor;
        currentWorkedHours  = new Integer(0);
    }

    public Server(Doctor assignedDoctor,int simulationSpeed){
        patientsWaiting = new ArrayList<Patient>(100);
        patientsFinished = new ArrayList<Patient>(100);
        amountBilled = new AtomicInteger(0);
        this.assignedDoctor = assignedDoctor;
        currentWorkedHours  = new Integer(0);
        this.simulationSpeed =simulationSpeed;
    }

    public void assignPatient(Patient patient){
        patientsWaiting.add(patient);
    }



    @Override
    public void run() {


        Timer timeCounter = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                currentWorkedHours++;
            }
        };

        timeCounter.scheduleAtFixedRate(timerTask,1000,1000);

        while(currentWorkedHours < (int)(WORKING_TIME/simulationSpeed) && patientsWaiting.size() > 0){

            //treat patients assigned;
            int untreatedPatients = patientsWaiting.size();
            Random r = new Random();
            int selectedPatientIndex = r.nextInt(untreatedPatients);
            Patient selectedPatient = patientsWaiting.get(selectedPatientIndex);
            String reason = selectedPatient.getReason();
            int amountToBill = 0;

            if(reason.equals("Consultation")){
                try {
                    amountToBill = 50;
                    Thread.sleep((180000/simulationSpeed));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(reason.equals("Prescription")){
                try {
                    amountToBill = 20;
                    Thread.sleep(120000/simulationSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    amountToBill = 35;
                    Thread.sleep(240000/simulationSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            amountBilled.addAndGet(amountToBill);
            patientsFinished.add(selectedPatient);
            patientsWaiting.remove(selectedPatientIndex);

            //System.out.println("Amount billed: "+amountBilled.intValue()+" at TIME: "+currentWorkedHours);
            //System.out.println("Patients left:" +patientsWaiting.size());
        }
        printFinishedPatients();
        if(patientsWaiting.size()>0) {
            printUnfinishedPatients();
        }else{
            System.out.println((simulationSpeed == 60 ? "[FAST-MODE]" :"[REAL-TIME-MODE]")+"All patients assigned to this doctor were checked");
        }
        timerTask.cancel();

        //momentantly this runs to infinity, should check in scheduler


    }

    public void checkPatientsAssigned(){
        System.out.println("Doctor ["+assignedDoctor.getIDNumber()+"] will treat:");
        for (Patient patient :patientsWaiting) {
            System.out.println(patient.getFirstName()+ "  " + patient.getLastName());
        }
        System.out.println();
    }

    private void printFinishedPatients(){
        if(simulationSpeed == 60){
            System.out.print("[FAST-MODE] ");
        }else
        {
            System.out.print("[REAL-TIME-MODE] ");
        }
        System.out.println(assignedDoctor.getFirstName()+", "+assignedDoctor.getLastName()+
                " -- "+assignedDoctor.getIDNumber()+": "+patientsFinished.size()+
                " patients, "+amountBilled.intValue()+" RON");
    }
    private void printUnfinishedPatients(){
        System.out.println("Patients unchecked:");
        for (Patient patient : patientsWaiting) {
            System.out.println(patient.getFirstName()+", "+
                    patient.getLastName()+", "+
                    patient.getAge()+", "+
                    patient.getReason());
        }
}

}
