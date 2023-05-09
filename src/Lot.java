import java.time.LocalDateTime;

public class Lot {
    private String numerLotu;
    private String skad;
    private String dokad;
    private LocalDateTime dataWylotu;
    private LocalDateTime dataPrzylotu;
    private double cena;

    public Lot(String numerLotu, String skad, String dokad, LocalDateTime dataWylotu, LocalDateTime dataPrzylotu, double cena) {
        this.numerLotu = numerLotu;
        this.skad = skad;
        this.dokad = dokad;
        this.dataWylotu = dataWylotu;
        this.dataPrzylotu = dataPrzylotu;
        this.cena = cena;
    }

    public String getNumerLotu() {
        return numerLotu;
    }

    public LocalDateTime getData(){
        return dataWylotu;
    }

    public void setNumerLotu(String numerLotu) {
        this.numerLotu = numerLotu;
    }

    public String getSkad() {
        return skad;
    }

    public void setSkad(String skad) {
        this.skad = skad;
    }

    public String getDokad() {
        return dokad;
    }

    public void setDokad(String dokad) {
        this.dokad = dokad;
    }

    public LocalDateTime getDataWylotu() {
        return dataWylotu;
    }

    public void setDataWylotu(LocalDateTime dataWylotu) {
        this.dataWylotu = dataWylotu;
    }

    public LocalDateTime getDataPrzylotu() {
        return dataPrzylotu;
    }

    public void setDataPrzylotu(LocalDateTime dataPrzylotu) {
        this.dataPrzylotu = dataPrzylotu;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
