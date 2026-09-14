#Variables
SRC_DIR=src
TEST_DIR=test

#Buscar todos lo archivos .java
SOURCES=$(shell find $(SRC_DIR) -name "*.java")
TEST_SOURCES = $(shell find $(TEST_DIR) -name "*.java")
JACOCO = /home/lalo/Descargas/jacoco-0.8.15/lib
EXEC = jacoco.exec
COVERAGE = ./coverage

# Detectar separador de classpath según el SO
ifeq ($(OS),Windows_NT)
    SEP=;
else
    SEP=:
endif

all: compile

#Muestra el archivo de coverage
opencov:
	xdg-open $(COVERAGE)/index.html

#Compila
compile:
	javac -cp "lib/*:out" -d out $(SOURCES)

#Compila los test
test: compile
	javac -cp "lib/*:out" -d out $(TEST_SOURCES)
	java -cp "lib/*:out" org.junit.platform.console.ConsoleLauncher execute --scan-classpath

#Compila los test y muestra el coverage
coverage: compile
	javac -cp "lib/*:out" -d out $(TEST_SOURCES)
	java -javaagent:$(JACOCO)/jacocoagent.jar=destfile=jacoco.exec \
		-cp "lib/*:out" \
		org.junit.platform.console.ConsoleLauncher execute --scan-class-path
	java -jar $(JACOCO)/jacococli.jar report $(EXEC) \
		--classfiles out \
		--sourcefiles src \
		--html $(COVERAGE)
	xdg-open $(COVERAGE)/index.html
