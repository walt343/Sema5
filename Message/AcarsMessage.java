import java.util.Date;
import java.text.DateFormat;
public class AcarsMessage extends Message
{
    public String AircraftReg;
    public String FlightId;
    public int Mode;
    public int MessageLabel;
    public String BlockId;
    public String Ack;
    public int MessageNumber;
    public String Message;
    private long captureTime;
    
    public AcarsMessage(String ar, String fi, int m, int ml, String bid, String a, int mn, String ms)
    {
        AircraftReg = ar;
        FlightId = fi;
        Mode = m;
        MessageLabel = ml;
        BlockId = bid;
        Ack = a;
        MessageNumber = mn;
        Message = ms;
        captureTime = new Date().getTime();
        return;
    }
    public String render()
    {
        String out = "Aircraft Registration: " + AircraftReg;
        out = out + "\nFlight Id: " + FlightId;
        out = out + "\nMode: " + Mode;
        out = out + "\nMessage Label: " + MessageLabel;
        out = out + "\nBlock Id: " + BlockId;
        out = out + "\nAck: " + Ack;
        out = out + "\nMessage Number: " + MessageNumber;
        out = out + "\nMessage: " + Message;
        return out;
    }
    /*
    public convertToFile()
    {
        DateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
        String fileName = date.format(captureTime) + ".acars";
    }
    */
}
