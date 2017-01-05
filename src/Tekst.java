import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by piret on 28.12.16.
 */
public class Tekst {

    public static String tekst = "";

    // konstruktor
    public Tekst(String a) {
        tekst = a;
    }

    //Sõnade arv
    public static int sonadeArv(){
        String[] sõnad = tekst.replaceAll("[-–+.^:,;!?„“]","").split("[ \n]");
        ArrayList tühjadSõnadEemaldatud = new ArrayList();
        for (String sõna : sõnad){
            sõna = sõna.trim();
            if (sõna != null && !sõna.isEmpty()) {
                tühjadSõnadEemaldatud.add(sõna);
            }
        }
        return tühjadSõnadEemaldatud.size();

    }

    //Lausete arv
    public static int lauseteArv(){
        String[] laused = tekst.trim().split("[.!?„“]");
        ArrayList kirjavahemärgitaLaused = new ArrayList();
        for (String lause : laused){                                                                          //sõna : sõnad on for each kirjaviis, teisisõnu on see tsükkel. Võtan ühe sõna sõnade massiivist.
            lause = lause.trim();
            if (lause != null && !lause.isEmpty()) {
                kirjavahemärgitaLaused.add(lause);
            }
        }
        return kirjavahemärgitaLaused.size();
    }
    //Lause keskmine pikkus
    public static double lauseKeskmPikkus(){
        int lausetearv = lauseteArv();
        double sõnadearv = (double)sonadeArv();
        double LKP = sõnadearv/lausetearv;
        return LKP;
    }
    //Pikkade sõnade arv
    public static int pikkadeSonadeArv(){
        int arv = 0;
        String[] sõnad = tekst.split("[ \n]");
        for (String sõna : sõnad){                                                                          //sõna : sõnad on for each kirjaviis, teisisõnu on see tsükkel. Võtan ühe sõna sõnade massiivist.
            sõna = sõna.replaceAll("[-–+.^:,;!?„“]","");
            if (sõna.length() >= 7) {                                                                        //Võtan i-nda sõna, milles on 7 tähemärki või rohkem
                System.out.println("2 "+sõna);
                arv++;
            }
        }
        return arv;
    }
    //Pikkade sõnade protsent
    public static double pikkadeSonadeProtsent(){
        return pikkadeSonadeArv()*100/sonadeArv();
    }


    //LIX
    public static double lixArvutamine(){
        return lauseKeskmPikkus() + pikkadeSonadeProtsent();
    }

    //LIX kommentaar
    public static String lixKommentaar () {
        if (lixArvutamine() < 24) {
            return ("KOMMENTAAR: Tegemist on väga kerge tekstiga");
        } else if (lixArvutamine() > 24 && lixArvutamine() < 35) {
            return("KOMMENTAAR: Tegemist on kerge tekstiga");
        } else if (lixArvutamine() > 35 && lixArvutamine() < 45) {
            return("KOMMENTAAR: Tekst on keskmise raskusega");
        } else if (lixArvutamine() > 45 && lixArvutamine() < 54) {
            return("KOMMENTAAR: Tegemist on raske tekstiga");
        } else {
            return("KOMMENTAAR: Tegemist on väga raske tekstiga");
        }
    }
}
