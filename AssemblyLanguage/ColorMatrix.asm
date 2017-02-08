TITLE Color Matrix			ColorMatrix.asm
;this program displays letters and their backgrounds in different colors.

INCLUDE Irvine32.inc
.data
	prompt1		BYTE "Enter the text color value (0-15): ",0
	prompt2		BYTE "Enter the background color value (0-15): ",0
	prompt3		BYTE "Enter the beginning capital character: ",0
	test_string		BYTE "test", 0
	textColor	DWORD ?
	backColor	DWORD ?
	color DWORD 0
	character	DWORD  ?
.code
main PROC
	mov		edx, OFFSET prompt1		;put address of string into EDX
	call	WriteString				;print prompt - text colour
	call	ReadDec					;read unsigned into EAX
	mov		textColor, eax			;move EAX to textColor variable

	mov		edx, OFFSET prompt2		;put address of string2 into EDX
	call	WriteString				;print prompt - background colour
	call	ReadDec					;read unsigned into EAX
	mov		backColor, eax			;move EAX to backColor variable

	mov		edx, OFFSET prompt3		;put address of string3 into EDX
	call	WriteString				;print prompt - letter
	call	ReadChar				;read unsigned into EAX
	and		EAX,11011111b			;to upper
	mov		character, eax			;move EAX to beginning variable
	call	WriteChar
	call	Crlf

	; get the colour to the right spot
	mov ECX, backColor
	mov EAX, 0

ColorLoop:
	add EAX, 16
	loop ColorLoop
	add EAX, textColor
	mov color, EAX


	mov		ECX, 16					
	mov		EAX, color			;put chosen textcolor in EAX
	call	settextcolor		;set the text color
	;mov	EAX, character		;put character back into EAX with new color
	mov dx, 040Fh				; dh = cursor row, dl = cursor column
L1:
	call	Gotoxy				;set the cursor start  
	inc		dh					;increment the row
	push	edx					;save edx on the stack
	push ECX					; push ecx to the stack to save it
	mov ECX, 16					; get new counter for inner loop

L2:
			; Change the character

			mov		EAX, character
			call	WriteChar		; print the character
			inc		EAX				; move to the next character
			cmp		AL, 'Z'			; compare current character (Byte) to Z
			jbe		isalpha			; jump if less than or equal to Z
			mov		EAX, 'A'		; if not a letter, set back to A

isalpha:
			mov character, EAX		; store the character in character variable

			mov		EAX, color		; put chosen textcolor in EAX
			inc		EAX
			test	EAX, 00001111b
			jne		colorLabel
			sub		eax, 16

colorLabel:
			mov color, eax
			call	settextcolor	; set the text color

		loop L2						; loop the inner loop

		;call WriteHex
		add EAX, 16			
		and eax, 0ffh
		mov color, EAX
		;call WriteHex
		call SetTextColor

		call Crlf					; print the new line
		pop ECX						; get the outer loop counter back
		pop	edx						;restore edx
	loop L1
	call	Crlf

	mov		EAX, 07h
	call	SetTextColor

	exit
main ENDP
END main
