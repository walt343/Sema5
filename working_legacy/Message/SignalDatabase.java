import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class SignalDatabase
{
    public Message workingMessage;
    //Constructor
    public SignalDatabase()
    {
        workingMessage = null;
    }
////////////FUNCTIONS RELATED TO ACARS////////////
    //Reads in a specific ACARS file to a ACARSMessage object
    public AcarsMessage readAcarsFromFile(String filename)
    {
        String AircraftReg;
        String FlightId;
        int Mode;
        int MessageLabel;
        String BlockId;
        String Ack;
        int MessageNumber;
        String Message;
        try 
        {
            String line;
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
    //Returns a string array of all files that are sent by an Aircraft reg.
    public String[] findAcarsFile(String AircraftReg)
    {
        ArrayList<String> foundFiles = new ArrayList<String>();
        String directory  = "acars/";
        File dir = new File(directory);
        File[] listOfFiles = dir.listFiles(); //list all files in the ACARS directory
        if(listOfFiles != null)
        {
            for (File f : listOfFiles)
            {
                if(f.getName().startsWith(AircraftReg)) //filename starts with the registration number
                {
                    //add to the output 
                    foundFiles.add(f.getName());
                }
            }
        }
        String[] output = new String[foundFiles.size()];
        for(int i = 0; i < foundFiles.size(); i++)
        {
            output[i] = foundFiles.get(i);
        } 
        return output;
    }
    
////////////FUNCTIONS RELATED TO TRAIN TELEMETRY////////////
    //Reads in a specific .telem file to a TrainMessage object
    public TrainMessage readTrainFromFile(String filename)
    {
        double brakeLinePress;
        String unitID;
        int batteryStatus;
        boolean inMotion;
        try 
        {
            FileReader fr = new FileReader(filename);
            String line;
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            brakeLinePress = Double.parseDouble(line.split(": ")[1]);
            line = br.readLine();
            unitID = line.split(": ")[1];
            line = br.readLine();
            batteryStatus = Integer.parseInt(line.split(": ")[1]);
            line = br.readLine();
            if(line.equals("True"))
            {
                inMotion = true;
            }
            else
            {
                inMotion = false;
            }
            TrainMessage tm = new TrainMessage(brakeLinePress, unitID, batteryStatus, inMotion);
            return tm;
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
//////////////////PRINT MESSAGE///////////////
    public void printMessage(Message M)
    {
        System.out.println(M.render());
    }
    //for testing
    public static void main(String[] args)
    {
        //initialise database
        SignalDatabase db = new SignalDatabase();
        String[] foundFiles;
        //test acars work
        //db.workingMessage = new AcarsMessage();
        //System.out.println("Printing empty ACARS packet:");
        //db.printMessage(db.workingMessage);
        //db.workingMessage.convertToFile();
        //db.workingMessage = db.readAcarsFromFile("acars/FakePacket1.acars");
        //System.out.println("Printing fake ACARS packet:");
        //db.printMessage(db.workingMessage);
        System.out.println("Finding ACARS files with Tail Number NCC1701");
        foundFiles = db.findAcarsFile("NCC1701");
        for(int i = 0; i < foundFiles.length; i++)
        {
            System.out.println(foundFiles[i]);
        }
        //test ttelem work
        //db.workingMessage = new TrainMessage();
        //System.out.println("Printing empty Train Telemetry packet:");
        //db.printMessage(db.workingMessage);
        //db.workingMessage.convertToFile();
        //System.out.println("Printing fake Train Telemetry packet:");
        //db.workingMessage = db.readTrainFromFile("train/FakePacket1.telem");
        //db.printMessage(db.workingMessage);
    }
}
