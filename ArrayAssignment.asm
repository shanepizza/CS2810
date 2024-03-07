#$t0 = size of array this can either be initialized or we can put some random numbers into the array and figure out the size afterwards
#for the sake of brevity we have only code for one approach in which we receive a size and initialize an array of ints in ascending order.
    li $t0, 40
#store the array size
    sw $t0, ($sp)
    add $sp, $sp, 4
    
#used later for the $sp adjustment
    li $t4, -1
    li $t5, 4

#initialize the array
    li $t1, 0
    loopInitializer:
	sw $t1, ($sp)
	add $sp, $sp, 4
	add $t1, $t1, 1
	bne $t0, $t1, loopInitializer
    mult $t0, $t4
    mflo $t0
    sw $t0, ($sp)

#find one end of the array and move the stack pointer to the front. 
    lw $t0, ($sp)
    add $t0, $t0, -1
    mult $t0, $t5
    mflo $t0
    add $sp, $sp, $t0
    

#Get the array size and store it in $t0
#we minus one since our array atucally starts at 0. 
   lw $t0, ($sp)
   li $v0, 1
   sub $t0, $t0, 1
   
   
   #loop
    #Retrieve the info 
    #Print the current item in our register
    #add to $t8 for later
    #bne back to loop until we reach the end of the array size-1
   unloadingLoop:	
    #We print the space first because the bne check uses $a0 and will always fail if $a0 is 32. 
   	li $v0, 11
   	li $a0, 32
   	syscall
   	li $v0, 1
   	
   	add $sp, $sp, 4
   	lw $a0, ($sp)
   	add $t8, $t8, $a0
   	syscall
   	
   	bne $t0, $a0, unloadingLoop

    
#Get the average using integer division and print it.
    li $v0, 11
    li $a0, 10
    syscall
    li $v0, 1
    
    add $t0, $t0, 1
    div $t8, $t0
    mflo $a0
    syscall
    
    
    
    