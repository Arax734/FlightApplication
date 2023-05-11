import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RezerwacjaLotu extends JFrame implements ActionListener {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField departureField;
    private JTextField destinationField;
    private JTextField departureDateField;
    private JTextField returnDateField;
    private JTextField numPassengersField;

    private Uzytkownik uzytkownik;
    private BazaRezerwacji rezerwacje;

    public RezerwacjaLotu(Uzytkownik uzytkownik, BazaRezerwacji rezerwacje) {
        this.uzytkownik = uzytkownik;
        this.rezerwacje = rezerwacje;
        setTitle("Rezerwacja lotu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Tworzenie panelu z formularzem
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(9, 2));

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

        JLabel departureLabel = new JLabel("Miejsce wylotu:");
        departureField = new JTextField(20);
        formPanel.add(departureLabel);
        formPanel.add(departureField);

        JLabel destinationLabel = new JLabel("Miejsce przylotu:");
        destinationField = new JTextField(20);
        formPanel.add(destinationLabel);
        formPanel.add(destinationField);

        JLabel departureDateLabel = new JLabel("Data wylotu:");
        departureDateField = new JTextField(20);
        formPanel.add(departureDateLabel);
        formPanel.add(departureDateField);

        JLabel returnDateLabel = new JLabel("Data przylotu:");
        returnDateField = new JTextField(20);
        formPanel.add(returnDateLabel);
        formPanel.add(returnDateField);

        JLabel numPassengersLabel = new JLabel("Ilość pasażerów:");
        numPassengersField = new JTextField(20);
        formPanel.add(numPassengersLabel);
        formPanel.add(numPassengersField);

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
        if (e.getActionCommand().equals("Potwierdź")) {
            // Pobranie danych z pól tekstowych
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String departure = departureField.getText();
            String destination = destinationField.getText();
            String departureDate = departureDateField.getText();
            String returnDate = returnDateField.getText();
            String numPassengers = numPassengersField.getText();
            // Walidacja danych
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                    departure.isEmpty() || destination.isEmpty() || departureDate.isEmpty() ||
                    returnDate.isEmpty() || numPassengers.isEmpty()) {
                showMessage("Proszę wypełnić wszystkie pola.");
            } else {
                try {
                    int numPassengersInt = Integer.parseInt(numPassengers);
                    if (numPassengersInt <= 0) {
                        showMessage("Ilość pasażerów musi być większa niż 0.");
                    } else {
                        // Tutaj stworzymy nową rezerwację lotu
                        showInformation("Rezerwacja zostala pomyslnie wykonana");
                        PanelUzytkownika main = new PanelUzytkownika(uzytkownik, rezerwacje);
                        this.dispose();
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Ilość pasażerów musi być liczbą.");
                }
            }
        } else if (e.getActionCommand().equals("Anuluj")) {
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
