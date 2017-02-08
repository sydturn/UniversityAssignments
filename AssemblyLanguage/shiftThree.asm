TITLE Quesion Seven		shiftThree.asm
;Write a sequence of instructions that shift three memory bytes to the right by 1 bit position.

INCLUDE Irvine32.inc
.data
array BYTE 81h,20h,33h

.code
main PROC

	mov		ESI, OFFSET array
	call	DumpRegs
	add		esi, TYPE WORD
 	SHR		[esi], BYTE PTR			; high byte
	add		esi, TYPE BYTE
	RCR		[esi],BYTE PTR			; middle byte, include Carry flag
	RCR		[esi],BYTE PTR			; low byte, include Carry flag
	;do it backwards to shift right

	exit
main ENDP
END main