module music_decoder (
    input[5:0] track,
    output reg[3:0] music_view_code_left,
    output reg[3:0] music_view_code_right
);
    //10 表示没有，2表示升，1表示降调
    always @(track) begin
        if (track == 0) begin
            music_view_code_left = 10;//左边不显示，右边才是休止符
        end else begin
        case (track%12)
            1 : music_view_code_left = 10;
            2 : music_view_code_left = 2;   
            3 : music_view_code_left = 10;
            4 : music_view_code_left = 1;
            5 : music_view_code_left = 10;
            6 : music_view_code_left = 10;
            7 : music_view_code_left = 2;
            8 : music_view_code_left = 10;
            9 : music_view_code_left = 1;
            10: music_view_code_left = 10;
            11: music_view_code_left = 1;
            0 : music_view_code_left = 10; //case:12 actually
            default: music_view_code_left = 10;
        endcase
        end
        
    end
    always @(track) begin
        if (track == 0) begin
            music_view_code_right = 0;
        end else begin
        case (track%12)
            1 : music_view_code_right= 3;
            2 : music_view_code_right= 3;
            3 : music_view_code_right= 4;

            4 : music_view_code_right= 5;
            5 : music_view_code_right= 5;
            6 : music_view_code_right= 6;

            7 : music_view_code_right= 6;
            8 : music_view_code_right= 7;
            9 : music_view_code_right= 8;

            10: music_view_code_right= 8;
            11: music_view_code_right= 9;
            0 : music_view_code_right= 9; //12 actually
            default: music_view_code_right= 0;
        endcase
        end
    end
endmodule