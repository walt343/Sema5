
public class ISRMessage {

    ByteArrayInputStream rawAudioBytes;
    AudioInputStream audioStream;

    public ISRMessage(byte[] bytes) {  //constructor
        this.rawAudioBytes = new ByteArrayInputStream(bytes);
        this.audioStream = new AudioInputStream(rawAudioBytes, );
    }
}
