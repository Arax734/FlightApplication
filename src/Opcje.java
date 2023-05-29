import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Opcje {
    private JFrame frame;
    private JPanel panel;
    private JButton option1Button;
    private JButton option2Button;

    public Opcje() {
        frame = new JFrame("Menu główne");
        panel = new JPanel(new GridLayout(0, 1)); // set the layout to a single column

        option1Button = new JButton("Rejestracja");
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rejestracja();
                frame.dispose();
            }
        });
        panel.add(option1Button);

        option2Button = new JButton("Logowanie");
        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Logowanie();
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
