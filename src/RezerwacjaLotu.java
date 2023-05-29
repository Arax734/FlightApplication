import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RezerwacjaLotu extends JFrame implements ActionListener {
    private JFormattedTextField numPassengersField;
    private double cena;
    private Lot selected;

    private Uzytkownik uzytkownik;

    public RezerwacjaLotu(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
        setTitle("Rezerwacja lotu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Tworzenie panelu z formularzem
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2));

        Lot defaultlot = BazaLotow.getInstance().getLoty().get(0);
        selected = defaultlot;

        JLabel departureLabel = new JLabel("Miejsce wylotu:");
        JLabel departureField;
        if (defaultlot == null) {
            departureField = new JLabel("---");
        } else {
            departureField = new JLabel(defaultlot.getSkad());
        }
        formPanel.add(departureLabel);
        formPanel.add(departureField);

        JLabel destinationLabel = new JLabel("Miejsce przylotu:");
        JLabel destinationField;
        if (defaultlot == null) {
            destinationField = new JLabel("---");
        } else {
            destinationField = new JLabel(defaultlot.getDokad());
        }
        formPanel.add(destinationLabel);
        formPanel.add(destinationField);

        JLabel departureDateLabel = new JLabel("Data wylotu:");
        JLabel departureDateField;
        if (defaultlot == null) {
            departureDateField = new JLabel("---");
        } else {
            LocalDateTime dateTime = defaultlot.getDataWylotu();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = dateTime.format(formatter);
            departureDateField = new JLabel(formattedDateTime);
        }
        formPanel.add(departureDateLabel);
        formPanel.add(departureDateField);

        JLabel returnDateLabel = new JLabel("Data przylotu:");
        JLabel returnDateField;
        if (defaultlot == null) {
            returnDateField = new JLabel("---");
        } else {
            LocalDateTime dateTime = defaultlot.getDataWylotu();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = dateTime.format(formatter);
            returnDateField = new JLabel(formattedDateTime);
        }
        formPanel.add(returnDateLabel);
        formPanel.add(returnDateField);

        JLabel priceLabel = new JLabel("Cena: ");
        JLabel priceField;
        if (defaultlot == null) {
            priceField = new JLabel("--- PLN");
        } else {
            priceField = new JLabel(Double.toString(defaultlot.getCena())+" PLN");
            this.cena = defaultlot.getCena();
        }
        formPanel.add(priceLabel);
        formPanel.add(priceField);

        JLabel numPassengersLabel = new JLabel("Ilość pasażerów:");
        JFormattedTextField numPassengersField;

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(integerFormat);
        formatter.setValueClass(Integer.class);

        numPassengersField = new JFormattedTextField(formatter);
        numPassengersField.setColumns(20);
        numPassengersField.setValue(1);
        this.numPassengersField = numPassengersField;
        
        numPassengersField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String passengerNumberText = numPassengersField.getText();
                int passengerNumber = Integer.parseInt(passengerNumberText);
                double doublepassengernumber = (double) passengerNumber;
                double fullcena;
                if(selected != null){
                    fullcena = selected.getCena() * doublepassengernumber;
                }
                else{
                    fullcena = defaultlot.getCena() * doublepassengernumber;
                }
                if(passengerNumber <= 0){
                    return;
                }
                priceField.setText(Double.toString(fullcena) + " PLN");
            }
        });

        formPanel.add(numPassengersLabel);
        formPanel.add(numPassengersField);  

        JLabel flightLabel = new JLabel("Lot: ");
        ArrayList<Lot> loty = BazaLotow.getInstance().getLoty();
        String[] loty_string = new String[loty.size()];
        for (int x = 0; x < loty.size(); x++) {
            if (loty.get(x) != null) {
                loty_string[x] = loty.get(x).getSkad();
                loty_string[x] += " - " + loty.get(x).getDokad();
            }
        }
        JComboBox<String> flightField = new JComboBox<>(loty_string);
        flightField.setSelectedIndex(0);
        flightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lot selectedFlight = BazaLotow.getInstance().getLoty().get(flightField.getSelectedIndex());
                selected = selectedFlight;
                int passengernumber = (int) numPassengersField.getValue();
                double doublepassengernumber = (double) passengernumber;
                double fullcena = selectedFlight.getCena() * doublepassengernumber;
                if(passengernumber <= 0){
                    return;
                }
                priceField.setText(Double.toString(fullcena) + " PLN");
                cena = fullcena;
                LocalDateTime dateTime = selectedFlight.getDataWylotu();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String formattedDateTime = dateTime.format(formatter);
                departureDateField.setText(formattedDateTime);
                LocalDateTime dateTime2 = selectedFlight.getDataPrzylotu();
                String formattedDateTime2 = dateTime2.format(formatter);
                returnDateField.setText(formattedDateTime2);
                departureField.setText(selectedFlight.getSkad());
                destinationField.setText(selectedFlight.getDokad());
            }
        }); 
        formPanel.add(flightLabel);
        formPanel.add(flightField);

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
            int passengernumber = (int) numPassengersField.getValue();
                try {
                    if (passengernumber <= 0) {
                        showMessage("Ilość pasażerów musi być większa niż 0.");
                    } else {
                        BazaRezerwacji baza = BazaRezerwacji.getInstance();
                        int id = baza.getRezerwacje().size()+1;
                        String nazwaPliku = "databases/baza_rezerwacji.txt";
                        baza.dodajRezerwacje(new Rezerwacja(id,selected.getNumerLotu(),uzytkownik.getLogin(),Integer.parseInt(numPassengersField.getText()),cena), nazwaPliku);
                        showInformation("Rezerwacja została pomyślnie wykonana");
                        new PanelUzytkownika(uzytkownik);
                        this.dispose();
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Ilość pasażerów musi być liczbą.");
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
