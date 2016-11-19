package practitioner_desktop;

import java.util.Random;

/**
 * Created by Carlos on 11/19/2016.
 */
public class Patient {

    private String name;
    private double EHR_ID;

    Random rand = new Random(); // used to populate unique sample data

    public Patient(String name, double EHR_ID){
        this.name = name;
        this.EHR_ID = EHR_ID;
    }

    public String getName() {
        return name;
    }

    public double getEHR_ID() {
        return EHR_ID;
    }

    public String getMessageSubject(){
        return "Sample Subject " + rand.nextInt()%100;
    }

    public String getMessageContent(){
        return "Sample Message Content " + rand.nextInt()%100;
    }

    public int getMessageSeverity(){
        return rand.nextInt()%100;
    }

    @Override
    public String toString(){
        return this.name + " [" + getMessageSubject() + "] - Severity: " + getMessageSeverity();
    }
}
