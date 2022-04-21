# Techonologies used

1. Java 1.8
2. Spring (JDBC, MVC) 5.3.19
3. Servlet 2.4
4. JSP 2.1
5. JSTL 1.2
6. MySQL 5.1
7. Lombok 1.18.22
8. Jackson 2.13

# Steps to execute the application

1. Dump the sql script in MySQL database. In two ways you can dump the sql script.

   1. Copy all the content from `kpacs-sql-script.sql` file which is located inside the `sql-script` directory and paste into the MySQL command.
   2. If you have MySQL Workbench then goto the main memu `Server` -> Data Import. Select the 2nd radio button i.e. Import from Self-Contained File and choose the location where `kpacs-sql-script.sql` file is present.

2. Update the MySQL database `username` & `password`. Goto the `src/main/resources` and update the `database.properties` file.

3. Open the project into Terminal/Command Prompt and start the build using `mvn clean install`.

4. Start the Web Server(Apache Tomcat) and deploy the war file which you can find inside the target folder.

5. Goto the [http://localhost:8080/kpac-task/](http://localhost:8080/kpac-task/) and enjoy with the application.