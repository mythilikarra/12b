#Mythili Karra
#mmkarra
#12M
#2/8/2019
#This is the Makefile
FLAGS = -std=c99 -Wall
SOURCES = README Makefile charType.c
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.w19 lab4
all: $(EXEBIN)
$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
$(OBJECTS) : charType.c 
	gcc -c $(FLAGS) charType.c
clean :
	rm -f $(EXEBIN) $(OBJECTS)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
