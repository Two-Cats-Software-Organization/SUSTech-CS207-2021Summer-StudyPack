`timescale 1ns / 1ps
module project_top(
    input EGO1_Clock,EGO1_Switches_Left0, //手动控制reset
    // input EGO1_Reset, // 低电平有效reset

    input EGO1_Uart_fromPC,
    output EGO1_Uart_toPC,

    output EGO1_Audio_SD,
    output EGO1_Audio_PWM,

    output[7:0] EGO1_DigitalTubes_Enable,
    output[15:0] EGO1_DigitalTube,

    output[15:0]EGO1_Lights
    );
    // wire reset;
    // assign reset = ~EGO1_Reset; // 高有效reset
    wire [5:0] track0, track1, track2, track3;
    track_manager tm(EGO1_Clock, EGO1_Switches_Left0, EGO1_Uart_fromPC, EGO1_Uart_toPC, track0, track1, track2, track3);
    song_driver   sd(EGO1_Clock, EGO1_Switches_Left0, track0, track1, track2, track3, EGO1_Audio_PWM, EGO1_Audio_SD);
    // wire [15:0] seg_out;
    MusicView     mv(EGO1_Clock, EGO1_Switches_Left0, track0, track1, track2, track3, EGO1_DigitalTubes_Enable, EGO1_DigitalTube, EGO1_Lights);
    // assign {EGO1_DigitalTube_Left, EGO1_DigitalTube_Right} = seg_out;
endmodule
