# Overview

A functional toy robot implementation in Scala.


# Install

(Be patient ... it will need to download and install the world!)

## Option 1 - Use Docker

If you have [docker](https://docs.docker.com/engine/installation/) installed, you can run in a docker container:

``` bash
$ docker run -v `pwd`:/workspace -it openjdk:latest /bin/bash
$ cd /workspace
$ ./sbt build         
```



## Option 2 - Run Locally

You will need java jdk8 installed.  All other dependencies will be installed on build.

`./sbt build`


#Test

`./sbt test`


#Run

`./sbt run`

Please see PROBLEM.md for details of commands.  

# Behaviour choices

I have chosen to fail silently for all invalid commands.




