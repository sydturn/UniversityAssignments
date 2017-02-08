TITLE Math with arrays			(MathWithArrays.asm)

INCLUDE Irvine32.INC

printArray PROTO, arrayPointer:DWORD, arrayRow:DWORD, arrayCol:DWORD
AddRows2 PROTO, arrayPointer:DWORD, arrayRow:DWORD, arrayCol:DWORD

.data
num_rows = 6
num_cols = 5
Array	SDWORD	num_rows*num_cols DUP(?)
titHx	BYTE "Initial 2Dtable values ", 0
break	BYTE "_________________________________________________",0
row1	BYTE "Random A |",0
row2    BYTE "Random B |",0
row3	BYTE "C = A+B  |",0
row4    BYTE "D = A*C  |",0
row5	BYTE "E = D/B  |",0
row6	BYTE "F = D%B  |",0

openBrace	BYTE "{",0
closeBrace	BYTE "}",0
divider		BYTE	", ",0

temp	DWORD 2

.code
;*****************************
;add row A to row B and store in row C
AddRows2 PROC, arrayPointer:DWORD, arrayRow:DWORD, arrayCol:DWORD
LOCAL rowMemLen:DWORD
;*****************************
    mov eax, arrayRow
    mov		eax, TYPE DWORD
    mul		arrayCol	
    mov		rowMemLen, EAX
	
    mov		ESI, arrayPointer		;set Esi to row 1
    mov		EDI, arrayPointer
    add		EDI, rowMemLen			;set EDI to row 1 and add a row to get to row 2
    mov		EBX, EDI
    add		EBX, rowMemLen			;set EBX to row 2 plus a row to ge row 3
    mov		EAX, 0                  ;set EAX to 0 to keep track of how far in the array we are

    mov		ECX, arrayCol
L1:
    mov	EDX, [ESI + EAX]            ;move esi index(row A) to edx 
    add	EDX, [EDI + EAX]            ;add edi(row B) index to edx (add to row A)
    mov	[EBX + EAX], EDX            ;move some into EBX index (row C)
    add	EAX, TYPE DWORD             ;increment EAX
Loop L1
    ret 

AddRows2 ENDP



;*********************************************************************		
;generates 2 rows of 5 random numbers and populates the remaining 4 rows with zeors						
;esi = address of array, ecx = number of columns(6), edx = number of rows(5)
;*********************************************************************
populateArray PROC
	pushfd

	mov 	EAX, 300						;move upper to eax
	sub		EAX, -200						;subtract lower from upper
	inc		EAX								;add 1 to upper
	mov		EBX, EAX						;put difference into EBX
	mov		EAX, ECX						;put number of columns into eax momentarily
	mov		ECX, 2							;put 2 into ECX

	randomPopulate:
		push ECX
		mov	 ECX, eax						;put number of columns back into ecx
		populateRow:
			push	EAX
			mov		EAX, EBX				;put difference in eax
			call	RandomRange
			add		EAX, -200				;add lower value
			mov		[ESI], EAX				;put generated number into address of esi(in array)
			add		ESI, TYPE SDWORD		;increment array pointer
			pop		EAX			
		loop populateRow
		pop ECX								;put number of rows back into ecx
	loop randomPopulate
	
	sub		EDX, 2							;subtract first two rows from EDX
	mov		ECX, EDX						;put leftover rows into ECX

	zeroesPopulate:
		push ECX
		mov	 ECX, eax						;put number of columns back into ecx (note: EAX still has number of columns in it from above)
		populateZRows:
			push	EAX
			mov		AX, 0
			mov		[ESI], AX				;put 0 into address
			add		ESI, TYPE SWORD			;increment array pointer
			pop		EAX			
		loop populateZRows
		pop ECX								;put number of rows back into ecx
	loop zeroesPopulate


	popfd
	ret     
populateArray ENDP

