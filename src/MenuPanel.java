import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ta klasa sluzy do zdefiniowania paska menu i funkcji jego przyciskow
 *
 * @author Gb125Dev
 * @version 1.1
 */
public class MenuPanel{
    /** Pasek menu zawierajacy przyciski */
    protected static final JMenuBar glownyPanel = new JMenuBar();
    /** Przycisk paska menu prowadzacy do ustawien */
    protected static final JMenu ustawienia = new JMenu("Ustawienia");
    /** Przycisk paska menu wyswietlajacy informacje */
    protected static final JMenu info = new JMenu("Informacje");
    /**
     * Ten konstruktor sluzy do ustawienia paska menu i jego przyciskow a takze funkcji przyciskow
     */
    public MenuPanel() {
        glownyPanel.setBackground(Ustawienia.kolorTlaDomyslny);

        ustawienia.setForeground(Ustawienia.kolorNapisowDomyslny);
        ustawienia.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        new UstawieniaPanel().pokazPanel();
                    }
                });

        info.setForeground(Ustawienia.kolorNapisowDomyslny);
        info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(
                        null,
                        "Prosty minutnik w javie \nWersja 1.1 \nAutor: Gabriel Stańczyk",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        glownyPanel.add(ustawienia);
        glownyPanel.add(info);
    }
}
