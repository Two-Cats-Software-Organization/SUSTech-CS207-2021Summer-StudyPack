module MusicViewSim (
);
    reg EGO1_Clock = 0, reset = 1;
    always #5 EGO1_Clock = ~EGO1_Clock;
    reg[5:0] track0=0, track1=0, track2=0, track3=0;

    wire[7:0] EGO1_DigitalTubes_Enable;
    wire[15:0] seg_out;
    MusicView_for_sim     mv(EGO1_Clock, reset, track0, track1, track2, track3, EGO1_DigitalTubes_Enable, seg_out);
    initial begin
        #10 reset = 0;
        track0 = 5'b0000_0010;
        track1 = 5'b0000_0001;
    end
endmodule