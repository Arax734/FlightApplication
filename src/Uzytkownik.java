public class Uzytkownik {
    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private String numerTelefonu;

    public String login;
    public String haslo;

    public boolean admin;

    public Uzytkownik(int id,String imie, String nazwisko, String email, String numerTelefonu, String login, String haslo, boolean admin) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
        this.login = login;
        this.haslo = haslo;
        this.admin = admin;
    }
    protected int getID(){
        return id;
    }
    protected String getImie() {
        return imie;
    }

    protected String getNazwisko() {
        return nazwisko;
    }

    protected String getEmail() {
        return email;
    }

    protected String getNumerTelefonu() {

        return numerTelefonu;
    }

    protected String getLogin() {

        return login;
    }

    protected String getHaslo() {

        return haslo;
    }
    protected boolean isAdmin(){
        return admin;
    }

}
