import java.io.*;
import java.util.ArrayList;

public class BazaUzytkownikow {
    private static BazaUzytkownikow instance;
    private ArrayList<Uzytkownik> uzytkownicy = new ArrayList<>();

    private BazaUzytkownikow() {
        wczytajUzytkownikowZPliku("databases/baza_uzytkownikow.txt");
    }

    public static BazaUzytkownikow getInstance() {
        if (instance == null) {
            instance = new BazaUzytkownikow();
        }
        return instance;
    }

    protected ArrayList<Uzytkownik> getUzytkownicy(){
        return this.uzytkownicy;
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

                    this.uzytkownicy.add(new Uzytkownik(id, imie, nazwisko, email, telefon, login, haslo, admin));
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

    protected boolean checkIfUserExists(String login) {
        BazaUzytkownikow baza = BazaUzytkownikow.getInstance();
        for (int x = 0; x < baza.uzytkownicy.size(); x++) {
            if(baza.uzytkownicy.get(x) != null) {
                if (baza.uzytkownicy.get(x).getLogin().equals(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected int getUserSlot(String login){
        BazaUzytkownikow baza = BazaUzytkownikow.getInstance();
        if(this.checkIfUserExists(login)) {
            for (int x = 0; x < baza.uzytkownicy.size(); x++) {
                if (baza.uzytkownicy.get(x) != null) {
                    if (baza.uzytkownicy.get(x).getLogin().equals(login)) {
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
        this.uzytkownicy.add(uzytkownik);
    }

    protected void usunKonto(int idKonta, String nazwaPliku) {
        try {
            File inputFile = new File(nazwaPliku);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] kontoData = currentLine.split(",");
                int currentNumerRezerwacji = Integer.parseInt(kontoData[0]);
                if (currentNumerRezerwacji != idKonta) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                System.out.println("Wystąpił błąd podczas usuwania konta.");
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytu/zapisu pliku: " + e.getMessage());
        }
    }
}
