#------------------------------------------------------------------------------
# A simple Makefile
# #------------------------------------------------------------------------------
Search: Search.class
	echo Main-class: Search > Manifest
	jar cvfm Search Manifest Search.class
	rm Manifest
	chmod +x Search
Search.class: Search.java
	javac -Xlint Search.java
clean:
	rm -f Search Search.class
submit:
	submit cmps012b-pt.w19 pa1 README Makefile Search.java
