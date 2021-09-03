package util;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static javax.swing.JOptionPane.UNINITIALIZED_VALUE;
import static javax.swing.JOptionPane.getRootFrame;
/**
 * designed by 叶璨铭
 * 本类封装了swing的一些常用方法(dpi,播放音乐等)，设计了一些控件(游戏信息框、输入框，图片按钮、图片框(后两个是因为我对swing的不了解导致的遗留产物，正确写法不是这样的，不过没关系了))
 */
public class MySwingUI_Template {
    public static void setDPI(String dpi){
        System.setProperty("sun.java2d.win.uiScaleX", dpi);
        System.setProperty("sun.java2d.win.uiScaleY", dpi);
    }
    public static void setDPI(int dpi){
        System.setProperty("sun.java2d.win.uiScaleX", ""+dpi+"dpi");
        System.setProperty("sun.java2d.win.uiScaleY", ""+dpi+"dpi");
    }
    public static void imShow(ImageIcon imageIcon){
        JFrame a = new JFrame("view");
        a.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
        a.setLocationRelativeTo(null);
        JLabel b = new JLabel(imageIcon);
        a.add(b);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);
    }
    public static String input(String message){
        return JOptionPane.showInputDialog(new JLabel(message));
    }
    public static String GameInput(String Hints, String Question){
        return GameInput(Hints,Question,0);
    }
    public static String GameInput(String Question){
        return GameInput(Question,0);
    }
    public static String GameInput(String Hints, String Question, String Quotation){
        return GameInput(Hints,Question,Quotation,0);
    }
    public static String input2(String message){
        return input2(message,0);
    }

    public static String GameInput(String Question, int playerNow){
        String html = "<html><h2>";
        html+="<font size=\"6\" color='blue'>"+Question+"</font>";
        html+= "</h2></html>";
        return input2(html,playerNow);
    }
    public static String GameInput(String Hints, String Question, int playerNow){
        String html = "<html><h2>";
        html+="<font size=\"8\" color='blue'>"+Question+"</font><br>";
        html+="<font size=\"4\" color='#cc22ff'>"+Hints+"</font><br>";
        html+= "</h2></html>";
        return input2(html,playerNow);
    }
    public static String GameInput(String Hints, String Question, String Quotation, int playerNow){
        String html = "<html><h2>";
        html+="<font size=\"10\" color='blue'>"+Question+"</font><br>";
        html+="<font size=\"5\" color='#cc22ff'>"+Hints+"</font><br>";
        html+="<font size=\"4\" color='#87CEEB'>"+Quotation+"</font><br>";
        html+= "</h2></html>";
        return input2(html,playerNow);
    }

    public static String input2(String message,int playerNow){
//        JOptionPane pane = new JOptionPane(new JLabel(message), JOptionPane.QUESTION_MESSAGE,
//                JOptionPane.DEFAULT_OPTION);
//        JDialog dialog = pane.createDialog("请输入：");
//        pane.selectInitialValue();
//        dialog.show();
//        dialog.dispose();
//        return ""+pane.getValue();
        JOptionPane pane = new JOptionPane(new JLabel(message), 3, 2, ResourcesManager.getInstance().getPlayer(playerNow), (Object[])null, (Object)null);
        pane.setWantsInput(true);
        pane.setSelectionValues(null);
        pane.setInitialSelectionValue(null);
        pane.setComponentOrientation(((Component)(null == null ? getRootFrame() : null)).getComponentOrientation());
        int style = styleFromMessageType(3);
        JDialog dialog = pane.createDialog("");
        pane.selectInitialValue();
        dialog.setSize((int)(dialog.getWidth()*1.2),(int)(dialog.getHeight()*1.2));
        dialog.show();
        dialog.dispose();
        Object value = pane.getInputValue();
        return value == UNINITIALIZED_VALUE ? null : ""+value;
    }
    private static int styleFromMessageType(int messageType) {
        switch(messageType) {
            case -1:
            default:
                return 2;
            case 0:
                return 4;
            case 1:
                return 3;
            case 2:
                return 8;
            case 3:
                return 7;
        }
    }
    public static void GameMsgBox(String 事件性质, String 触发条件, String 事件结果, String 人物引言){
        GameMsgBox(事件性质, 触发条件,事件结果, 人物引言,null,false);
    }
    public static void GameMsgBox(String 事件性质, String 触发条件, String 事件结果, String 人物引言, String 声优资源, boolean 是否警告){
//        setDPI((int) (96*1.5));
//        setDPI((int) (180));
        if (声优资源!=null)
            new VoicePlayer(声优资源).start();
        String html = "<html><h2>";

        html+="<font size=\"10\" color='blue'>"+事件结果+"</font><br>";
        html+="<br>";
        html+="<br>";

        html+="<font size=\"5\" color='#cc22ff'>"+触发条件+"</font><br>";
        html+="<br>";
        html+="<br>";
        html+="<font size=\"4\" color='#87CEEB'>"+人物引言+"</font><br>";

        html+= "</h2></html>";
        msgBox(html,事件性质,是否警告?JOptionPane.WARNING_MESSAGE:JOptionPane.INFORMATION_MESSAGE);
//        setDPI(96);
    }
    public static void msgBox(String message,String title,int messageType){
        JOptionPane.showMessageDialog(null,new JLabel(message),title,messageType);
    }
    public static void msgBox(String message,int messageType){
        JOptionPane.showMessageDialog(null,new JLabel(message),"提示",messageType);
    }
    public static void msgBox(String message){
        JOptionPane.showMessageDialog(null,new JLabel(message),"提示",JOptionPane.WARNING_MESSAGE);
    }
    public static class VideoPlayer extends JPanel{

    }
    public static class SuperButton extends JButton {
        private ImageIcon NormalView;
        private ImageIcon FiredView;
        private ImageIcon DisabledView;

        public ImageIcon getNormalView() {
            return NormalView;
        }

        public void setNormalView(ImageIcon normalView) {
            NormalView = normalView;
            super.setIcon(normalView);
            this.setSize(normalView.getIconWidth(),normalView.getIconHeight());
        }

        public ImageIcon getFiredView() {
            return FiredView;
        }

        public void setFiredView(ImageIcon firedView) {
            FiredView = firedView;
            super.setRolloverIcon(firedView);
        }

        public ImageIcon getDisabledView() {
            return DisabledView;
        }

        public void setDisabledView(ImageIcon disabledView) {
            DisabledView = disabledView;
            super.setDisabledIcon(disabledView);
        }
        public SuperButton(){
            super();
            super.setMargin(new Insets(0,0,0,0));
        }
        public SuperButton(ImageIcon normalView, ImageIcon firedView) {
            this();
            this.setNormalView(normalView);
            this.setFiredView(firedView);
        }
        public SuperButton(ImageIcon normalView, ImageIcon firedView, ImageIcon disabledView) {
            this(normalView,firedView);
            this.setDisabledView(disabledView);
        }
    }
    public static class PicturePanel extends JPanel
            /*implements Cloneable*/{
        private ImageIcon imageIcon;

//        @Override
//        protected Object clone() throws CloneNotSupportedException {
//            Object obj = super.clone();
//            ImageIcon img = null;
//            if (obj instanceof PicturePanel)
//                img = (ImageIcon) ((PicturePanel) obj).getImageIcon();
//            ((PicturePanel) obj).setImageIcon((ImageIcon) img.clone());
//        }

        @Override
        public void setSize(int width, int height) {
            super.setSize(width, height);
            repaint();
        }

        public PicturePanel() {
            //没有无参数构造器就实现不了子类先加载，后超级
            super();
            this.setBackground(null);
            this.setOpaque(false);//设置为透明
        }
        public PicturePanel(ImageIcon imageIcon) {
            this();
            this.imageIcon = imageIcon;
            repaint();
        }
        public ImageIcon getImageIcon() {
            return imageIcon;
        }

        public void setImageIcon(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
            repaint();
        }
        protected void AdapterForChessPiece(Graphics g){
            super.paintComponent(g);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

//            g2d.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(),this.getHeight(), imageIcon.getImageObserver());
            g2d.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(),this.getHeight(),
                    this);
        }
    }
    public static class PictureBox extends JLabel{
        public PictureBox(ImageIcon imageIcon) {
            super(imageIcon);
            super.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());//default
            super.setBackground(Color.BLACK);//debug
        }

        @Override
        public void setBounds(int x, int y, int width, int height) {
            super.setBounds(x, y, width, height);
            repaint();
        }

        //    public PictureBox() {
