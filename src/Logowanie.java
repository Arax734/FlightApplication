import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;


public class Logowanie extends JFrame implements ActionListener {
    private JTextField loginField;
    private JTextField hasloField;
    private String login;
    private String haslo;
    private BazaUzytkownikow baza;

    public Logowanie(BazaUzytkownikow baza) {
        this.baza = baza;
        setTitle("Logowanie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Tworzenie panelu z formularzem
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(9, 2));

        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField(20);
        formPanel.add(loginLabel);
        formPanel.add(loginField);

        JLabel hasloLabel = new JLabel("Hasło:");
        hasloField = new JTextField(20);
        formPanel.add(hasloLabel);
        formPanel.add(hasloField);

        // Tworzenie panelu z przyciskami
        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Potwierdź");
        confirmButton.addActionListener(this);
        buttonPanel.add(confirmButton);

        JButton cancelButton = new JButton("Anuluj");
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

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // set the margins as needed
        contentPane.setLayout(new BorderLayout());

        contentPane.add(formPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(messagePanel, BorderLayout.NORTH);

        setContentPane(contentPane);

        this.setLocationRelativeTo(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Potwierdź")) {
            // Pobranie danych z pól tekstowych;
            String login = loginField.getText();
            String haslo = hasloField.getText();
            // Walidacja danych
            if (login.isEmpty() || haslo.isEmpty()){
                showMessage("Proszę wypełnić wszystkie pola.");
            } else {
                try {
                    if(baza.checkIfUserExists(login, baza)){
                        Uzytkownik uzytkownik = baza.uzytkownicy[baza.getUserSlot(login,baza)];
                        if(uzytkownik.getHaslo().equals(haslo)){
                            System.out.println("Zalogowano pomyslnie");
                            this.dispose();
                        }else {
                            showInformation("Podano bledne haslo!");
                        }
                    }else {
                        showInformation("Podany uzytkownik nie istnieje");
                    }
                } catch (NumberFormatException ex) {
                }
            }
        } else if (e.getActionCommand().equals("Anuluj")) {
            Opcje main = new Opcje(baza);
            this.dispose();
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
