package model;

/**
 * Created by Alicja on 2017-11-17.
 */
public class Uzytkownik {

    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String email;
    private String miejscowosc;
    private TypUzytkownika typUzytkownika;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;

    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypUzytkownika getTypUzytkownika() {
        return typUzytkownika;
    }

    public void setTypUzytkownika(TypUzytkownika typUzytkownika) {
        this.typUzytkownika = typUzytkownika;
    }

}
