import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Opcje {
    private BazaUzytkownikow baza;
    private JFrame frame;
    private JPanel panel;
    private JButton option1Button;
    private JButton option2Button;
    private JButton option3Button;

    public Opcje(BazaUzytkownikow baza) {
        this.baza = baza;
        frame = new JFrame("Menu główne");
        panel = new JPanel();

        option1Button = new JButton("Rejestracja");
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rejestracja rejestracja = new Rejestracja(baza);
                frame.dispose();
            }
        });
        panel.add(option1Button);

        option2Button = new JButton("Logowanie");
        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logowanie logowanie = new Logowanie(baza);
                frame.dispose();
            }
        });
        panel.add(option2Button);

        frame.add(panel);
        frame.setSize(300, 150);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
