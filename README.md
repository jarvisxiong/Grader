# About

Grading framework by Prasun Dewan and Joshua Monson.

The purpose of this project is to provide a framework and tools to allow for the automatic and assisted grading of
Java-based programs. It employs several methods to check code validity including reflection-based invocation,
abstract syntax tree inspection, output parsing, source code validation, JVM modification, and 
manual inspection of source code and user-interface.

Output parsing and manual inspection can work for any language, so recently this work has been extended to allow these actions to be performed also for C/C++.
To use this extension, the system path, include and lib must be set to allow dynamic compilation and execution of C/C++ programs.

Supporting these features for other languages is a matter of writing some "simple" plug-ins.

The grader  uses a GUI to display a grading rubric and the results of automatic grading.  The instructor running the program can view these data and input grades into the GUI to override auto grading steps and do manual grading.  Grades can then be stored and uploaded to Sakai

The grader evolved in a bottom up fashion by integrating two different projects, framework and grader, which insipred each other but were separate. These two projects were then glued together through some wrappers. Because of this history, there is substantial duplication of functionality.
It was used originally for Fall 2013 Comp 401 by Josh. Then two branches of it were created, one for Spring Comp 110 and the other for Spring Comp 410. The former was used by Josh and the latter by Jacob. We hope to merge these branches in Fall 2014. This is the Comp 110 branch owned by Jacob.



**Table of Contents**

* <a href="#quick-start-with-eclipse">Quick Start with Eclipse</a>
* <a href="#project-setup">Project Setup</a>
* <a href="#configuration">Configuration</a>
* <a href="#examples">Examples</a>

# Quick Start with Eclipse

The following is a guide to getting up an running as quickly as possible. The only assumption this quick start guide
makes is that you have Eclipse installed.

## Step 1: Get Maven

Download Maven from http://maven.apache.org, extract it, and add the binaries folder to your system path. Make sure the
`JAVA_HOME` environment variable is set.

## Step 2: Setup Eclipse with Maven

From the **Help** menu select **Eclipse Marketplace**.

Install the plugin called *Maven Integration for Eclipse*. This will require Eclipse to restart.

After restarting, go to **Window->Preferences** in Eclipse, and then go to **Maven->Installations**.  Add the install of Maven from Step 1.

## Step 3: Get and Initialize the Repository

Clone the repository.

```
git clone https://github.com/jwbartel/Grader.git
```

From a command line (if using Windows, use command prompt not powershell), navigate to the folder you just cloned and
run the following command.

```
mvn install:install-file -Dfile=oeall-22.jar -DgroupId=edu.unc -DartifactId=oeall -Dversion=22 -Dpackaging=jar
```

This adds the Object Editor jar file to your local Maven repository so that the dependency can be resolved.

## Step 4: Add the Project to Eclipse

In Eclipse, import a Maven project. Select the project you just cloned.

*Note:* The compliance level may be set to **1.5** so be sure to change this to **1.7**.

## Step 5: Run the Program

That's it, you're all set up. The default entry point is `graderTools.Driver`. You can run this file to run the grading
tool.

# Project Setup

## Testing, Building, and Executing

Because this is a Maven project, you can run the tests and build it using Maven commands.

### Testing

To run all the tests in the `test` folder, just run the following command:

```
mvn test
```

### Building

To compile:

```
mvn compile
```

To build the .jar (which does the testing and compilation as well):

```
mvn package
```

### Executing

The name of the jar depends on the `version` defined in `pom.xml`. Run the jar:

```
java -jar target/comp401-grader-Assignment-X-jar-with-dependencies.jar
```

If you have the project set up in an IDE you can run it there as well.

# Configuration

The entry point in the program (the one which Maven is configured to use) looks at the configuration file to determine
what and how to run. There are the following settings that you can set:

* `project.requirements`: This is the canonical name of a class which extends `ProjectRequirements` to use as the grading
criteria.
* `project.name`: The name of the project. Something like "Assignment4".
* `grader.controller`: This specifies which controller is used to load projects and dictate the grading process.
* `grader.logger`: This setting allows you to set how results will be saved. You can choose which loggers are used by selecting any of the following concatenated with '+':
 * `feedback-txt`: This saves a text file in the students' feedback folder
 * `feedback-json`: This saves a json file in the students' feedback folder
 * `feedback`: Equivalent to "feedback-txt + feedback-json"
 * `local-txt`: This saves a text file in a local log folder
 * `local-json`: This saves a json file in the local log folder
 * `local`: Equivalent to "local-txt + local-json"
 * `spreadsheet`: This saves all the scores in a spreadsheet

# Features
* A rubic GUI for grading features and storing grades on a student per student basis.
* Can automatically grade non-GUI program functionality and output
* All program output (including GUI output) can be run alongside the rubric GUI for easy grading
* Can analyze the following types of functionality and output:
 * reflection-based invocation 
 * abstract syntax tree inspection (see `gradingTools.examples.NoStringToolsTestCase`)
 * output parsing (see `gradingTools.examples.ForwardPrinterTestCase`)
 * source code validation

* There has been some work to analyze lower level issues that would lead to compilation errors
 * See `getJavacSourceClass` in `grader.project.AClassDescription` for access to this type of information
			
	

# Examples

There are a number of examples on how to use the system under the `examples` package.
There is, somewhat, an order to them.

* GraderExample.java
* FrameworkExample.java
* GraderWithProjectRequirementsExample.java
* GraderWithNewGUIExample.java
* DemoAndTest.java
* CDemoAndTest.java (for C programs)

To run CDemoAndTest, you need to set the path, include and lib variables.

Here are example values:
64 bit Windows 8.1 

Include=C:\Program Files (x86)\Microsoft Visual Studio 11.0\VC\include

Lib=C:\Program Files (x86)\Microsoft Visual Studio 11.0\VC\lib;C:\Program Files(x86)\Microsoft Visual Studio 11.0\VC\atlmfc\lib;C:\Program Files (x86)\Windows
Kits\8.0\Lib\win8\um\x86

Path=C:\Program Files (x86)\Microsoft Visual Studio 11.0\Common7\IDE;C:\Program Files (x86)\Microsoft Visual Studio 11.0\VC\bin

32 bit Windows 7 machine:

Include=C:\Program Files\Microsoft Visual Studio 10.0\VC\include

Lib=C:\Program Files\Microsoft Visual Studio 10.0\VC\lib;C:\Program Files\Microsoft Visual Studio 10.0\VC\atlmfc\lib;C:\Program Files\Microsoft SDKs\Windows\v7.0A\Lib

Path=C:\Program Files\Microsoft Visual Studio 10.0\Common7\IDE;C:\Program Files\Microsoft Visual Studio 10.0\VC\bin;D:\Program Files\Java\jdk1.7.0_21\bin


