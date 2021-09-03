package view;

import config.ViewSettings;
import model.music_notation.*;
import model.piano.TrackManager;
import util.MySwingUI_Template;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.util.Objects;

public class PianoKeyBoardView extends MySwingUI_Template.PicturePanel{
    public PianoKeyBoardView(ImageIcon imageIcon) {
        super(imageIcon);
        setBounds(0,0, ViewSettings.WIDTH, ViewSettings.HEIGHT);
//        registerPianoBoardListener("Z",new MusicNote(Domain.LOW, SyllableName.Do));
        registerPianoBoardListener("A",         Domain.LOW, SyllableName.Do);
        registerPianoBoardListener("COMMA",     Domain.LOW, SyllableName.Do);
        registerPianoBoardListener("LEFT",      Domain.LOW, SyllableName.Do);

        registerPianoBoardListener("S",         Domain.LOW, SyllableName.Re);
        registerPianoBoardListener("PERIOD",    Domain.LOW, SyllableName.Re);
        registerPianoBoardListener("DOWN",      Domain.LOW, SyllableName.Re);

        registerPianoBoardListener("D",         Domain.LOW, SyllableName.Mi);
        registerPianoBoardListener("SLASH",     Domain.LOW, SyllableName.Mi);
        registerPianoBoardListener("RIGHT",     Domain.LOW, SyllableName.Mi); //注意，right不会有command

        registerPianoBoardListener("F",         Domain.LOW, SyllableName.Fa);
        registerPianoBoardListener("UP",        Domain.LOW, SyllableName.Fa);

        registerPianoBoardListener("G",         Domain.LOW, SyllableName.So);
        registerPianoBoardListener("NUMPAD0",   Domain.LOW, SyllableName.So);

        registerPianoBoardListener("H",         Domain.LOW, SyllableName.La);
        registerPianoBoardListener("DECIMAL",   Domain.LOW, SyllableName.La);

        registerPianoBoardListener("J",         Domain.LOW, SyllableName.Si);
        registerPianoBoardListener("ENTER",     Domain.LOW, SyllableName.Si);

        registerPianoBoardListener("Q",         Domain.MIDDLE, SyllableName.Do);
        registerPianoBoardListener("K",         Domain.MIDDLE, SyllableName.Do);
        registerPianoBoardListener("NUMPAD1",   Domain.MIDDLE, SyllableName.Do);

        registerPianoBoardListener("W",         Domain.MIDDLE, SyllableName.Re);
        registerPianoBoardListener("L",         Domain.MIDDLE, SyllableName.Re);
        registerPianoBoardListener("NUMPAD2",   Domain.MIDDLE, SyllableName.Re);

        registerPianoBoardListener("E",         Domain.MIDDLE, SyllableName.Mi);
        registerPianoBoardListener("SEMICOLON", Domain.MIDDLE, SyllableName.Mi);
        registerPianoBoardListener("NUMPAD3",   Domain.MIDDLE, SyllableName.Mi);

        registerPianoBoardListener("R",         Domain.MIDDLE, SyllableName.Fa);
        registerPianoBoardListener("QUOTE",     Domain.MIDDLE, SyllableName.Fa);
        registerPianoBoardListener("NUMPAD4",   Domain.MIDDLE, SyllableName.Fa);

        registerPianoBoardListener("T",         Domain.MIDDLE, SyllableName.So);
        registerPianoBoardListener("NUMPAD5",   Domain.MIDDLE, SyllableName.So);

        registerPianoBoardListener("Y",         Domain.MIDDLE, SyllableName.La);
        registerPianoBoardListener("NUMPAD6",   Domain.MIDDLE, SyllableName.La);

        registerPianoBoardListener("U",         Domain.MIDDLE, SyllableName.Si);
        registerPianoBoardListener("NUMPAD7",   Domain.MIDDLE, SyllableName.Si);

        registerPianoBoardListener("1",         Domain.HIGH, SyllableName.Do);
        registerPianoBoardListener("I",         Domain.HIGH, SyllableName.Do);
        registerPianoBoardListener("NUMPAD8",   Domain.HIGH, SyllableName.Do);

        registerPianoBoardListener("2",         Domain.HIGH, SyllableName.Re);
        registerPianoBoardListener("O",         Domain.HIGH, SyllableName.Re);
        registerPianoBoardListener("NUMPAD9",   Domain.HIGH, SyllableName.Re);

        registerPianoBoardListener("3",         Domain.HIGH, SyllableName.Mi);
        registerPianoBoardListener("P",         Domain.HIGH, SyllableName.Mi);
        registerPianoBoardListener("ADD",       Domain.HIGH, SyllableName.Mi);

        registerPianoBoardListener("4",         Domain.HIGH, SyllableName.Fa);
        registerPianoBoardListener(219,         Domain.HIGH, SyllableName.Fa);
        registerPianoBoardListener("NUM_LOCK",  Domain.HIGH, SyllableName.Fa);

        registerPianoBoardListener("5",         Domain.HIGH, SyllableName.So);
        registerPianoBoardListener(221,         Domain.HIGH, SyllableName.So);
        registerPianoBoardListener("DIVIDE",  Domain.HIGH, SyllableName.So);

        registerPianoBoardListener("6",         Domain.HIGH, SyllableName.La);
        registerPianoBoardListener("MULTIPLY",  Domain.HIGH, SyllableName.La);

        registerPianoBoardListener("7",         Domain.HIGH, SyllableName.Si);
        registerPianoBoardListener("SUBTRACT",  Domain.HIGH, SyllableName.Si);
    }


