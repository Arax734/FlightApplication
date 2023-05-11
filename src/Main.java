public class Main {
    public static void main(String[] args) {
        // Tworzymy bazy uzytkownikow i rezerwacji, otwieramy menu
        BazaUzytkownikow baza_uzytkownikow = new BazaUzytkownikow();
        BazaRezerwacji baza_rezerwacji = new BazaRezerwacji();
        Opcje menu = new Opcje(baza_uzytkownikow, baza_rezerwacji);
    }
}