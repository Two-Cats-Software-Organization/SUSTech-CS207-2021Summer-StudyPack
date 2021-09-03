//package model.serial;
//
//import draft.CustomException;
//
//import java.io.IOException;
////import draft.SerialPortUtils;
//
//class SerialPortUtilsTest {
//    public static void main(String[] args) throws CustomException, IOException {
//        // 实例化串口操作类对象
//        SerialPortManager serialPort = new SerialPortManager();
//        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
//        ParamConfig paramConfig = new ParamConfig("COM4", 9600, 0, 8, 1);
//        // 初始化设置,打开串口，开始监听读取串口数据
//        serialPort.init(paramConfig);
//        // 调用串口操作类的sendComm方法发送数据到串口
//        serialPort.sendComm("FEF10A000000000000000AFF");
//        while(true){
//            ;
//        }
//        // 关闭串口
////        serialPort.closeSerialPort();
//    }
//}