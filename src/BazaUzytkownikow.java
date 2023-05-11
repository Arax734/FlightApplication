public class BazaUzytkownikow {
    protected Uzytkownik[] uzytkownicy = new Uzytkownik[300];
    private int ilosc_uzytkownikow;

    BazaUzytkownikow(){
        for(int x=0; x< uzytkownicy.length; x++){
            uzytkownicy[x] = null;
        }
        // w tym momencie tworzymy statycznego użytkownika posiadającego właściwości admina
        this.dodajUzytkownika(new Uzytkownik("Kacper","Fryt","kf@gmail.com","123456789",
                "kfryt","haslo",true));
    }

    private int showLastSlot(){
        return ilosc_uzytkownikow;
    }


    protected boolean checkIfUserExists(String login, BazaUzytkownikow baza) {
        for (int x = 0; x < baza.uzytkownicy.length; x++) {
            if(baza.uzytkownicy[x] != null) {
                if (baza.uzytkownicy[x].getLogin().equals(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected int getUserSlot(String login, BazaUzytkownikow baza){
        if(this.checkIfUserExists(login, baza)) {
            for (int x = 0; x < baza.uzytkownicy.length; x++) {
                if (baza.uzytkownicy[x] != null) {
                    if (baza.uzytkownicy[x].getLogin().equals(login)) {
                        return x;
                    }
                }
            }
        }
        // Sytuacja nie powinna mieć miejsca
        return -1;
    }

    protected void dodajUzytkownika(Uzytkownik uzytkownik){
        int slot = this.showLastSlot();
        this.uzytkownicy[slot] = uzytkownik;
        ilosc_uzytkownikow++;
    }
}
