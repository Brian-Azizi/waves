build: src/com/brian/main/*.java
	javac src/com/brian/main/*.java -d build

run: build
	java -cp build com.brian.main.Game
