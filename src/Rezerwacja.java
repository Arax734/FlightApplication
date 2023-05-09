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

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Uzytkownik getKlient() {
        return uzytkownik;
    }

    public void setKlient(Uzytkownik klient) {
        this.uzytkownik = klient;
    }

    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public boolean isStatusPlatnosci() {
        return statusPlatnosci;
    }

    public void setStatusPlatnosci(boolean statusPlatnosci) {
        this.statusPlatnosci = statusPlatnosci;
    }

    public String getNumerRezerwacji(){
        return numer_rezerwacji;
    }
}
