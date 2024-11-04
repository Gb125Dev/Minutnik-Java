import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
/**
 * Ta klasa utrzymuje i wprowadza funkcje minutnika takie jak odliczanie czasu w dol.
 *
 * @author Gb125Dev
 * @version 1.1
 */
public class MinutnikFunkcja implements ActionListener{
    /** Odpowiada za czas na ekranie */
    private int czasTrwajacy;
    /** Zmienna sprawdzajaca dzialanie minutnika */
    private static boolean dzialanie = false;
    /** Zmienna sprawdzająca czy minutnik jest uruchomiony */
    private static boolean urchomione = false;
    /** timer sluzy do odliczania czasu jak i jego zatrzymywania i resetowania */
    private Timer timer;
    /** ta zmienna z kolei sluzy do wlaczenia(pobrania/otrzymania) pliku audio do aplikacji */
    private AudioInputStream audioWejscie;
    /** zmienna klip sluzy z kolei do wywolywania pliku audio z systemu */
    private Clip klip;
    /**
     * Ten konstruktor sluzy do uruchamiania minutnika lub zatrzymywania go
     *
     * @param czasNaSek - zmienna typu tekstowego zmieniana na sekundy w formacie numerycznym typu integer.
     */
    public MinutnikFunkcja(String czasNaSek){
        czasTrwajacy = czasNaSekundy(czasNaSek);
        dzialanie = !dzialanie;
        if (!urchomione) {
            timer = new Timer(1000, this);
            timer.start();
            MinutnikPanel.czas.setText(sekundyNaCzas(czasTrwajacy--));
            urchomione = true;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!dzialanie) {
            timer.stop();
            urchomione = false;
        }else if (czasTrwajacy == -1) {
            dzialanie = false;
            urchomione = false;
            MinutnikPanel.aktywny = !MinutnikPanel.aktywny;
            zagrajMuzyke(Ustawienia.domyslnyDzwonek);
            MinutnikPanel.start_stop.setText("Start");
            MinutnikPanel.start_stop.setBackground(Color.GREEN);
            timer.stop();
            int opcja = JOptionPane.showConfirmDialog(null, "Zadanie skończone", "Koniec zadania", JOptionPane.OK_CANCEL_OPTION);
            if(opcja == JOptionPane.OK_OPTION || opcja == JOptionPane.CANCEL_OPTION){
                klip.stop();
            }
        }else{
            MinutnikPanel.czas.setText(sekundyNaCzas(czasTrwajacy--));
        }
    }
    /**
     * Prywatna metoda czasNaSekundy zmieniajaca czas podany w formacie typu tekstowego na sekundy
     * podane w formie liczbowej typu integer(int)
     *
     * @param tekstCzas - jest tekstowa wersja czasu podanego przez uzytkownika
     * @return czas w sekundach
     */
    private int czasNaSekundy(String tekstCzas){
        String[] tablText = tekstCzas.split(":", 3);
        return Integer.parseInt(tablText[2]) + Integer.parseInt(tablText[1]) * 60 + Integer.parseInt(tablText[0]) * 3600;
    }
    /**
     * Prywatna metoda sekundyNaCzas zmieniająca czas podany w formacie typu numerycznego calkowitego(int)
     * na format typu tekstowego
     *
     * @param czasSek - jest numeryczna calkowita wersja czasu podanego przez uzytkownika
     * @return czas w odpowiednim formacie tekstowym
     */
    private String sekundyNaCzas(int czasSek){
        int godziny = czasSek / 60;
        return String.format("%02d:%02d:%02d", godziny / 60, godziny % 60, czasSek % 60);
    }
    /**
     * Prywatna metoda zagrajMuzyke gra muzyke gdy skonczy się odliczanie
     *
     * @param jingle - jest tekstowym odnosnikiem do pliku z muzyka
     */
    private void zagrajMuzyke(String jingle){
        try {
            klip = AudioSystem.getClip();
            audioWejscie = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource(jingle)));
            klip.open(audioWejscie);
            klip.start();
            klip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
