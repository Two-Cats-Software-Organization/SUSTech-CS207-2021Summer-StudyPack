// module complied by 叶璨铭
module uart_manager ( 
    input EGO1_Clock,
    input EGO1_Reset,

    //receiver
    input EnableUart,    // a switch to enable this manager
    output isWorking,
    
    input receiveOnce,// can be a button or a fast clock, 
    //this changes the output of message received
    
    input EGO1_Uart_fromPC,
    output rx_buf_not_empty, //whether the receive buffer is not empty
    output rx_buf_full,      //whether the receive buffer is full
    output[7:0]message_received,
    //sender
    input send_btn_r,
    input [7:0]tx_send_data,                           //SW0~Sw7
    output EGO1_Uart_toPC,
    output tx_buf_not_full  //whether the send buffer is full
    );
    
endmodule