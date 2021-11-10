# ssgApplication

Implemented using Java to generate a static html site from a txt file.

----

## Features
argument option "-v" or "--version" will display the current version of the application

argument option "-h" or "--help" will display the list of command options

argument option "-i" or "--i" will allow user input the single file or files by passing the directory. It supports both .txt and .md (feature: Heading, Bold)

argument option "-c" or "--config" will allow users to specify a JSON config file that contains input and optional output values, such as:

```bash
{
    "input": "docs",
    "output": "web"
}
```

----
## Prerequisites
java (jdk-17), Maven, IntelliJ

----
##Installation
1. Clone this repository
2. Locate the ssgApplication.jar

----
## Run
1. open command line
2. run .jar file using java -jar with the argument
```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar -i <txtFileName>
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar -c <configFileName>
```
----
## Contribution

### File a new issue, or work on an existing issue

>1. Fork this repository
>2. Create a feature branch with a name "feature-issue-##(replace with the issue number)"
>3. Check out the branch and make changes

### Code format

>1. Search "google-java-format" from IntelliJ Marketplace
>2. Install and enable the plugin
>3. Reformat the Code by "CTRL+ALT+L" in the file you worked on

### Testing

To test the code :
1. Run "testProcessInput" in HTMLBuilderTest.java

2. Fix any error that does not pass the testing

3. Click [here](https://junit.org/junit5/docs/current/user-guide/) for more information for Junit testing framework

To create a unit test :

Right click the class name in the code, and select Show Context Action
Select >Create Test< and select the function you want to add
Add testing to the corresponding testclass



### Fix Linter

>1. Run ```mvn spotbugs:gui``` to spot the area of improvement in a SpotBug UI
>2. Fix the linter (Especially if it has red dot)

### Pull Request

>1. Commit and push the changes
>2. Create a pull request against irenejoeunpark/master
>3. Mention the issue in the pull request

----
##License
MIT

----
##Deployment
version 1.0.0, Sept 2021 by author Irene Park
