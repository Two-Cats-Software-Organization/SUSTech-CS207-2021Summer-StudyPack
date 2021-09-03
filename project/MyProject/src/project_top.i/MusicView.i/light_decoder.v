module light_decoder (
    input[5:0] track0,
    input[5:0] track1,
    input[5:0] track2, 
    input[5:0] track3, 
    output[15:0] light
);
    wire[15:0] light0,light1,light2,light3;
    light_decoder_one_track l0(track0, light0);
    light_decoder_one_track l1(track1, light1);
    light_decoder_one_track l2(track2, light2);
    light_decoder_one_track l3(track3, light3);

    assign light = light0|light1|light2|light3;
endmodule