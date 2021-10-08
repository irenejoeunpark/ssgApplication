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
java

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
## Output

You can find the generated outputs at \dist or the custom directory specified in the config file

----  
## Demo
```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar --version
ssgApplication ver 1.0.0
```

```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar -h <or --help>
---------------------------------------
* -v OR --version - current version   *
* -i OR --input - input file or files *
* -c OR --config - JSON config file   *
---------------------------------------
```

```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar -i sources\Sherlock-Holmes-Selected-Stories
>Silver Blaze.html has been created in dist!
>The Adventure of the Six Napoleans.html has been created in dist!
>The Adventure of the Speckled Band.html has been created in dist!
>The Naval Treaty.html has been created in dist!
>The Red Headed League.html has been created in dist!
```

```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar -c config.json
>Silver Blaze.html has been created in web!
>The Adventure of the Six Napoleans.html has been created in web!
>The Adventure of the Speckled Band.html has been created in web!
>The Naval Treaty.html has been created in web!
>The Red Headed League.html has been created in web!
```

----
##License
MIT

----
##Deployment
version 1.0.0, Sept 2021 by author Irene Park
