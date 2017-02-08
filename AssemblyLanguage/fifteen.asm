TITLE Question Thirteen					QuestionThirteen.asm
; this program converts and displays a binary value in decimal format

INCLUDE Irvine32.inc
.data

.code
main PROC

	mov		al,00001000b			; range limit: 0 to 99 (change number here for conversion)
	call	showDecimal8			;I didn't put in read or write b/c I'm not allowed to call those according to the question


 exit
main ENDP

showDecimal8 PROC
	mov			EDX, 0
	movzx		EAX, AL
	mov			EBX, 10d
	div			EBX					; divide AL by 10
	add			EAX, '0'			; Add character 0 to quotient to turn to char
	call		WriteChar			; print quotient
	mov			EAX, EDX			; move remainder into EAX
	add			EAX, '0'			; add character 0 to remainder to turn to char
	call		WriteChar			; write character
	call		Crlf

	ret
showDecimal8	ENDP

END main
