import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUzytkownika {
    private JFrame frame;
    private JPanel panel;
    private JButton option1Button;
    private JButton option2Button;
    private JButton option3Button;
    private JButton option4Button;
    private JButton option5Button;
    private JButton option6Button;

    public PanelUzytkownika(Uzytkownik uzytkownik) {
        frame = new JFrame("Menu użytkownika");
        panel = new JPanel(new GridLayout(0, 1));

        option1Button = new JButton("Rezerwacja lotu");
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RezerwacjaLotu(uzytkownik);
                frame.dispose();
            }
        });
        panel.add(option1Button);

        option5Button = new JButton("Anuluj rezerwacje");
        option5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnulujRezerwacje(uzytkownik);
                frame.dispose();
            }
        });
        panel.add(option5Button);

        if(uzytkownik.admin == true){
            option2Button = new JButton("Dodaj lot");
            option2Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new DodajLot(uzytkownik);
                    frame.dispose();
                }
            });
            panel.add(option2Button);
            option3Button = new JButton("Zarzadzanie rezerwacjami");
            option3Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ZarzadzanieRezerwacjami(uzytkownik);
                    frame.dispose();
                }
            });
            panel.add(option3Button);
        }

        option6Button = new JButton("Usun konto");
        option6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsunKonto(uzytkownik);
                frame.dispose();
            }
        });
        panel.add(option6Button);

        option4Button = new JButton("Wyloguj");
        option4Button.setBackground(Color.RED);
        option4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Opcje();
                frame.dispose();
            }
        });
        panel.add(option4Button);

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
