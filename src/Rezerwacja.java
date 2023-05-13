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

    protected Lot getLot() {

        return lot;
    }

    private void setLot(Lot lot) {

        this.lot = lot;
    }

    protected Uzytkownik getUzytkownik() {

        return uzytkownik;
    }

    private void setUzytkownik(Uzytkownik uzytkownik) {

        this.uzytkownik = uzytkownik;
    }

    protected int getLiczbaMiejsc() {

        return liczbaMiejsc;
    }

    private void setLiczbaMiejsc(int liczbaMiejsc) {

        this.liczbaMiejsc = liczbaMiejsc;
    }

    protected double getCena() {

        return cena;
    }

    private void setCena(double cena) {

        this.cena = cena;
    }

    protected boolean getStatusPlatnosci() {

        return statusPlatnosci;
    }

    protected void setStatusPlatnosci(boolean statusPlatnosci) {

        this.statusPlatnosci = statusPlatnosci;
    }

    protected String getNumerRezerwacji(){

        return numer_rezerwacji;
    }
}
