package model;

/**
 * Created by Alicja on 2017-11-17.
 */
public class Uzytkownik {

    private int id;
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String email;
    private String miejscowosc;

    Uzytkownik(int id, String login, String haslo, String imie,
               String nazwisko, String email, String miejscowosc) {
        this.id = id;
        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.miejscowosc = miejscowosc;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    void setImie(String imie) {
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

    void setEmail(String email) {
        this.email = email;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }


    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", miejscowosc='" + miejscowosc + '\'' +
                '}';
    }
}