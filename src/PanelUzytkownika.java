import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUzytkownika {
    private Uzytkownik uzytkownik;
    private JFrame frame;
    private JPanel panel;
    private JButton option1Button;
    private JButton option2Button;
    private JButton option3Button;

    private BazaRezerwacji rezerwacje;

    public PanelUzytkownika(Uzytkownik uzytkownik, BazaRezerwacji rezerwacje) {
        this.uzytkownik = uzytkownik;
        this.rezerwacje = rezerwacje;
        frame = new JFrame("Menu użytkownika");
        panel = new JPanel(new GridLayout(0, 1));

        option1Button = new JButton("Rezerwacja lotu");
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RezerwacjaLotu rezerwacja = new RezerwacjaLotu(uzytkownik, rezerwacje);
                frame.dispose();
            }
        });
        panel.add(option1Button);

        if(uzytkownik.admin == true){
            option2Button = new JButton("Zarzadanie lotami");
            option2Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ZarzadzanieLotami loty = new ZarzadzanieLotami();
                    frame.dispose();
                }
            });
            panel.add(option2Button);
            option3Button = new JButton("Zarzadzanie rejestracjami");
            option3Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ZarzadzanieRezerwacjami zarz_rezerwacje = new ZarzadzanieRezerwacjami();
                    frame.dispose();
                }
            });
            panel.add(option3Button);
        }

        frame.add(panel);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
