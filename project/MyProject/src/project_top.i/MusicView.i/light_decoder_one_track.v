module light_decoder_one_track (
    input[5:0] track,
    output reg [15:0] light
);
    always @(track) begin
        light = 0;
        if (track /12 == 0 && track%12 >8) begin
            light = 0;
        end else begin
        case (track%12)
            1 : light[13-(track /12-1) *7]= 1;
            2 : light[13-(track /12-1) *7]= 1;

            3 : light[12-(track /12-1) *7]= 1;
 
            4 : light[11-(track /12-1) *7]= 1;
            5 : light[11-(track /12-1) *7]= 1;
 
            6 : light[10-(track /12-1) *7]= 1;
            7 : light[10-(track /12-1) *7]= 1;

            8 : light[9-(track /12-1) *7]= 1;

            9 : light[15-track /12 *7]= 1;
            10: light[15-track /12 *7]= 1;

            11: light[14-track /12 *7]= 1;
            0 : light[14-track /12 *7]= 1; //12 actually
            default: light= 0;
        endcase
        end
    end
endmodule