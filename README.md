# TestProject
Assignment for wallethub

For facebook login exercise, i have created a standalone test file

For review posting exercise, I have created a scalable framework using testNg, extent reports and 
page object model with externalized object repository, test data, and config.property file

For easy maintenance one page file will be present for each screen. 

The object properties are stored in an external repository which is loaded when the page constructor is called.


Steps to configure the project:

1.Please add all the necessary jar files to build path.
2. Import TestNG library
2.Place the driver.exe files in a folder and add that path as ChromeDriver or FirefoxDriver
3.If you do not want logs in D:/, change the path to your desired location
4.Browser can be Chrome or Firefox


About the project:

package:

com.wallethub.automation
Contains all the test files. If more test cases are to be automated, additional test files will be added here

FaceBookStatusTest.java : 

Contains automation for facebook login functionality. 
This is a stand alone test file and is to be run as a java application

PostReviewTest.java

This is an automated test to post a review as per exercise 2. This has to be run as a TestNG test.

This test uses a test automation framework which I developed for wallethub website functionalities.

This scalable framework can be further used to automate more test cases easily.

Test data for this test is available in resources/data.testdata


SignUpTest.java :

Additional standalone test file.

com.wallethub.commonactions

This package contains common functionalities to be used across multiple test cases. 
(e.g, reading data from csv file, reading object repository, entering text, clicking a button etc)


BaseTestCase.java:

contains beforetest method to launch webdriver, initialize reporter, etc)
contains aftermethod to quit the webdriver and flush data into reporter

com.wallethub.pages

Contains page files. Corresponding object repository file for each page is placed in resources/data.objectproperties



