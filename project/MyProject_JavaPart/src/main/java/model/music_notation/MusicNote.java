package model.music_notation;

import java.io.Serializable;

public class MusicNote implements Serializable {
    public static final MusicNote nothing = new MusicNote(Domain.LOW, MusicOrder.零);
    private Domain domain;//key
    private MusicOrder musicOrder;//key
    private int binaryCode;
    private double frequency;
//    private SyllableName syllableName;// 不要建立这个字段，不是必须的，会导致问题
    private int playedTimes = 0; //为音轨分配而设立
    public void markPlayedOnce(){playedTimes++;}
    public void resetPlayedTimes(){playedTimes=0;}
    public MusicNote(Domain domain, MusicOrder musicOrder, Tone tone) {
        this(domain, MusicOrder.values()[musicOrder.order+tone.ordinal()-1]);
    }
    public MusicNote(Domain domain, MusicOrder musicOrder) {
        this.domain = domain;
        this.musicOrder = musicOrder;
        frequency = musicOrder.getFrequency()* Math.pow(2,domain.ordinal());
        binaryCode = musicOrder.getOrder()==0?0: musicOrder.getOrder() +12*domain.ordinal();
    }

    public MusicNote(Domain domain, SyllableName syllableName) {
        this(domain,syllableName.getOrder());
    }
    public MusicNote(Domain domain, SyllableName syllableName, Tone tone){
        this(domain,SyllableName.values()[syllableName.ordinal()+ tone.ordinal()-1]);//即使越界，也只是不发音
        //fixme 无法跨音域，比如说低音si无法升到中音do
    }

    public Domain getDomain() {
        return domain;
    }

    public MusicOrder getMusicOrder() {
        return musicOrder;
    }

    public int getBinaryCode() {
        return binaryCode;
    }

    public double getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "MusicNote{" +
                "domain=" + domain +
                ", musicOrder=" + musicOrder +
                ", binaryCode=" + binaryCode +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicNote musicNote = (MusicNote) o;

        if (domain != musicNote.domain) return false;
        return musicOrder == musicNote.musicOrder;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (musicOrder != null ? musicOrder.hashCode() : 0);
        return result;
    }

}
