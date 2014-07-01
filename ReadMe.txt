===============================================
Author : Saideep Sambaraju
Title :  A Network Design Application
Date : 07/01/2014
===============================================

The purpose of this document is to lay out the guidelines for executing the source code that is bundled with the application

I.The directory structure
==========================
1. aatnproject1 - contains the main source code in the form of three files Main.java, Graph.java and Edge.java
2. data - not included with the submission but is present in the github link. Contains all the data files that are output-ed by the program
3. plots - not included with the submission but is present in the github link. Contains all the generated network topology diagrams as well as graphs presented in the report
4. R Script for drawing graphs - not included with the submission but is present in the github link. The R script used in generating the graphs that were presented in the report
5. AATN-Project1.pdf - This is the project report file in PDF format

II. Compilation Instructions
============================
aatnproject1 is the package containing all the source files. This software was built in a Windows system hence the directory structure should be Windows compatible

compiling the source code
Prompt> javac aatnproject1/*.java

III Running the program
========================
To run the program make sure that the user has permission to create directories.
When the program runs it creates a folder called "data" and dumps the output into files named k3.dat....k25.dat,experimentStats.dat

running the progam
Prompt> java aatnproject1/Main

IV GitHub Link for the project
==============================
https://github.com/ssdeep/aatn-project1


Regards,
Saideep