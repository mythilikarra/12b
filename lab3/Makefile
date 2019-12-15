# Makefile for hello.c
# Mythili Karra
# mmkarra
# 12M
# 01/22/2019
# This is the Makefile
FileReverse : FileReverse.o
	gcc -o FileReverse FileReverse.o
FileReverse.o : FileReverse.c
	gcc -c -Wall FileReverse.c
clean :
	rm -f FileReverse FileReverse.o
submit:  README Makefile FileReverse.c
	submit cmps012b-pt.w19 lab3 README Makefile FileReverse.c
