OBJS=$(wildcard *.java)


PLATFORM=All
PROG=Initiation
DEST=./$(PROG).jar

LFLAGS=	
CFLAGS=	

main:$(OBJS)
	@echo -n "Compiling "
	@echo `ls | grep java`
	@echo -n " ..."
	@javac $(OBJS) $(LFLAGS)
	@echo -n " Done\n"
	@echo -n "Creating manifest..."
	@echo -n "Main-Class: Initiation" > manifest.txt
	@echo -n " Done\n"
	@echo -n "Linking..."
	@jar cvfm Initiation.jar manifest.txt *.class
	@echo -n " Done\n"

clean:
	@echo -n "Cleaning up..."
	@rm -f *.class
	@echo " Done"

bak:
	@make clean
	@zip -q -j  backup/$(PROG)_`date "+%x_%X"`.zip *

loc:
	@cat *.java | wc -l

