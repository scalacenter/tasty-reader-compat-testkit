# TASTy Reader Compatibility Testkit

[TASTy Reader for Scala 2](https://scala.epfl.ch/projects.html#tastyScala2) is a
project of the Scala Center to enable forward compatibility of the Scala 2
compiler with Scala 3.

This repository uses the Scala 2 compiler to test compatibility of third party
libraries which have been compiled by Scala 3. The intention is to validate
typical code that exists in the real world, and catch issues for further iteration
on the TASTy reader.

- branch `main` tests final releases of libraries with final releases of Scala 2
  and Scala 3.
- branch `develop` is a workspace for using any commit of the scala compiler to
  test libraries built from source, this is useful for integration testing, or
  trying out modifications to a library to improve compatibility.

This repo is organised as an sbt project with many subprojects.
A typical example is run from the command line with:
```
> sbt "cats-effect-demo/run --do-it"
```