;*****************************************************************************	
; prints rows of array in hexidecimal value	   
; ebp+16 = Offset of array; ebp+12 =number of columns ; ebp+8 = number of rows
;*****************************************************************************
printInitialValues PROC
    mov EDX, OFFSET tithx           ;print table title 
    call    WriteString
    call    crlf
    mov EDX, OFFSET break           ;print line break
    call    WriteString
    call    crlf
	push ebp                        
	mov ebp,esp

	mov	esi, [ebp+16]               ;move offset of array to esi
	mov	edi, 0                      ;index tracker
	mov	ECX, [ebp+8]                ;move number of rows to ecx
	PrintRow:
		push ECX
		mov	 ECX, [ebp+12]          ;move number of columns to ecx for inner loop
		PrintCol:
			mov		EAX,[esi]       ;move item at pointer to eah
			call	writeHexB       ;print item in hex value
			mov		al, ','
			call	WriteChar
			add		esi, Type DWORD
		loop PrintCol
		call crlf
		add		esi, edi            
		mov		edi, 0              ;set row index tracker to 0
		pop ECX
	loop PrintRow
	mov		esp, ebp
	pop		ebp
    call    crlf
	ret 12
printInitialValues ENDP

;*********************************************************************	
;prints array as table						   
printArray PROC, arrayPointer:DWORD, arrayRow:DWORD, arrayCol:DWORD
;*********************************************************************
    mov EDX, OFFSET break               ;print line
    call    WriteString         
    call    Crlf
    mov EDX, OFFSET row1                ;all these print the row title
    call    WriteString
    mov edi, arrayRow                   
	    mov	 esi, arrayPointer         ;move pointer to start of array         
		mov	 ECX, arrayCol             ;set counter to number of columns                        
        PrintRow1:                                                
			mov		EAX,[esi]          ;print value                              
			call	writeInt                                     
			mov		al, 9d             ;print tab                         
			call	writeChar
            mov     al, '|'            ;print line break
            call    writeChar                                      
		                                                         
			add		ESI, Type DWORD    ;increment counter                          
		loop PrintRow1   
        call    crlf    
        
        mov EDX, OFFSET row2            ;reapeat above steps for next 5 rows...
        call    WriteString
        mov	 ECX, arrayCol   
         PrintRow2:                                                
			mov		EAX,[esi]                                    
			call	writeInt                                     
			mov		al, 9d                                     
			call	writeChar  
            mov     al, '|'
            call    writeChar                                    
		                                                         
			add		ESI, Type DWORD                              
		loop PrintRow2    
        call    crlf                                  
         
         mov EDX, OFFSET row3
         call    WriteString
         mov	 ECX, arrayCol                   
         PrintRow3:                                                
			mov		EAX,[esi]                                    
			call	writeInt                                     
			mov		al, 9d                                      
			call	writeChar 
            mov     al, '|'
            call    writeChar                                     
		                                                         
		add		ESI, Type DWORD                              
		loop PrintRow3 
        call    crlf

        mov EDX, OFFSET row4
        call    WriteString
        mov	 ECX, arrayCol   
         PrintRow4:                                                
			mov		EAX,[esi]                                    
			call	writeInt                                     
			mov		al, 9d                                      
			call	writeChar  
            mov     al, '|'
            call    writeChar                                    
		                                                         
			add		ESI, Type DWORD                              
		loop PrintRow4  
        call    crlf

        mov EDX, OFFSET row5
        call    WriteString
        mov	 ECX, arrayCol   
        PrintRow5:                                                
			mov		EAX,[esi]                                    
			call	writeInt                                     
			mov		al, 9d                                     
			call	writeChar 
            mov     al, '|'
            call    writeChar                                   
		                                                         
		add		ESI, Type DWORD                              
		loop PrintRow5
        call    crlf
        
        mov EDX, OFFSET row6
        call    WriteString
        mov	 ECX, arrayCol   
        PrintRow6:                                                
			mov		EAX,[esi]                                    
			call	writeInt                                     
			mov		al, 9d                                      
			call	writeChar  
            mov     al, '|'
            call    writeChar                                    
		                                                         
			add		ESI, Type DWORD                              
		loop PrintRow6    
        call    crlf
                                                                          
    call crlf                                                    
	ret                                                          
printArray ENDP

