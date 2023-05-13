import java.io.*;

public class BazaRezerwacji {
    private Rezerwacja[] rezerwacje = new Rezerwacja[500];
    private int ilosc_rezerwacji=0;

    BazaRezerwacji(){
        for(int x=0; x< rezerwacje.length; x++){
            rezerwacje[x] = null;
        }
        wczytajRezerwacjeZPliku("FlightApplication/databases/baza_rezerwacji.txt");
    }

    private void wczytajRezerwacjeZPliku(String nazwaPliku) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Podziel linię na poszczególne elementy użytkownika
                String[] daneRezerwacji = linia.split(",");

                if (daneRezerwacji.length == 6) {
                    // Do modyfikacji typy danych
                    String lot = daneRezerwacji[0];
                    String uzytkownik = daneRezerwacji[1];
                    String liczbaMiejsc = daneRezerwacji[2];
                    String cena = daneRezerwacji[3];
                    String statusPlatnosci = daneRezerwacji[4];
                    String numer_rezerwacji = daneRezerwacji[5];

                    //this.rezerwacje[ilosc_rezerwacji] = new Rezerwacja(lot, uzytkownik, liczbaMiejsc, cena, statusPlatnosci, numer_rezerwacji);
                    ilosc_rezerwacji++;
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

    protected void dodajRezerwacje(Rezerwacja rezerwacja, String nazwaPliku){
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku, true))) {
            String nowaRezerwacja =
                    rezerwacja.getLot() + "," +
                            rezerwacja.getUzytkownik() + "," +
                            rezerwacja.getLiczbaMiejsc() + "," +
                            rezerwacja.getCena() + "," +
                            rezerwacja.getStatusPlatnosci() + "," +
                            rezerwacja.getNumerRezerwacji();
            writer.println(nowaRezerwacja);
            System.out.println("Dodano nowego użytkownika do pliku.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu do pliku: " + e.getMessage());
        }
        int slot = this.showLastSlot();
        this.rezerwacje[slot] = rezerwacja;
        ilosc_rezerwacji++;
    }
}
