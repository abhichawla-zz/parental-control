Notes:
This exercise is based on java 8.
As a build tool, gradle is used and all the dependencies are mentioned in the build.gradle file.

#For Compiling
gradle compile

#For Testing
gradle test

Architecture:
1. ParentalControlService has a logic to call MovieService to for movie's parent control preference and compares
it with the preference set by client. It uses ParentalControlComparator class to compare the preferences.
2. TechnicalFailureException and TitleNotFoundException are exception classes that MovieService can throw.
3. MessageCallback interface is used to display message to client as the requirement suggest to return only boolean,
   object of MessageCallback is passed in a method to store message and display.


Assumption:
1. No implementation is provided for 'MovieService' as the requirement suggests only to provide implementation of
'ParentalControlService'. MovieService and associated expectations are mocked in ParentalControlServiceTest.
2. No implementation of MessageCallBack or other methods are defined as they are not required as per scope.
