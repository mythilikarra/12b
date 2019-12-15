SOURCES   = $(ADT_NAME).c 
#OBJECTS   = $(ADT_NAME).o 
EXEBIN    = five
FLAGS     = -std=c99 -Wall
SUBMIT    = submit cmps012b-pt.w19 lab5

all: $(EXEBIN)

$(EXEBIN) :
	gcc -o $(EXEBIN)

/bin/bash: q: command not found
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 
