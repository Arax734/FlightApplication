public class Rezerwacja {
    private int numer_rezerwacji;
    private Lot lot;
    private Uzytkownik uzytkownik;
    private int liczbaMiejsc;
    private double cena;
    BazaUzytkownikow baza_uzytkownikow = BazaUzytkownikow.getInstance();
    BazaLotow baza_lotow = BazaLotow.getInstance();
    private String numerLotu;
    private String loginuzytkownika;

    public Rezerwacja(int numer_rezerwacji, String numerLotu, String uzytkownik, int liczbaMiejsc, double cena) {
        this.numer_rezerwacji = numer_rezerwacji;
        this.lot = BazaLotow.getInstance().getLoty().get(BazaLotow.getInstance().getFlightSlot(numerLotu));
        this.uzytkownik = baza_uzytkownikow.getUzytkownicy().get(baza_uzytkownikow.getUserSlot(uzytkownik));
        this.liczbaMiejsc = liczbaMiejsc;
        this.cena = cena;
        this.numerLotu = numerLotu;
        this.loginuzytkownika = uzytkownik;
    }

    protected Lot getLot() {
        return lot;
    }

    protected Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    protected int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    protected double getCena() {
        return cena;
    }

    protected int getNumerRezerwacji(){
        return numer_rezerwacji;
    }

    protected String getNumerLotu(){
        return numerLotu;
    }

    protected String getLoginUzytkownika(){
        return loginuzytkownika;
    }
}
