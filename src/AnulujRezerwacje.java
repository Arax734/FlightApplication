import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class AnulujRezerwacje extends JFrame implements ActionListener{
    private Rezerwacja selected;

    private Uzytkownik uzytkownik;
    private BazaRezerwacji rezerwacje;

    public AnulujRezerwacje(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
        this.rezerwacje = BazaRezerwacji.getInstance();
        setTitle("Usun rezerwacje");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Tworzenie panelu z formularzem
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2));

        JLabel rezerwacjaLabel = new JLabel("Rezerwacja: ");
        ArrayList<Rezerwacja> rezerwacje = BazaRezerwacji.getInstance().getRezerwacje();
        ArrayList<Rezerwacja> moje = new ArrayList<>();
        for (int x = 0; x < rezerwacje.size(); x++) {
            if (rezerwacje.get(x) != null) {
                if(rezerwacje.get(x).getLoginUzytkownika().equals(this.uzytkownik.getLogin())){
                    moje.add(rezerwacje.get(x));
                }
            }
        }
        if(moje.isEmpty()){
            showInformation("Brak wykonanych rezerwacji");
        }
        String[] rezerwacja_string = new String[moje.size()];
        for (int x = 0; x < moje.size(); x++) {
            if (moje.get(x) != null) {
                rezerwacja_string[x] = rezerwacje.get(x).getLot().getSkad();
                rezerwacja_string[x] += " - " + rezerwacje.get(x).getLot().getDokad();
                rezerwacja_string[x] += " | Ilosc osob: " + rezerwacje.get(x).getLiczbaMiejsc();
                rezerwacja_string[x] += " | Cena: " + rezerwacje.get(x).getCena()+" PLN";
            }
        }
        JComboBox<String> rezerwacjeField = new JComboBox<>(rezerwacja_string);
        rezerwacjeField.setSelectedIndex(0);
        rezerwacjeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rezerwacja selectedRezerwacja = BazaRezerwacji.getInstance().getRezerwacje().get(rezerwacjeField.getSelectedIndex());
                selected = selectedRezerwacja;
            }
        }); 
        if(selected == null){
            selected = moje.get(0);
        }
        formPanel.add(rezerwacjaLabel);
        formPanel.add(rezerwacjeField);

        // Tworzenie panelu z przyciskami
        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Anuluj rezerwacje");
        confirmButton.addActionListener(this);
        buttonPanel.add(confirmButton);

        JButton cancelButton = new JButton("Powrot");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);

        // Tworzenie panelu z informacjami zwrotnymi
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        JLabel messageLabel = new JLabel(" ");
        messagePanel.add(messageLabel);

        // Dodanie paneli do ramki
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(messagePanel, BorderLayout.NORTH);

        // Panel z pustym obramowaniem
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // set the margins as needed
        contentPane.setLayout(new BorderLayout());

        // Dodaje istniejące komponenty do okna
        contentPane.add(formPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(messagePanel, BorderLayout.NORTH);

        setContentPane(contentPane);
        this.setLocationRelativeTo(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Anuluj rezerwacje")) {
                try {
                    int obecna = selected.getNumerRezerwacji();
                    BazaRezerwacji.getInstance().getRezerwacje().remove(selected);
                    BazaRezerwacji.getInstance().usunRezerwacje(obecna, "databases/baza_rezerwacji.txt");
                    
                    showInformation("Rezerwacja usunieta pomyslnie!");
                    this.dispose();
                    new AnulujRezerwacje(uzytkownik);
                } catch (NumberFormatException ex) {
                }
            
        } else if (e.getActionCommand().equals("Powrot")) {
            new PanelUzytkownika(uzytkownik);
            dispose();
        }
    }

    private void showInformation(String message) {
        // Wyświetlenie informacji zwrotnej dla użytkownika
        JOptionPane.showMessageDialog(this, message, "Operacja zakończona pomyślnie", JOptionPane.INFORMATION_MESSAGE);
    }
}
