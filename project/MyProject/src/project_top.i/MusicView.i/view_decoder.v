module view_decoder (
    input[3:0] music_view_code,
    output reg [7:0]seg_out
);
    always @(music_view_code) begin
        case (music_view_code)
            0 :  seg_out = 8'b1111_1100 ; //显示0 或者休止符， 不是啥也没有
            1 :  seg_out = 8'b0011_1110 ; //小b表示降调
            2 :  seg_out = 8'b0110_0010 ; //-|表示升调

            3 :  seg_out = 8'b1001_1100 ; //C
            4 :  seg_out = 8'b0111_1010 ; //D
            5 :  seg_out = 8'b1001_1110 ; //E
            6 :  seg_out = 8'b1000_1110 ; //F
            7 :  seg_out = 8'b1011_1110 ; //G
            8 :  seg_out = 8'b1110_1110 ; //A
            9 :  seg_out = 8'b0011_1110 ; //B
            10:  seg_out = 8'b0000_0000 ; //nothing, 比如没有升降调。 这是什么都不显示。
            11:  seg_out = 8'b0000_0000 ;
            12:  seg_out = 8'b0000_0000 ;
            13:  seg_out = 8'b0000_0000 ;
            14:  seg_out = 8'b0000_0000 ;
            15:  seg_out = 8'b0000_0000 ;
            default: seg_out =0;
        endcase
    end
endmodule