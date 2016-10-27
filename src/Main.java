import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main  extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();
        Scene esimeneAken = new Scene(vbox, 500, 400);
        primaryStage.setScene(esimeneAken);
        primaryStage.show();

        Label pealkiri = new Label("Sisesta tekst, mille raskusastet soovid teada");
        TextField tekstiKast = new TextField();
        tekstiKast.setPrefWidth(200);
        tekstiKast.setPrefHeight(300);
        Button submitbutton = new Button("Arvuta");

        vbox.getChildren().addAll(pealkiri, tekstiKast, submitbutton);

    }
}
