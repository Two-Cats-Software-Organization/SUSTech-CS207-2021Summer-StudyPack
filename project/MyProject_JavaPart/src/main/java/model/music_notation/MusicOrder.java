package model.music_notation;

public enum MusicOrder {
    零(0,0),
    一(1,261.626),
    二(2,277.183),
    三(3,293.665),
    四(4,311.127),
    五(5,329.629),
    六(6,349.228),
    七(7,369.991),
    八(8,391.995),
    九(9,415.305),
    十(10,440.000),
    十一(11,466.164),
    十二(12,493.883);
    public int order;
    public double frequency;
    MusicOrder(int order, double frequency) {
        this.order = order;
        this.frequency = frequency;
    }
    public static MusicOrder musicOfOrder(int order){
        return values()[order];
    }

    public static void main(String[] args) {
        System.out.println(musicOfOrder(0));
        System.out.println(musicOfOrder(12));
    }

    public int getOrder() {
        return order;
    }

    public double getFrequency() {
        return frequency;
    }
}
