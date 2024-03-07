#Length and Width
li $t0, 5
li $t1, 10

#loading variables instead of using global variables
	addi $a0, $t0, 0
	move $a1, $t1
#jump to method
	jal CalculateArea

#Random code to show we are skipping it
	addi $t0, $t0, 12
	move $t9, $v0
	j End
	
#This is the Method we are running
	CalculateArea:
		mult $a0, $a1
		mflo $v0
		j $ra
	
	
#End Label
	End:
