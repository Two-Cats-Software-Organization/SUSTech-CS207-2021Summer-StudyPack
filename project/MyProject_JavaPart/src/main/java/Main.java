import view.frames.GameFrame;

import javax.swing.*;

import static util.MySwingUI_Template.setDPI;

public class Main {
    public static void main(String[] args) {
//        System.out.println(System.getProperty("sun.java2d.win.uiScaleY"));
//        setDPI(96);
        setDPI(80);
        GameFrame.main(null);
    }
}
