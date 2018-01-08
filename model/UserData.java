package model;


import java.util.Date;
import java.util.List;

public class UserData {
    private int status;
    private double kwota;
    private String nazwaKonferencji;
    private String login;
    private String miejsce;
    private Date data;
    private String temat;
    private String imie;
    private String nazwisko;
    private String tresc;
    List<UserData> userData;


    public List<UserData> getUserData() {
        return userData;
    }


    public UserData(int status, double kwota, String nazwaKonferencji, String login, String miejsce, Date data, String temat, String imie, String nazwisko, String tresc) {
        this.status = status;
        this.kwota = kwota;
        this.nazwaKonferencji = nazwaKonferencji;
        this.login = login;
        this.miejsce = miejsce;
        this.data = data;
        this.temat = temat;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.tresc = tresc;
    }

    public UserData(String nazwaKonferencji, String login, double kwota, int status) {
        this.status = status;
        this.kwota = kwota;
        this.nazwaKonferencji = nazwaKonferencji;
        this.login = login;
    }

    public UserData(String nazwaKonferencji, String temat, int status) {
        this.status = status;

        this.nazwaKonferencji = nazwaKonferencji;
        this.temat = temat;
    }

    public UserData(String nazwaKonferencji, Date date, String miejsce, int status) {
        this.status = status;
        this.data = date;
        this.nazwaKonferencji = nazwaKonferencji;
        this.miejsce = miejsce;
    }

    public int getStatus() {
        return status;
    }

    public double getKwota() {
        return kwota;
    }

    public String getNazwaKonferencji() {
        return nazwaKonferencji;
    }

    public String getLogin() {
        return login;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public Date getData() {
        return data;
    }

    public String getTemat() {
        return temat;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getTresc() {
        return tresc;
    }

    public UserData(List<UserData> userData) {

        this.userData = userData;


    }
}
