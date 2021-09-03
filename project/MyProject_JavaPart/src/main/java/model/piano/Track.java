package model.piano;

import model.music_notation.MusicNote;

public enum Track {
    零,
    一,
    二,
    三;
    private MusicNote currentMusicNote;
    Track() {
        reset();
    }
    public MusicNote getCurrentMusicNote() {
        return currentMusicNote;
    }
    public void reset() {
        this.currentMusicNote = MusicNote.nothing;
    }
    public boolean isEmpty(){
        return this.currentMusicNote == MusicNote.nothing;
    }
    public void setCurrentMusicNote(MusicNote currentMusicNote) {
        this.currentMusicNote = currentMusicNote;
    }
}
