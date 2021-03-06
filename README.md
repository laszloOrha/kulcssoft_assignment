# kulcssoft_assignment

Created with IntelliJ IDEA<br>

Heroku: https://kulcssoft-userapp-ol.herokuapp.com/<br>

**The code on the master branch is set for Heroku deployment while the one on the development branch can be accessed on http://localhost:8080 after the server has been started from the IDEA.**

There are 2 sample users already created and filled with different sample data. You can register new users and add data for that account.

**Sample users:**<br>
ADMIN1:<br>
E-mail: 'admin@kulcssoft.hu'<br>
Password: 'Kulcssoft01'<br>

ADMIN2:<br>
E-mail: 'admin2@kulcssoft.hu'<br>
Password: 'Kulcssoft01'<br>

**How to launch from Intellij:**<br>
-Java Development Kit 8 (JDK 8) need to be installed on the computer.<br>
-The appliction is built with springBoot.<br>
-The pom.xml has all the dependencies needed which are downloaded upon the first time the project is opened.<br>
-At the top bar near the 'play' button please choose the KulcsSoftAppApplication option click the play button.<br>
-If it doesn't work then the SDK should be set to Java 1.8.<br>
-While the server run, you can reach the application on http://localhost:8080<br>
-You can reach the DB separately on http://localhost:8080/h2-console, the username and password can be found in the file called 'application.properties'.<br>
-In the test folder there are tests for the repositories, you can run them with the little play buttons near the method names or you can run them all with the one near the class name.<br>
