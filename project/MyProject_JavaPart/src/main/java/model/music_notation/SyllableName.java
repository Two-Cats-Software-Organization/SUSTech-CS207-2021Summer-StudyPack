package model.music_notation;

public enum SyllableName {
    NothingFlat(Tone.FALLING,'0', MusicOrder.零),
    Nothing(Tone.NORMAL,'0', MusicOrder.零),
    NothingSharp(Tone.RISING,'0', MusicOrder.零),

    Do(Tone.NORMAL, 'C', MusicOrder.一),
    DoSharp(Tone.RISING, 'C', MusicOrder.二),

    ReFlat(Tone.FALLING, 'D', MusicOrder.二),
    Re(Tone.NORMAL, 'D', MusicOrder.三),
    ReSharp(Tone.RISING, 'D', MusicOrder.四),

    MiFlat(Tone.FALLING, 'E', MusicOrder.四),
    Mi(Tone.NORMAL, 'E', MusicOrder.五),
    MiSharp(Tone.RISING, 'E', MusicOrder.六),

    FaFlat(Tone.FALLING, 'F', MusicOrder.五),
    Fa(Tone.NORMAL, 'F', MusicOrder.六),
    FaSharp(Tone.RISING, 'F', MusicOrder.七),

    SoFlat(Tone.FALLING, 'G', MusicOrder.七),
    So(Tone.NORMAL, 'G', MusicOrder.八),
    SoSharp(Tone.RISING, 'G', MusicOrder.九),

    LaFlat(Tone.FALLING, 'A', MusicOrder.九),
    La(Tone.NORMAL, 'A', MusicOrder.十),
    LaSharp(Tone.RISING, 'A', MusicOrder.十一),

    SiFlat(Tone.FALLING, 'B', MusicOrder.十一),
    Si(Tone.NORMAL, 'B', MusicOrder.十二),

    NothingTooHigh(Tone.RISING,'0', MusicOrder.零); //防范越界，比如按下shift键。
    private Tone tone;
    private char letter;
    private MusicOrder order;

    SyllableName(Tone tone, char letter, MusicOrder order) {
        this.tone = tone;
        this.letter = letter;
        this.order = order;
    }

    public Tone getTone() {
        return tone;
    }

    public char getLetter() {
        return letter;
    }

    public MusicOrder getOrder() {
        return order;
    }
}
