public class Uzytkownik {
    private String imie;
    private String nazwisko;
    private String email;
    private String numerTelefonu;

    public String login;
    public String haslo;

    public boolean admin;

    public Uzytkownik(String imie, String nazwisko, String email, String numerTelefonu, String login, String haslo, boolean admin) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
        this.login = login;
        this.haslo = haslo;
        this.admin = admin;
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

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
