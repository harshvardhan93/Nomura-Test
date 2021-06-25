# Nomura-Test

# How To Play

In order to run the program you can run the following command:

```
java -jar Nomura-Test-1.0-SNAPSHOT-jar-with-dependencies.jar
```
*NOTE: The jar file is available in the target folder*

After the game starts, the user specifies rolls for each frame. At the end of each frame we print out the roll information and the score upto the frame. 

Each input should be between 0 and 10. For inputs outside these bounds, we assume that the roll was “invalid” and assume that the user has rolled 0 pins.

Symbols on the scoreboard should be interpreted as follows:
```
X   - Strike
/   - Spare
-   - Indicates no roll required (skipping the third roll of the last frame).
```

# Setup and Build Instructions

The user requires three tools to setup and build the code:

1. IDE - You can pick an IDE of your choice.
2. Java - This program requires Java 8 or higher to be able to compile and run. You can download Java 8 from here (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
3. Maven 
    - Maven can be downloaded and setup from here (https://maven.apache.org/download.cgi)
    - In order to finish the maven setup make sure that you have setup ```MAVEN_HOME``` and added the binary folder to ```PATH``` variable.
    - Once you have setup Maven you can run the following command to build the code
      ```
      mvn clean install
      ```

