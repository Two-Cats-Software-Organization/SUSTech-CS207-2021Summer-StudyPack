package model.serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortDataListenerWithExceptions;
import com.fazecast.jSerialComm.SerialPortEvent;
import config.ModelSettings;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SerialPortManager implements AutoCloseable {
    private SerialPort serialPort;
    // 保存串口返回信息
    private String data;
    // 保存串口返回信息十六进制
    private String dataHex;
    // 输入流
    private InputStream inputStream;
    // 输出流
    private OutputStream outputStream;
    private boolean firstWrite = true;
    public SerialPortManager(Component component) throws IOException{
        SerialPort[] possibleValues = SerialPort.getCommPorts();
        if (possibleValues.length == 0)
            throw new IOException("没有可用端口！");
        serialPort = (SerialPort) JOptionPane.showInputDialog(component, "请选择EGO1的串口", "连接电子琴",
        JOptionPane.QUESTION_MESSAGE, null,  possibleValues, possibleValues[0]);
        open();
    }
    public SerialPortManager() throws IOException {
        serialPort = SerialPort.getCommPort(ModelSettings.serialPortConfig.getSerialNumber());
        serialPort.setBaudRate(ModelSettings.serialPortConfig.getBaudRate());
        serialPort.setParity(ModelSettings.serialPortConfig.getCheckoutBit());
        serialPort.setNumDataBits(ModelSettings.serialPortConfig.getDataBit());
        serialPort.setNumStopBits(ModelSettings.serialPortConfig.getStopBit());
        open();
    }
    private void open()throws IOException{
        serialPort.openPort();
        if (!serialPort.isOpen())
            throw new IOException("串口连接失败！");
    }
    /**
     * 十六进制串口返回值获取
     */
    public String nextHex() {
        throw  new NotImplementedException();
//        String result = dataHex;
//        // 置空执行结果
//        dataHex = null;
//        // 返回执行结果
//        return result;
    }
    public String next() {
        throw  new NotImplementedException();
//        String result = data;
//        // 置空执行结果
//        dataHex = null;
//        // 返回执行结果
//        return result;
    }

    @Override
    public void close() throws IOException {
        serialPort.closePort();
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     * @author 叶璨铭. 通过上下位机通信包发送。
     * @param communicationPackage
     * @throws IOException
     * @see model.serial.CommunicationPackage
     */
    public void write(CommunicationPackage communicationPackage) throws IOException {
        write(communicationPackage.getMessage());
    }
    public void write(byte[] bytes) throws IOException {
        try {
            if (firstWrite) {
                firstWrite = false;
                outputStream = serialPort.getOutputStream();
            }
            outputStream.write(bytes);
            outputStream.flush();
        } catch (NullPointerException e) {
            throw new IOException("找不到串口。");
        } catch (IOException e) {
            throw new IOException("发送信息到串口时发生IO异常");
        }
    }
    /**
     * * @author LinWenLi
     *      * @date 2018年7月21日下午3:44:16
     * @param hexString
     */
    public void write(String hexString) throws IOException {
        byte[] writerBuffer = null;
        try {
            writerBuffer = hexToByteArray(hexString);
        } catch (NumberFormatException e) {
            throw new IOException("命令格式错误！");
        }
        write(writerBuffer);
    }
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }
    /**
     * * @author LinWenLi
*      * @date 2018年7月21日下午3:44:16
     * hex字符串转byte数组
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            // 奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            // 偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }
     /* 数组转换成十六进制字符串
     * @author LinWenLi
     * @date 2018年7月21日下午3:44:16
     * @param  bArray
     * @return HexString
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
//            final SerialPortManager serialPortManager = new SerialPortManager(null);
            final SerialPortManager serialPortManager = new SerialPortManager();
            serialPortManager.write("A0");
            serialPortManager.write("B0");
//            serialPortManager.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