//        new PictureBox(null);
//    }
//    public PictureBox(ImageIcon pictureSource) {
//        new PictureBox(pictureSource,ShowMode.JLabelFirst);
//    }
//    public PictureBox(ImageIcon pictureSource, ShowMode showMode) {
//        super();
//        this.PictureSource = pictureSource;
//        this.showMode = showMode;
////        this.setPictureSource(pictureSource);
//        this.setShowMode(showMode);
//    }
        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            ImageIcon icon = (ImageIcon) super.getIcon();
            g2d.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(),
                    icon.getImageObserver());

        }

//    public void setShowMode(ShowMode showMode) {
//        this.showMode = showMode;
//        setPictureSource(PictureSource);//repaint
//    }
//    public void setPictureSource(ImageIcon pictureSource) {
//        PictureSource = pictureSource;
//        ViewPicture = pictureSource;
//        switch (showMode){
//            case PictureFirst:
//                break;
//            case JLabelFirst:case 比例不变缩小到JLabel://TODO 以后再说
//                ViewPicture.setImage(ViewPicture.getImage().getScaledInstance(super.getWidth(),
//                        super.getHeight(), Image.SCALE_DEFAULT));
//                break;
//
////                int dominantSide = Math.max(ViewPicture.getIconHeight(),ViewPicture.getIconWidth());
//
////                break;
//        }
//        super.setIcon(ViewPicture);
//    }
//
//
//
//
//    public ImageIcon getPictureSource() {
//        return PictureSource;
//    }
//    public ShowMode getShowMode() {
//        return showMode;
//    }
//    private ImageIcon PictureSource;
//    private ShowMode showMode;
//    enum ShowMode{
//        PictureFirst,
//        JLabelFirst,
//        比例不变缩小到JLabel
//    }
//    private ImageIcon ViewPicture;
    }

    public static void main(String[] args) {
                setDPI(96);
//        System.out.println(System.getProperty("sun.java2d.win.uiScaleX"));
//                gameMsgBox("恭喜你","本次骰子数之和大于十！","你获得了一个新的回合！","\"欧皇就是我，我就是运气王！\"");
//        System.out.println(System.getProperty("sun.java2d.win.uiScaleX"));
        System.out.println(input2("测试"));
        System.out.println(GameInput("测试","只是两行吗"));
        System.out.println(GameInput("测试","这样可以吗?","真的可以吗"));
        System.out.println(GameInput("测试","这样可以吗?",""));
        //        JLabel ChessBoard = new PictureBox(GameResources.getChessBoard());
//        ChessBoard.setBounds(0,0, ViewSettings.WIDTH/2,ViewSettings.HEIGHT/2);
//        ChessBoard.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.out.println("You clicked(relative to JLabel)");
//                System.out.println("x = "+e.getX());
//                System.out.println("y = "+e.getY());
//            }
//        });
//        this.add(ChessBoard,BorderLayout.EAST);

//        JLabel a = new PictureBox(GameResources.getChessBoard());
//        this.add(a);//ok
    }

    /**
     * 音频文件已流的方式读入到内存中，然后内存将这个流交给音频设备，
     * @author Emine Wang
     */


    public static class VoicePlayer2 extends Thread
    {
        private String path;
        private AudioFormat format=null;
        private AudioInputStream aistream=null;
        private float sampleRate=0;
        private float framelength=0;
        private DataLine.Info datalineinfo=null;
        private SourceDataLine dataline;
        private boolean pause=false;
        private boolean stop=false;
        private int played=0;
        private int play_from=0;
        boolean pass=false;
        private boolean playing=false;
        public VoicePlayer2(String wavFile)
        {
            setPath(wavFile);
        }
        public void setPath(String p)
        {
            path=p;
        }
        public String getPath()
        {
            return path;
        }
        public void stopPlaying()
        {
            stop=true;
        }
        public boolean isStopped()
        {
            return stop;
        }
        public int getPlayed()
        {
            return played;
        }
        public void playFrom(int t)
        {
            play_from=t;
        }
        public float getSecLength()
        {
            return framelength/sampleRate;
        }
        public void setPause(boolean p)
        {
            pause=p;
        }
        public boolean isPaused()
        {
            return pause;
        }
        public boolean isPlaying()
        {
            return playing;
        }
        public void set()
        {
            try
            {
                aistream=AudioSystem.getAudioInputStream(new File(path));
                format=aistream.getFormat();
                sampleRate=format.getSampleRate();
                framelength=aistream.getFrameLength();
                datalineinfo=new DataLine.Info(SourceDataLine.class, format);
                dataline=(SourceDataLine)AudioSystem.getLine(datalineinfo);
            }
            catch(LineUnavailableException err)
            {
                System.out.println("LineUnavailableException");
            }
            catch(UnsupportedAudioFileException err)
            {
                System.out.println("UnsupportedAudioFileException");
            }
            catch(IOException err)
            {
                System.out.println("IOException");
            }
        }
        @Override
        public void run()
        {
            try
            {
                byte[] bytes=new byte[512];
                int length=0;
                dataline.open(format);
                dataline.start();
                played=0;
                playing=true;
                stop=false;
                while(stop==false)
                {
                    if(pause==true)
                    {
                        Thread.sleep(1500);
                        continue;
                    }
                    if(pass==true)
                    {
                        if((length=aistream.read(bytes))<=0)
                        {
                            break;
                        }
                        played+=1;
                        continue;
                    }
                    if(played<play_from)
                    {
                        if((length=aistream.read(bytes))>0)
                        {
                            played+=1;
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    }
                    if((length=aistream.read(bytes))>0)
                    {
                        dataline.write(bytes, 0, length);
                        played+=1;
                    }
                    else
                    {
                        break;
                    }
                }
                stop=true;
                aistream.close();
                dataline.drain();
                dataline.close();

                aistream=null;
                format=null;
                datalineinfo=null;
                dataline = null;
            }
            catch(Exception err)
            {
                System.out.println("Error");
                err.printStackTrace();
            }
            catch(Error err)
            {
                System.out.println("Error: can not play the audio");
                err.printStackTrace();
            }
        }
    }


    public static class VoicePlayer extends Thread implements Serializable {

        private String filename;//音频文件的路径
        public VoicePlayer(String wavfile) {
            filename = wavfile;

        }
        private boolean Pause = false;
        private SourceDataLine auline;
        public void pause(){
            //auline.stop();
            this.Pause = !Pause;
        }
        public void run() {

            File soundFile = new File(filename);

            AudioInputStream audioInputStream = null;//音频流
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (Exception e1) {
                e1.printStackTrace();
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            auline.start();
            int nBytesRead = 0;

            byte[] abData = new byte[1024];//缓冲在byte中

            try {
                while (nBytesRead != -1) {
                    //已循环形式读这个流
                    if (Pause) {
                        Thread.sleep(1500);
                        continue;
                    }
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);

                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                auline.drain();
                auline.close();
            }

        }

        public static void main(String[] args) {
            setDPI(96);
            GameMsgBox("提示","不能因为点数不好，就退出重进，重新投掷点数。","你已经投掷骰子！现在退出有开挂嫌疑。","二猫云反外挂系统正在保护您的游戏公平！");

                    //VoicePlayer player=new VoicePlayer(GameResources.get没有开挂());
//            VoicePlayer player=new VoicePlayer(GameResources.getDidNotCheat());
//            player.start();
//            System.out.println("ok");
//
//            try {
//                player.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("finished");
            //        ChessBoard1.setVisible(false);

//        JButton DiceRoller =
//                new SuperButton(
//                        GameResources.getDice(DiceView.DiceType.TypeA,2),
//                        GameResources.getDice(DiceView.DiceType.TypeB,2));
//
//        ChessBoard1.add(DiceRoller);
        }

    }
}
