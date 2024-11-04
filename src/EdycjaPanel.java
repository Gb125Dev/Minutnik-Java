import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.Properties;
/**
 * Ta klasa wczytuje okno dialogowe i zapisuje preferencje uzytkownika dotyczace czasu.
 *
 * @author Gb125Dev
 * @version 1.1
 */
public class EdycjaPanel {
    /** Zmienna odpowiadająca za wczytywanie i zapisywanie wlasciwosci czasu i ustawien*/
    private final Properties wlasciwosci = new Properties();
    /** Ta zmienna sluzy do nadpisania nazw standardowych opcji w panelu dialogowym */
    private final Object[] opcje = {"Zatwierdż","Anuluj"};
    /** Ta zmienna z kolei sluzy do zmiany czcionki na czcionke Serif o rozmiarze 45*/
    private final Font czcionka = new Font("Serif", Font.PLAIN, 45);
    /** Etykieta służąca do pokazania tytułu edycji */
    private final JLabel czasTytul = new JLabel("Wybierz czas do odliczania");
    /** Zmienna umozliwiajaca uzytkownikowi wybor czasu */
    private JFormattedTextField czasWybierz;
    /** Sluzy do wyznaczenia przedzialow w ktorych mieszcza sie elementy panelu dialogowego */
    private final GridBagConstraints c = new GridBagConstraints();
    /**
     * Metoda pokazPanel sluzy do pokazania panelu w ktorym mozna podawac dane
     */
    protected void pokazPanel(){
        int czas = JOptionPane.showOptionDialog(null,
                nowyPanel(),
                "Edycja godziny",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcje,
                opcje[0]);
        if (czas == JOptionPane.OK_OPTION) {
            try {
                wlasciwosci.load(new FileInputStream("timesVariables.properties"));
                Ustawienia.zapis(czasNaPoprawnyCzas(czasWybierz.getText()),
                        wlasciwosci.getProperty("tloNumer",String.valueOf(UstawieniaPanel.tlo_tekst_wybor.getSelectedIndex())),
                        wlasciwosci.getProperty("napisyNumer",String.valueOf(UstawieniaPanel.wielkosc_wybor.getSelectedIndex())),
                        wlasciwosci.getProperty("muzykaNumer",String.valueOf(UstawieniaPanel.muzyka_wybor.getSelectedIndex())));
                MinutnikPanel.czas.setText(czasNaPoprawnyCzas(czasWybierz.getText()));
                MinutnikPanel.ostatniaZmienna = czasNaPoprawnyCzas(czasWybierz.getText());
            }catch(Exception e){
                JOptionPane.showConfirmDialog(null,"Żle podana godzina." + "\n" + "Spróbuj podać poprawną godzinę.","Błąd godziny", JOptionPane.YES_NO_OPTION);
            }
        }
    }
    /**
     * Prywatna metoda nowyPanel sluzy do nadpiasania istniejacego panelu dialogowego
     * @return okno panelowe w typie zmiennej JPanel
     */
    private JPanel nowyPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,500));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Ustawienia.kolorTlaSpecjalny);

        czasTytul.setFont(czcionka);
        czasTytul.setForeground(Ustawienia.kolorNapisowDomyslny);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(czasTytul, c);

        try {
            czasWybierz = new JFormattedTextField(new MaskFormatter ("##:##:##"));}
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
        czasWybierz.setText("00:00:00");
        czasWybierz.setFont(czcionka);
        czasWybierz.setForeground(Color.BLACK);
        czasWybierz.setBackground(Color.GRAY);
        czasWybierz.setHorizontalAlignment(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,10,0);
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(czasWybierz, c);

        return panel;
    }
    /**
     * Prywatna metoda czasNaPoprawnyCzas zmienia czas w formacie tekstowym na format tekstowy GG:MM:SS.
     * Sprawdza czy odpowiednie luki czasu nie są powyzej liczby 60 lub poniżej liczby 0
     * @param tekstCzas - Czas w formacie tekstowym ustawiony przez uzytkownika
     * @return czas w formacie tekstowym
     */
    private String czasNaPoprawnyCzas(String tekstCzas){
        String[] tablText = tekstCzas.split(":", 3);
        int numerCzas = Integer.parseInt(tablText[2]) + Integer.parseInt(tablText[1]) * 60 + Integer.parseInt(tablText[0]) * 3600;
        int godziny = numerCzas / 60;
        return String.format("%02d:%02d:%02d", godziny / 60, godziny % 60, numerCzas % 60);
    }
}
