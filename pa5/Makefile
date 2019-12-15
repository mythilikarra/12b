#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#-----------------------------------------------------------------------------
#//Mythili Karra
#//mmkarra
#//12B
#//14 March 2019

ADT     = Dictionary
EXEBIN  = $(ADT)Client
SOURCES = $(ADT).c $(EXEBIN).c
OBJECTS = $(ADT).o $(EXEBIN).o
HEADERS = $(ADT).h
SUBMIT     = submit cmps012b-pt.w19 pa5
all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)
clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN)
submit:$(SOURCES)
	$(SUBMIT) README DictionaryTest.c Makefile $(SOURCES) $(HEADERS)
	 
