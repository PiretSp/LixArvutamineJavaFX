import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Main  extends Application{
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        algus(primaryStage);
    }

    //Esimene aken, mis ette tuleb, kui programm käivitada
    public static void algus (Stage algusStage){
        VBox algusAken = new VBox();
        Scene esimeneAken = new Scene(algusAken, 500, 400);
        algusStage.setScene(esimeneAken);
        algusStage.show();
        Label valiMidaTeha = new Label("Vali, mida soovid teha");
        valiMidaTeha.setPadding(new Insets(10, 0, 0, 200));
        Button mineArvutama = new Button("Arvuta teksti loetavust");
        mineArvutama.setOnAction(event -> {
            tekstiAnalyysimine(algusStage);
        });

        Button lisaTekst = new Button("Lisa tekst andmebaasi");
        lisaTekst.setOnAction(event -> {
            andmebaasigaTegelemine(algusStage);
        });
        Button lopetaButton = new Button("Lõpeta");
        lopetaButton.setOnAction(event -> {
                    System.exit(0);
        });

        algusAken.setSpacing(20);
        algusAken.getChildren().addAll(valiMidaTeha, mineArvutama, lisaTekst, lopetaButton);
    }

    //Aken, kus kasutaja sisestab teksti, mida soovib analüüsida
    public static void tekstiAnalyysimine (Stage firstStage){

        //Teen kasti, mis küsib kasutajalt sisestatavat teksti. Hetkel on aken 500*400,

        VBox vBox = new VBox();                         //Teeb uue vBoxi
        vBox.setPadding(new Insets(10));                //Lisab ülesse äärde tühja ruumi.
        vBox.setSpacing(20);                            //Lisab kasti eri osade vahele tühja ruumi.
        Scene esimeneAken = new Scene(vBox, 500, 400);  //Stseeni loomine ja suurus.
        firstStage.setScene(esimeneAken);
        firstStage.show();

        Label pealkiri = new Label("Sisesta tekst, mille raskusastet soovid teada:"); //Nn kasti pealkiri
        TextArea tekstiKast = new TextArea();
        tekstiKast.setWrapText(true);                    //Murrab teksti uuele reale
        tekstiKast.setPrefWidth(300);                   //Annab kasti esialgsed mõõdud
        tekstiKast.setPrefHeight(200);

        // * Teen nupu, millele vajutades hakkaks programm arvutama.
        // * Arvutamiseks tegin uue mooduli (??) kõige alla. (public static void tegelemeTekstiga)

        Button submitbutton = new Button("Arvuta");      //Teen uue nupu, millele vajutades programm arvutab
        submitbutton.setOnAction(event -> {             //See ütleb, et midagi peab juhtuma, kui nupule vajutada
            String kasutajaSisestus = tekstiKast.getText(); //See käsib võtta kasutaja sisestatud teksti ja sellega toimetama hakata.
            tegeleTekstiga(kasutajaSisestus);               //tegeleTekstiga on uus moodul
            Tekst kasutajaTekst = new Tekst(kasutajaSisestus);
            tulemusteVäljastamine(firstStage, kasutajaTekst);
        });

        Button mineEsilehele = new Button("Mine algusesse");     //Teen uue nupu, millele vajutades tuleb uus aken (teksti lisamine andmebaasi)
        mineEsilehele.setOnAction(event -> {                               //See ütleb, et midagi peab juhtuma, kui nupule vajutada
            algus(firstStage);
        });

        Button lopetaButton = new Button("Sulge programm");
        lopetaButton.setOnAction(event -> {
                    System.exit(0);
        });

        vBox.setSpacing(10);
        vBox.getChildren().addAll(pealkiri, tekstiKast, submitbutton, mineEsilehele, lopetaButton);

    }


    // Programm peab analüüsib kasutaja sisestatud teksti. See meetod on ainult minu enda jaoks.
    public static void tegeleTekstiga(String a){
        Tekst kasutajaTekst = new Tekst(a);

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
   public static void tulemusteVäljastamine(Stage secondaryStage, Tekst kasutajaTekst) {
       VBox teineKast = new VBox();
       Scene tulemused = new Scene(teineKast, 500, 400);
       secondaryStage.setScene(tulemused);
       secondaryStage.show();
       Label sonadeArv = new Label("Sõnade arv: " + kasutajaTekst.sonadeArv());
       Label lauseteArv = new Label("Lausete arv: " + kasutajaTekst.lauseteArv());
       DecimalFormat df = new DecimalFormat("###.00");
       Label LKP = new Label("LKP:" + df.format(kasutajaTekst.lauseKeskmPikkus()));
       Label pikadSonad = new Label ("Pikkade sõnade arv: " + kasutajaTekst.pikkadeSonadeArv());
       Label LIX = new Label("Teksti loetavusindeks: " + df.format(kasutajaTekst.lixArvutamine()));
       Label Kommentaar = new Label (kasutajaTekst.lixKommentaar());

       Button mineEsilehele = new Button("Mine algusesse");     //Teen uue nupu, millele vajutades tuleb uus aken (teksti lisamine andmebaasi)
       mineEsilehele.setOnAction(event -> {                               //See ütleb, et midagi peab juhtuma, kui nupule vajutada
           algus(secondaryStage);
       });

       Button andmeBaasiButton = new Button("Sisesta tekst andmebaasi");     //Teen uue nupu, millele vajutades tuleb uus aken (teksti lisamine andmebaasi)
       andmeBaasiButton.setOnAction(event -> {                               //See ütleb, et midagi peab juhtuma, kui nupule vajutada
           andmebaasigaTegelemine(secondaryStage);
           //System.exit(0);

       });

       Button lopetaButton = new Button("Sulge programm");
       lopetaButton.setOnAction(event -> {
           System.exit(0);
       });
       teineKast.setSpacing(5);
       teineKast.getChildren().addAll(sonadeArv, lauseteArv, LKP, pikadSonad, LIX, Kommentaar, mineEsilehele, andmeBaasiButton, lopetaButton);
   }

   //Teen akna, kus kasutajal on võimalik tekst andmebaasi lisada
   public static void andmebaasigaTegelemine(Stage thirdStage){
       VBox kolmasKast = new VBox();
       Scene uusTekst = new Scene(kolmasKast, 600, 500);
       thirdStage.setScene(uusTekst);
       thirdStage.show();

       Andmebaas yhendus = new Andmebaas();
       Label autor = new Label("Sisesta autor");
       TextField autoriKast = new TextField();
       Label pealkiri = new Label("Sisesta pealkiri");
       TextField pealkirjaKast = new TextField();
       Label lihtsustatudTekst = new Label("Sisesta lihtsustatud tekst");
       TextArea lihtsustatudTekstiKast = new TextArea();
       Label kommentaar = new Label("Sisesta kommentaar");
       TextField kommentaariKast = new TextField();

       Button salvestaAndmebaasi = new Button("Salvesta");
       salvestaAndmebaasi.setOnAction(event -> {
           String lisaAutor = autoriKast.getText();
           String lisaPealkiri = pealkirjaKast.getText();
           String lisaTekst = lihtsustatudTekstiKast.getText();
           String lisaKommentaar = kommentaariKast.getText();
           yhendus.lisaTekstAndmebaasi(lisaAutor, lisaPealkiri, lisaTekst, lisaKommentaar);
           yhendus.sulgeYhendus();
           tagasisideLeht(thirdStage);
       });

       Button mineEsilehele = new Button("Mine algusesse");     //Teen uue nupu, millele vajutades tuleb uus aken (teksti lisamine andmebaasi)
       mineEsilehele.setOnAction(event -> {                               //See ütleb, et midagi peab juhtuma, kui nupule vajutada
           algus(thirdStage);
       });

       Button lopetaButton = new Button("Sulge programm");
       lopetaButton.setOnAction(event -> {
           System.exit(0);
       });
       kolmasKast.setSpacing(10);
       kolmasKast.getChildren().addAll(autor, autoriKast, pealkiri, pealkirjaKast, kommentaar, kommentaariKast, lihtsustatudTekst, lihtsustatudTekstiKast, salvestaAndmebaasi, mineEsilehele, lopetaButton);
   }

    public static void tagasisideLeht(Stage tagasisideStage){
        VBox neljasKast = new VBox();
        Scene tagasiside = new Scene(neljasKast, 500, 400);
        tagasisideStage.setScene(tagasiside);
        tagasisideStage.show();
        Label tänuSõnum = new Label("Aitäh, et täiendasid andmebaasi");

        Button andmeBaasiButton = new Button("Sisesta uus tekst andmebaasi");     //Teen uue nupu, millele vajutades tuleb uus aken (teksti lisamine andmebaasi)
        andmeBaasiButton.setOnAction(event -> {                               //See ütleb, et midagi peab juhtuma, kui nupule vajutada
            andmebaasigaTegelemine(tagasisideStage);
        });

        Button mineEsilehele = new Button("Mine algusesse");     //Teen uue nupu, millele vajutades tuleb uus aken (teksti lisamine andmebaasi)
        mineEsilehele.setOnAction(event -> {                               //See ütleb, et midagi peab juhtuma, kui nupule vajutada
            algus(tagasisideStage);
        });

        Button lopetaButton = new Button("Sulge programm");
        lopetaButton.setOnAction(event -> {
            System.exit(0);
        });
        neljasKast.setSpacing(10);
        neljasKast.getChildren().addAll(tänuSõnum, andmeBaasiButton, mineEsilehele, lopetaButton);
    }
}


