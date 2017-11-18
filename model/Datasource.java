package model;

import java.sql.*;

/**
 * Created by Alicja on 2017-11-17.
 */
public class Datasource {
    public static final String DB_NAME = "konferencje.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\ConferenceSystem\\db\\" + DB_NAME;

    private Connection conn;

    private static Datasource instance = new Datasource();
    private PreparedStatement queryUzytkownik;
    private PreparedStatement insertIntoArtists;
    public Statement stmt;


    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
stmt=conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Uzytkownik");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
                        rs.getString(3));
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

}
