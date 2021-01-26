# Refactoring, Static Analysis and Code Metrics

##Step 1. Find a refactoring kata

* Reasonable - https://github.com/adi-bolb/trivia/tree/master/java
* Pretty Good - https://github.com/emilybache/Racing-Car-Katas
 
##Step 2. Refactoring and Cleaning

* Clean up based on best practices from Clean Code (safe refactorings guided by a tool).  
* Try and write a test around it and use code coverage (run mvn cobertura:cobertura)
* Run Intellij > Analyse to tell you what's wrong with it - https://www.jetbrains.com/idea/help/code-analysis.html
* Run PMD - mvn pmd:pmd and look in target/site/pmd.html
* Run Findbugs - mvn findbugs:check

Sometimes, you can glance at a small codebase and provide guidance on how it should be just based on single responsibility principle, number of classes, how easy it is change, good naming principles, comments, formatting and JDK best practices (e.g. use Guava Lists instead of creating ArrayLists).  Sometimes that's good enough and you don't need anything else.

If you want to go further then design should be guided by tools - e.g. run Intellij inspections to identify common bugs.  Alternatively, PMD and findbugs can be run against your code (complex conditionals - bad).  This is generally referred to as static analysis.  Going further look at software metrics (large classes - bad, classes with lots of dependencies - bad).

## Tools Appendix

Read - http://blog.jetbrains.com/idea/2014/09/touring-plugins-issue-1/

##Formatting:
* Checkstyle - http://checkstyle.sourceforge.net/

###Static Analysis:
* PMD - https://pmd.github.io/
* Findbugs - http://findbugs.sourceforge.net/

###Code Coverage
* Clover - https://confluence.atlassian.com/display/CLOVER/Clover-for-IDEA
* Cobertura - https://cobertura.github.io/cobertura/

###Metrics:
* Metrics Reloaded - http://plugins.jetbrains.com/plugin/?idea&id=93

###Architcture Analysis:
* SonarGraph Architecture and Metrics Checker - https://www.hello2morrow.com/products/sonargraph
* Code Iris (Visualises Structure) - http://www.codeiris.com/what-is-it/
* JArchitect - http://jarchitect.com/
