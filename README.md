###A simple chat program with a client and server
####Written in java

Compilation:

```

javac *.java

```

Server Usage:

```
java Server <port number>

```
Client Usage:

```
java Client <hostname> <port number> <login name -optional>

```

To support multiple simoultaneous clients, the clients are treated as threads. Each time a new client connects, a new concurrent thread is created to support any of that clients activity.

The initial implementation of the Server that I used didnt synchronized any of the shared data, mainly the ArrayList of stored messages. This caused problems with the Server and indirectly the client. The messages would lock if a Client attempted to fetch their messages at the same time as another was fetching or sending. A Synchronized block for accessing the messages fixes the issue.

Done for an assignment, as posted [here](http://leda.science.uoit.ca/teaching/sysdev/assignments/assignment5).