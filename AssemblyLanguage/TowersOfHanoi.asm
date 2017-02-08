TITLE Towers of Hanoi		TowersOfHanoi.asm
;this program recreates and solves the towers of hanoi problem.

INCLUDE Irvine32.inc
.data
prompt1	BYTE "Enter the number of disks in the origin tower: ",0
Disk1	BYTE "Disk 1 from ",0
Bubbles	BYTE " to ",0
Disk	BYTE "Disk ",0
From	BYTE " from ",0
.code
main PROC
	mov		edx, OFFSET prompt1	; put address of string into EDX
	call	WriteString			; print prompt
	call	ReadDec				; read signed into EAX
	mov		ECX,EAX				; move EAX value to A
	
	mov		EAX, 'A'	; from
	mov		EBX, 'B'	; spare
	mov		EDX, 'C'	; to
   ;mov		ECX, N

	call	towers

	exit
main ENDP

towers PROC
	
	cmp		ECX, 1					; compare n to 1
	je		BaseCase				; if n == 1 jump to base case

	;towers(n - 1, from, to, spare);
	dec		ECX						; otherwise minus 1 from ecx
	xchg	EBX, EDX				; swap B and C
	call	towers					; call towers with new values

	; ??
	xchg EDX, EBX
	inc ECX

	
	;System.out.println("Disk " + n + " from " + from + " to " + to);
	push	EDX						; push EDX for future use, we need EDX for printing
	mov		edx, OFFSET disk		; put "Disk " into edx
	call	WriteString				; print "Disk "
	push	EAX						; push EAX for future use, needed for dec print
	mov		EAX, ECX				; move ECX into EAX for printing
	call	WriteDec				; print ECX
	mov		edx, OFFSET from		; put "from" into EDX
	call	WriteString				; print "from"
	pop		EAX						; put original EAX back by poping of stack
	call	WriteChar				; print EAX
	mov		EDX, OFFSET bubbles		; put "to" into EDX
	call	WriteString				; print "to"
	pop		EDX						; put original back into EDX for printing
	push	EAX						; push EAX onto stack for safe keeping
	mov		EAX, EDX				; move 'C' (EDX) onto EAX for printing
	call	WriteChar				; print 'C'
	pop		EAX						; put original EAX back

	call	Crlf

	;towers(n - 1, spare, from, to);
	dec		ECX						;subtract 1 from n
	xchg	EAX, EBX				;swap A and B
	call	towers
	
	; ?? put stuff back
	xchg	EBX, EAX
	inc		ECX
	ret

	;System.out.println("Disk 1 from " + from + " to " + to);
	BaseCase:
	push edx
	mov	edx, OFFSET Disk1		
	call WriteString			; "disk 1 from "
	call WriteChar				; print A (which is already stored in EAX)
	mov	edx, OFFSET Bubbles		; "to"
	call WriteString
	pop edx
	push eax
	mov	eax, edx				; print C
	call WriteChar				; "Disk 1 from A to C"
	pop eax
	call Crlf

	ret
towers ENDP



END main