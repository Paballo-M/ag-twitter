# Twitter Feed like simulation

Java application that reads two files, **user.txt** and **tweet.txt** and create a twitter feed like simulation based on users, their followers and tweets as read from the two file.

## Getting Started

For development: Only the folder twitter-app is needed. After cloning **ag-twitter** into you local machine, load only twitter-app onto your IDE <br />
Compiled jar: this is a packaged jar of the source code as contained in the **twitter-app** folder. Run this in command-line, ensure **user.txt** and **tweet.txt** are in the same directory as jar

### Prerequisites

* IDE for source code (twitter-app)
* At least Java 8 
* At least JUnit 3
* NB: when running source-code within the IDE remember to provide Run Configuration Arguments (**user.txt** and **tweet.txt**)

### Running JAR
NB: Java JDK or JRE required to run jar. Link of how to install JRE: 
<a href="https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-microsoft-windows-platforms.htm#JSJIG-GUID-A7E27B90-A28D-4237-9383-A58B416071CA">Java Platform, Standard Edition Installation Guide</a>
In command-line navigate to the folder **compiled-jar**
execute command:
```
java -jar twitter-app.jar user.txt tweet.txt
```

## Running the tests

In the source-code two Test files are provided, **TestUserService.java** and **TestTweetService.java**, they can be triggered with 'Run As.. JUnit Test' Configuration 
<br />NB: You might have to add **JUnit** onto your Build Path


## Assumptions
* The name of the user is unique
* The name of the user does not contain a space
* Tweets are not unique


