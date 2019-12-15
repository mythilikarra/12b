#Mythili Karra
#mmkarra
# 12B
# 2/8/19
JAVAC      = javac 
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)
submit:  README Makefile Dictionary.java 
	submit cmps012b-pt.w19 pa3 README Makefile Dictionary.java DictionaryTest.java DuplicateKeyException.java KeyNotFoundException.java DictionaryInterface.java DictionaryClient.java  
