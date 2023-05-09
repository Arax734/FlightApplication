import java.util.ArrayList;
import java.util.List;

public class ZarzadzanieLotami {
    private List<Lot> listaLotow;

    public ZarzadzanieLotami() {
        listaLotow = new ArrayList<>();
    }

    public void dodajLot(Lot lot) {
        listaLotow.add(lot);
    }

    public void usunLot(Lot lot) {
        listaLotow.remove(lot);
    }

    public List<Lot> wyszukajLoty(String skad, String dokad) {
        List<Lot> znalezioneLoty = new ArrayList<>();

        for (Lot lot : listaLotow) {
            if (lot.getSkad().equals(skad) && lot.getDokad().equals(dokad)) {
                znalezioneLoty.add(lot);
            }
        }

        return znalezioneLoty;
    }

    public List<Lot> getListaLotow() {
        return listaLotow;
    }
}
