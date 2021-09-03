package model.music_notation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicNote_verilog {
    public static void main(String[] args) {
        for (Domain d:Domain.values()) {
            for (int i = 1; i <= 12; i++) {
                final MusicNote musicNote = new MusicNote(d, MusicOrder.musicOfOrder(i));
                System.out.println("\t\t\t"+musicNote.getBinaryCode()+":\torigin <= "+
                        Math.round((16383 - 3000000/musicNote.getFrequency()))+";"
                );
            }
        }
    }
}