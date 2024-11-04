import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Ta klasa sluzy do ustawienia i zapisywania roznych rzeczy
 *
 * @author Gb125Dev
 * @version 1.0
 */
public class Ustawienia {
    /** Zmienna odpowiadajaca za wczytywanie i zapisywanie wlasciwosci czasu i ustawien */
    private static final Properties wlasciwosci = new Properties();
    /** Zmienna odpowiadajaca za kolor tla*/
    protected static Color kolorTlaDomyslny;
    /** Zmienna odpowiadajaca za kolor napisow*/
    protected static Color kolorNapisowDomyslny;
    /** Zmienna odpowiadajaca za specjalny kolor tla*/
    protected static Color kolorTlaSpecjalny;
    /** Zmienna odpowiadajaca za rozmiar czcionki*/
    protected static int rozmiarCzcionki;
    /** Zmienna odpowiadajaca za dzwonek/muzyke na koncu zadania*/
    protected static String domyslnyDzwonek;
    /** Zmienna odpowiadajaca za zmiane rozmiaru czcionki*/
    protected static Font nowaCzcionka;

    /**
     * Publiczna metoda ustawianieKoloru wybierajaca odpowiednie zmienne tla,koloru napisow,czcionki i muzyki
     * na podstawie podanych wartosci, zapisyweania i nakladanie tego na szate graficzna
     *
     * @param wybranyKolor - jest liczbowa wersja koloru wybranego przez uzytkownika
     * @param wybranyRozmCzcionki - jest liczbowa wersja rozmiaru czcionki wybranej przez uzytkownika
     * @param wybranaMuzyka - jest liczbowa wersja muzyki wybranej przez uzytkownika
     */
    public static void ustawianieKoloru(int wybranyKolor,int wybranyRozmCzcionki,int wybranaMuzyka){
        switch (wybranyKolor) {
            case 0:
                kolorTlaDomyslny = Color.black;
                kolorNapisowDomyslny = Color.ORANGE;
                break;
            case 1:
                kolorTlaDomyslny = Color.white;
                kolorNapisowDomyslny = Color.GRAY;
                break;
            case 2:
                kolorTlaDomyslny = new Color(99, 70, 45);
                kolorNapisowDomyslny = new Color(164, 116, 73);
                break;
            case 3:
                kolorTlaDomyslny = Color.decode("#777777");
                kolorNapisowDomyslny = Color.decode("#cccccc");
                break;
        }
        switch (wybranyRozmCzcionki) {
            case 0:
                rozmiarCzcionki = 20;
                break;
            case 1:
                rozmiarCzcionki = 35;
                break;
            case 2:
                rozmiarCzcionki = 50;
                break;
            case 3:
                rozmiarCzcionki = 56;
                break;
        }
        switch (wybranaMuzyka) {
            case 0:
                domyslnyDzwonek = "/jingleA.wav";
                break;
            case 1:
                domyslnyDzwonek = "/jingleB.wav";
                break;
            case 2:
                domyslnyDzwonek = "/jingleC.wav";
                break;
            case 3:
                domyslnyDzwonek = "/jingleD.wav";
                break;
            case 4:
                domyslnyDzwonek = "/jingleE.wav";
                break;
        }
        nowaCzcionka = new Font("Serif", Font.PLAIN, rozmiarCzcionki);
        zapis(MinutnikPanel.czas.getText(),
                String.valueOf(wybranyKolor),
                String.valueOf(wybranyRozmCzcionki),
                String.valueOf(wybranaMuzyka));
        ustawienie();
    }
    /**
     * Prywatna metoda ustawienie ustawiajaca kolor tla,napisow,wielkosc napisow i muzyke na wybrana przez uzytkownika
     */
    private static void ustawienie(){
        MinutnikPanel.glownyPanel.setBackground(kolorTlaDomyslny);
        MinutnikPanel.panelFunkcja.setBackground(kolorTlaDomyslny);
        MinutnikPanel.minutnikTytul.setForeground(kolorNapisowDomyslny);
        MinutnikPanel.minutnikTytul.setFont(nowaCzcionka);
        MinutnikPanel.czas.setForeground(kolorNapisowDomyslny);
        MinutnikPanel.czas.setFont(nowaCzcionka);
        MenuPanel.glownyPanel.setBackground(kolorTlaDomyslny);
        MenuPanel.ustawienia.setForeground(kolorNapisowDomyslny);
        MenuPanel.info.setForeground(kolorNapisowDomyslny);
    }
    /**
     * Chroniona metoda zapis zapisujaca wszystkie podane wartosci
     *
     * @param ustawionyCzasZapis - to tekstowa wartosc czasu
     * @param tloZapis - to tekstowa wartosc wybranej liczby numeru tla
     * @param czcionkaRozmiarZapis - to tekstowa wartosc liczby wybranej czcionki
     * @param muzykaZapis - to tekstowa wartosc liczby wybranej muzyki
     */
    protected static void zapis(String ustawionyCzasZapis,String tloZapis,String czcionkaRozmiarZapis,String muzykaZapis){
        wlasciwosci.setProperty("ostatniCzasZapis", ustawionyCzasZapis);
        wlasciwosci.setProperty("tloNumer", tloZapis);
        wlasciwosci.setProperty("napisyNumer", czcionkaRozmiarZapis);
        wlasciwosci.setProperty("muzykaNumer", muzykaZapis);
        try {
            wlasciwosci.store(new FileOutputStream("timesVariables.properties"),null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


