TITLE Assignment Six					Assignment6.asm

INCLUDE Irvine32.inc
.data
array SDWORD 10 DUP(0)
op1		BYTE "1- Populate the array with random numbers",0
op2		BYTE "2- Multiply the array with a user provided multiplier",0
op3		BYTE "3- Divide the array with a user provided divisor",0
op4		BYTE "4- Mod the array with a user provided divisor",0
op5		BYTE "5- Print the array",0
op0		BYTE "0- Exit",0
uInp	DWORD ?
prompt1	BYTE "Enter upper parameter: ",0
prompt2	BYTE "Enter lower parameter: ",0
prompt3	BYTE "Enter multiplier: ",0
prompt4	BYTE "Enter divisor parameter: ",0


a	DWORD 5
b	DWORD 10
openBrace	BYTE "{",0
closeBrace	BYTE "}",0
divider	BYTE	", ",0

.code
;*****************************
	populateArray PROTO, 
	lower: DWORD,
	upper: DWORD,
	arrayPointer: DWORD,
	arrayLength: DWORD
;*****************************
;*****************************
	printArray PROTO,
	arrayPointer: DWORD,
	arrayLength: DWORD
;*****************************

main PROC
call	Randomize
start:

	call printMenu
	cmp		EAX, 0							;compare returned value to 0
	je		endProgram						;jump to exit if equal
	
	cmp		EAX, 1							;compare returned value to 1
	je		Option1							;jump to option 1 if equal
	
	cmp		EAX, 2							;compare returned value to 2
	je		Option2							;jump to option 2 if equal
	
	cmp		EAX, 3							;compare returned value to 3
	je		Option3							;jump to option 3 if equal
	
	cmp		EAX, 4							;compare returned value to 4
	je		Option4							;jump to option 4 if equal
	
	cmp		EAX, 5							;compare returned value to 5
	je		Option5							;jump to option 5 if equal

Option1:
	mov		EDX, OFFSET prompt2				;prompt for upper parameter
	call	WriteString
	call	ReadInt
	mov		a, EAX

	mov		EDX, OFFSET prompt1				;prompt fot lower parameter
	call	WriteString
	call	ReadInt	
	mov		b, EAX

	INVOKE	populateArray, a, b, OFFSET array, LENGTHOF array
	jmp		start

Option2:
	push	LENGTHOF array
	push	OFFSET array			;pushes the address of the array onto the stack
	mov		EDX, OFFSET prompt3
	call	WriteString
	call	ReadInt					;reads multiplier
	push	EAX						;pushes multiplier onto stack
	call	multArray				;multiplies array with multiplier
	jmp		start

Option3:
	push	LENGTHOF array
	push	OFFSET array			;pushes the address of the array onto the stack
	mov		EDX, OFFSET prompt4
	call	WriteString
	call	ReadInt					;reads divisor
	push	EAX						;pushes divisor onto stack
	call	divArray
	jmp		start

Option4:
	push	LENGTHOF array
	push	OFFSET array			;pushes the address of the array onto the stack
	mov		EDX, OFFSET prompt4
	call	WriteString
	call	ReadInt					;reads divisor
	push	EAX						;pushes divisor onto stack
	call	modArray
	jmp		start

Option5:
	INVOKE	printArray, OFFSET array, LENGTHOF array
	jmp		start

endProgram:
 exit
main ENDP

;***********************************************************
;*			 print menu and accept user input			   *
;*			     return user input in EAX				   *
;***********************************************************

printMenu PROC USES edx

	mov		EDX, OFFSET op1
	call	WriteString
	call	Crlf

	mov		EDX, OFFSET op2
	call	WriteString
	call	Crlf

	mov		EDX, OFFSET op3
	call	WriteString
	call	Crlf

	mov		EDX, OFFSET op4
	call	WriteString
	call	Crlf

	mov		EDX, OFFSET op5
	call	WriteString
	call	Crlf

	mov		EDX, OFFSET op0
	call	WriteString
	call	Crlf

	call	readDec
	mov		uInp, EAX

	ret
