import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class UsunKonto extends JFrame implements ActionListener {
    private Rezerwacja selected;
    private Uzytkownik uzytkownik;
    private JTextField emailField;
    private JTextField hasloField;
    private JTextField haslo2Field;

    public UsunKonto(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
        setTitle("Zmiana ustawien");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Tworzenie panelu z formularzem
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 3));

        JLabel emailLabel = new JLabel("Wstaw nowy adres email: ");
        emailField = new JTextField(20);

        formPanel.add(emailLabel);
        formPanel.add(emailField);

        JLabel hasloLabel = new JLabel("Haslo: ");
        hasloField = new JTextField(20);

        formPanel.add(hasloLabel);
        formPanel.add(hasloField);

        JLabel haslo2Label = new JLabel("Potwierdz haslo: ");
        haslo2Field = new JTextField(20);

        formPanel.add(haslo2Label);
        formPanel.add(haslo2Field);

        // Tworzenie panelu z przyciskami
        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Usun konto");
        confirmButton.addActionListener(this);
        buttonPanel.add(confirmButton);

        JButton cancel2Button = new JButton("Powrot");
        cancel2Button.addActionListener(this);
        buttonPanel.add(cancel2Button);

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
        if (e.getActionCommand().equals("Usun konto")) {
            try {
                if (hasloField.getText().equals(haslo2Field.getText()) && (emailField.getText().equals(this.uzytkownik.getEmail()))) {
                    if (hasloField.getText().equals(this.uzytkownik.getHaslo())) {
                        ArrayList<Rezerwacja> bazar = BazaRezerwacji.getInstance().getRezerwacje();
                        for(int x=0; x<bazar.size(); x++){
                            if(bazar.get(x).getUzytkownik() == this.uzytkownik){
                                int obecna = bazar.get(x).getNumerRezerwacji();
                                BazaRezerwacji.getInstance().getRezerwacje().remove(bazar.get(x));
                                BazaRezerwacji.getInstance().usunRezerwacje(obecna, "databases/baza_rezerwacji.txt");
                            }
                        }
                        int obecny = this.uzytkownik.getID();
                        BazaUzytkownikow.getInstance().getUzytkownicy().remove(uzytkownik);
                        BazaUzytkownikow.getInstance().usunKonto(obecny, "databases/baza_uzytkownikow.txt");

                        showInformation("Konto usuniete pomyslnie!");
                        this.dispose();
                        new Opcje();
                    }
                }
                showMessage("Podano bledne hasla lub email!");
            } catch (NumberFormatException ex) {
            }
        }
        else if (e.getActionCommand().equals("Powrot")) {
            new PanelUzytkownika(uzytkownik);
            dispose();
        }
    }

    private void showMessage(String message) {
        // Wyświetlenie informacji zwrotnej dla użytkownika
        JOptionPane.showMessageDialog(this, message, "Błąd", JOptionPane.ERROR_MESSAGE);
    }

    private void showInformation(String message) {
        // Wyświetlenie informacji zwrotnej dla użytkownika
        JOptionPane.showMessageDialog(this, message, "Operacja zakończona pomyślnie", JOptionPane.INFORMATION_MESSAGE);
    }
}
