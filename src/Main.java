public class Main {
    public static void main(String[] args) {
        BazaUzytkownikow baza = new BazaUzytkownikow();
        Opcje menu = new Opcje(baza);
        baza.dodajUzytkownika(new Uzytkownik("Kacper","Fryt","kf@gmail.com","123456789",
                "kfryt","haslo",true));
    }
}