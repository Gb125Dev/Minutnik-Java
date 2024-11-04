import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Objects;
/**
 * Klasa Main czyli glowna klasa implementuje uruchomienie calej aplikacji
 * Minutnika ktory ustawa czas ktory spada do zera i
 * odtwarza dzwiek gdy zadanie(ustawiony przez uzytkownika czas)
 * sie skonczy.
 *
 * @author Gb125Dev
 * @version 1.1
 * */
public class Main {
    /**
     * Metoda statyczna sluzaca do uruchomienia calej ramki aplikacji i ikony
     *
     * @param args sluzy do uruchomienia calej aplikacji
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ramka = new JFrame("Prosty Minutnik");

            ramka.pack();
            ramka.setVisible(true);
            ramka.setMinimumSize(new Dimension(800,800));
            ramka.setResizable(true);
            new MenuPanel();
            ramka.setJMenuBar(MenuPanel.glownyPanel);

            new MinutnikPanel();
            ramka.add(MinutnikPanel.glownyPanel);
            ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ramka.setLocationRelativeTo(null);
            try {
                ramka.setIconImage(ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/minutnik_logo.png"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}