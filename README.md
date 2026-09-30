\# Student Management System



A Java-based desktop application for managing student records using

Java Swing, JDBC, and MySQL.



\## Features



\- Add student records

\- Search students using USN

\- Update student information

\- Delete student records

\- Display all student records

\- Input validation

\- Persistent data storage using MySQL

\- Simple desktop GUI



\## Technologies Used



\- Java

\- Java Swing

\- JDBC

\- MySQL

\- MySQL Connector/J



\## Database



The application uses a MySQL database named:



student\_management



The database contains a `students` table with:



\- ID

\- Name

\- Age

\- USN

\- Marks



The database setup script is available in:



database/student\_management.sql



\## Project Structure



StudentManagementSystem\_GitHub/



├── database/



│   └── student\_management.sql



├── lib/



├── Student.java



├── StudentGUI.java



├── StudentManagementSystem.java



├── .gitignore



└── README.md



\## How to Run



1\. Install Java JDK.

2\. Install MySQL Server.

3\. Create the database using:

&#x20;  `database/student\_management.sql`

4\. Configure the required database environment variables.

5\. Compile the Java source files with MySQL Connector/J.

6\. Run `StudentGUI`.



\## Main Operations



\### Add Student

Enter the student's name, age, USN, and marks and click Add Student.



\### Search Student

Enter the student's USN and click Search.



\### Update Student

Enter the updated information and click Update.



\### Delete Student

Enter the student's USN and click Delete.



\## Future Enhancements



\- Student login system

\- Admin login

\- Attendance management

\- Grade calculation

\- Export student records

\- Improved user interface

