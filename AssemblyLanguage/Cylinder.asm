;Ask the user to supply the radius and height of a regular cylinder
;and then report the Surface Area of a single end, the area of the
;side, the total surface area (both ends and the side) of the cylinder
;and the volume of the cylinder. Your program should loop until the
TITLE Volume and Area of a Cylinder		Cylinder.asm
;Calculates the volume and area of a cylinder

INCLUDE Irvine32.inc
.data
promptRadius	BYTE "Please supply the radius of a regular cylinder: ", 0
promptHeight	BYTE "Now supply the height of the cylinder: ", 0 
A1				BYTE "The surface area of the top of the cylinder is: ",0
A2				BYTE "The surface area of the side of the cylinder is: ", 0
A3				BYTE "The total surface area of the cylinder is: ", 0 
V1				BYTE "The volume of the cylinder is: ", 0 
exitCode		BYTE "System exit.", 0
SingleArea		REAL4 ?
SideArea		REAL4 ?
FullArea		REAL4 ?
Volume			REAL4 ?
height			REAL4 ?
radius			REAL4 ?

temp			DWORD 2

.code
main PROC
	FINIT
begin:
	mov			EDX, OFFSET promptRadius
	call		WriteString
	call		ReadFloat
	
	FLDZ						;add zero to stack
	FCOMP						;compare zero to next on stack and pop
	FNSTSW		ax				;move to ax
	SAHF						;move ax to flags
	je			finished		;jump if equal to zero to exit
	
	fstp		radius			;put radius from top stack into variable

	mov			EDX, OFFSET promptHeight
	call		WriteString
	call		ReadFloat

	FLDZ						;add zero to stack
	FCOMP						;compare zero to next on stack and pop
	FNSTSW		ax				;move to ax
	SAHF						;move ax to flags
	je			finished		;jump if equal to zero to exit

	call		Crlf
	fstp		height			;put height in variable(from top of stack)

	;*************************************************
	;report the Surface Area of a single end (pi)r^2
	;*************************************************

	FLD			radius			;r
	FLD			radius			;r, r
	FMUL						;r*r
	FLDPI						;PI, r*r
	FMUL						;PI*r*r
	
	mov			EDX, OFFSET A1
	call		WriteString
	call		WriteFloat		;print area of single surface
	call		Crlf
	FSTP		singleArea		;save single surface area
			
	;*************************************************
	;the area of the side 2(pi)rh
	;*************************************************
	FLDPI						;PI
	FIMUL	temp				;PI*2
	FLD		radius				;radius, PI*2
	FMUL						;radius*PI*2
	FLD		height				;height, radius*PI*2
	FMUL						;height*radius*PI*2

	mov		EDX, OFFSET A2
	call	WriteString
	call	WriteFloat
	call	Crlf
	FSTP	SideArea

	;*************************************************
	;the total surface area 2(pi)r^2 + 2(pi)rh
	;*************************************************
	FLDPI						;PI
	FIMUL	temp				;2*PI
	FLD		radius				;radius, 2*PI
	fld	radius
	fmul
	FMUL						;radius*2*PI
	FLD		height				;height, radius*2*PI
	FMUL						;radius*2*PI*height
	FADD	SideArea			;SideArea + radius*2*PI*height

	mov		EDX, OFFSET A3
	call	WriteString
	call	WriteFloat
	call	Crlf
	FSTP	FullArea

	;*************************************************
	;the volume of the cylinder (pi)r^2h
	;*************************************************
	FLD		radius				;radius
	FLD		radius				;radius, radius
	FMUL						;radius * radius
	FLDPI						;PI, radius*radius
	FMUL						;PI*radius*radius
	FLD		height				;height, PI*radius*radius
	FMUL						;height*PI*radius*radius

	mov		EDX, OFFSET V1
	call	WriteString
	call	Writefloat
	call	Crlf
	call	Crlf
	FSTP	volume

	jmp		begin

finished: 
	mov		EDX, OFFSET exitCode
	call	WriteString
	call	Crlf
	exit
main ENDP
END main
