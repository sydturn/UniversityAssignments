TITLE Queston Eleven	eleven.asm
;Implement the following C++ expression in assembly language, using 32-bit unsigned
;operands:
;val1 = (val2 * val3) / (val4 - 3)

INCLUDE Irvine32.inc
.data
val1		DWORD ?
val2		DWORD ?
val3		DWORD ?
val4		DWORD ? 
prompt 1	BYTE "Enter first unsigned value: ",0
prompt 2		BYTE "Enter second unsigned value: ",0
prompt 3		BYTE "Enter third unsigned value: ",0

.code
main PROC

mov		EDX, OFFSET prompt1				; prompt for first value
call	WriteString						; print prompt
call	ReadDec							; read first value
mov		val2, EDX						; store first value in val2S

mov		EDX, OFFSET prompt2				; prompt for second value
call	WriteString						; print prompt
call	ReadDec							; read second value
mov		val3, EDX						; store second value in val3

;if val2 = 123 and val 3 = 36 then:
;EAX * 36 = EAX * (25 + 22)
;= EAX * (32 + 4)
;= (EAX * 32) + (EAX * 4)

mov eax,123								
mov ebx,eax
shl eax,5								; multiply by 25
shl ebx,2								; multiply by 22
add eax,ebx								; add the products

;val2*val3 = eax

mov		EDX, OFFSET prompt3				; prompt for 3rd value
call	WriteString						; print prompt
call	ReadDec							; read 3rd value
sub		EAX, 3							; subtract 3 from 3rd value
mov		val4, EAX						; store 3rd value into val4

;In the following example, 64 is divided by 23

mov		al,01000000b					; AL = 64
shr		al,3							; divide by 8, AL = 00001000b


	exit
main ENDP
END main