package model.piano;

import model.music_notation.MusicNote;
import model.serial.CommunicationPackage;
import model.serial.SerialPortManager;

import java.util.Arrays;

public class MusicInformation extends CommunicationPackage { //依赖于Track
    public MusicInformation(Track[] tracks) {
        super();
        byte[] temp = new byte[tracks.length];
        for (int i = 0; i < tracks.length; i++) {
            Track track = tracks[i];
            temp[i] += track.ordinal()<<6;
            temp[i] += track.getCurrentMusicNote().getBinaryCode();
        }
        super.message = temp;
    }
    @Deprecated
    public MusicInformation() { //new 我就完事了。
        this(Track.values());
    }

    @Override
    public String toString() {
        return "MusicInformation{" +
                "message=" + SerialPortManager.bytesToHexString(message) +
                '}';
    }
}
