.data 
	Space: 		.asciiz " "

.text
li $t0, 7 #n number of fibonacci numbers to print

li $a0, 0 #starting values for our sequence
li $a1, 1 #

li $a3, 2 #Counter that is used

#Manually storing the initial recursive values 
	sw $a0, ($sp)
	addi $sp, $sp, -4
	sw $a1, ($sp)
	addi $sp, $sp, -4

#Jump to the recursive method
	jal FibonacciMethod #First call of the recursive method
	
#Manually printing the initial recursive values. 	
	addi $sp, $sp, 4
	lw $a0, ($sp)
	li $v0, 1
	syscall
#Printing a space
	la $a0, Space
	li $v0, 4
	syscall
#Manually printing the initial recursive values. 	
	addi $sp, $sp, 4
	lw $a0, ($sp)
	li $v0, 1
	syscall
	
	j End#End of the program
	
	
FibonacciMethod:
	beq $a3, $t0, JumpToRa #This is a counter that will simply jump us once we have reached the appropriate value
	
	#All of our code for calculating the next number
		add $a2, $a0, $a1
		sw $a2, ($sp)
		addi $sp, $sp, -4
	
		move $a0, $a1
		move $a1, $a2
		addi $a3, $a3, 1
	#***************************************
	
	#save $ra to memory
		sw $ra, ($sp)
		addi $sp, $sp, -4
		
		
	#this tells us to run the method recursively until our counter runs out
		jal FibonacciMethod
		
		#Grab the new address for $ra for the jump command below
			addi $sp, $sp, 4
			lw $ra, ($sp)

	
		#We just grab and print the variables down here. that is all
			addi $sp, $sp, 4
			lw $a0, ($sp)
			li $v0, 1
			syscall
		#Printing a space. This could be simpler but I am being lazy
			la $a0, Space
			li $v0, 4
			syscall
	
	
	
	
	
	jr $ra #This sends us back to where we print the values of from memory
	
	
	
JumpToRa:
	jr $ra
	
	
	
	
	
End:
