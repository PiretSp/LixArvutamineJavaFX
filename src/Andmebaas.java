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
        System.out.println("Opened database successfully");                             // lihtsalt meie enda jaoks teade
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
        System.out.println("Tekst on lisatud.");
    }

    /*public boolean login(String username, String password) {
        try {
            Statement stat = conn.createStatement();

            /*SELECT on nagu excelis hiirega "selekteeriks" mingeid kaste. SQLis tähendab konkreetselt,
            et milliste tulpade infot soovid kätte saada. WHERE'iga käsid välja võtta ainult tingimustele
            vastavad väljad.*/
            /*String sql = "SELECT * FROM USERS WHERE USERNAME = '" + username + "' LIMIT 1;";

            // Kuna tegu on päringuga siis käsuks on executeQuery ja ta tagastab andme objekti ResultSet.
            ResultSet rs = stat.executeQuery(sql);

            /*Kui Query andmeid ei tagastanud (päring ei toonud tulemusi) siis rs-i kasutada ei saa.
            Seepärast, kui kasutajat ei eksisteeri, tuleb lihtsalt error ja "return" käsuni ei jõutagi.
            Aga jõutakse "return false" käsuni.*/
            /*String dbPassword = rs.getString("password");

            rs.close();
            stat.close();
            return password.equals(dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }*/

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

    public void uuendaKasutajaAndmeid(HashMap<String, String> andmed) {
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