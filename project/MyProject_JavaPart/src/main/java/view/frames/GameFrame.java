package view.frames;

import model.piano.TrackManager;
import model.record.ArchiveManager;
import model.record.Record;
import model.serial.SerialPortManager;
import util.ResourcesManager;
import view.PianoKeyBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import static util.MySwingUI_Template.*;

public class GameFrame extends CommonFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new GameFrame();
        });
    }

    @Override
    protected void setFrameField() {
        super.setFrameField();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("EGO1 电子琴");
//        this.getContentPane().setForeground(Color.BLUE);

    }
    private JLayeredPane  layeredPane;
    private PianoKeyBoardView pianoKeyBoardView;
    private JButton open, record_button, play;
    @Override
    protected void createComponent() {
        super.createComponent();
        layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);

        pianoKeyBoardView = new PianoKeyBoardView((ImageIcon) ResourcesManager.getInstance().getPianoKeyBoard());
        layeredPane.add(pianoKeyBoardView,100,0);

        open = new JButton("连接设备");
        open.setFont(new Font("MS Song", 0, 36));
        open.setSize(208 * 3 / 2, 40 * 3 / 2);
        open.setLocation(61 * 3 / 2, 448 * 3 / 2);
        open.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEGO1();
            }
        });
        layeredPane.add(open, 150, 0);

        record_button = new JButton("录制");
        record_button.setFont(new Font("MS Song", 0, 36));
        record_button.setSize(208 * 3 / 2, 40 * 3 / 2);
        record_button.setLocation(61 * 3 *3 , 448 * 3 / 2);
        record_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                record();
            }
        });
        layeredPane.add(record_button, 150, 0);

        play = new JButton("播放");
        play.setFont(new Font("MS Song", 0, 36));
        play.setSize(208 * 3 / 2, 40 * 3 / 2);
        play.setLocation(61 * 3 *6 , 448 * 3 / 2);
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                play();
            }
        });
        layeredPane.add(play, 150, 0);
    }

    private void play() {
        if (!isPlaying){
            if (!isOpen){
                GameMsgBox("异常", "还没有打开EGO1", "请连接EGO1后再进行播放", "如果需要电脑播放模式，请走后门。");
                return;
            }
            this.record_button.setEnabled(false);
            this.play.setText("播放中, 再次按下停止播放");
            final String s = GameInput("请输入你要播放的钢琴曲名：", "录制时输入相同的钢琴曲名即可录制。");
            record = ArchiveManager.getInstance().load(s);
            isPlaying = true;
        }else{
            this.record_button.setEnabled(true);
            this.play.setText("播放");
            record = null;
            GameMsgBox("结束", "钢琴曲播放结束", "好听吗？", "");
            isPlaying = false;
        }
    }

    private boolean isRecording = false;
    private boolean isPlaying = false;
    private Record record;
    private void record() {
        if (!isRecording){
            if (!isOpen){
                GameMsgBox("异常", "还没有打开EGO1", "请连接EGO1后再进行录音", "如果需要后台录音模式，请走后门。");
                return;
            }
            this.record_button.setText("保存");
            this.play.setEnabled(false);
            record = new Record();  //具体录音操作在时钟那里。
            isRecording = true;
        }else{
            this.play.setEnabled(true);
            this.record_button.setText("录制");
            final String s = GameInput("请输入你演奏的钢琴曲名：", "播放时输入相同的钢琴曲名即可播放。");
            ArchiveManager.getInstance().save(record,s);
            record = null;
            isRecording = false;
        }
    }

    private SerialPortManager serialPortManager;
    private TrackManager trackManager;
    private TrackScanner trackScanner;
    private boolean isOpen = false;
    private void openEGO1(){
        if (!isOpen) {
            try {
                serialPortManager = new SerialPortManager();
            } catch (IOException e) {
                System.err.println("预定义的配置无法打开EGO1，请手动选择正确的串口: ");
                try {
                    serialPortManager = new SerialPortManager(this);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    GameMsgBox("异常", "无法打开EGO1连接！", "请重试拔插接口", "哦我的电子琴！我的串口通信！");
                }
            }
            if (serialPortManager != null) {
                trackManager = new TrackManager(serialPortManager);
                trackScanner = new TrackScanner(trackManager);
                trackScanner.start();
                isOpen = true;
                this.open.setText("关闭连接");
                GameMsgBox("欢迎", "成功连接", "成功连接设备：EGO1。", "");
            }
        }else{
            if (isRecording)
                record();
            if (isPlaying)
                play();
            trackManager.closeTrack();
            try {
                serialPortManager.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            serialPortManager = null;
            trackManager = null;
            trackScanner.stop();
            trackScanner =null;
            isOpen = false;
            this.open.setText("连接设备");
            GameMsgBox("已关闭", "连接成功关闭", "与设备：EGO1断开连接。", "");
        }
    }
//    private java.util.Timer timer_16hz;
    @Override
    protected void createEvent() {
        super.createEvent();
//        timer_16hz = new java.util.Timer();
//        timer_16hz.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("16hz");
//            }
//        },0, (long) 62.5);
//        final int[] i = {0};
        //可以满足要求。不要用setDelay就行。用Thread.sleep();
    }
    private class TrackScanner extends Timer{
        private TrackManager trackManager;
        public TrackScanner(TrackManager trackManager) {
            super(0, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Thread.sleep(62,500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    if (isPlaying)
                        try {
                            record.playOnce();
                        }catch (IndexOutOfBoundsException exception){ //不是ArrayIndex.... 因为不是Array
                            play();
                        }
                    trackManager.sendMusicInformation();
                    if (isRecording)
                        record.recordOnce();
                }
            });
            this.trackManager = trackManager;
        }
    }


}
