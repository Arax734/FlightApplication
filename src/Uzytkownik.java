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

    private String getImie() {
        return imie;
    }

    private void setImie(String imie) {
        this.imie = imie;
    }

    private String getNazwisko() {
        return nazwisko;
    }

    private void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    private String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private String getNumerTelefonu() {
        return numerTelefonu;
    }

    protected String getLogin() {
        return login;
    }

    protected String getHaslo() {
        return haslo;
    }

    private void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
