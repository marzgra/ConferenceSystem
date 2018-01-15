package model;

public class Zgloszenie {
    private String nazwaKonferencji;
    private String nazwaWykladu;
    private String nazwaZgloszenia;

    public Zgloszenie(String nazwaKonferencji, String nazwaWykladu, String nazwaZgloszenia) {
        this.nazwaKonferencji = nazwaKonferencji;
        this.nazwaWykladu = nazwaWykladu;
        this.nazwaZgloszenia = nazwaZgloszenia;
    }

    @Override
    public String toString() {
        return (nazwaKonferencji + " " + nazwaWykladu + " " + nazwaZgloszenia);
    }
}
