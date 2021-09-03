package draft;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import static java.awt.event.KeyEvent.VK_SEMICOLON;
import static java.awt.event.KeyEvent.VK_SHIFT;

public class key {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
//            System.out.println(KeyEvent.getKeyText(VK_SEMICOLON));
//            System.out.println(KeyStroke.getKeyStroke(VK_SEMICOLON, InputEvent.BUTTON1_MASK));
//            System.out.println(KeyStroke.getKeyStroke(VK_SEMICOLON,InputEvent.SHIFT_MASK));
            System.out.println(KeyStroke.getKeyStroke("shift released SEMICOLON")); //关闭输入法，不要全角符号，不然会有bug
            System.out.println(KeyStroke.getKeyStroke("pressed SEMICOLON"));
//            System.out.println(KeyStroke.getKeyStroke("pressed ["));
            System.out.println(KeyStroke.getKeyStroke(219, InputEvent.META_DOWN_MASK));
//            System.out.println(KeyStroke.getKeyStroke(scanner.nextLine()));
        }
//        KeyStroke ctrlBKey = KeyStroke.getKeyStroke("released B");
//        InputMap imap = this.pianoKeyBoardView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//        imap.put(ctrlBKey,"cheat");
//        ActionMap amap = this.pianoKeyBoardView.getActionMap();
//        amap.put("cheat", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("released B");
//                System.out.println(e.getSource());
//            }
//        });
//
//        KeyStroke ctrlCKey = KeyStroke.getKeyStroke("released C");
//        KeyStroke CKey = KeyStroke.getKeyStroke("pressed C");
//        imap.put(ctrlCKey,"released C");
//        imap.put(CKey,"pressed C");
//        amap.put("released C", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("released C");
//            }
//        });
//        amap.put("pressed C", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("pressed C");
//            }
//        });
    }
}
