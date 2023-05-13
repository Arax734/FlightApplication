import java.io.*;

public class BazaUzytkownikow {
    protected Uzytkownik[] uzytkownicy = new Uzytkownik[300];
    private int ilosc_uzytkownikow=0;

    BazaUzytkownikow() {
        for (int x = 0; x < uzytkownicy.length; x++) {
            uzytkownicy[x] = null;
        }
        wczytajUzytkownikowZPliku("FlightApplication/databases/baza_uzytkownikow.txt");
    }

    private void wczytajUzytkownikowZPliku(String nazwaPliku) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Podziel linię na poszczególne elementy użytkownika
                String[] daneUzytkownika = linia.split(",");

                if (daneUzytkownika.length == 8) {
                    int id = Integer.parseInt(daneUzytkownika[0]);
                    String imie = daneUzytkownika[1];
                    String nazwisko = daneUzytkownika[2];
                    String email = daneUzytkownika[3];
                    String telefon = daneUzytkownika[4];
                    String login = daneUzytkownika[5];
                    String haslo = daneUzytkownika[6];
                    boolean admin = Boolean.parseBoolean(daneUzytkownika[7]);

                    this.uzytkownicy[ilosc_uzytkownikow] = new Uzytkownik(id, imie, nazwisko, email, telefon, login, haslo, admin);
                    ilosc_uzytkownikow++;
                } else {
                    System.out.println("Niepoprawny format danych w linii: " + linia);
                }
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());

            // Jeśli plik nie istnieje, możesz go utworzyć
            try {
                PrintWriter writer = new PrintWriter(nazwaPliku);
                writer.close();
                System.out.println("Utworzono nowy plik: " + nazwaPliku);
            } catch (IOException ex) {
                System.out.println("Wystąpił błąd podczas tworzenia pliku: " + ex.getMessage());
            }
        }
    }

    protected int showLastSlot(){
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

    protected void dodajUzytkownika(Uzytkownik uzytkownik, String nazwaPliku){
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku, true))) {
            String nowyUzytkownik =
                    uzytkownik.getID() + "," +
                    uzytkownik.getImie() + "," +
                    uzytkownik.getNazwisko() + "," +
                    uzytkownik.getEmail() + "," +
                    uzytkownik.getNumerTelefonu() + "," +
                    uzytkownik.getLogin() + "," +
                    uzytkownik.getHaslo() + "," +
                    uzytkownik.isAdmin();
            writer.println(nowyUzytkownik);
            System.out.println("Dodano nowego użytkownika do pliku.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu do pliku: " + e.getMessage());
        }
        int slot = this.showLastSlot();
        this.uzytkownicy[slot] = uzytkownik;
        ilosc_uzytkownikow++;
    }
}
