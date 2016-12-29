import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main  extends Application{
    

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Teen kasti, mis küsib kasutajalt sisestatavat teksti. Hetkel on aken 500*400,
        //nüüd oleks vaja leiutada viis, kuidas teksti saaks kasti sees murda.

        VBox vBox = new VBox();                         //Teeb uue vBoxi
        vBox.setPadding(new Insets(10));                //Lisab ülesse äärde tühja ruumi.
        vBox.setSpacing(20);                            //Lisab kasti eri osade vahele tühja ruumi.
        Scene esimeneAken = new Scene(vBox, 500, 400);  //Stseeni loomine ja suurus.
        primaryStage.setScene(esimeneAken);
        primaryStage.show();

        Label pealkiri = new Label("Sisesta tekst, mille raskusastet soovid teada:"); //Nn kasti pealkiri
        TextArea tekstiKast = new TextArea();
        //tekstiKast.setWrapText(true);                    //Murrab teksti uuele reale
        tekstiKast.setPrefWidth(300);                   //Annab kasti esialgsed mõõdud
        tekstiKast.setPrefHeight(200);
        tekstiKast.setMaxSize(500, 400);                //Annab kasti max mõõdud


        // * Teen nupu, millele vajutades hakkaks programm arvutama.
        // * Arvutamiseks tegin uue mooduli (??) kõige alla. (public static void tegelemeTekstiga)

        Button submitbutton = new Button("Arvuta");     //Teen uue nupu, millele vajutades programm arvutab
            submitbutton.setOnAction(event -> {             //See ütleb, et midagi peab juhtuma, kui nupule vajutada
            String kasutajaSisestus = tekstiKast.getText(); //See käsib võtta kasutaja sisestatud teksti ja sellega toimetama hakata.
            tegeleTekstiga(kasutajaSisestus);               //tegeleTekstiga on uus moodul
            Tekst kasutajaTekst = new Tekst(kasutajaSisestus);
            tulemusteVäljastamine(primaryStage, kasutajaTekst);
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
       Label LKP = new Label("LKP:" + kasutajaTekst.lauseKeskmPikkus());
       Label pikadSonad = new Label ("Pikkade sõnade arv: " + kasutajaTekst.pikkadeSonadeArv());
       Label LIX = new Label("Teksti loetavusindeks: " + kasutajaTekst.lixArvutamine());


       Button lopetaButton = new Button("Lõpeta");     //Teen uue nupu, millele vajutades programm arvutab
       lopetaButton.setOnAction(event -> {             //See ütleb, et midagi peab juhtuma, kui nupule vajutada
           System.exit(0);
       });

       teineKast.getChildren().addAll(sonadeArv, lauseteArv, LKP, pikadSonad, LIX, lopetaButton);
   }
}


