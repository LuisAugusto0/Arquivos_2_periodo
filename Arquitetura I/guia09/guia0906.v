// -------------------------
// Guia_0906
// Nome: Luís Augusto Lima de Oliveira
// Matricula: 805413
// -------------------------

`include "clock.v"
 
module pulse1 ( signal, clock );
    input clock;
    output signal;
    reg signal;
 
 
    initial signal = 1'b0;
    always @(negedge clock) begin
        signal = ~signal;
        #4 signal = ~signal;
    end
endmodule // pulse1
 
module Guia_0906;
    wire clock;
    clock clk ( clock );
    wire p1;
    pulse1 pls1 ( p1, clock );

      initial begin
        $dumpfile ( "Guia_0906.vcd" );
        $dumpvars ( 1, clock, p1);
        #480 $finish;
    end
endmodule // Guia_0906

