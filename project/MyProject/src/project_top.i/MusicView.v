// CSE
module MusicView (
    input EGO1_Clock,reset, // 高有效reset
    input[5:0] track0,
    input[5:0] track1,
    input[5:0] track2, 
    input[5:0] track3, 

    output [7:0] EGO1_DigitalTubes_Enable,
    output [15:0] EGO1_DigitalTube,
    output [15:0] EGO1_Lights
);
    wire[7:0] track0_view_left,track1_view_left,track2_view_left,track3_view_left;
    wire[7:0] track0_view_right,track1_view_right,track2_view_right,track3_view_right;

    wire[3:0] music_view_code_left0,music_view_code_left1,music_view_code_left2,music_view_code_left3;
    wire[3:0] music_view_code_right0,music_view_code_right1,music_view_code_right2,music_view_code_right3;

    music_decoder md0(track0, music_view_code_left0, music_view_code_right0);
    music_decoder md1(track1, music_view_code_left1, music_view_code_right1);
    music_decoder md2(track2, music_view_code_left2, music_view_code_right2);
    music_decoder md3(track3, music_view_code_left3, music_view_code_right3);

    view_decoder  vd0_l(music_view_code_left0,track0_view_left); 
    view_decoder  vd0_r(music_view_code_right0,track0_view_right);

    view_decoder  vd1_l(music_view_code_left1,track1_view_left); 
    view_decoder  vd1_r(music_view_code_right1,track1_view_right);

    view_decoder  vd2_l(music_view_code_left2,track2_view_left); 
    view_decoder  vd2_r(music_view_code_right2,track2_view_right);

    view_decoder  vd3_l(music_view_code_left3,track3_view_left); 
    view_decoder  vd3_r(music_view_code_right3,track3_view_right);

    DigitalTubeDriver_ultimate dtdu(EGO1_Clock, reset, EGO1_DigitalTubes_Enable, EGO1_DigitalTube, 
    track0_view_left, track0_view_right, track1_view_left, track1_view_right, track2_view_left, track2_view_right, track3_view_left, track3_view_right);
    light_decoder ld(track0, track1, track2,track3, EGO1_Lights);
endmodule