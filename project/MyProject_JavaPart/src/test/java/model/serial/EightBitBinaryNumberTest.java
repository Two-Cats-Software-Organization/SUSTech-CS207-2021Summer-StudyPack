package model.serial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EightBitBinaryNumberTest {

    @Test
    void getUnsignedByte() {
        final EightBitBinaryNumber eightBitBinaryNumber = new EightBitBinaryNumber(new byte[]{0, 1, 1, 0, 1, 1, 1, 0});
        assertEquals(eightBitBinaryNumber.getUnsignedByte(), (byte) Integer.parseInt("6e", 16));
    }
}