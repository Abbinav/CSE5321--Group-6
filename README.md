# CSE5321--Group-6

Steps to run the project:

Clone the Repo
Build & Clean the Project
Run Configurations -> SpringBoot App
Once the Tomcat Server has started, go to http://localhost:8087/calendar


Database:
We are utilizing a H2 Relationship Database Management System. The database content could be accessed using:

1.http://localhost:8087/h2-console
2.To login into DB: 
	Driver Class:org.h2.Driver
	JDBC URL: jdbc:h2:mem:testdb
	User Name: sa
	No Password required
	Connect
3. The DB Consists of an Activity Table that stores all the events created on the screen. There are 4 columns in the table:
	-ID
	-ACTIVITY_NAME
	-END_TIME
	-START_TIME


