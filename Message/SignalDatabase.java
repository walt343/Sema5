import java.io.*;
import java.util.Scanner;
public class SignalDatabase
{
    public Message workingMessage;
    public SignalDatabase()
    {
        workingMessage = null;
    }
    public AcarsMessage readAcarsFromFile()
    {
        String line = null;
        String AircraftReg;
        String FlightId;
        int Mode;
        int MessageLabel;
        String BlockId;
        String Ack;
        int MessageNumber;
        String Message;
        Scanner cin = new Scanner(System.in);
        String filename = cin.nextLine();
        try 
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            AircraftReg = line.split(": ")[1];
            line = br.readLine();
            FlightId = line.split(": ")[1];
            line = br.readLine();
            Mode = Integer.parseInt(line.split(": ")[1]);
            line = br.readLine();
            MessageLabel = Integer.parseInt(line.split(": ")[1]);
            line = br.readLine();
            BlockId = line.split(": ")[1];
            line = br.readLine();
            Ack = line.split(": ")[1];
            line = br.readLine();
            MessageNumber = Integer.parseInt(line.split(": ")[1]);
            line = br.readLine();
            Message = line.split(": ")[1];
            AcarsMessage am = new AcarsMessage(AircraftReg, FlightId, Mode, MessageLabel, BlockId, Ack, MessageNumber, Message);
            return am;
        }
        catch(FileNotFoundException x)
        {
            System.out.println("Error opening file!");
        }
        catch(IOException x)
        {
            x.printStackTrace();
        }
        return null;
    }
    public void printMessage(Message M)
    {
        System.out.println(M.render());
    }
    public static void main(String[] args)
    {
        SignalDatabase db = new SignalDatabase();
        db.workingMessage = db.readAcarsFromFile();
        db.printMessage(db.workingMessage);
        
    }
}
