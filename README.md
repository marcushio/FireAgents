# Fire Monitor Simulation

Colton Trujillo & Marcus Trujillo

### Introduction

This project simulates a network of sensors that use agents to communicate with a central base station to log
the behavior of forest fires.

### Usage

Run the program from the command-line, feeding in an argument with the complete path to the configuration file
you will use. You'll need to use \\ instead of \ for windows. 

### Project Assumptions



### Design Choice
For parsing the configuration file, we decided that if duplicate stations are given, the station will be specified by the entry lowest in the document.
Othwerwise, we assume configuration files will match what was given in the pdf. Using files with invalid words or formats will likely crash the program.

Explain any special algorithm, data structure, or logic used that was crucial to
solving the problem. Also explain any design choice you feel is important.

The unique ID of each agent is specified by the a timestamp of when it was created and the node it was created from. 

### Versions

All version submitted and their features.

### Docs

The design document is in the doc folder.

### Known Issues


### Testing and Debugging

Testing included running the program and watching the simulation multiple times with various config files.
Debugging was performed with the Intellij IDE. 
