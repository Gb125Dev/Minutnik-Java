import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Ta klasa sluzy do utworzenia i utrzymania komponentow calego minutnika na ekranie.
 *
 * @author Gb125Dev
 * @version 1.1
 */
public class MinutnikPanel{
    /** Zmienna odpowiadajaca za wczytywanie wlasciwosci czasu i ustawien*/
    private final Properties wlasciwosci = new Properties();
    /** Panel zawierajacy wszystkie inne rzeczy(w tym podrzedny panel) */
    protected static final JPanel glownyPanel = new JPanel();
    /** Panel zawierajacy glowne funkcje */
    protected static final JPanel panelFunkcja = new JPanel(new GridBagLayout());
    /** Czcionka uzywana w calym panelu */
    protected static Font czcionka = new Font("Serif", Font.PLAIN, Ustawienia.rozmiarCzcionki);
    /** Etykieta(JLabel) sluzaca do wyswietlania tytulu na ekranie */
    protected static final JLabel minutnikTytul = new JLabel("Minutnik");
    /** Etykieta(JLabel) sluzaca do wyswietlania czasu na ekranie */
    protected static final JLabel czas = new JLabel();
    /** Przycisk sluzacy do rozpoczynania lub zatrzymywania minutnika */
    public static JButton start_stop = new JButton("Start");
    /** Zmienna od ktorej jest zalezny stan przycisku start_stop, jego kolor,napis i funkcjonalnosc po kliknieciu */
    protected static boolean aktywny = false;
    /** Zmienna sluzaca do ustawiania ostatniej zmiennej z czasu*/
    protected static String ostatniaZmienna;
    /** Ta klasa sluzy do pokazania i uruchomienia panelu podania czasu */
    private final EdycjaPanel edycjaPanel = new EdycjaPanel();
    /** Sluzy do uruchamia rozych funkcji minutnika.W tym wypadku do uruchamiania i zartrzymywania czasu */
    private MinutnikFunkcja minutnikFunkcja;
    /**
     * Ten konstruktuor sluzy do utworzenia calega panelu minutnika na ktorym bedzie cala aplikacja operowala
     */
    public MinutnikPanel(){
        glownyPanel.setBounds(0,0,700,700);
        glownyPanel.setBackground(Ustawienia.kolorTlaDomyslny);
        glownyPanel.setLayout(new GridLayout(0,3));

        glownyPanel.add(new Box(BoxLayout.LINE_AXIS));

        panelFunkcja.setBackground(Ustawienia.kolorTlaDomyslny);
        glownyPanel.add(panelFunkcja);



        minutnikTytul.setFont(czcionka);
        minutnikTytul.setForeground(Ustawienia.kolorNapisowDomyslny);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.RELATIVE;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        panelFunkcja.add(minutnikTytul, c);

        try {
            wlasciwosci.load(new FileInputStream("timesVariables.properties"));
            czas.setText(wlasciwosci.getProperty("ostatniCzasZapis","00:00:00"));
            ostatniaZmienna = czas.getText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        czas.setFont(czcionka);
        czas.setForeground(Ustawienia.kolorNapisowDomyslny);
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,10,0);
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        panelFunkcja.add(czas, c);

        Ustawienia.ustawianieKoloru(Integer.parseInt(wlasciwosci.getProperty("tloNumer","0")),
                Integer.parseInt(wlasciwosci.getProperty("napisyNumer","2")),
                Integer.parseInt(wlasciwosci.getProperty("muzykaNumer","0")));

        JButton edycja = new JButton("Edytuj");
        edycja.addActionListener(e -> edycjaPanel.pokazPanel());
        edycja.setBackground(Color.BLUE);
        edycja.setForeground(Color.GRAY);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 1.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panelFunkcja.add(edycja, c);

        JButton reset = new JButton("Odśwież");
        reset.addActionListener(e -> czas.setText(ostatniaZmienna));
        reset.setBackground(Color.ORANGE);
        c.gridx = 1;
        c.gridy = 2;
        panelFunkcja.add(reset, c);

        start_stop.addActionListener(e -> {
            minutnikFunkcja = new MinutnikFunkcja(czas.getText());
            if (!aktywny){
                start_stop.setText("Stop");
                start_stop.setBackground(Color.RED);
            }else{
                start_stop.setText("Start");
                start_stop.setBackground(Color.GREEN);
            }
            aktywny = !aktywny;
        });
        start_stop.setBackground(Color.GREEN);
        c.gridx = 2;
        c.gridy = 2;
        panelFunkcja.add(start_stop, c);

        glownyPanel.add(new Box(BoxLayout.LINE_AXIS));
    }
}