;*****************************************************************************		   
; ebp+16 = Offset of array; ebp+12 =number of columns ; ebp+8 = number of rows
; Multiply A by C and store in D
;*****************************************************************************
MulRows PROC
	ENTER	4,0

    mov		eax, TYPE DWORD
    mov     ebx, [ebp+12]           ;move number of columns to ebx
	mul		ebx
	mov		[ebp-4], EAX            ;store row length into local variable
	
	mov		ESI, [ebp+16]   		;row 1
	
    mov		EDI, [ebp+16]           ;store row 1 into edi
	add		EDI, [ebp-4]			;add row to get to row 2
    mov		EBX, EDI                ;move row2 into ebx
	add		EBX, [ebp-4]			;add a row to get to row 3

    mov     EDI, EBX                ;put row 3 into EDI
    add     EBX, [ebp-4]            ;add a row to get to row 4
	
	mov		ECX, [ebp+12]           ;put number of columns into ecx
	L1:
		mov	EDX, [EDI]              ;move first row into edx
        mov	EAX, [ESI]              ;move second row into eax
        imul EDX                    ;multiply the two
        cdq                         ;signed extend
        push ebp                    
        mov ebp,2           
        idiv ebp                    ;divide answer by 2
        pop ebp
		mov	[EBX], EAX              ;store answer into D
		add	ESI, TYPE DWORD         ;increment
        add	EDI, TYPE DWORD         ;increment
        add	EBX, TYPE DWORD         ;increment
	Loop L1

    LEAVE
	ret 12
MulRows ENDP

;*****************************************************************************		   
; ebp+16 = Offset of array; ebp+12 =number of columns ; ebp+8 = number of rows
; Divide D by B and store Quotient in E and remainder in F
;*****************************************************************************
DivModRows PROC
	push ebp
	mov ebp,esp
    sub esp, 4

    mov		eax, TYPE DWORD
    mov     ebx, [ebp+12]           ;store number of columns in ebx
	mul		ebx
	mov		[ebp-4], EAX            ;store row length into local
	
	mov		ESI, [ebp+16]   		;row 1(A)
	add     ESI, [ebp-4]            ;row 2(B)
    add     ESI, [ebp-4]            ;row 3(C)
    add     ESI, [ebp-4]            ;row 4(D)

    mov     EDI, [ebp+16]           ;row 1(A)
    add     EDI, [ebp-4]            ;row 2(B)

    mov     EBX, ESI                ;row 4(D)
	add     EBX, [ebp-4]            ;row 5(E)

    mov     EAX, EBX                ;row 5(E)
	add     EAX, [ebp-4]            ;row 6(F)

    ;ESI = D, EDI = B, EBX = E, EAX = F
	mov		ECX, [ebp+12]           ;store number of columns into ecx
	L4:
        push EAX
		mov	EAX, [ESI]              ;move D into eax
        cdq                         ;sign extend
        idiv DWORD PTR [EDI]        ;divide by B
		mov	[EBX], EAX              ;put quotient into EBX
        pop EAX
        mov [EAX], EDX              ;put remainder into EAX
		add	ESI, TYPE DWORD         ;increment all pointers
        add	EDI, TYPE DWORD
        add	EBX, TYPE DWORD
        add	EAX, TYPE DWORD
	Loop L4

    mov esp, ebp
    pop ebp
	ret 12
DivModRows ENDP

main PROC
	;pass by register
	mov	esi, OFFSET array
	mov	ecx, num_cols
	mov	edx, num_rows
	call	populateArray

	;pass by stack: print first two rows' hex values 4 digits
	push	OFFSET array
	push	num_cols
	push	num_rows
	call	printInitialValues

	;using invoke directive with named parameters add 1st row to 2nd row and store in 3rd row
	INVOKE	AddRows2, ADDR array, num_rows, num_cols

    ;using invoke directive with named parameters add 1st row to 2nd row and store in 3rd row
    push	OFFSET array
    push	num_cols
    push	num_rows
    call	MulRows
      
    ;pass by stack for dividing 4th row by 2nd: store quotient in 5th row and remainder in 6th
	push	OFFSET array
	push	num_cols
	push	num_rows
	call	DivModRows

	INVOKE	printArray, OFFSET array, num_rows, num_cols

	exit
main ENDP
END main
