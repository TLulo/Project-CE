#Variables
SRC_DIR=src
TEST_DIR=test

#Buscar todos lo archivos .java
SOURCES=$(shell find $(SRC_DIR) -name "*.java")
TEST_SOURCES = $(shell find $(TEST_DIR) -name "*.java")

# Detectar separador de classpath según el SO
ifeq ($(OS),Windows_NT)
    SEP=;
else
    SEP=:
endif

all: compile

#Muestra el archivo de coverage
opencov:
	xdg-open ./coverage/index.html

#Compila
compile:
	javac -cp "lib/*:out" -d out $(SOURCES)

#Compila los test
test: compile
	javac -cp "lib/*:out" -d out $(TEST_SOURCES)
	java -cp "lib/*:out" org.junit.platform.console.ConsoleLauncher --scan-classpath

#Compila los test y muestra el coverage
coverage: compile
	javac -cp "lib/*:out" -d out $(TEST_SOURCES)
	java -javaagent:/home/lalo/Descargas/jacoco-0.8.15/lib/jacocoagent.jar -cp "lib/*:out" org.junit.platform.console.ConsoleLauncher execute --scan-class-path
	java -jar /home/lalo/Descargas/jacoco-0.8.15/lib/jacococli.jar report jacoco.exec --classfiles out --sourcefiles src --html coverage
	opencov