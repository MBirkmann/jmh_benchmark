# jmh_benchmark
This project contains experiments using the Java Microbenchmarking Harness ([JMH](http://openjdk.java.net/projects/code-tools/jmh/)).

## Getting started
### Prerequisites
The project requires at least JDK 11 and Maven 3.6 properly installed.

### Installation
Installation with Maven:  
```
git clone https://github.com/MBirkmann/jmh_benchmark.git  
cd jmh_benchmark  
mvn clean package  
java -jar target/jmh_benchmark.jar
```

## About JMH
JMH is a Java harness for building, running, and analysing nano/micro/milli/macro benchmarks written in Java and other languages targetting the JVM.

Please read [nanotrusting nanotime](http://shipilev.net/blog/2014/nanotrusting-nanotime/) and other blog posts on micro-benchmarking (or why most benchmarks are wrong) and make sure your benchmark is valid, before you set out to implement your benchmarks.
