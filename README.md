# PjlFilter

This program processes all the print stream file and modifies the PJL Header user id, then creates a new print stream file, which can be used later. 
The PJL Headers to be modified can be specified in a csv file as an Input.

It developed using Spring Boot.

## How to Access the Code

Clone the repository from github

	https://github.com/SangeethaAsokarajan/PjlFilter.git
	
Install Spring STS (if not already available in IDE)
	
## How to Build 
	
Goto the project location in commandline and
Execute the command 

	$ mvn install

## How to Run

The generated jar file will be in the target folder of project location and
Execute the command 

	$ java -jar pjl-filter-0.0.1-SNAPSHOT.jar D:\printstream\samples D:\printstream\csv
	
#### 1st parameter is the location of print stream files
	
	eg: D:\printstream\samples
	
#### 2nd parameter is the location of commands file (as csv). 
	
	eg: D:\printstream\csv
	
#### Format of csv file 
		
		USERID, USER1
		PUNCH, 50
		COPIES,5
	
## How to Test

Goto the project location in commandline and 
Execute the command 

	$ mvn test

