**Fire Monitor Simulation**
@authors: Colton Trujillo & Marcus Trujillo

**Introduction**
This project simulates a network of sensors that use agents to communicate with a
central base station to log the behavior of forest fires.

**Usage**
_How to Run_
Run the program from the command-line, feeding in an argument with the complete
path to the configuration file you will use. You'll need to use \\ instead of \ for windows.
i.e. C:\\fireFolder\\config.txt.
-The following command was successfully used during testing...
  "java -jar fire-monitor-simulation_coltonbtrujillo_marcustrujillo.jar C:\\config\\config.txt"
  of course during your run, change the last argument to where you have the configuration file stored.
The program runs indefinitely (because we never put out the fire) so after all Nodes
have burnt just ctrl+c (standard quitting in windows) in the command line to stop
the simulation. 

_How to read display_
1. Node Colors -
     +_Blue_- The node is in working condition and none of it's neighbors are on fire.
     +_Yellow_-The node is in working condition and at least one of it's neighbors is on fire.  
     +_Red_-The node is not working, it can't send messages, it can't receive agents.
            it is on fire.
2. Nodes with Halos - These nodes have Agents on them.
3. Node with S in the middle - this node is the station node.

**Project Assumptions**
1. Configuration files need to follow the format given in the examples that were
   on Learn. (there's a station, and a place for the fire to start). Config files
   with misspellings will likely result in the program crashing.
2. There is only one station present in the network and one starting location of the fire.
3. We expect integer values for the locations of Nodes(sensors).

**Design Choice**
_Parsing_ For parsing the configuration file, we decided that if duplicate stations
are given, the station will be specified by the entry lowest in the document.
Otherwise, we assume configuration files will match what was given in the pdf.
Using files with invalid words or formats will likely crash the program.

_Messaging_ Messages that ended up in the log were encapsulated by LogEntry objects.
These objects tracked their journey through the network by documenting where they visited.
This resulted in them doing a sort of DFS. It's just not quite the same because
unlike DFS they don't just go back to the start when they can't traverse further.

_Fire Spreading_ We simply had fire as it's own thread and spread from Node to node
after a fixed interval.

_Unique Agent Naming_ No agent will be created at both the same time and the same node.
So to give them unique Id's we had their id be the time they were created and the
coordinate that they started on.

Explain any special algorithm, data structure, or logic used that was crucial to
solving the problem. Also explain any design choice you feel is important.

_Versions_
We're only submitting one version.

_Docs_
The design document is in the doc folder.

### Known Issues

_Division of Labor_
Marcus Trujillo
 -Implementation of Fire, Messaging, Agent Behavior, some node behavior,
  most concurrency behaviors.
Colton Trujillo
 -GUI work, a LOT of debugging Marcus' code, Node changing colors/status, initialization
  of the program and processing of configuration files, created configuration files
  for testing.
