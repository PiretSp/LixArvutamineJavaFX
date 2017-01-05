import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by piret on 28.12.16.
 */
public class Tekst {    //Uus klass, mille nimi on Tekst

    public static String tekst = "";    //

    // konstruktor
    public Tekst(String analüüsitavTekst) { //
        tekst = analüüsitavTekst;           //
    }

    //SÕNADE ARV - Leitakse kasutaja sisestatud tekstist sõnade arv
    public static int sonadeArv(){
        String[] sõnad = tekst.replaceAll("[-–+.^:,;!?„“]","").split("[ \n]");  //Eemaldan märgid
        ArrayList tühjadSõnadEemaldatud = new ArrayList();                      //Teen ArrayListi, kuhu pannakse vaid märkideta sõnad
        for (String sõna : sõnad){                                              //
            sõna = sõna.trim();                                                 //
            if (sõna != null && !sõna.isEmpty()) {                              //Kui sõna ei ole tühi, siis lisatakse ArrayListi
                tühjadSõnadEemaldatud.add(sõna);
            }
        }
        return tühjadSõnadEemaldatud.size();                                    //Annan Main.java-le korrastatud ArrayListist sõnade arvu //.size loendab.

    }

    //LAUSETE ARV - Leitakse kasutaja sisestatud tekstist lausete arv
    public static int lauseteArv(){
        String[] laused = tekst.trim().split("[.!?„“]");                        //Laused eraldatakse nende märkide abil
        ArrayList kirjavahemärgitaLaused = new ArrayList();                     //Teen ArrayListi, kuhu pannakse märkideta laused
        for (String lause : laused){                                            //sõna : sõnad on for each kirjaviis, teisisõnu on see
            lause = lause.trim();                                               //tsükkel. Võtan ühe sõna sõnade massiivist.
            if (lause != null && !lause.isEmpty()) {
                kirjavahemärgitaLaused.add(lause);
            }
        }
        return kirjavahemärgitaLaused.size();                                   //Tagastan korrastatud ArrayListist Main.java-le lausete arvu
    }

    //LKP - Leitakse kasutaja sisestatud teksti lausete keskmine pikkus
    public static double lauseKeskmPikkus(){
        int lausetearv = lauseteArv();
        double sõnadearv = (double)sonadeArv();
        double LKP = sõnadearv/lausetearv;
        return LKP;                                         //Tagastan Main.java-le LKP
    }

    //PIKKADE SÕNADE ARV - Leitakse pikkade sõnade arv kasutaja sisestatud tekstist
    public static int pikkadeSonadeArv(){
        int arv = 0;
        String[] sõnad = tekst.split("[ \n]");
        for (String sõna : sõnad){                          //sõna : sõnad on for each kirjaviis, teisisõnu on see tsükkel. Võtan ühe sõna sõnade massiivist.
            sõna = sõna.replaceAll("[-–+.^:,;!?„“]","");
            if (sõna.length() >= 7) {                       //Võtan sõna ja kontrollin, kas on >=7 tähte
                arv++;                                      //
            }
        }
        return arv;                                         //Tagastan Main.java-le pikkade sõnade arvu
    }

    //PIKKADE SÕNADE % - leitakse kasutaja sisestatud tekstist pikkade sõnade %
    public static double pikkadeSonadeProtsent(){
        return pikkadeSonadeArv()*100/sonadeArv();          //Tagastan Main.java-le pikkade sõnade %-i
    }

    //LIX - leitakse loetavusindeks LIX
    public static double lixArvutamine(){
        return lauseKeskmPikkus() + pikkadeSonadeProtsent();    //Tagastan Main.java-le LIX-i
    }

    //LIX KOMMENTAAR - täiendab LIX infot
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
