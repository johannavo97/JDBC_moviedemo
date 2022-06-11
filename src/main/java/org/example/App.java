package org.example;

import java.sql.*;

public class App
{
    static final String DB_URL = "jdbc:mariadb://localhost:3306/jdbcmoviedemo?createDatabaseIfNotExist=true";
    static final String USER = "root";
    static final String PASS = "root";
    public static void main( String[] args )throws ClassNotFoundException {

        ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            //Drop table MOVIES if exists
            try (Statement deleteS = conn.createStatement()) {
                deleteS.executeUpdate("DROP TABLE IF EXISTS MOVIES");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Create table MOVIES if not exists
            try (Statement CreateS = conn.createStatement()) {
                String sql = "create table if not exists MOVIES (" +
                        "                          id int auto_increment primary key," +
                        "                          title varchar(255) not null," +
                        "                          genre varchar(255) not null," +
                        "                          yearOfRelease INTEGER" +
                        ")";
                CreateS.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Insert three records
            try (Statement InsertS = conn.createStatement()) {
                InsertS.executeUpdate("insert into MOVIES (title, genre, yearOfRelease) values('Memory', 'Action', 2022)");
                InsertS.executeUpdate("insert into MOVIES (title, genre, yearOfRelease) values('Minions', 'Comedy', 2015)");
                InsertS.executeUpdate("insert into MOVIES (title, genre, yearOfRelease) values('Men', 'Horror', 2022)");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Update records
            try (PreparedStatement ps = conn.prepareStatement("UPDATE MOVIES SET title = ? WHERE id = ?")) {
                ps.setString(1, "Morbius");
                ps.setInt(2, 1);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Delete a specified record by id
            try (Statement deS = conn.createStatement()) {
                deS.execute("DELETE FROM MOVIES WHERE id = 3");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Display records
            try (Statement displayS = conn.createStatement()) {
                rs = displayS.executeQuery("SELECT * FROM MOVIES");

                System.out.printf("%-4s %-15s %-15s %-10s%n",
                        "ID",
                        "Title",
                        "Genre",
                        "Year of release"
                );
                System.out.println("--------------------------------------------");
                while (rs.next()) {
                    System.out.printf("%-4d %-15s %-15s %-10d%n",
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4)
                    );
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
