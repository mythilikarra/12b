#//Mythili Karra
#mmkarra
#12B
#02/5/2019
# This is the makefile.
Queens: Queens.class
	echo Main-class: Queens > Manifest
	jar cvfm Queens Manifest Queens.class
	rm Manifest
	chmod +x Queens
Queens.class: Queens.java
	javac -Xlint Queens.java
clean:
	rm -f Queens Queens.class
submit: Makefile Queens.java
	submit cmps012b-pt.w19 pa2 Makefile Queens.java