printMenu ENDP

;***********************************************************
;   Option 1 - populate array with random numbers		
	populateArray PROC,
	lower: DWORD,
	upper: DWORD,
	arrayPointer: DWORD,
	arrayLength: DWORD									
;***********************************************************
	pushfd

	sub		ESP, 4							;make room for local variable DWORD
	mov		EAX, upper
	sub		EAX, lower
	mov		[EBP-4], EAX					;put difference into local variable
	mov		ECX, arrayLength
	mov		ESI, arrayPointer

	populateLoop:
		mov		EAX, DWORD PTR [EBP-4]		;put difference in eax(upper of random)
		call	RandomRange
		add		EAX, lower
		mov		[ESI], EAX
		add		ESI, TYPE SDWORD

	loop populateLoop

	popfd
	ret     
populateArray ENDP

;***********************************************************
;*	Option 2 - Multiply array with provided multiplier	   
;*  reads multiplier off stack, then offset off stack	
	multArray PROC USES ecx eax esi edx
	LOCAL multiplier: DWORD  
;***********************************************************
	
	mov eax, [ebp+8]
	mov multiplier, eax
	mov esi, [ebp+12]
	mov	ecx, [ebp+16]

	multiplyArray:
		mov		EBX, DWORD PTR [esi]
		imul	EBX
		mov		[ESI], EAX
		add		ESI, TYPE SDWORD
		mov		EAX, multiplier

	loop multiplyArray

	

	ret
multArray ENDP

;***********************************************************
;*	Option 3 - divide array with provided divisor	       
;*  reads divisor off stack, then offset off stack		  
	divArray PROC USES ecx eax esi edx
	LOCAL divisor: DWORD
;***********************************************************

	mov eax, [ebp+8]
	mov divisor, eax
	mov esi, [ebp+12]
	mov	ecx, [ebp+16]

	pushfd
	divideArray:
		mov		EAX, [ESI]						;move array number into EAX for dividng
		cdq										;sign extend EAX into EDX
		idiv	divisor							;divide EAX by divisor
		mov		[ESI], EAX						;move quotient into array
		add		ESI, TYPE SDWORD				

	loop divideArray
	popfd


	ret
divArray ENDP

;***********************************************************
;*	Option 4 - Mod array with provided divisor	           
;*  reads divisor off stack, then offset off stack		  
	modArray PROC USES ecx eax esi edx
	LOCAL divisor: DWORD
;***********************************************************

	mov eax, [ebp+8]
	mov divisor, eax
	mov esi, [ebp+12]
	mov	ecx, [ebp+16]

	pushfd

	divideArray2:
		mov		EAX, [ESI]						;move array number into EAX for dividng
		cdq										;sign extend EAX into EDX
		idiv	divisor							;divide EAX by divisor
		mov		[ESI], EDX						;move remainder into array
		add		ESI, TYPE SDWORD				

	loop divideArray2
	popfd

	ret
modArray ENDP

;***********************************************************
;	Option 5 - Print array								   
	printArray PROC,
	arrayPointer: DWORD,
	arrayLength: DWORD				  
;***********************************************************

	pushad
	pushfd

	mov			EDX, OFFSET openBrace
	call		WriteString

	mov			ECX, arrayLength
	sub			ECX, 1						;make so last element is not printed
	mov			ESI, arrayPointer

	L1:										;begin loop
		mov		EAX, [ESI]					;stores AL in EAX so that it is printable(and pads it so it fits)
		add		ESI, TYPE array				;increments the esi pointer by the array type
		call	WriteInt					;prints the signed element
		mov		edx, OFFSET divider			;put address of string into EDX
		call	WriteString					;print comma
		loop L1								;end loop
		
	mov			EAX	, [ESI]					;move last element to EAX
	call		WriteInt					;print last element without comma

	mov			EDX, OFFSET closeBrace
	call		WriteString	
	call		Crlf						;print a line for the table

	popfd
	popad

	ret
printArray ENDP

END main
