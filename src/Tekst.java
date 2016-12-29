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
    //Lause keskmine pikkus
    //Pikkade sõnade arv
    //Pikkade sõnade protsent
    //LIX
}
