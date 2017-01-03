import javafx.application.Application;
import javafx.geometry.Insets;
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
    public static void algus (Stage firstStage){

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
        //tekstiKast.setMaxSize(500, 400);                //Annab kasti max mõõdud


        // * Teen nupu, millele vajutades hakkaks programm arvutama.
        // * Arvutamiseks tegin uue mooduli (??) kõige alla. (public static void tegelemeTekstiga)

        Button submitbutton = new Button("Arvuta");      //Teen uue nupu, millele vajutades programm arvutab
        submitbutton.setOnAction(event -> {             //See ütleb, et midagi peab juhtuma, kui nupule vajutada
            String kasutajaSisestus = tekstiKast.getText(); //See käsib võtta kasutaja sisestatud teksti ja sellega toimetama hakata.
            tegeleTekstiga(kasutajaSisestus);               //tegeleTekstiga on uus moodul
            Tekst kasutajaTekst = new Tekst(kasutajaSisestus);
            tulemusteVäljastamine(firstStage, kasutajaTekst);
        });

        vBox.getChildren().addAll(pealkiri, tekstiKast, submitbutton);

    }


    // Programm peab analüüsib kasutaja sisestatud teksti.
    public static void tegeleTekstiga(String tekst){
        Tekst kasutajaTekst = new Tekst(tekst);

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

       Button lopetaButton = new Button("Lõpeta");
       lopetaButton.setOnAction(event -> {
           System.exit(0);
       });

       teineKast.getChildren().addAll(sonadeArv, lauseteArv, LKP, pikadSonad, LIX, Kommentaar, mineEsilehele, andmeBaasiButton, lopetaButton);
   }

   //Teen akna, kus kasutajal on võimalik tekst andmebaasi lisada
   public static void andmebaasigaTegelemine(Stage thirdStage){
       VBox kolmasKast = new VBox();
       Scene uusTekst = new Scene(kolmasKast, 500, 400);
       thirdStage.setScene(uusTekst);
       thirdStage.show();
       Label autor = new Label("Sisesta autor");
       TextField autoriKast = new TextField();
       Label pealkiri = new Label("Sisesta pealkiri");
       TextField pealkirjaKast = new TextField();
       Label lihtsustatudTekst = new Label("Sisesta lihtsustatud tekst");
       TextArea lihtsustatudTekstiKast = new TextArea();
       Button mineEsilehele = new Button("Mine algusesse");     //Teen uue nupu, millele vajutades tuleb uus aken (teksti lisamine andmebaasi)
       mineEsilehele.setOnAction(event -> {                               //See ütleb, et midagi peab juhtuma, kui nupule vajutada
           algus(thirdStage);
       });

       Button lopetaButton = new Button("Lõpeta");
       lopetaButton.setOnAction(event -> {
           System.exit(0);
       });

           kolmasKast.getChildren().addAll(autor, autoriKast, pealkiri, pealkirjaKast, lihtsustatudTekst, lihtsustatudTekstiKast, mineEsilehele);
          }
}


