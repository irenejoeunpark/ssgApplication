# ssgApplication

Implemented using Java to generate a static html site from a txt file.

----

## Features 
argument option "-v" or "--version" will display the current version of the application
argument option "-h" or "--help" will display the list of command options
argument option "-i" or "--i" will allow user input the single file or files by passing the directory
----
## Run

1. clone this repository
2. open command line
3. run .jar file using java -jar with the argument
```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar -i <txtFileName>
```


----  
## Output

You can find the generated outputs at \dist

----  
## Example
```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar --version
ssgApplication ver 1.0.0
```

```bash
java -jar out\artifacts\ssgApplication_jar\ssgApplication.jar -h <or --help>
---------------------------------------
* -v OR --version - current version   *
* -i OR --input - input file or files *
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
