import java.util.Date;
import java.text.DateFormat;

public class TrainMessage extends Message {

    private float brakeLinePress;  //brake line pressure of train
    private long captureTime;  //time signal was captured

    public TrainMessage() {  //default constructor
        brakeLinePress = "n/a";
        time = new Date().getTime();  //current time in sec since epoch
    }

    public TrainMessage(String brakeLinePress) {  //non-default constructor
        self.brakeLinePress = brakeLinePress;
        time = new Date().getTime();
    }

    public String render() {
        String blp =  "Brake Line Pressure: " + brakeLinePress + "\n";  //brake line pressure line

        return blp;
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
