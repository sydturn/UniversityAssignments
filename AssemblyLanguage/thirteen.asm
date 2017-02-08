TITLE Question Thirteen		thirteen.asm
;Write a procedure that displays an unsigned 8-bit binary value in decimal format. Pass the
;binary value in AL. The input range is limited to 0 to 99, decimal. The only procedure you
;can call from the book’s link library is WriteChar. The procedure should contain approximately
;eight instructions. Here is a sample call:
;mov al,65 ; range limit: 0 to 99
;call showDecimal8

INCLUDE Irvine32.inc
.data

.code
main PROC

	exit
main ENDP
END main