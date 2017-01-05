import java.sql.*;
import java.util.HashMap;

// Aluseks on võetud Krister Viirsaare näidis
// Allikas: https://github.com/KristerV/javaSQLNaide/blob/master/src/Andmebaas.java

public class Andmebaas {
    Connection conn = null;

    // Konsutruktor ehk meetod, mis käivitub kohe objekti välja kutsumisel
    public Andmebaas() {
        looYhendus();
        looTabel();
    }

    // Et andmebaasi kasutada peame sellega esiteks ühenduse looma
    private void looYhendus() {
        try {
            Class.forName("org.sqlite.JDBC");                                           // Lae draiver sqlite.jar failist
            conn = DriverManager.getConnection("jdbc:sqlite:tekstidAndmebaasis.db");    // Loo ühendus andmebaasi failiga
        } catch ( Exception e ) {                                                       // Püüa kinni võimalikud errorid
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Ühendus on loodud");                             // lihtsalt meie enda jaoks teade
    }

    // Et andmebaasist kasu oleks, loome uue tabeli. See on nagu uus 'sheet' excelis.
    public void looTabel() {
        // Käsk ise on CREATE TABLE ja sulgude vahel on kõik tulbad, mida tahan, et tabel hoiaks.
        String sqlKask = "CREATE TABLE IF NOT EXISTS TEKSTID_ANDMEBAASIS (AUTOR TEXT, PEALKIRI TEXT, " + // jätkub järgmisel real
                "TEKST TEXT, KOMMENTAAR TEXT);";
        teostaAndmebaasiMuudatus(sqlKask);
    }

    /* Andmebaasi muudatused ei tagasta väärtusi (erinevalt
    päringutest) ja on lihtne eraldi meetodi tuua.*/
    private void teostaAndmebaasiMuudatus(String sql) {
        try {
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lisaTekstAndmebaasi(String autor, String pealkiri, String tekst, String kommentaar) {
        /* Andmete sisestamiseks on käsk INSERT. Esimestes sulgudes on tulbad, KUHU salvestada,
        teistes sulgudes VALUES() on MIDA salvestada.*/
        String sql = "INSERT INTO TEKSTID_ANDMEBAASIS (AUTOR, PEALKIRI, TEKST, KOMMENTAAR) VALUES ('"+autor+"','"+pealkiri+"','"+tekst+"','"+kommentaar+"')";
        teostaAndmebaasiMuudatus(sql);
        System.out.println("Tekst on lisatud");
    }


    /*Kui programmis avad ainult ühendusi ja ühtegi ei sulg,e siis see kulutab arvuti (serveri) ressursse.
    Üsna kiiresti võib masina kokku jooksutada.*/
    public void sulgeYhendus() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }

    // Teksti otsimine andmebaasist
    public HashMap otsiTeksti (String autor) {
        HashMap andmed = new HashMap();
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM TEKSTID_ANDMEBAASIS WHERE AUTOR = '" + autor + "' LIMIT 3;"; // LIMIT piirab tulemuste arvu.

            ResultSet rs = stat.executeQuery(sql);
            // Kui stat.executeQuery() toob tagasi tühja tulemuse, siis rs'i kasutada ei saa.

            // Kui oleks mitu rida andmeid, peaks tsükliga lahendama while (rs.next()) {}

            // Nopin käsitsi andmed ühelt realt välja
            andmed.put("autor", rs.getString("autor"));
            andmed.put("pealkiri", rs.getString("pealkiri"));
            andmed.put("tekst", rs.getString("tekst"));
            andmed.put("kommentaar", rs.getString("kommentaar"));

            rs.close();
            stat.close();
            return andmed;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return andmed;
    }

    public void uuendaTeksti (HashMap<String, String> andmed) {
        String autor = andmed.get("autor");
        String pealkiri = andmed.get("pealkiri");
        String tekst = andmed.get("tekst");
        String kommentaar = andmed.get("kommentaar");

        try {
            Statement stat = conn.createStatement();
            // Andmete uuendamise käsk on UPDATE. SET ütleb, et nüüd tulevad need uued andmed sisse. WHERE ütleb mis ridu uuendada.
            String sql = String.format("UPDATE TEKSTID_ANDMEBAASIS SET AUTOR = '%s', PEALKIRI = '%s', TEKST = '%s', KOMMENTAAR = '%s' WHERE AUTOR = '%s';", autor, pealkiri, tekst, kommentaar);
            stat.executeUpdate(sql);
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}