module testTrack (
);
    reg [5:0] track[3:0];
    initial begin
        track[0] = 5'b000100;
        track[1] = 5'b000100;
        track[2] = 5'b000100;
        track[3] = 5'b000100;
        $display(track[0]);
        $display(track[1]);
        $display(track[2]);
        $display(track[3]);
    end 
endmodule