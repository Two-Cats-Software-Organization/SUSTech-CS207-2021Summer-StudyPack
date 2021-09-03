package view.frames;

import config.ViewSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public abstract class CommonFrame extends JFrame {
    static Toolkit tool = Toolkit.getDefaultToolkit();
    CommonFrame(){
        super();
        setFrameField();//设置本身窗口的属性
        createComponent();//设置窗口拥有的控件
//        System.out.println("run to here1");
//        Do();
        createEvent();
    }

//    protected abstract void Do();

    private int BufferedX = 0;
    private int BufferedY = 0;
    protected void setFrameField(){
        setUndecorated(false);
        this.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                BufferedX = e.getX();
                BufferedY = e.getY();
            }
        });
        this.addMouseMotionListener( new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - BufferedX;
                int yy = yOnScreen - BufferedY;
                CommonFrame. this .setLocation(xx, yy);
            }
        });
        setSize(ViewSettings.WIDTH, ViewSettings.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); // Center the window
        setLayout(null);
        setVisible(true);
    }
    protected void createComponent(){

    }
    protected void createEvent(){

    }

    public static void setMiddleLocation(Container parent,JComponent me) {
        setMiddleLocation(parent,me,me.getWidth(),me.getHeight());
    }
    public static void setMiddleLocation(Container parent,JComponent me, int width, int height) {
//        Dimension parentD = parent.getSize();
        int x = 0, y = 0;
        int a =parent.getWidth(),b = parent.getHeight();
        x = (a - width) / 2;
        y = (b - height) / 2;
        me.setBounds(x,y,width,height);
    }
    private void setMiddleLocation(boolean isWindows, int width, int height) {

        int x = 0, y = 0;
        if (isWindows) {
            Dimension screenSize = tool.getScreenSize();
            int a = screenSize.width, b = screenSize.height;

            x = (a - width) / 2;
            y = (b - height) / 2;
        }
        setBounds(x, y, width, height);
    }
}

