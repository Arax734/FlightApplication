import java.util.ArrayList;

public class ZarzadzanieRezerwacjami {
    private ArrayList<Rezerwacja> listaRezerwacji;

    public ZarzadzanieRezerwacjami() {
        listaRezerwacji = new ArrayList<>();
    }

    public void dodajRezerwacje(Rezerwacja rezerwacja) {
        listaRezerwacji.add(rezerwacja);
    }

    public void usunRezerwacje(Rezerwacja rezerwacja) {
        listaRezerwacji.remove(rezerwacja);
    }

    public ArrayList<Rezerwacja> getListaRezerwacji() {
        return listaRezerwacji;
    }

    public void setListaRezerwacji(ArrayList<Rezerwacja> listaRezerwacji) {
        this.listaRezerwacji = listaRezerwacji;
    }

    public void wyswietlRezerwacje() {
        for (Rezerwacja rezerwacja : listaRezerwacji) {
            System.out.println(rezerwacja);
        }
    }

    public Rezerwacja znajdzRezerwacje(String numerRezerwacji) {
        for (Rezerwacja rezerwacja : listaRezerwacji) {
            if (rezerwacja.getNumerRezerwacji().equals(numerRezerwacji)) {
                return rezerwacja;
            }
        }
        return null;
    }
}