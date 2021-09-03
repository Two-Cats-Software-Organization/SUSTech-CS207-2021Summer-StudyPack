package model.piano;

import model.music_notation.MusicNote;
import model.serial.SerialPortManager;
import util.RandomUtil;

import java.io.IOException;

public class TrackManager {//自动分配音轨
    private SerialPortManager serialPortManager;

    public TrackManager(SerialPortManager serialPortManager) {
        this.serialPortManager = serialPortManager;
    }

    /**
     * invoke by key board pressed action.
     * 自动分配音轨，采取占空策略。 如果没有空位，那么新音优先于旧音。
     * 如果已经有自己的音符了，也应该去抢占音轨，这是因为一个音符可以多个键盘码。按下就是命令。
     *
     * 以后可能会改： 现在策略是抢占所有空位，然后没有写邻近发包策略，第二个音是否抢占完全是不确定的
     * @param musicNote
     * @return
     */
    public static boolean addMusicNote(MusicNote musicNote){
        musicNote.markPlayedOnce();
//        Track.零.setCurrentMusicNote(musicNote);
//        Track.一.setCurrentMusicNote(musicNote);
//        Track.二.setCurrentMusicNote(musicNote);
//        Track.三.setCurrentMusicNote(musicNote);
        boolean onTrack = false;
        for (Track t:Track.values())
            if (t.isEmpty()){
                t.setCurrentMusicNote(musicNote);
                onTrack = true;
            }
        if (!onTrack)
            Track.values()[RandomUtil.getRandomIntBetween(0,3)].setCurrentMusicNote(musicNote);
        return true;
    }

    /**
     * 自动寻音（所有的这个音）， 延时释放(释放所有, 如果一个音的占有时间长，延时就要短)。
     * invoke by key board released action.
     * @param musicNote
     */
    public static void removeMusicNote(MusicNote musicNote) {
        musicNote.resetPlayedTimes();
        for (Track t:Track.values())
            if (t.getCurrentMusicNote().equals(musicNote)) //要用equals
                t.reset();
    }

    private long musicRunCount =0; //how many 16分 音符 has been played?
    public boolean sendMusicInformation(){ //时钟调用
        try {
            serialPortManager.write(new MusicInformation());
            musicRunCount++;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void resetTrack(){
        Track.零.reset();
        Track.一.reset();
        Track.二.reset();
        Track.三.reset();
    }
    public boolean closeTrack(){
        resetTrack();
        try {
            serialPortManager.write(new MusicInformation());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
