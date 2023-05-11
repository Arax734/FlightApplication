public class BazaRezerwacji {
    private Rezerwacja[] rezerwacje = new Rezerwacja[500];
    private int ilosc_rezerwacji;

    BazaRezerwacji(){
        for(int x=0; x< rezerwacje.length; x++){
            rezerwacje[x] = null;
        }
    }

    private int showLastSlot(){
        return ilosc_rezerwacji;
    }

    private boolean checkIfExists(String numer_rezerwacji, BazaRezerwacji baza) {
        for (int x = 0; x < baza.rezerwacje.length; x++) {
            if(baza.rezerwacje[x] != null) {
                if (baza.rezerwacje[x].getNumerRezerwacji().equals(numer_rezerwacji)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getUserSlot(String numer_rezerwacji, BazaRezerwacji baza){
        if(this.checkIfExists(numer_rezerwacji, baza)) {
            for (int x = 0; x < baza.rezerwacje.length; x++) {
                if (baza.rezerwacje[x] != null) {
                    if (baza.rezerwacje[x].getNumerRezerwacji().equals(numer_rezerwacji)) {
                        return x;
                    }
                }
            }
        }
        // Sytuacja nie powinna mieć miejsca
        return -1;
    }

    private void dodajRezerwacje(Rezerwacja rezerwacja){
        int slot = this.showLastSlot();
        this.rezerwacje[slot] = rezerwacja;
        ilosc_rezerwacji++;
    }
}
