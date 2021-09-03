package model.serial;

/**
 * 上下位机通信包，可以通过继承来重写。
 */
public class CommunicationPackage {
    protected byte[] message;

    protected CommunicationPackage() {
    }
    public CommunicationPackage(byte[] message) {
        this.message = message;
    }

    public byte[] getMessage() {
        return message;
    }
    @Override
    public String toString() {
        return "CommunicationPackage{" +
                "message=" + SerialPortManager.bytesToHexString(message) +
                '}';
    }
}
