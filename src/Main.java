import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Main  extends Application{
    

    @Override
    public void start(Stage primaryStage) throws Exception {    //See on kõige esimene ja peamine Stage, mis viib automaatselt algusStage'i
        algus(primaryStage);
    }

    //Esimene aken, mis ette tuleb, kui programm käivitada
    public static void algus (Stage algusStage){
        VBox algusAken = new VBox();                         //Teeb uue vBoxi
        algusAken.setPadding(new Insets(10));                //Lisab äärtesse tühja ruumi.
        algusAken.setSpacing(20);                            //Lisab kasti eri osade vahele tühja ruumi.
        Scene esimeneAken = new Scene(algusAken, 500, 400);  //Teeb uue stseeni ja määrab akna suuruse
        algusStage.setScene(esimeneAken);
        algusStage.show();
        Label valiMidaTeha = new Label("Vali, mida soovid teha");   //Tekst kasti sees kasutaja jaoks

        //Nupp, millele vajutades liigutakse meetodisse, mis arvutab teksti keerukust
        Button mineArvutama = new Button("Arvuta teksti keerukust");
        mineArvutama.setOnAction(event -> {
            tekstiAnalyysimine(algusStage);
        });

        //Nupp, millele vajutades liigutakse meetodisse, mis lisab teksti andmebaasi
        Button lisaTekst = new Button("Lisa tekst andmebaasi");
        lisaTekst.setOnAction(event -> {
            andmebaasigaTegelemine(algusStage);
        });

        //Nupp, millele vajutades pannakse programm kinni
        Button lopetaButton = new Button("Sulge programm");
        lopetaButton.setOnAction(event -> {
                    System.exit(0);
        });

        //getChildren lisab loodud nupud ja labelis aknasse.
        algusAken.getChildren().addAll(valiMidaTeha, mineArvutama, lisaTekst, lopetaButton);
    }

    //Aken, kus kasutaja sisestab teksti, mida soovib analüüsida
    public static void tekstiAnalyysimine (Stage firstStage){
        VBox analüüsiAken = new VBox();                         //Teeb uue vBoxi
        analüüsiAken.setPadding(new Insets(10));                //Lisab äärtesse tühja ruumi
        analüüsiAken.setSpacing(20);                            //Lisab kasti eri osade vahele tühja ruumi.
        Scene esimeneAken = new Scene(analüüsiAken, 500, 400);  //Stseeni loomine ja suurus.
        firstStage.setScene(esimeneAken);
        firstStage.show();                                      //Teeb Stage'i nähtavaks

        Label pealkiri = new Label("Sisesta tekst, mille raskusastet soovid teada:"); //Nn kasti pealkiri
        TextArea tekstiKast = new TextArea();            //Teeb kasti, kuhu sisse saab teksti lisada
        tekstiKast.setWrapText(true);                    //Murrab teksti uuele reale

        //Nupp, millele vajutades liigutakse tekstiga analüüsimise meetodisse
        Button submitbutton = new Button("Arvuta");             //Teen uue nupu, millele vajutades programm arvutab
        submitbutton.setOnAction(event -> {                     //setOnAction ütleb, et midagi peab juhtuma, kui nupule vajutada
            String kasutajaSisestus = tekstiKast.getText();     //See käsib võtta kasutaja sisestatud teksti ja sellega toimetama hakata.
            tegeleTekstiga(kasutajaSisestus);                   //tegeleTekstiga on uus meetod
            Tekst kasutajaTekst = new Tekst(kasutajaSisestus);  //
            tulemusteVäljastamine(firstStage, kasutajaTekst);   //Nupule vajutades avatakse aken, kus kuvatakse tulemused
        });

        //Nupp, millele vajutades avatakse esimene aken
        Button mineEsilehele = new Button("Mine algusesse");
        mineEsilehele.setOnAction(event -> {                   //See ütleb, et midagi peab juhtuma, kui nupule vajutada
            algus(firstStage);                                 //See ütleb, et peab minema meetodisse "algus".
        });

        //Nupp, millele vajutades pannakse programm kinni
        Button lopetaButton = new Button("Sulge programm");
        lopetaButton.setOnAction(event -> {
                    System.exit(0);                             //See ütlen, et peab programmi sulgema
        });

        analüüsiAken.getChildren().addAll(pealkiri, tekstiKast, submitbutton, mineEsilehele, lopetaButton);

    }


    // Programm peab analüüsib kasutaja sisestatud teksti. SEE MEETOD ON VAID MINU ENDA JAOKS. SEDA KASUTAJA EI NÄE.
    public static void tegeleTekstiga(String analüüsitavTekst){
        Tekst kasutajaTekst = new Tekst(analüüsitavTekst);      //

        //Sõnade arvu leidmine
        System.out.println("Sõnade arv sisestatud tekstis: " + kasutajaTekst.sonadeArv());

        //Lausete arvu leidmine
        System.out.println("Lausete arv sisestatud tekstis: " + kasutajaTekst.lauseteArv());

        //Lause keskmise pikkuse leidmine
        DecimalFormat df = new DecimalFormat("###.00");
        System.out.println("Lausete keskmine pikkus on " + df.format(kasutajaTekst.lauseKeskmPikkus()) + " sõna");

        //Pikkade sõnade leidmine
        System.out.println("Pikki sõnu on tekstis " + kasutajaTekst.pikkadeSonadeArv());                                                //Prindin pikad sõnad

        //Pikkade sõnade osakaalu leidmine
        System.out.println("Pikkade sõnade osakaal: " + kasutajaTekst.pikkadeSonadeProtsent() + "%");

        //LIX arvutamine
        System.out.println("Teksti loetavusindeks LIX on " + kasutajaTekst.lixArvutamine());


    }

    // Programm väljastab kasutajale analüüsitud teksti tulemused.
   public static void tulemusteVäljastamine(Stage secondaryStage, Tekst kasutajaTekst) {    //Teen uue meetodi nimega tulemusteVäljastamine
       VBox teineKast = new VBox();                                                         //Teen uue vBoxi
       teineKast.setPadding(new Insets(10));                //Lisab äärtesse tühja ruumi
       teineKast.setSpacing(20);                            //Lisab kasti eri osade vahele tühja ruumi.
       Scene tulemused = new Scene(teineKast, 500, 400);
       secondaryStage.setScene(tulemused);
       secondaryStage.show();
       Label sonadeArv = new Label("Sõnade arv: " + kasutajaTekst.sonadeArv());             //Teen uued labelid ja lisan nende juurde
       Label lauseteArv = new Label("Lausete arv: " + kasutajaTekst.lauseteArv());          //ka tulemused, mille saan Text.java-st
       DecimalFormat df = new DecimalFormat("###.00");                                      //Seda on vaja, et näitaks 2 kohta peale koma
       Label LKP = new Label("LKP: " + df.format(kasutajaTekst.lauseKeskmPikkus()));         //Kaks kohta peale koma kajastub LKP kuvamisel
       Label pikadSonad = new Label ("Pikkade sõnade arv: " + kasutajaTekst.pikkadeSonadeArv());
       Label LIX = new Label("Teksti loetavusindeks: " + df.format(kasutajaTekst.lixArvutamine()));
       Label Kommentaar = new Label (kasutajaTekst.lixKommentaar());

       //Nupp, millele vajutades liigutakse esimesel lehele
       Button mineEsilehele = new Button("Mine algusesse");
       mineEsilehele.setOnAction(event -> {
           algus(secondaryStage);
       });

       //Nupp, millele vajutades liigutakse teksti andmebaasi lisamise meetodisse
       Button andmeBaasiButton = new Button("Sisesta tekst andmebaasi");
       andmeBaasiButton.setOnAction(event -> {
           andmebaasigaTegelemine(secondaryStage);
           //System.exit(0);
       });

       //Nupp, millele vajutades programm sulgub
       Button lopetaButton = new Button("Sulge programm");
       lopetaButton.setOnAction(event -> {
           System.exit(0);
       });

       teineKast.getChildren().addAll(sonadeArv, lauseteArv, LKP, pikadSonad, LIX, Kommentaar, mineEsilehele, andmeBaasiButton, lopetaButton);
   }

   //Teen akna, kus kasutajal on võimalik tekst andmebaasi lisada
   public static void andmebaasigaTegelemine(Stage thirdStage){         //Teen uue meetodi nimega andmebaasigaTegelemine
       VBox kolmasKast = new VBox();
       kolmasKast.setPadding(new Insets(10));
       kolmasKast.setSpacing(10);
       Scene uusTekst = new Scene(kolmasKast, 600, 500);
       thirdStage.setScene(uusTekst);
       thirdStage.show();

       Andmebaas yhendus = new Andmebaas();                             //Ühendan andmebaasiga
       Label autor = new Label("Sisesta autor");                        //Kirjeldan, mida kasutajalt oodatakse
       TextField autoriKast = new TextField();                          //Kast, kuhu kasutaja saab oodatava info kirjutada
       Label pealkiri = new Label("Sisesta pealkiri");
       TextField pealkirjaKast = new TextField();
       Label lihtsustatudTekst = new Label("Sisesta lihtsustatud tekst");
       TextArea lihtsustatudTekstiKast = new TextArea();
       Label kommentaar = new Label("Sisesta kommentaar");
       TextField kommentaariKast = new TextField();

       //Nupp, millele vajutades salvestatakse sisestatud info ja minnakse
       Button salvestaAndmebaasi = new Button("Salvesta");
           salvestaAndmebaasi.setOnAction(event ->{
               if (autoriKast.getText().isEmpty() || pealkirjaKast.getText().isEmpty() || kommentaariKast.getText().isEmpty() || lihtsustatudTekstiKast.getText().isEmpty()) {
                   yhendus.sulgeYhendus();
                   erroriAken(thirdStage);
               } else {
                   String lisaAutor = autoriKast.getText();
                   String lisaPealkiri = pealkirjaKast.getText();
                   String lisaTekst = lihtsustatudTekstiKast.getText();
                   String lisaKommentaar = kommentaariKast.getText();
                   yhendus.lisaTekstAndmebaasi(lisaAutor, lisaPealkiri, lisaTekst, lisaKommentaar);
                   yhendus.sulgeYhendus();

                   tagasisideLeht(thirdStage);
               }
           });


       //Nupp, millele vajutades minnakse esimesele lehel
       Button mineEsilehele = new Button("Mine algusesse");
       mineEsilehele.setOnAction(event -> {
           algus(thirdStage);
       });

       //Nupp, millele vajutades pannakse programm kinni
       Button lopetaButton = new Button("Sulge programm");
       lopetaButton.setOnAction(event -> {
           System.exit(0);
       });

       kolmasKast.getChildren().addAll(autor, autoriKast, pealkiri, pealkirjaKast, kommentaar, kommentaariKast, lihtsustatudTekst, lihtsustatudTekstiKast, salvestaAndmebaasi, mineEsilehele, lopetaButton);
   }
    //Teen akna, kus kasutajale kuvatakse tagasiside
    public static void tagasisideLeht(Stage tagasisideStage){
        VBox neljasKast = new VBox();
        neljasKast.setPadding(new Insets(10));
        neljasKast.setSpacing(10);
        Scene tagasiside = new Scene(neljasKast, 500, 400);
        tagasisideStage.setScene(tagasiside);
        tagasisideStage.show();
        Label tänuSõnum = new Label("Aitäh, et täiendasid andmebaasi");
        tänuSõnum.setAlignment(Pos.TOP_CENTER);

        //Nupp, millele vajutades minnakse teksti andmebaasi lisamise meetodisse
        Button andmeBaasiButton = new Button("Sisesta uus tekst andmebaasi");
        andmeBaasiButton.setOnAction(event -> {
            andmebaasigaTegelemine(tagasisideStage);
        });

        //Nupp, millele vajutades minnakse esimesele lehele
        Button mineEsilehele = new Button("Mine algusesse");
        mineEsilehele.setOnAction(event -> {
            algus(tagasisideStage);
        });

        //Nupp, millele vajutades pannakse programm kinni
        Button lopetaButton = new Button("Sulge programm");
        lopetaButton.setOnAction(event -> {
            System.exit(0);
        });

        neljasKast.getChildren().addAll(tänuSõnum, andmeBaasiButton, mineEsilehele, lopetaButton);
    }

    public static void erroriAken (Stage erroriStage){
        VBox erroriKast = new VBox();
        erroriKast.setSpacing(10);
        erroriKast.setPadding(new Insets(10));
        erroriKast.setAlignment(Pos.TOP_CENTER);
        Scene error = new Scene(erroriKast, 300, 100);
        erroriStage.setScene(error);
        erroriStage.show();
        Label erroriKiri = new Label("Palun täida kõik väljad!");

        //Nupp, millele vajutades minnakse teksti andmebaasi lisamise meetodisse
        Button andmeBaasiButton = new Button("Sisesta tekst uuesti");
        andmeBaasiButton.setOnAction(event -> {
            andmebaasigaTegelemine(erroriStage);
        });

        erroriKast.getChildren().addAll(erroriKiri, andmeBaasiButton);
    }
}


