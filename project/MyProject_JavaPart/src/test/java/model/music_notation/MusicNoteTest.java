package model.music_notation;

import static org.junit.jupiter.api.Assertions.*;

class MusicNoteTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        MusicNote musicNote = new MusicNote(Domain.HIGH, SyllableName.DoSharp, Tone.RISING);
        System.out.println(musicNote);
        musicNote= new MusicNote(Domain.HIGH, SyllableName.MiSharp, Tone.RISING);
        System.out.println(musicNote);

        musicNote = new MusicNote(Domain.HIGH, SyllableName.Si, Tone.NORMAL);
        System.out.println(musicNote);
        assertEquals(36,musicNote.getBinaryCode());

        musicNote = new MusicNote(Domain.HIGH, SyllableName.Si, Tone.RISING);
        System.out.println(musicNote);
        assertEquals(0,musicNote.getBinaryCode());
    }
}