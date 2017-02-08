TITLE Floating Point Binary			FloatingPointBinary.asm

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
;This is a procedure that receives a single-precision floating-point binary value and displays it

INCLUDE Irvine32.inc

.data

	prompt1  BYTE "Enter a single-precision floating-point binary value: ",0
	onePoint BYTE "1.",0
	eOh		 BYTE "E",0
	binNum	 DWORD ?

.code
main PROC

beginning:
	mov		EDX, OFFSET prompt1
	call	WriteString
	call	ReadFloat					;read float
	FST  	binNum						;store into DWORD to turn to binary rep.
	FLDZ								;0, binNum
	FCOMP								;compare 0 and pop
	FNSTSW	ax							;move flag stuff to ah
	SAHF								;move ah to flag
	je		theEnding					;jump to final stage if user entered 0
	call	BinConversion				;else call BinConversion
	jmp		beginning					;loop back to start

theEnding:
	
	mov		binNum, 00000000000000000000000000000000b	;Positive Zero
	call	BinConversion

	mov		binNum, 10000000000000000000000000000000b	;Negative Zero
	call	BinConversion

	mov		binNum, 01111111100000000000000000000000b	;positive infinity
	call	BinConversion

	mov		binNum, 11111111100000000000000000000000b	;negative infinity
	call	BinConversion

	mov		binNum, 01111111110000000000000000000000b	;QNaN
	call	BinConversion

	mov		binNum, 11111111101111111111111111111111b	;SNaN
	call	BinConversion

	exit

main ENDP

BinConversion PROC

mov		EBX, binNum
	shl		EBX, 1				;shift left once to get sign
	jnc		positive			;if the carry flag is not set, jump to printing plus sign

	mov		AL, '-'
	call	WriteChar			;print a negative symbol for signed bit
	jmp		moveon
positive:
	mov		AL, '+'
	call	WriteChar			;print a positive symbol for signed bit

moveOn:
	mov		EDX, OFFSET onePoint
	call	WriteString			;print 1.
	SHL		EBX, 8				;remove the mantassia
	mov		ECX, 23				;loop through the rest of the binary number 23 times

	L1:
		SHL	EBX, 1				;shift left
		JNC continuing			;check if carry flag was set by shift, if so print 1, if not jump
		mov	AL, '1'
		call WriteChar
		jmp	finishing

continuing:
		mov	AL, '0'				;carry flag was not set therefore number was a 0, print 0
		call WriteChar

finishing:
	loop L1

	mov al, ' '
	call writechar				;print a space to signal beginning of exponent

	mov	EDX, OFFSET eOh
	call WriteString

	mov	EAX, binNum				;put original number into eax
	SHL	EAX, 1					;shift left one to remove signed bit
	SHR EAX, 24					;shift left 24 times (23 plus the one left shift we did)
	sub EAX, 127d				;subtract 127 from exponent
	call WriteInt				;print exponent
	
	call Crlf

	ret
	BinConversion ENDP

END main