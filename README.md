# Cob_Spec Server

Requirements
------------
This server requires that you have `java` and `maven` installed on your machine.

Please run `java -version` from the commandline to check if you have java.

If you don't then follow the instructions at this link: [Getting Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Tests
-----

There are 56 tests for this server, and currently they all pass.

The tests for this server can be run with the command `mvn test`, at the root.
In order to run the test suites for this game you'll need to have Maven installed.

Please run `mvn --version` from the commandline to check if it is installed.

If you don't, then there are a couple of ways for you to get up and running with maven. I personally believe that the eaisest way to get started is through `homebrew`, using the command `brew install maven`.

[Installing brew](https://brew.sh/)

The other, less user friendly way is to follow the instructions at this link: [Installing Maven](https://maven.apache.org/download.cgi)

Getting started
---------------

1. Run `git clone https://github.com/GeorgeSmith-Sweeper/HTTPServer.git` in your terminal.

2. Run `cd HTTPServer`.

3. Run `mvn package` at the root.

Starting the Http Server
--------------
The server jar needs to take two command line arguments.
- `-p` which specifies the port to listen on. Default is `5000`.
- `-d` which specifies the directory to serve files from. 

You can get the server up and running 
`java -jar target/HttpServer-1.0-SNAPSHOT.jar -p 5000 -d <Path to cob_spec public folder>`

