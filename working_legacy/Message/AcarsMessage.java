import java.util.Date;
import java.text.*;
import java.io.*;
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
    
    public AcarsMessage()
    {
        AircraftReg = "Unknown";
        FlightId = "Unknown";
        Mode = 0;
        MessageLabel = 0;
        BlockId = "";
        Ack = "";
        MessageNumber = 0;
        Message = "";
        
        return;
    }
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
    
    public void convertToFile()
    {
        long captureTime = new Date().getTime();
        DateFormat date = new SimpleDateFormat("ddMMMyyyy_hhmmss");
        String fileName = FlightId + date.format(captureTime) + ".acars";
        fileName = "acars/" + fileName;
        try
        {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(this.render());
            writer.close();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        
    }
    
}
