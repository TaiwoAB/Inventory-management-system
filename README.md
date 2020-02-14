# Inventory-management-system
This project is to create an application for an inventory management managament system that an end user can interact with via Command line interface. 
##Prequisites FOR USAGE PURPOSES
```
To create this system
- Dowload an integrated development environment. The one used for this project is Eclipse - (https://www3.ntu.edu.sg/home/ehchua/programming/howto/EclipseJava_HowTo.html)
-Download Java which is used to craete the source code - (https://www3.ntu.edu.sg/home/ehchua/programming/howto/EclipseJava_HowTo.html)
-Create a Google cloud account (GCP) to create an instance to host the database -(https://cloud.google.com/sql/docs/mysql/quickstart)
-Download MySQL to craete the database-(https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/windows-install-archive.html
-Download Maven as the build tool-(https://howtodoinjava.com/maven/how-to-install-maven-on-windows/
-Git is used as the VCS in this project-(https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
```
```
After setting up, follow these steps:
1)	You can use git to perform a pull request
2)	Open an IDE and,
3)Run the Application to add update delete and read customers and items from your database.
```
##FOR CREATION PURPOSES-
```
Install Eclipse -Ensure that when java is being installed you download both jdk and jre (mainly jdk).
-After downloading eclipse you should then install maven ensuring that it is added to your path file.
-Download the MySQL workbench.
-Create a GCP Instance and generate an IP address to host your database and connect your database to it.
-Add the relevant to plugins such as mockito, apache.maven, junit, mysql connector and other dependencies needed 
-Ensure that you connect your workspace to your database (via mysql connector plugin).
-Test the connection between your workspace and database by inserting data and querying data.
-Ensure that all relevant JDBC drivers are installed and contain your database name and GCP instance ip address.
```
###Unit Testing
```
Junit is the open source framework and a build tool used for unit testing of methods of classes.The is done to check if each code unit passes a test. For this, download the Junit plugin or dependency and place it in you pom file. It can be found here:To run such a test a Junit plugin is downloaded from https:0//mvnrepository.com/artifact/junit/junit. Create a test class to perform the unit testing
Versioning We use SemVer for versioning.
##Authors
* **Christopher Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Taiwo Aina-Badejo** - *Main body of work*- [TaiwoAB](https://github.com/TaiwoAB)
## License
This project is licensed under the MIT license - see the LICENSE.md file for details
Apache License, Version 2.0 (LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0)
MIT license (LICENSE-MIT or http://opensource.org/licenses/MIT)
