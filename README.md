# Vincent's SecuritEase Tech Assessment

|         |                                               |
|---------|-----------------------------------------------|
| Started | 30-sep-2023                                   |
| For     | [Mark Sage](mailto:mark.sage@securitease.com) |

### Foreword
I'm not hugely familiar with Java/IntelliJ etc. I've played a little bit with java in the past, but only recently started contributing to an existing Java/RestAssured test suite at work. 

## Pre-Requisites
These steps were previously done on my laptop
1. Create a git account
2. Install Git

## Steps Performed
1. Download and install IntelliJ IDEA 2023.2.2 (Ultimate Edition)
2. Create new project using Sprint Initializr generator
3. Create local repo and commit initial set of autogenerated files
4. Create new private repo in my personal space and set as origin for local repo
5. Create the README.md file (ie. this file) and start documenting the tasks
6. Add dependencies for testng, rest-assured, assertj, junit to the pom. 
7. Create New java class APITest and copy skeleton code from instructions. 
8. Swap to using testng, update baseURI, try to run the test, fix any issues encountered. 

Notes:
* There were a few issues that needed fixing when I copied the skeleton code
  * text copied & pasted from the PDF went a bit funny in places 
  * deleted the `>` after defining the baseURI
  * JUnit assertion needed it's input parameters to be re-arranged to `assertEquals(msg,expected,actual)`
* There were some CVE's reported for spring and testng dependencies when I added them to the pom. I investigated, but I couldn't quickly figure out how to fix them and was keen to ask a more experienced java dev about them. 
* I swapped to using AssertJ - a personal preference
