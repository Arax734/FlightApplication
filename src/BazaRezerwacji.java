import java.io.*;
import java.util.ArrayList;

public class BazaRezerwacji {
    private static BazaRezerwacji instance;
    private ArrayList<Rezerwacja> rezerwacje = new ArrayList<>();

    BazaRezerwacji() {
        wczytajRezerwacjeZPliku("databases/baza_rezerwacji.txt");
    }

    public static BazaRezerwacji getInstance() {
        if (instance == null) {
            instance = new BazaRezerwacji();
        }
        return instance;
    }

    private void wczytajRezerwacjeZPliku(String nazwaPliku) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Podziel linię na poszczególne elementy użytkownika
                String[] daneRezerwacji = linia.split(",");

                if (daneRezerwacji.length == 5) {
                    int numer_rezerwacji = Integer.parseInt(daneRezerwacji[0]);
                    String lot = daneRezerwacji[1];
                    String uzytkownik = daneRezerwacji[2];
                    int liczbaMiejsc = Integer.parseInt(daneRezerwacji[3]);
                    double cena = Double.parseDouble(daneRezerwacji[4]);

                    this.rezerwacje.add(new Rezerwacja(numer_rezerwacji, lot, uzytkownik, liczbaMiejsc,
                    cena));
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

    private boolean checkIfExists(int numer_rezerwacji, BazaRezerwacji baza) {
        for (int x = 0; x < baza.rezerwacje.size(); x++) {
            if (baza.rezerwacje.get(x) != null) {
                if (baza.rezerwacje.get(x).getNumerRezerwacji() == numer_rezerwacji) {
                    return true;
                }
            }
        }
        return false;
    }

    protected int getRezerwacjaSlot(int numer_rezerwacji, BazaRezerwacji baza) {
        if (this.checkIfExists(numer_rezerwacji, baza)) {
            for (int x = 0; x < baza.rezerwacje.size(); x++) {
                if (baza.rezerwacje.get(x) != null) {
                    if (baza.rezerwacje.get(x).getNumerRezerwacji() == numer_rezerwacji) {
                        return x;
                    }
                }
            }
        }
        // Sytuacja nie powinna mieć miejsca
        return -1;
    }

    protected int getLotID(String numerLotu) {
        return BazaLotow.getInstance().getFlightSlot(numerLotu);
    }

    protected ArrayList<Rezerwacja> getRezerwacje() {
        return rezerwacje;
    }

    protected void dodajRezerwacje(Rezerwacja rezerwacja, String nazwaPliku) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku, true))) {
            String nowaRezerwacja = rezerwacja.getNumerRezerwacji() + "," +
                    rezerwacja.getNumerLotu() + "," +
                    rezerwacja.getLoginUzytkownika() + "," +
                    rezerwacja.getLiczbaMiejsc() + "," +
                    rezerwacja.getCena();
            writer.println(nowaRezerwacja);
            System.out.println("Dodano nowa rezerwacje do pliku.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu do pliku: " + e.getMessage());
        }
        this.rezerwacje.add(rezerwacja);
    }

    protected void usunRezerwacje(int numerRezerwacji, String nazwaPliku) {
        try {
            File inputFile = new File(nazwaPliku);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] rezerwacjaData = currentLine.split(",");
                int currentNumerRezerwacji = Integer.parseInt(rezerwacjaData[0]);
                if (currentNumerRezerwacji != numerRezerwacji) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                System.out.println("Wystąpił błąd podczas usuwania rezerwacji.");
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytu/zapisu pliku: " + e.getMessage());
        }
    }

}