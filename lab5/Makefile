#------------------------------------------------------------------------------
# Makefile for any ADT and its test client
# #------------------------------------------------------------------------------
ADT_NAME  = Dictionary
SOURCES   = $(ADT_NAME).c $(ADT_NAME)Client.c
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)Client.o
HEADERS   = $(ADT_NAME).h
EXEBIN    = Dictionary 
FLAGS     = -std=c99 -Wall
SUBMIT    = submit cmps012b-pt.w19 lab5 

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit: $(SOURCES)
	$(SUBMIT)$(SOURCES) README Makefile DictionaryTest.c Dictionary.h 