    /**
     * @author 叶璨铭
     * @param keyName 根据Java标准来，比如SEMICOLON是有效的, ;是无效的。 C是有效的，c是无效的
     * @param domain,syllableName 要绑定的音符。注意升降调、延音不用指定，本方法自动生成对应键音对 对应的升降调和延音 键音对。
     *
     * 这个方法可以自动防御，如果按下的键过低，会绑定到Nothing这个音符，Music order 为0
     * 注意//!! 不要打开输入法，如果是全角符号，就会干扰钢琴运行！java无法绑定全角符号
     */
    private void registerPianoBoardListener(String keyName, Domain domain, SyllableName syllableName){
//        registerPianoBoardListener(keyName, new MusicNote(domain, syllableName));
        registerPianoBoardPressedListener(KeyStroke.getKeyStroke("pressed "+keyName)       ,   new MusicNote(domain, syllableName));
        registerPianoBoardReleasedListener(KeyStroke.getKeyStroke("released "+keyName)     ,   new MusicNote(domain, syllableName));

        registerPianoBoardPressedListener(KeyStroke.getKeyStroke("ctrl pressed " + keyName),   new MusicNote(domain, syllableName, Tone.FALLING));
        //可能有问题，万一用户先释放了ctrl 再释放c，就会导致对应音无法释放
        registerPianoBoardReleasedListener(KeyStroke.getKeyStroke("ctrl released " + keyName), new MusicNote(domain, syllableName, Tone.FALLING));

        registerPianoBoardPressedListener(KeyStroke.getKeyStroke("shift pressed " + keyName),  new MusicNote(domain, syllableName, Tone.RISING));
        registerPianoBoardReleasedListener(KeyStroke.getKeyStroke("shift released " + keyName),new MusicNote(domain, syllableName, Tone.RISING));

    }
    private void registerPianoBoardListener(int keyCode, Domain domain, SyllableName syllableName) {
        registerPianoBoardPressedListener(KeyStroke.getKeyStroke(keyCode, InputEvent.META_DOWN_MASK, false),   new MusicNote(domain, syllableName));
        registerPianoBoardReleasedListener(KeyStroke.getKeyStroke(keyCode, InputEvent.META_DOWN_MASK, true),   new MusicNote(domain, syllableName));

        registerPianoBoardPressedListener(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK, false),   new MusicNote(domain, syllableName, Tone.FALLING));
        registerPianoBoardReleasedListener(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK, true),   new MusicNote(domain, syllableName, Tone.FALLING));

        registerPianoBoardPressedListener(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK, false),  new MusicNote(domain, syllableName, Tone.RISING));
        registerPianoBoardReleasedListener(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK, true),  new MusicNote(domain, syllableName, Tone.RISING));
    }

    @Deprecated
    private void registerPianoBoardListener(String keyName, MusicNote musicNote){
        registerPianoBoardPressedListener(KeyStroke.getKeyStroke("pressed "+keyName), musicNote);
        try {
            registerPianoBoardPressedListener(KeyStroke.getKeyStroke("ctrl pressed " + keyName), new MusicNote(musicNote.getDomain(), musicNote.getMusicOrder(), Tone.FALLING));
        } catch (Exception e) {
            System.out.println("绑定的音符过低，程序继续");
        }
        try {
            registerPianoBoardPressedListener(KeyStroke.getKeyStroke("shift pressed " + keyName), new MusicNote(musicNote.getDomain(), musicNote.getMusicOrder(), Tone.RISING));
        } catch (Exception e) {
            System.out.println("绑定的音符过高，程序继续");
        }
    }

    //don't use us directly, we only count for one key, one note. pressed count for pressed, released count for released.
    private void registerPianoBoardPressedListener(KeyStroke keyStroke, MusicNote musicNote){
        InputMap imap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        imap.put(keyStroke,musicNote.getBinaryCode()+"pressed");//多个按键对应一个code。
        ActionMap actionMap = this.getActionMap();
        actionMap.put(musicNote.getBinaryCode()+"pressed", new PianoKeyPressedAction(musicNote)); //一一映射
    }
    private void registerPianoBoardReleasedListener(KeyStroke keyStroke, MusicNote musicNote){
        InputMap imap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        imap.put(keyStroke,musicNote.getBinaryCode()+"released");//多个按键对应一个code。
        ActionMap actionMap = this.getActionMap();
        actionMap.put(musicNote.getBinaryCode()+"released", new PianoKeyReleasedAction(musicNote)); //一一映射
    }


    private static class PianoKeyReleasedAction extends AbstractAction{
        private MusicNote musicNote;
        public PianoKeyReleasedAction(MusicNote musicNote) {
            super();
            this.musicNote = musicNote;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand()+" released"); //可以获得具体输入了什么。
            System.out.println(musicNote);
            TrackManager.removeMusicNote(musicNote);//自动寻音（所有的这个音）， 延时释放(释放所有, 如果一个音的占有时间长，延时就要短)。
        }
    }
    private static class PianoKeyPressedAction extends AbstractAction{
        private MusicNote musicNote;
        public PianoKeyPressedAction(MusicNote musicNote) {
            super();
            this.musicNote = musicNote;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand()+" pressed"); //可以获得具体输入了什么。
            System.out.println(musicNote);
            TrackManager.addMusicNote(musicNote);//自动分配音轨，采取占空策略， 如果已经有自己的音符了，就不会抢占别的音轨。 发包策略不同。
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PianoKeyPressedAction that = (PianoKeyPressedAction) o;

            return Objects.equals(musicNote, that.musicNote);
        }

        @Override
        public int hashCode() {
            return musicNote != null ? musicNote.hashCode() : 0;
        }
    }
}
