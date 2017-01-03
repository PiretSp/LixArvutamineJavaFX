/**
 * Created by piret on 28.12.16.
 */
public class Tekst {

    public static String tekst = "";

    public Tekst(String kasutajaTekst) {
        tekst = kasutajaTekst;
    }

    //Sõnade arv
    public static int sonadeArv(){
        String[] sõnad = tekst.split("[ \n]");
        return sõnad.length;
    }

    //Lausete arv
    public static int lauseteArv(){
        String[] laused = tekst.trim().split("[.!?]");
        return laused.length;
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
            sõna = sõna.replaceAll("[-+.^:,;!?]","");
            if (sõna.length() >= 7) {                                                                        //Võtan i-nda sõna, milles on 7 tähemärki või rohkem
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
            return ("Tegemist on väga kerge tekstiga");
        } else if (lixArvutamine() > 24 && lixArvutamine() < 35) {
            return("Tegemist on kerge tekstiga");
        } else if (lixArvutamine() > 35 && lixArvutamine() < 45) {
            return("Tekst on keskmise raskusega");
        } else if (lixArvutamine() > 45 && lixArvutamine() < 54) {
            return("Tegemist on raske tekstiga");
        } else {
            return("Tegemist on väga raske tekstiga");
        }
    }
}
