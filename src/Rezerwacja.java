public class Rezerwacja {
    private Lot lot;
    private Uzytkownik uzytkownik;
    private int liczbaMiejsc;
    private double cena;
    private boolean statusPlatnosci;
    private String numer_rezerwacji;

    public Rezerwacja(Lot lot, Uzytkownik uzytkownik, int liczbaMiejsc, double cena, boolean statusPlatnosci) {
        this.lot = lot;
        this.uzytkownik = uzytkownik;
        this.liczbaMiejsc = liczbaMiejsc;
        this.cena = cena;
        this.statusPlatnosci = statusPlatnosci;
    }

    private Lot getLot() {
        return lot;
    }

    private void setLot(Lot lot) {
        this.lot = lot;
    }

    private Uzytkownik getKlient() {
        return uzytkownik;
    }

    private void setKlient(Uzytkownik klient) {
        this.uzytkownik = klient;
    }

    private int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    private void setLiczbaMiejsc(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    private double getCena() {
        return cena;
    }

    private void setCena(double cena) {
        this.cena = cena;
    }

    private boolean isStatusPlatnosci() {
        return statusPlatnosci;
    }

    private void setStatusPlatnosci(boolean statusPlatnosci) {
        this.statusPlatnosci = statusPlatnosci;
    }

    protected String getNumerRezerwacji(){
        return numer_rezerwacji;
    }
}
