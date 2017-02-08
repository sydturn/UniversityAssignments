TITLE Alter String					StringChange.asm
;this program alters an inputted string and saves it to a file.

INCLUDE Irvine32.inc
.data
stringSize DWORD ?
string	BYTE	251 DUP (0)					;0 means end of string
prompt1	BYTE	"Enter a string of up to 250 characters: ",0
filename BYTE	"output.txt", 0
filehandle	HANDLE ?
NewLine		BYTE	13d,10d

.code
main PROC

		;create a new text file
		mov		edx, OFFSET filename
		call	CreateOutputFile
		mov		filehandle, eax
		
		;take user input
		mov		edx, OFFSET prompt1			;put address of string into EDX
		call	WriteString					;print prompt
		mov		ECX, SIZEOF string			;max length of string
		mov		EDX, OFFSET string
		call	ReadString					;read string into EDX
		
		;write to output file
		mov		stringSize, eax				;ReadString puts length of string into eax: put length of inputted string into variable for future use
		mov		ecx, stringSize
		mov		eax,filehandle
		mov		edx,OFFSET string
		call	WriteToFile
		
		mov		ecx, LENGTHOF NewLine		;Print line
		mov		eax,filehandle
		mov		edx,OFFSET NewLine
		call	WriteToFile


		mov		ECX, LENGTHOF string		;set loop counter to string length
		mov		ESI, OFFSET string			;put pointer at string

		call	editString

		;write to output file
		mov		eax,filehandle
		mov		edx,OFFSET string
		mov		ecx, stringSize
		call	WriteToFile
		call	closeFile

	exit
main ENDP

editString PROC
		mov		EAX, 0 
	
	L1:										;begin loop

		cmp		EAX, 0						;check if "0"					
		je		evean						;jump to 'even' if  EAX and 0 are equal
		
		;otherwise it came back as 1 and so the character is odd

		mov		EAX, 0						;next character is even so set EAX to 0
		cmp		Byte PTR [ESI], 'A'			;check if character is uppercase by checking if it is...
		jb		done						;below A (then it's lowercase or character, jump to done)
		cmp		Byte PTR[ESI], 'Z'			;compare again to make sure it is not...
		ja		done						;above Z (then it's lowercase or character, jump to done)

		mov		EBX, 'A'					;otherwise we need to subtract from the character to make it lowercase
		sub		EBX, 'a'					;by finding the difference between A and a, then...
		sub		[ESI], EBX					;subtracting the difference from the character at ESI

		jmp done							;now it is lowercase and we can jump to done
		
		
		evean:
		mov		EAX, 1						;next character is odd so set EAX to 1
		cmp		Byte PTR [ESI], 'a'			;check if character is uppercase by checking if it is...
		jb		done						;below 'a' (then it's uppercase or character, jump to done)
		cmp		Byte PTR[ESI], 'z'			;compare again to make sure it is not...
		ja		done						;above 'z' (then it's uppercase or character, jump to done)

		mov		EBX, 'A'					;otherwise we need to add to the character to make it uppercase
		sub		EBX, 'a'					;by finding the difference between A and a, then...
		add		[ESI], EBX					;adding the difference from the character at ESI

		done:
		inc		ESI							;incriment to next character
		loop L1	

		mov		edx, OFFSET string			;put address of string into EDX
		call	WriteString					;print prompt
		call	Crlf
				
	ret
editString ENDP

END main
