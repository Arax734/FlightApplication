import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Rejestracja extends JFrame implements ActionListener {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;

    private JTextField loginField;
    private JTextField hasloField;
    private BazaUzytkownikow baza;

    public Rejestracja() {
        this.baza = BazaUzytkownikow.getInstance();
        setTitle("Rejestracja");
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

        JLabel firstNameLabel = new JLabel("Imię:");
        firstNameField = new JTextField(20);
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Nazwisko:");
        lastNameField = new JTextField(20);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        JLabel emailLabel = new JLabel("Adres e-mail:");
        emailField = new JTextField(20);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Numer telefonu:");
        phoneField = new JTextField(20);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

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
            // Pobranie danych z pól tekstowych
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String login = loginField.getText();
            String haslo = hasloField.getText();
            // Walidacja danych
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()){
                showMessage("Proszę wypełnić wszystkie pola.");
            } else {
                try {
                    if (haslo.length() <= 0) {
                        showMessage("Musisz ustawić hasło!");
                    }
                    if(baza.checkIfUserExists(login)){
                        showMessage("Użytkownik o danym loginie już istnieje!");
                    }else {
                        int id = baza.getUzytkownicy().size();
                        String nazwaPliku = "databases/baza_uzytkownikow.txt";
                        baza.dodajUzytkownika(new Uzytkownik(id,firstName,lastName,email,phone,login,haslo,false),nazwaPliku);
                        showInformation("Rejestracja została pomyślnie wykonana");
                        new Opcje();
                        this.dispose();
                    }
                } catch (NumberFormatException ex) {
                }
            }
        } else if (e.getActionCommand().equals("Anuluj")) {
            new Opcje();
            this.dispose();
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Błąd", JOptionPane.ERROR_MESSAGE);
    }

    private void showInformation(String message) {
        JOptionPane.showMessageDialog(this, message, "Operacja zakończona pomyślnie", JOptionPane.INFORMATION_MESSAGE);
    }
}
