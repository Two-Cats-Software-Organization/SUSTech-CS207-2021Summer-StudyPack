package model.record;

import model.music_notation.MusicNote;
import model.piano.Track;

import java.io.Serializable;
import java.util.LinkedList;

public class Record implements Serializable {
    private LinkedList<MusicNote>[] disk = new LinkedList[4];

    public Record() {
        for (int i = 0; i < 4; i++) {
            disk[i] = new LinkedList<MusicNote>();
        }
    }
    public void recordOnce(){
        for (int i = 0; i < 4; i++) {
            disk[i].add(Track.values()[i].getCurrentMusicNote());
        }
    }

    public void playOnce() {
        for (int i = 0; i < 4; i++) {
            Track.values()[i].setCurrentMusicNote(disk[i].remove(0));
        }
    }
}
