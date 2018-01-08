package model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "TO_PROJEKT";
    private static final String DB_PASSWORD = "projekt";

    private static final String TABELA_UZYTKOWNIK = "UZYTKOWNIK";
    private static final String TABELA_UCZESTNIK = "UCZESTNIK";
    private static final String TABELA_PRELEGENT = "PRELEGENT";
    private static final String TABELA_ORGANIZATOR = "ORGANIZATOR";
    private static final String TABELA_KONFERENCJE = "KONFERENCJA"; //zamienilam‚am E na A
    private static final String KOLUMNA_ID_UZYTKOWNIK = "ID_UZYTKOWNIK";
    private static final String KOLUMNA_ID_UCZESTNIK = "ID_UCZESTNIK";
    private static final String KOLUMNA_ID_ORGANIZATOR = "ID_ORGANIZATOR";
    private static final String KOLUMNA_ID_PRELEGENT = "ID_PRELEGENT";
    private static final String KOLUMNA_LOGIN = "LOGIN";
    private static final String KOLUMNA_HASLO = "HASLO";
    private static final String KOLUMNA_EMAIL = "EMAIL";
    private static final String KOLUMNA_MIEJSCOWOSC = "MIEJSCOWOSC";


    private static final String NOWY_UZYTKOWNIK = "INSERT INTO " + TABELA_UZYTKOWNIK
            + "(ID_UZYTKOWNIK, LOGIN, HASLO, IMIE, NAZWISKO, EMAIL, MIEJSCOWOSC) VALUES"
            + "(?, ?, ?, ?, ?, ?, ?)";

    private static final String NOWY_UCZESTNIK = "INSERT INTO " + TABELA_UCZESTNIK
            + "(ID_UCZESTNIK) VALUES"
            + "(?)";

    private static final String NOWY_ORGANIZATOR = "INSERT INTO " + TABELA_ORGANIZATOR
            + "(ID_ORGANIZATOR) VALUES"
            + "(?)";

    private static final String NOWY_PRELEGENT = "INSERT INTO " + TABELA_PRELEGENT
            + "(ID_PRELEGENT) VALUES"
            + "(?)";

    private static final String ZNAJDZ_UZYTKOWNIKA = "SELECT COUNT(1) FROM "
            + TABELA_UZYTKOWNIK + " WHERE " + KOLUMNA_LOGIN + " = ?";

    private static final String SPRAWDZ_HASLO = "SELECT " + KOLUMNA_HASLO + " FROM "
            + TABELA_UZYTKOWNIK + " WHERE " + KOLUMNA_LOGIN + " = ?";

    private static final String POBIERZ_DANE = "SELECT * FROM "
            + TABELA_UZYTKOWNIK + " WHERE " + KOLUMNA_LOGIN + " = ? ";

    private static final String USUN_UZYTKOWNIKA = "DELETE FROM " + TABELA_UZYTKOWNIK
            + " WHERE " + KOLUMNA_LOGIN + " = ?";

    private static final String ZMIEN_EMAIL = "UPDATE " + TABELA_UZYTKOWNIK + " SET "
            + KOLUMNA_EMAIL + " = ? WHERE " + KOLUMNA_LOGIN + " = ?";

    private static final String ZMIEN_HASLO = "UPDATE " + TABELA_UZYTKOWNIK + " SET "
            + KOLUMNA_HASLO + " = ? WHERE " + KOLUMNA_LOGIN + " = ?";

    private static final String ZMIEN_MIEJSCOWOSC = "UPDATE " + TABELA_UZYTKOWNIK + " SET "
            + KOLUMNA_MIEJSCOWOSC + " = ? WHERE " + KOLUMNA_LOGIN + " = ?";

    private static final String NOWA_KONFERENCJA = "INSERT INTO " + TABELA_KONFERENCJE
            + "() VALUES"
            + "(?, ?, ?, ?, ?, ?, ?)";

    private static final String DANE_KONFERENCJI = "SELECT ID_KONFERENCJA,DATA_ROZPOCZECIA,OPIS,NAZWA" + " FROM "
            + TABELA_KONFERENCJE;

    private static final String MIEJSCOWOSC_KONFERENCJI = "SELECT ID_KONFERENCJA, NAZWA, OPIS from KONFERENCJA" +
            "  WHERE  KONFERENCJA.ID_MIEJSCE in (SELECT MIEJSCE.ID_MIEJSCE from MIEJSCE WHERE MIEJSCOWOSC=" + "?" + " )";

    private static final String ORGANIZATOR_KONFERENCJI = "SELECT ID_KONFERENCJA,  NAZWA, OPIS from KONFERENCJA  join " +
            "UZYTKOWNIK on  UZYTKOWNIK.ID_UZYTKOWNIK=KONFERENCJA.ID_ORGANIZATOR  where imie=" + "?" + " and nazwisko=" + "?";

    private static final String PRELEGENT_KONFERENCJI = "SELECT ID_KONFERENCJA, KONFERENCJA.NAZWA, KONFERENCJA.OPIS " +
            "from KONFERENCJA where KONFERENCJA.ID_KONFERENCJA in(SELECT WYKLAD.ID_KONFERENCJA from WYKLAD where" +
            " WYKLAD.ID_WYKLAD  in (SELECT ZGLOSZENIE.ID_WYKLAD from ZGLOSZENIE  where" +
            " ZGLOSZENIE.ID_PRELEGENT in (SELECT PRELEGENT.ID_PRELEGENT from PRELEGENT where PRELEGENT.ID_PRELEGENT in" +
            " (SELECT UZYTKOWNIK.ID_UZYTKOWNIK from  UZYTKOWNIK  where imie=" + "?" + " and nazwisko=" + "?" + "))))";

    private static final String DATA_OD_DO_KONFERENCJE = "SELECT ID_KONFERENCJA, NAZWA, OPIS from KONFERENCJA" +
            " WHERE DATA_ROZPOCZECIA BETWEEN " + "?" + " and " + "?";

    private static final String UZYTKOWNIK_PRELEGENT = "SELECT ID_UZYTKOWNIK FROM UZYTKOWNIK " +
            "WHERE ID_UZYTKOWNIK IN ( SELECT ID_PRELEGENT FROM PRELEGENT WHERE ID_PRELEGENT=" + "?" + " )";

    private static final String UZYTKOWNIK_UCZESTNIK = "SELECT ID_UZYTKOWNIK FROM UZYTKOWNIK WHERE ID_UZYTKOWNIK IN" +
            " ( SELECT ID_UCZESTNIK FROM UCZESTNIK WHERE ID_UCZESTNIK=" + "?" + " )";

    private static final String UZYTKOWNIK_ORGANIZATOR = "SELECT ID_UZYTKOWNIK FROM UZYTKOWNIK WHERE" +
            " ID_UZYTKOWNIK IN ( SELECT ID_ORGANIZATOR FROM ORGANIZATOR WHERE ID_ORGANIZATOR=" + "?" + " )";

    private static final String POBIERZ_DANE_PLATNOSC =" SELECT KONFERENCJA.NAZWA,UZYTKOWNIK.LOGIN,PLATNOSC.KWOTA, " +
            "PLATNOSC.STATUS,PLATNOSC.ID_UZYTKOWNIK from  PLATNOSC join KONFERENCJA on" +
            " KONFERENCJA.ID_KONFERENCJA=PLATNOSC.ID_KONFERENCJA join UZYTKOWNIK on UZYTKOWNIK.ID_UZYTKOWNIK=PLATNOSC.ID_UZYTKOWNIK" +
            " WHERE KONFERENCJA.ID_ORGANIZATOR = " + " ?";

    private static final String POBIERZ_DANE_ZGLOSZENIA = "select KONFERENCJA.NAZWA, ZGLOSZENIE.TEMAT,ZGLOSZENIE.STATUS " +
            "from ZGLOSZENIE join WYKLAD ON ZGLOSZENIE.ID_WYKLAD=WYKLAD.ID_WYKLAD join KONFERENCJA on " +
            "WYKLAD.ID_KONFERENCJA=KONFERENCJA.ID_KONFERENCJA where ZGLOSZENIE.ID_PRELEGENT=" + "?";

    private static final String POBIERZ_DANE_KONFERENCJE = "SELECT   KONFERENCJA.NAZWA, KONFERENCJA.DATA_ROZPOCZECIA, " +
    "MIEJSCE.MIEJSCOWOSC, PLATNOSC.STATUS from KONFERENCJA  JOIN MIEJSCE on KONFERENCJA.ID_MIEJSCE=MIEJSCE.ID_MIEJSCE " +
    "join PLATNOSC on KONFERENCJA.ID_KONFERENCJA=PLATNOSC.ID_KONFERENCJA " +
    "JOIN UZYTKOWNIK ON PLATNOSC.ID_UZYTKOWNIK=UZYTKOWNIK.ID_UZYTKOWNIK where UZYTKOWNIK.ID_UZYTKOWNIK=" + " ? ";

    private static final String MINIONE_KONFERENCJE = "SELECT KONFERENCJA.ID_KONFERENCJA," +
            "KONFERENCJA.NAZWA, KONFERENCJA.OPIS from KONFERENCJA WHERE DATA_ZAKONCZENIA<CURRENT_DATE";

    private static final String POTWIERDZENIE_PLATNOSCI = "UPDATE PLATNOSC SET STATUS = "+"?"+" where ID_UZYTKOWNIK =" + "?";

    private static final String DODAJ_OCENE_MIEJSCA = "INSERT INTO OCENA_MIEJSCA(ID_MIEJSCA," +
            " ID_UZYTKOWNIKA,OCENA) VALUES(" + "?" + "," + "?" + "," + "?" + ")";

    private static final String DODAJ_OCENE_KONFERENCJI = "INSERT INTO OCENA_KONFERENCJI(ID_KONFERENCJA," +
            " ID_UZYTKOWNIKA,OCENA) VALUES(" + "?" + "," + "?" + "," + "?" + ")";

    private static final String DODAJ_OCENE_PRELEGENTA = "INSERT INTO OCENA_PRELEGENTA(ID_PRELEGENTA," +
            " ID_UZYTKOWNIKA,OCENA) VALUES(" + "?" + "," + "?" + "," + "?" + ")";

    private Connection connection;

    private static Uzytkownik user;
    private static Conference conference;
    private static UserData userData;
    /***
     * lazy initialization - instance of this class won't be created, until
     * the first time other class calls the getInstance method
     */
    private static Server instance = new Server();

    /***
     *  private constructor, cannot be used outside this class
     */
    private Server() {
    }

    /***
     * @return singleton - there is only one instance of this object
     */
    public static Server getInstance() {
        return instance;
    }

    public static Uzytkownik getUserInstance() {
        return user;
    }

    public static Conference getConferenceInstance() {
        return conference;
    }

    public static UserData getUserDataInstance() {
        return userData;
    }

    List<Conference> conferences = new ArrayList<Conference>();

    List<UserData> userDataList = new ArrayList<UserData>();
    public boolean open() {

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    /**
     * close connection to db
     */
    public void close() {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    /**
     * @param table from db
     * @return number of rows in table
     */
    private int getCount(String table) {

        String sql = "SELECT COUNT(*) AS count FROM " + table;

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            int count = 0;
            while (results.next()) {
                count = results.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        }
    }

    /**
     * @param table  from db
     * @param column ID column
     * @return new value used as an ID of new record in db
     */
    private int getId(String table, String column) {

        String sql = "SELECT MAX(" + column + ") AS max FROM " + table;
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            int maxId = 0;
            if (result.next()) {
                maxId = result.getInt(1);
            }
            return maxId;
        } catch (SQLException e) {
            System.out.println("getID() error: " + e.getMessage());
            return -1;
        }
    }

    /**
     * @param login check if this login already is used in db
     * @return true if login is free
     */
    private boolean isLoginFree(String login) {

        try {
            PreparedStatement statement = connection.prepareStatement(ZNAJDZ_UZYTKOWNIKA);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) != 1;

        } catch (SQLException e) {
            System.out.println("isLoginFree error: " + e.getMessage());
            return false;
        }
    }

    /**
     * create new user of declared type if given login is free
     */

    public boolean insertUser(String login, String haslo,
                              String imie, String nazwisko,
                              String email, String miejscowosc, TypUzytkownika typ) {

        if (isLoginFree(login)) {
            try {
                PreparedStatement statement = connection.prepareStatement(NOWY_UZYTKOWNIK);

                int id = getId(TABELA_UZYTKOWNIK, KOLUMNA_ID_UZYTKOWNIK) + 1;

                statement.setInt(1, id);
                statement.setString(2, login);
                statement.setString(3, haslo);
                statement.setString(4, imie);
                statement.setString(5, nazwisko);
                statement.setString(6, email);
                statement.setString(7, miejscowosc);

                statement.executeUpdate();

                if (typ == TypUzytkownika.UCZESTNIK) {
                    statement = connection.prepareStatement(NOWY_UCZESTNIK);
                    statement.setInt(1, id);
                }
                if (typ == TypUzytkownika.ORGANIZATOR) {
                    statement = connection.prepareStatement(NOWY_ORGANIZATOR);
                    statement.setInt(1, id);
                }
                if (typ == TypUzytkownika.PRELEGENT) {
                    statement = connection.prepareStatement(NOWY_PRELEGENT);
                    statement.setInt(1, id);
                }

                statement.executeUpdate();
                System.out.println("Uzytkownik dodany.");
                return true;

            } catch (SQLException e) {
                System.out.println("inserUser: nie dodano uĹĽytkownika: " + e.getMessage());
                return false;
            }
        }
        System.out.println("insertUser: nie dodano uzytkownika");
        return false;

    }

    /**
     * @return true if user with given password exists in db
     */
    public boolean logIn(String login, String haslo) {

        try {
            PreparedStatement statement = connection.prepareStatement(SPRAWDZ_HASLO);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getString(1).equals(haslo)) {
                System.out.println("Logowanie: haslo poprawne");

                statement = connection.prepareStatement(POBIERZ_DANE);
                statement.setString(1, login);
                resultSet = statement.executeQuery();
                resultSet.next();

                user = new Uzytkownik(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                System.out.println(user.toString());
                return true;
            }
            System.out.println("Logowanie: niepoprawne hasĹ‚o.");
            return false;
        } catch (SQLException e) {
            System.out.println("Logowanie error: " + e.getMessage());
            return false;
        }
    }

    /**
     * delete user from table UZYTKOWNIK and other related tables (FK on delete cascade)
     */
    public void deleteUsesr() {
        try {
            PreparedStatement statement = connection.prepareStatement(USUN_UZYTKOWNIKA);
            statement.setString(1, user.getLogin());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Usun uzytkownika error: " + e.getMessage());
        }
    }

    /**
     * modify email
     */
    public void modifyUserEmail(String newEmail) {
        user.setEmail(newEmail);
        try {
            PreparedStatement statement = connection.prepareStatement(ZMIEN_EMAIL);
            statement.setString(1, newEmail);
            statement.setString(2, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("modifyUserEmail error: " + e.getMessage());
        }
    }

    /**
     * modify city
     */
    public void modifyUserMiejscowosc(String newMiejscowosc) {
        user.setMiejscowosc(newMiejscowosc);
        try {
            PreparedStatement statement = connection.prepareStatement(ZMIEN_MIEJSCOWOSC);
            statement.setString(1, newMiejscowosc);
            statement.setString(2, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("modifyUserMiejscowosc error: " + e.getMessage());
        }
    }

    /**
     * modify password
     */
    public void modifyUserHaslo(String newHaslo) {
        user.setHaslo(newHaslo);
        try {
            PreparedStatement statement = connection.prepareStatement(ZMIEN_HASLO);
            statement.setString(1, newHaslo);
            statement.setString(2, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("modifyUserHaslo error: " + e.getMessage());
        }
    }

    /**
     * conference information
     * @return true if are information about conference
     */
    public boolean getConferenceInfo() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DANE_KONFERENCJI);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                conferences.add(new Conference(
                       resultSet.getInt(1),
                        resultSet.getDate(2),
                        resultSet.getString(3),
                         resultSet.getString(4))
                );


            }
            if (!conferences.isEmpty()) {
                conference = new Conference(conferences);
                return true;
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * conference information about location
     *@param miejscowosc conference location
     * @return true if there is information about the conference
     */

    public boolean searchLocation(String miejscowosc) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(MIEJSCOWOSC_KONFERENCJI);
            statement.setString(1, miejscowosc);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                conferences.add(new Conference(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));


            }
            if (!conferences.isEmpty()) {
                conference = new Conference(conferences);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * search Organiser of conference
     *@param imie Organiser name
     *@param nazwisko Organiser surname
     * @return true if there is information about organiser
     */

    public boolean searchOrganiser(String imie, String nazwisko) {
        PreparedStatement statement = null;
        try {

            statement = connection.prepareStatement(ORGANIZATOR_KONFERENCJI);
            statement.setString(1, imie);
            statement.setString(2, nazwisko);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                conferences.add(new Conference(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));


            }
            if (!conferences.isEmpty()) {
                conference = new Conference(conferences);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * search Lecturer of conference
     *@param imie Lecturer name
     *@param nazwisko Lecturer surname
     * @return true if there is information about lecturer
     */
    public boolean searchLecturer(String imie, String nazwisko) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(PRELEGENT_KONFERENCJI);
            statement.setString(1, imie);
            statement.setString(2, nazwisko);
            int i;
            String c, d;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                conferences.add(new Conference(
                        i = resultSet.getInt(1),
                        c = resultSet.getString(2),
                        d = resultSet.getString(3)));

                System.out.print(i + c + d);

            }
            if (!conferences.isEmpty()) {
                conference = new Conference(conferences);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * search date of conference
     *@param dataDo start date
     *@param dataOd end date
     * @return true if there is information about date of conference
     */
    public boolean searchDate(String dataOd, String dataDo) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DATA_OD_DO_KONFERENCJE);
            statement.setString(1, dataOd);
            statement.setString(2, dataDo);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                conferences.add(new Conference(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));


            }
            if (!conferences.isEmpty()) {
                conference = new Conference(conferences);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param postac type of user currently logged in
     * @param id     user currently logged in
     * @return true if there is information about person
     */
    public boolean userType(String postac, int id) {
        int x;

        PreparedStatement statement = null;
        try {
            if (postac.equals("PRELEGENT")) {
                statement = connection.prepareStatement(UZYTKOWNIK_PRELEGENT);
                statement.setInt(1, id);
            }

            if (postac.equals("ORGANIZATOR")) {
                statement = connection.prepareStatement(UZYTKOWNIK_ORGANIZATOR);
                statement.setInt(1, id);
            }


            if (postac.equals("UCZESTNIK")) {
                statement = connection.prepareStatement(UZYTKOWNIK_UCZESTNIK);
                statement.setInt(1, id);
            }


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                x = resultSet.getInt(1);
                System.out.println(x + " id uczestnika");
                return true;
            }


        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("nie ma wynikĂłw ");
        }

        return false;
    }


    /**
     * information about the participant's payment for conferences
     * @param id user id
     * @return true if there is information about payment
     */
    public boolean paymentInfo(int id) {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(POBIERZ_DANE_PLATNOSC);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userDataList.add(new UserData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));


            }
            if (!userDataList.isEmpty()) {
                userData = new UserData(userDataList);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * information about the Lecturer applications
     * @param id user currently logged in
     * @return true if there is information about applications
     */
    public boolean applicationInfo(int id) {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(POBIERZ_DANE_ZGLOSZENIA);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userDataList.add(new UserData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));


            }
            if (!userDataList.isEmpty()) {
                userData = new UserData(userDataList);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * information about conference and status of payment
     * @param id user currently logged in
     * @return true if there is information about applications
     */
    public boolean conferenceAndPaymentInfo(int id) {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(POBIERZ_DANE_KONFERENCJE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userDataList.add(new UserData(
                        resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)));


            }
            if (!userDataList.isEmpty()) {
                userData = new UserData(userDataList);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * information about past conferences
     * @return true if there is information about conference
     */

    public boolean pastConferenceInfo() {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(MINIONE_KONFERENCJE);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                conferences.add(new Conference(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));


            }
            if (!conferences.isEmpty()) {
                conference = new Conference(conferences);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * modify payment status
     *@param id user id
     */
    public void modifyUserPayment(int id)
    {

        try {
            PreparedStatement statement = connection.prepareStatement(POTWIERDZENIE_PLATNOSCI);
           statement.setInt(1,1);
            statement.setInt(2,id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" error: " + e.getMessage());
        }
    }
    /**
     * add location mark
     *@param nazwa name of conference
     *@param id_uzytkownika id user
     *@param ocena location mark
     */
    public void addLocationMark(String nazwa, int id_uzytkownika, int ocena)
    {
        int id_miejsca=0;
        String sql = "SELECT ID_MIEJSCE FROM KONFERENCJA WHERE NAZWA=" + "?";
        try {
            PreparedStatement statementUserId = connection.prepareStatement(sql);
            statementUserId.setString(1,nazwa);

            ResultSet resultSet =statementUserId.executeQuery();
            if(resultSet.next())
            id_miejsca= resultSet.getInt(1);

            PreparedStatement statement = connection.prepareStatement(DODAJ_OCENE_MIEJSCA);
            statement.setInt(1,id_miejsca);
            statement.setInt(2,id_uzytkownika);
            statement.setInt(3,ocena);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
    /**
     * add conference mark
     *@param id_konferencji conference id
     *@param id_uzytkownika id user
     *@param ocena conference mark
     */
    public void addConferenceMark(int id_konferencji, int id_uzytkownika, int ocena)
    {
        try {
            PreparedStatement statement = connection.prepareStatement(DODAJ_OCENE_KONFERENCJI);
            statement.setInt(1,id_konferencji);
            statement.setInt(2,id_uzytkownika);
            statement.setInt(3,ocena);


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
    /**
     * add lecturer mark
     *@param imie lecturer name
     *@param nazwisko lecturer surname             
     *@param id_uzytkownika id user
     *@param ocena conference mark
     */
    public void addLecturerMark(String imie,String nazwisko,int id_uzytkownika, int ocena)
    { 
        int id_prelegenta=0;
        String sql = "SELECT ID_UZYTKOWNIK FROM UZYTKOWNIK WHERE IMIE= " + " ? "+"AND" + " NAZWISKO = " + " ? ";
        try {
            PreparedStatement statementUserId = connection.prepareStatement(sql);
            statementUserId.setString(1,imie);
            statementUserId.setString(2,nazwisko);

            ResultSet resultSet =statementUserId.executeQuery();
            if(resultSet.next())
           id_prelegenta= resultSet.getInt(1);

            PreparedStatement statement = connection.prepareStatement(DODAJ_OCENE_PRELEGENTA);
            statement.setInt(1,id_prelegenta);
            statement.setInt(2,id_uzytkownika);
            statement.setInt(3,ocena);



            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" error: " + e.getMessage());
        }

    }

}