package model.serial;

public class EightBitBinaryNumber {
    private byte unsignedByte;

    //如果不是0和1的数组，后果自负，为了效率，不做异常检测！
    public EightBitBinaryNumber(byte[] eightBitArray) {//most significant bit at index 0.
        byte unsignedByte = 0;
        for (int i = 0; i < 8; i++)
            unsignedByte += eightBitArray[i] << (7 - i);
        this.unsignedByte = unsignedByte;
    }

    public byte getUnsignedByte() {
        return unsignedByte;
    }

}
