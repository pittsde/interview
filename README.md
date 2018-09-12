# Interview automation

This code covers automation of the KiwiSaver calculator and FX converter specified in the Automation Test docs

## Getting Started

These instructions will get you a copy of the project up and running on your local machine 

### Prerequisites

What things you need to install the software and how to install them


####Java 1.8

1. [Download java 8 sdk](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. [Set JAVA_HOME](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)
3. run ```javac -version``` in a command prompt window
4. Ensure correct version returned

####Gradle
1. [Follow installation instructions here](https://gradle.org/install/)
2. run ```gradle -v``` in a command prompt window
3. Ensure correct version returned

####Git
1. [Install GIT](https://git-scm.com/downloads)
2. clone the repo https://github.com/pittsde/interview.git

####Chromedriver
1. [Download chromedriver](http://chromedriver.chromium.org/downloads)
2. [Add chromedriver location to system environment variable](http://chromedriver.chromium.org/getting-started)


### Running the tests

1. Change directory in a command prompt window to the location of the repo
2. run the command ```gradle cucumber```
3. Cucumber report will be generated in interview-automation/output/cucumber-html-report/index.html


### Questions

#### FX questions ####

#####Why did you choose this tool/framework? Explain if there are any other possible alternative with pros/cons of them.#####

I used cucumber, java, webdriver gradle tools.

Cucumber was used as step definitions were easy to reuse for the second scenario and anybody could read the test and see what was being tested

Java/Webdriver was used as I have most experience with these tools and can run on multiple browsers and are also platform independent

Chrome was used as it is the most common browser

Gradle was used as the build tool which I was most familiar with and I think the build script is easy to read.


#####Do you think the priorities of the user story are appropriate in relation the overall need of currency converter?  #####

No - I don't think the scenarios cover certain areas.
Verifying the actual conversion is correct when the conversion is performed. We don't check the actual rates here 

The total contents of the results section is not tested e.g. traveller cheques rates..telegraphic transfers rates etc.

Verifying conversion results when source and destination are the same

Manual test could be added to verify when the back end system that returns the rates is down

We also do not check which currencies should be available

##### What are the possible points at which your tests can fail and how can you handle them?  #####

1:
I have written navigation steps that click links based on the link text. 

This was done to reduce the amount of code involved in navigation.

This could cause problems if the link text changed. The link would not be found and we wouldnt be able to reach the Currency converter screen

The location strategy I have used often does this. I'm not sure how frequently the UI changes but this could cause issues.

2:
These tests will only pass if the viewport is a particular size. The menu changes when the viewport shrinks to a particular size

I have maximized the browser on startup but a better strategy might be setting the viewport size

3:
There seems to be a problem with gherking processing spaces in values. 

I have therefore entered "not" instead of "not employed" in one of the KiwiSaver tests


##### How can we drive your tests using any CI tool? Explain key things we need to bear in mind. #####

The CI tool would need to ensure all software is present on the server that is going to run the tests.

How often are the tests going to be run? Should the tests be run every time that code is checked into the UI repo?

Does the server that runs the test have a screen? If not, do we need to run the tests headlessly.

Can the server that runs the tests connect to the internet? Would we need to set up a proxy within WebDriver.

How many runs of the suite do we need to retain?
How much space on the server is taken up every time the CI tool downloads the repo if this happens?
Does the results need to be stored anywhere for compliance?

Can we run the tests using Selenium grid as none of the tests will be affected if run in parallel?


     

