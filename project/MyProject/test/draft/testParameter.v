module testParameter (
);
    parameter divider = 10000_0000 /123.423;
    reg[32:0] i = divider;
    initial begin
        $display("%d",divider);
         
        $display("%d",i);
    end
endmodule