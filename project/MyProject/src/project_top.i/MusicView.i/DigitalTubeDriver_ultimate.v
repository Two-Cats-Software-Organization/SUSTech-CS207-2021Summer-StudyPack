module DigitalTubeDriver_ultimate ( //右边开始编号
    input EGO1_Clock, reset,
    output[7:0] EGO1_DigitalTubes_Enable,
    output[15:0] EGO1_DigitalTube,

    input[7:0] text7,
    input[7:0] text6,
    input[7:0] text5,
    input[7:0] text4,
    input[7:0] text3,
    input[7:0] text2,
    input[7:0] text1,
    input[7:0] text0
);
    wire clock_delayed;
    reg[31:0] count;
    reg[2:0] scan_count;
    reg[7:0] seg_out_reg;
    reg[7:0] seg_en_reg;
    assign EGO1_DigitalTube = {seg_out_reg, seg_out_reg};
    assign EGO1_DigitalTubes_Enable = seg_en_reg;

    // divide frequency
    ClockDecelerator #(500)clockDecelerator(EGO1_Clock, reset, clock_delayed);
    //scan
    always @(posedge clock_delayed or posedge reset) begin
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

    always @(scan_count) begin
        case (scan_count)
            7: seg_en_reg = 8'b1000_0000;
            6: seg_en_reg = 8'b0100_0000;
            5: seg_en_reg = 8'b0010_0000;
            4: seg_en_reg = 8'b0001_0000;
            3: seg_en_reg = 8'b0000_1000;
            2: seg_en_reg = 8'b0000_0100;
            1: seg_en_reg = 8'b0000_0010;
            0: seg_en_reg = 8'b0000_0001;
            default: seg_en_reg = 0;
        endcase
    end
    always @(scan_count) begin
        case (scan_count)
            7: seg_out_reg = text7;
            6: seg_out_reg = text6;
            5: seg_out_reg = text5;
            4: seg_out_reg = text4;
            3: seg_out_reg = text3;
            2: seg_out_reg = text2;
            1: seg_out_reg = text1;
            0: seg_out_reg = text0;
            default: seg_out_reg=0;
        endcase
    end
endmodule