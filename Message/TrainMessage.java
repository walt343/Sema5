import java.util.Date;
import java.text.DateFormat;

public class TrainMessage extends Message {

    private float brakeLinePress;  //brake line pressure of train
    private long captureTime;  //time signal was captured
    private String unitID;
    private int batteryStatus;
    private boolean inMotion;

    public TrainMessage() {  //default constructor
        brakeLinePress = 0.0;  //default to 0
        captureTime = 0;
        unitID = "n/a";
        batteryStatus = 100;
        inMotion = false;
        time = new Date().getTime();  //current time in sec since epoch
    }

    public TrainMessage(float brakeLinePress, String unitID, int batteryStatus, boolean inMotion) {  //non-default constructor
        self.brakeLinePress = brakeLinePress;
        captureTime = new Date().getTime();
        self.unitID = unitID;
        self.batteryStatus = batteryStatus;
        self.inMotion = inMotion;
    }

    public String render() {
        String ret =  "Brake Line Pressure: " + brakeLinePress + "\n";  //brake line pressure line
        ret = ret + "Unit ID: " + unitID + "\n";  //unit ID line
        ret = ret + "Battery: " + batteryStatus + "\n";  //battery line
        
        if(inMotion) {  //determine if in motion
            ret = ret + "In Motion: True\n";
        }
        else {
            ret = ret + "In Motion: False\n";
        }

        return ret;
    }

    public convertToFile() {
        DateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
        String fileName = date.format(captureTime) + "TT.telem";

        //TODO--FIND LOCATION OF DIRECTORY--FROM KEVIN'S WORK
        FileWriter fout = new FileWriter(fileName);
        fout.write(render());
        fout.close();
    }
}
