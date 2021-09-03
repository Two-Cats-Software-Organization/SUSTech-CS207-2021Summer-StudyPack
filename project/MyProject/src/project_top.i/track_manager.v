module track_manager (
    input EGO1_Clock,reset,
    input EGO1_Uart_fromPC,
    output EGO1_Uart_toPC,

    output reg [5:0] track0,
    output reg [5:0] track1,
    output reg [5:0] track2,
    output reg [5:0] track3
);
    wire[7:0] data_received;
    uart_manager um(EGO1_Clock, ~reset, EGO1_Uart_fromPC, EGO1_Uart_toPC, data_received);

    //um提供data_received，这里提供音轨. 需要异步清零
    //这个是对的，下面的那个实现是错的，下面那个没有实际生成寄存器，所以音轨赋值会干扰
    always @(posedge EGO1_Clock or posedge reset) begin 
        if (reset) begin
            track0 <= 0;
            track1 <= 0;
            track2 <= 0;
            track3 <= 0;
        end else
        case (data_received[7:6])
            0: track0 <= data_received[5:0];
            1: track1 <= data_received[5:0];
            2: track2 <= data_received[5:0];
            3: track3 <= data_received[5:0];
        endcase
    end
    // always @(data_received or reset) begin //bug: 截断了，如果有另外一个音轨有信息，原本音轨会被破坏
    //     if (reset) begin
    //         track0 = 0;
    //         track1 = 0;
    //         track2 = 0;
    //         track3 = 0;
    //     end else
    //     case (data_received[7:6])
    //         0: track0 = data_received[5:0];
    //         1: track1 = data_received[5:0];
    //         2: track2 = data_received[5:0];
    //         3: track3 = data_received[5:0];
    //     endcase
    // end
endmodule