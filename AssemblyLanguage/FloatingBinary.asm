;Write a procedure that receives a single-precision floating-point binary value and displays it in
;the following format: sign: display  or ; significand: binary floating-point, prefixed by “1.”;;exponent: display in decimal, unbiased, preceded by the letter E and the exponent’s sign.
;Sample:
;.data
;sample REAL4 -1.75
;Displayed output: -1.11000000000000000000000 E+0
;Create a procedure to print the value as described in the textbook. The procedure could be
;named printRealValue. Prompt the user for the number until the user enters 0. Just before
;leaving display the following values using your printRealValue procedure: -0, +0, positive
;infinity, negative infinity, QNaN, and SNaN. You may need to manually load values for the last
;two from predefined variables. 

TITLE Floating Point Binary			FloatingPointBinary.asm
;This is a procedure that receives a single-precision floating-point binary value and displays it

INCLUDE Irvine32.inc
.data


.code
main PROC

	exit
main ENDP
END main