import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BazaLotow {
    private static BazaLotow instance;
    ArrayList<Lot> loty = new ArrayList<>();

    private BazaLotow() {
        wczytajLotyZPliku("databases/baza_lotow.txt");
    }

    public static BazaLotow getInstance() {
        if (instance == null) {
            instance = new BazaLotow();
        }
        return instance;
    }

    protected ArrayList<Lot> getLoty() {
        return this.loty;
    }

    private void wczytajLotyZPliku(String nazwaPliku) {
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] daneLotu = linia.split(",");

                if (daneLotu.length == 6) {
                    String numerLotu = daneLotu[0];
                    String skad = daneLotu[1];
                    String dokad = daneLotu[2];

                    String dataWylotu = daneLotu[3];
                    if (dataWylotu.length() > 16) {
                        dataWylotu = dataWylotu.substring(0, 16);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    LocalDateTime dataWylotuKonwertowana = LocalDateTime.parse(dataWylotu, formatter);
                    String dataPrzylotu = daneLotu[4];
                    if (dataPrzylotu.length() > 16) {
                        dataPrzylotu = dataPrzylotu.substring(0, 16);
                    }
                    LocalDateTime dataPrzylotuKonwertowana = LocalDateTime.parse(dataPrzylotu, formatter);

                    double cena = Double.parseDouble(daneLotu[5]);

                    this.loty.add(
                            new Lot(numerLotu, skad, dokad, dataWylotuKonwertowana, dataPrzylotuKonwertowana, cena));
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

    protected boolean checkIfFlightExists(String numerLotu) {
        BazaLotow baza = BazaLotow.getInstance();
        for (Lot lot : baza.loty) {
            if (lot != null && lot.getNumerLotu().equals(numerLotu)) {
                return true;
            }
        }
        return false;
    }
    
    protected int getFlightSlot(String numerLotu) {
        BazaLotow baza = BazaLotow.getInstance();
        for(int x=0; x<baza.getLoty().size(); x++){
            if(baza.getLoty().get(x).getNumerLotu().equals(numerLotu)){
                return x;
            }
        }
        return -1;
    }

    protected Lot getLotByNumber(String numerLotu) {
        for (int x = 0; x < loty.size(); x++) {
            if (loty.get(x) != null) {
                if (loty.get(x).getNumerLotu().equals(numerLotu)) {
                    return loty.get(x);
                }
            }
        }
        return null;
    }

    protected void dodajLot(Lot lot, String nazwaPliku) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nazwaPliku, true))) {
            String nowyLot = lot.getNumerLotu() + "," +
                    lot.getSkad() + "," +
                    lot.getDokad() + "," +
                    lot.getDataWylotu() + "," +
                    lot.getDataPrzylotu() + "," +
                    lot.getCena();
            String[] daneLotu = nowyLot.split(",");
            if (daneLotu[3].length() > 16) {
                daneLotu[3] = daneLotu[3].substring(0, 16);
            }
            if (daneLotu[4].length() > 16) {
                daneLotu[4] = daneLotu[4].substring(0, 16);
            }
            writer.println(daneLotu[0] + "," + daneLotu[1] + "," + daneLotu[2] + "," + daneLotu[3] + "," + daneLotu[4]
                    + "," + daneLotu[5]);
            System.out.println("Dodano nowy lot do pliku.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu do pliku: " + e.getMessage());
        }
        this.loty.add(lot);
    }
}
