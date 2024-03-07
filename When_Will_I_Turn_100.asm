.data
	GetNamePrompt: 		.asciiz "What is your name?: "
	GetAgePrompt: 		.asciiz  "How old are you?: "
	PrintAge: 		.asciiz "is your age"
	deathDate: 		.asciiz "You will turn 100 in the year, "
	Name: 			.asciiz 
	Age: 			.data

.text

   #Current Year
   	li $t7, 2024
   #Print the Prompt
	la $a0, GetNamePrompt
	li $v0, 4
	syscall
	
   #Get the Name 
	li $a1, 80
	li $v0, 8
	syscall 

   #Save name to memory
	sw $v0, Name

   #Print the GetAgePrompt
   	la $a0, GetAgePrompt
   	li $v0, 4
   	syscall
   	
   #Get the Age
   	li $v0, 5
   	syscall
   #Save to Age:
   	sw $v0, Age
   	
   #Print their age and the mage message
   	lw  $a0, Age
   	li $v0, 1
	syscall
   	
   	
   	la $a0, PrintAge
	li $v0, 4
	syscall
	
	
	
	
   
   #get the Current year and their age. 
   #Subtract current age then add 100
	
	lw $t1, Age
	sub $t9, $t7, $t1
	add $t9, $t9, 100
	
#Print when the you will turn 100
	la $a0, deathDate
	li $v0, 4
	syscall
	addi $a0, $t9, 0
	li $v0, 1
	syscall
	


