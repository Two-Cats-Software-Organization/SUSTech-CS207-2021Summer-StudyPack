// simulation only
module MusicView_for_sim (
    input EGO1_Clock,reset, // 高有效reset
    input[5:0] track0,
    input[5:0] track1,
    input[5:0] track2, 
    input[5:0] track3, 

    output reg[7:0] seg_en,
    output reg[15:0] seg_out
);
    wire clock_delayed;
    // reg[31:0] count;
    reg[2:0] scan_count;

    // reg[7:0] seg_en_reg;
    // reg[15:0] seg_out_reg;
    // assign seg_en = seg_en_reg;
    // assign seg_out = seg_out_reg;

    // divide frequency
    // ClockDecelerator #(500)cd(clock, reset, clock_delayed);

    //scan
    always @(posedge EGO1_Clock or posedge reset) begin
        if (reset) begin
            scan_count<=0;
        end
        else begin
            if(scan_count==3'b111)
                scan_count<=0;
            else
                scan_count <= scan_count+1;
        end
    end

    always @(scan_count) begin //将scan_count和数码管位置映射。这里我们从左边开始编号
        case (scan_count)
            0: seg_en = 8'b1000_0000;
            1: seg_en = 8'b0100_0000;
            2: seg_en = 8'b0010_0000;
            3: seg_en = 8'b0001_0000;
            4: seg_en = 8'b0000_1000;
            5: seg_en = 8'b0000_0100;
            6: seg_en = 8'b0000_0010;
            7: seg_en = 8'b0000_0001;
            default: seg_en = 0;
        endcase
    end

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
    
    always @(scan_count) begin
        case (scan_count)
            0: seg_out = {track0_view_left , track0_view_left };
            1: seg_out = {track0_view_right, track0_view_right};
            2: seg_out = {track1_view_left , track1_view_left };
            3: seg_out = {track1_view_right, track1_view_right};
            4: seg_out = {track2_view_left , track2_view_left };
            5: seg_out = {track2_view_right, track2_view_right};
            6: seg_out = {track3_view_left , track3_view_left };
            7: seg_out = {track3_view_right, track3_view_right};
            default: seg_out=0;
        endcase
        
    end
endmodule