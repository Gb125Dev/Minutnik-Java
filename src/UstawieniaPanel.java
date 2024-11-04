import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Ta klasa sluzy do zdefiniowania paska menu i funkcji jego przyciskow
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class UstawieniaPanel {
    /** Zmienna odpowiadająca za wczytywanie i zapisywanie wlasciwosci czasu i ustawien*/
    private final Properties wlasciwosci = new Properties();
    /** Ta zmienna sluzy do nadpisania nazw standardowych opcji w panelu dialogowym */
    private final Object[] opcje = {"Zatwierdż","Anuluj"};
    /** Etykieta(JLabel) sluzaca do wyswietlania tytulu ustawienia tla na ekranie */
    private final JLabel tlo_i_tekst = new JLabel("Tło", SwingConstants.CENTER);
    /** Tablica tekstu sluzaca do ustalenia opcji do listy wybieralnej. Zawiera tla*/
    private static final String[] tlo_tekst_rodz ={"Ciemne","Jasne","Drewniane","Metalowe"};
    /** Lista wybieralna kolorow tla i tekstu */
    protected static final JComboBox tlo_tekst_wybor = new JComboBox(tlo_tekst_rodz);
    /** Etykieta(JLabel) sluzaca do wyswietlania tytulu ustawienia wielkosci czcionki na ekranie */
    private final JLabel wielkosc_tekst = new JLabel("Wielkość czcionki", SwingConstants.CENTER);
    /** Tablica tekstu sluzaca do ustalenia opcji do listy wybieralnej. Zawiera wielkosci */
    private static final String[] wielkosc_rodz ={"Miniaturowa","Mała","Normalna","Duża"};
    /** Lista wybieralna wielkosci czcionki */
    protected static final JComboBox wielkosc_wybor = new JComboBox(wielkosc_rodz);
    /** Etykieta(JLabel) sluzaca do wyswietlania tytulu ustawienia muzyki */
    private final JLabel muzyka_tekst = new JLabel("Muzyka", SwingConstants.CENTER);
    /** Tablica tekstu sluzaca do ustalenia opcji do listy wybieralnej. Zawiera muzyke */
    private static final String[] muzyka_rodz ={"Pianino","Gitara","Dzwonek","Kontrabas","Banjo"};
    /** Lista wybieralna muzyki */
    protected static final JComboBox  muzyka_wybor = new JComboBox(muzyka_rodz);
    /**
     * Chroniona metoda pokazPanel sluzy do pokazania panelu w ktorym mozna zmieniac ustawenia
     */
    protected void pokazPanel(){
        int ustawienia = JOptionPane.showOptionDialog(null,
                nowyPanel(),
                "Ustawienia",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcje,
                opcje[0]);
        if (ustawienia == JOptionPane.OK_OPTION){
            Ustawienia.ustawianieKoloru(tlo_tekst_wybor.getSelectedIndex(),
                    wielkosc_wybor.getSelectedIndex(),
                    muzyka_wybor.getSelectedIndex());
        }
    }
    /**
     * Prywatna metoda nowyPanel sluzy do nadpiasania istniejacego panelu dialogowego
     * @return okno panelowe w typie zmiennej JPanel
     */
    private JPanel nowyPanel() {
        try {
            wlasciwosci.load(new FileInputStream("timesVariables.properties"));
            tlo_tekst_wybor.setSelectedIndex(Integer.parseInt(wlasciwosci.getProperty("tloNumer","0")));
            wielkosc_wybor.setSelectedIndex(Integer.parseInt(wlasciwosci.getProperty("napisyNumer","2")));
            muzyka_wybor.setSelectedIndex(Integer.parseInt(wlasciwosci.getProperty("muzykaNumer","0")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 125));
        panel.setLayout(new GridLayout(0,3));
        panel.setBackground(Ustawienia.kolorTlaSpecjalny);

        panel.add(new Box(BoxLayout.LINE_AXIS));

        JPanel panelFunkcja = new JPanel();
        panelFunkcja.setBackground(Ustawienia.kolorTlaSpecjalny);
        panelFunkcja.setLayout(new BoxLayout (panelFunkcja, BoxLayout.Y_AXIS));

        tlo_i_tekst.setForeground(Ustawienia.kolorNapisowDomyslny);
        wielkosc_tekst.setForeground(Ustawienia.kolorNapisowDomyslny);
        muzyka_tekst.setForeground(Ustawienia.kolorNapisowDomyslny);

        panel.add(panelFunkcja);
        panelFunkcja.add(tlo_i_tekst);
        panelFunkcja.add(tlo_tekst_wybor);
        panelFunkcja.add(wielkosc_tekst);
        panelFunkcja.add(wielkosc_wybor);
        panelFunkcja.add(muzyka_tekst);
        panelFunkcja.add(muzyka_wybor);
        panel.add(new Box(BoxLayout.LINE_AXIS));
        return panel;
    }
}
