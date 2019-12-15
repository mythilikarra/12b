#------------------------------------------------------------------------------
# Mythili Karra
# mmkarra
# 12M
# 01/22/2019
# This is the makefile
# #------------------------------------------------------------------------------
FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest
	jar cvfm FileReverse  Manifest FileReverse.class
	rm Manifest
	chmod +x FileReverse
FileReverse.class: FileReverse.java
	javac -Xlint FileReverse.java
clean:
	rm -f FileReverse FileReverse.class
submit: README Makefile FileReverse.java
	submit cmps012b-pt.w19 lab2 README Makefile FileReverse.java
