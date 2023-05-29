import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class DodajLot extends JFrame implements ActionListener {
    private JTextField numerLotuField;
    private JTextField skadField;
    private JTextField dokadField;
    private JSpinner dataWylotuField;
    private JSpinner dataPrzylotuField;
    private JTextField cenaField;
    private BazaLotow loty;
    private Uzytkownik uzytkownik;

    public DodajLot(Uzytkownik uzytkownik) {
        this.loty = BazaLotow.getInstance();
        this.uzytkownik = uzytkownik;
        setTitle("Dodaj lot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Tworzenie panelu z formularzem
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));

        JLabel numerLotuLabel = new JLabel("Numer lotu:");
        numerLotuField = new JTextField(20);
        formPanel.add(numerLotuLabel);
        formPanel.add(numerLotuField);

        JLabel skadLabel = new JLabel("Miejsce wylotu:");
        skadField = new JTextField(20);
        formPanel.add(skadLabel);
        formPanel.add(skadField);

        JLabel dokadLabel = new JLabel("Miejsce przylotu:");
        dokadField = new JTextField(20);
        formPanel.add(dokadLabel);
        formPanel.add(dokadField);

        JLabel dataWylotuLabel = new JLabel("Data wylotu:");
        dataWylotuField = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dataWylotuEditor = new JSpinner.DateEditor(dataWylotuField, "yyyy-MM-dd HH:mm");
        dataWylotuField.setEditor(dataWylotuEditor);
        formPanel.add(dataWylotuLabel);
        formPanel.add(dataWylotuField);

        JLabel dataPrzylotuLabel = new JLabel("Data przylotu:");
        dataPrzylotuField = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dataPrzylotuEditor = new JSpinner.DateEditor(dataPrzylotuField, "yyyy-MM-dd HH:mm");
        dataPrzylotuField.setEditor(dataPrzylotuEditor);
        formPanel.add(dataPrzylotuLabel);
        formPanel.add(dataPrzylotuField);

        JLabel cenaLabel = new JLabel("Cena: ");
        cenaField = new JTextField(10);
        formPanel.add(cenaLabel);
        formPanel.add(cenaField);

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
            String numerLotu = numerLotuField.getText();
            String skad = skadField.getText();
            String dokad = dokadField.getText();
            LocalDateTime dataWylotu = ((SpinnerDateModel) dataWylotuField.getModel()).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime dataPrzylotu = ((SpinnerDateModel) dataPrzylotuField.getModel()).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            double cena = Double.parseDouble(cenaField.getText());
            // Walidacja danych
            if (numerLotu.isEmpty() || skad.isEmpty() || dokad.isEmpty() || cenaField.getText().isEmpty()) {
                showMessage("Proszę wypełnić wszystkie pola.");
            } else {
                try {
                    if (cena <= 0) {
                        showMessage("Cena musi być większa niż 0.");
                    }
                    if(loty.checkIfFlightExists(numerLotu)){
                        showMessage("Lot o podanym numerze juz istnieje!");
                    }
                    if(dataPrzylotu.isBefore(dataWylotu)){
                        showMessage("Podaj poprawne daty!");
                    }
                    else {
                        showInformation("Lot zostal utworzony pomyslnie");
                        String nazwaPliku = "databases/baza_lotow.txt";
                        loty.dodajLot(new Lot(numerLotu,skad,dokad,dataWylotu,dataPrzylotu,cena),nazwaPliku);
                        new PanelUzytkownika(uzytkownik);
                        this.dispose();
                    }
                } catch (NumberFormatException ex) {
                    showMessage("");
                }
            }
        } else if (e.getActionCommand().equals("Anuluj")) {
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
