# JDBC_moviedemo
Task 1¶
Using the JDBC API and any relational database (e.g. H2) make the following queries:

create a table MOVIES with columns: id of type INTEGER AUTO INCREMENT,title of type VARCHAR (255), genre of type VARCHAR (255),yearOfRelease of type INTEGER. Note that a table named MOVIE may already exist. In that case, delete it.
add any three records to the MOVIES table
update one selected record (use the PreparedStatement)
delete selected record with specified id
display all other records in the database

In the task, focus on the correct use of the JDBC API. All queries can be made directly in the main method.
Use a single connection to execute all queries. However, remember to use try-with-resources when opening a 
connection and creating objects such asStatement or PreparedStatement. Also, don't worry about exception
handling in this task (in case of error, display stacktrace).
