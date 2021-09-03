
module one_track_player_sim (
);
	reg clk_6mhz = 0,clk_16hz = 0,reset = 1;
    reg [5:0] current_track;
    wire speaker;
	one_track_player otp(clk_6mhz,clk_16hz,reset,current_track,speaker);
	always #10 clk_6mhz = ~clk_6mhz;
	always #200 clk_16hz = ~clk_16hz;
	
	initial begin
	#20 reset = 0;
		current_track = 3;
	end
endmodule