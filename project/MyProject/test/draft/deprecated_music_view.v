wire clock_delayed;
    // reg[31:0] count;
    reg[2:0] scan_count;

    // reg[7:0] seg_en_reg;
    // reg[15:0] seg_out_reg;
    // assign EGO1_DigitalTubes_Enable = seg_en_reg;
    // assign seg_out = seg_out_reg;

    // divide frequency
    // ClockDecelerator #(250)cd(clock, reset, clock_delayed);
    //原来，是因为clock变量写错了
    // ClockDecelerator #(500)cd(clock, reset, clock_delayed); //怀疑频率过快
    ClockDecelerator #(500)cd(EGO1_Clock, reset, clock_delayed); 

    //scan //问题在reset
    always @(posedge clock_delayed or posedge reset) begin //要求高电平reset
        if (reset) begin
            scan_count<=0;
            // EGO1_DigitalTubes_Enable <= 8'b0001_0000;
            EGO1_DigitalTubes_Enable <= 1;
        end
        else begin
            if(scan_count==3'b111)begin
                scan_count<=0;
                EGO1_DigitalTubes_Enable <= 1;
            end
            else begin
                scan_count <= scan_count+1;
                EGO1_DigitalTubes_Enable <= 1<<scan_count;
            end
        end
    end

    //不是，这样也有bug
    // assign EGO1_DigitalTubes_Enable = 1<<scan_count; //从右往左编号
//怀疑就是从左边开始编号导致的bug！
    // always @(scan_count) begin //将scan_count和数码管位置映射。这里我们从左边开始编号
    //     case (scan_count)
    //         0: EGO1_DigitalTubes_Enable = 8'b1000_0000;
    //         1: EGO1_DigitalTubes_Enable = 8'b0100_0000;
    //         2: EGO1_DigitalTubes_Enable = 8'b0010_0000;
    //         3: EGO1_DigitalTubes_Enable = 8'b0001_0000;
    //         4: EGO1_DigitalTubes_Enable = 8'b0000_1000;
    //         5: EGO1_DigitalTubes_Enable = 8'b0000_0100;
    //         6: EGO1_DigitalTubes_Enable = 8'b0000_0010;
    //         7: EGO1_DigitalTubes_Enable = 8'b0000_0001;
    //         default: EGO1_DigitalTubes_Enable = 0;
    //     endcase
    // end

    
    always @(scan_count) begin
        case (scan_count)
            7: EGO1_DigitalTube = {track0_view_left , track0_view_left };
            6: EGO1_DigitalTube = {track0_view_right, track0_view_right};
            5: EGO1_DigitalTube = {track1_view_left , track1_view_left };
            4: EGO1_DigitalTube = {track1_view_right, track1_view_right};
            3: EGO1_DigitalTube = {track2_view_left , track2_view_left };
            2: EGO1_DigitalTube = {track2_view_right, track2_view_right};
            1: EGO1_DigitalTube = {track3_view_left , track3_view_left };
            0: EGO1_DigitalTube = {track3_view_right, track3_view_right};
            default: EGO1_DigitalTube=0;
        endcase
        
    end