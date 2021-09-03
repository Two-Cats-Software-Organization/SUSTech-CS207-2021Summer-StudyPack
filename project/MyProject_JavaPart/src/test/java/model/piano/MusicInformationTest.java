package model.piano;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicInformationTest {

    @Test
    void testToString() {
        MusicInformation musicInformation = new MusicInformation();
        System.out.println(musicInformation);
        assertEquals("MusicInformation{message=004080C0}",musicInformation.toString());
    }
}