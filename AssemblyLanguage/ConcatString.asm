TITLE Concatonate Strings		ConcatStrings.asm
;Concatonates two strings together, character by character

INCLUDE Irvine32.inc
.data

prompt1 BYTE "Enter string 1: ", 0
prompt2 BYTE "Enter string 2: ", 0
msg1 BYTE "Merged String 1 and String 2: ", 0
msg2 BYTE "Merged String 2 and String 1: ", 0
globString1 BYTE 250 DUP(0)
globStrLen1 DWORD ?
globString2 BYTE 250 DUP(0)
globStrLen2 DWORD ?
globString3 BYTE 250 DUP(?)
globStrLen3 DWORD ?

temp			DWORD 2

.code
;*****************************
	StringMerge PROTO, 
	string1: DWORD,
	string1Length: DWORD,
	string2: DWORD,
	string2Length: DWORD,
	string3: DWORD
;*****************************
main PROC
	
	mov		edx,OFFSET prompt1
	call	WriteString
	mov		edx,OFFSET globString1		;point to the buffer
	mov		ecx,SIZEOF globString1		;specify max characters
	call	ReadString					;input the string
	mov		globStrLen1,eax				;number of characters

	mov		edx,OFFSET prompt2
	call	WriteString
	mov		edx,OFFSET globString2		;point to the buffer
	mov		ecx,SIZEOF globString2		;specify max characters
	call	ReadString					;input the string
	mov		globStrLen2,eax				;number of characters

	;somewhere in the main method
	 INVOKE StringMerge,
	 ADDR globString1, globStrLen1,
	 ADDR globString2, globStrLen2,
	 ADDR globString3

	;some more code
	 mov edx, OFFSET msg1
	 call WriteString
	 mov edx, OFFSET globString3
	 call WriteString
	 call Crlf
	
	 INVOKE StringMerge,
	 ADDR globString2, globStrLen2,
	 ADDR globString1, globStrLen1,
	 ADDR globString2
	
	;some more code
	 mov edx, OFFSET msg2
	 call WriteString
	 mov edx, OFFSET globString2
     call WriteString
	 call Crlf

	exit

main ENDP

;***********************************************************		
	StringMerge PROC, 
	string1: DWORD,
	string1Length: DWORD,
	string2: DWORD,
	string2Length: DWORD,
	string3: DWORD
	LOCAL array[250]:BYTE						
;***********************************************************
	mov		ESI, string1
	lea		edi, array			;load address of local array

	push	string1Length
	push	string2Length

beginning:	
	movsb							;copy bytes from ESI spot pointer to EDI spot pointer and increments both
	inc		string1                 ;look at next letter
	dec		string1length           ;deduct one from length
	cmp		string1length, 0        ;check if length is zero, jump if yes 
	je		string1end

	mov		ESI, string2            ;otherwise move string to into pointer

	movsb                           ;copy bytes from esi to edi
	inc		string2                 ;look at next character
	dec		string2length           ;decrement string length
	cmp		string2length, 0        ;check if length is zero, jump if yes
	je		string2end
	

	mov		ESI, string1            ;put string1 back into pointer
	jmp		beginning               ;go back to beginning of loop

string1end:
	mov		ESI, string2            ;put string2 into pointer
	mov		ECX, string2length      ;move remaining length of string2 into ecx
	rep		movsb                   ;repeat moving byte from esi to edi until ecx is zero
	jmp		ending                  ;jump to end

string2end:
	mov		ESI, string1            ;put string1 into pointer
	mov		ECX, string1length      ;move remaining string length into ecx
	rep		movsb                   ;repeat moving byte from esi to edit until ecx is zero

ending:
	pop		ECX
	pop		EBX

	add		ECX,EBX
	lea		esi,array			;load address of local array
	mov		edi,string3         ;move address of string3 into edi 
	rep		movsb               ;loop through string in esi, putting characters into string3 (edi)

	ret     
	StringMerge ENDP
END main