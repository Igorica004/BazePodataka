package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import model.Fakultet;

import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
   public static Connection connection = null;

   public static void connect(){
      Properties properties = new Properties();
      properties.put("user","root");
      properties.put("password", "jovanaigor");
      try{
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novi_pocetak",properties);
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
   }

   public static int login(String username, String password){
      String query = "select psihoterapeut_id from nalog where username=? and password=?";
       try {
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setString(1,username);
           statement.setString(2,password);
           ResultSet rs = statement.executeQuery();
           if(rs.next())
              return rs.getInt("psihoterapeut_id");
           return -1;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

    public static ObservableList<NivoObrazovanja> getNivoObrazovanja(){
        ObservableList<NivoObrazovanja> nivoObrazovanjaList = FXCollections.observableArrayList();
        String query = "select * from nivo_obrazovanja where naziv is not null";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                nivoObrazovanjaList.add(getNivoObrazovanjaFromResultSet(rs));
            }
            return nivoObrazovanjaList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ObservableList<Termin> getTerminiByPsihoterapeut(int psihoterapeutId) {
        ObservableList<Termin> termini = FXCollections.observableArrayList();

        String query = "select * from termin where psihoterapeut_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,psihoterapeutId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                termini.add(getTerminiFromResultset(rs));
            }
            return termini;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Klijent> getKlijentiByPsihoterapeutId(Integer id){
        ObservableList<Klijent> klijenti = FXCollections.observableArrayList();
      //  String query = "select * from klijent";
        String query = "select * from klijent where ?=psihoterapeut_id";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                klijenti.add(getKlijentiFromResultSet(rs));
            }
            return klijenti;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ObservableList<TipPsihoterapeuta> getTipoviPsihoterapeuta() {
        ObservableList<TipPsihoterapeuta> tipovi = FXCollections.observableArrayList();
        String query = "select * from tip_psihoterapeuta where naziv is not null";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                tipovi.add(getTipoviFromResultSet(rs));
            }
            return tipovi;
        } catch (SQLException e) {
            throw new RuntimeException("Greška pri dohvaćanju tipova psihoterapeuta", e);
        }
    }

    private static Psihoterapeut getPsihoterapeutFromResultSet(ResultSet rs){
       try {
           Integer psihoterapeut_id = rs.getInt("psihoterapeut_id");
           String ime = rs.getString("ime");
           String prezime = rs.getString("prezime");
           Long JMBG = rs.getLong("JMBG");
           Date datum_rodjenja = rs.getDate("datum_rodjenja");
           String telefon = rs.getString("telefon");
           String email = rs.getString("email" );
           Integer adresa_id = rs.getInt("adresa_id");
           Integer tip_psihoterapeuta_id = rs.getInt("tip_psihoterapeuta_id");
           Integer nivo_obrazovanja_id = rs.getInt("nivo_obrazovanja_id");
           Integer supervizor_id = rs.getInt("supervizor_id");
           return new Psihoterapeut(psihoterapeut_id,ime,prezime,JMBG,datum_rodjenja,telefon,email,adresa_id,tip_psihoterapeuta_id,nivo_obrazovanja_id,supervizor_id);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
   public static Psihoterapeut getPsihoterapeutById(int id){
      String query = "select * from psihoterapeut where ?=psihoterapeut_id";
      try {
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setInt(1,id);
         ResultSet rs = statement.executeQuery();
         if(rs.next()){
            return getPsihoterapeutFromResultSet(rs);
         }
         return null;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }

   private static Adresa getAdresaFromResultSet(ResultSet rs){

       try {
           return new Adresa(rs.getInt("adresa_id"),rs.getString("opstina"),rs.getString("ulica"),rs.getString("broj"));
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

   public static Adresa getAdresaById(int id){
       String query = "select * from adresa where ?=adresa_id";
       try {
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,id);
           ResultSet rs = statement.executeQuery();
           if(rs.next()){
               return getAdresaFromResultSet(rs);
           }
           return null;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

   private static TipPsihoterapeuta getTipPsihoterapeutaFromResultSet(ResultSet rs){
       try {
           return new TipPsihoterapeuta(rs.getInt("tip_psihoterapeuta_id"),rs.getString("naziv"));
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

   public static TipPsihoterapeuta getTipPsihoterapeutaById(int id){
        String query = "select * from tip_psihoterapeuta where ?=tip_psihoterapeuta_id";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return getTipPsihoterapeutaFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   }

   public static ObservableList<Psihoterapeut> getPsihoterapeutiBezNaloga(){
       ObservableList<Psihoterapeut> psihoterapeuti = FXCollections.observableArrayList();
       String query = "select * from psihoterapeut as p left join nalog as n on p.psihoterapeut_id = n.psihoterapeut_id where n.psihoterapeut_id is null";
       try {
           PreparedStatement statement = connection.prepareStatement(query);
           ResultSet rs = statement.executeQuery();
           while(rs.next()){
               psihoterapeuti.add(getPsihoterapeutFromResultSet(rs));
           }
           return psihoterapeuti;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
   public static ObservableList<Psihoterapeut> getSertifikovaniPsihoterapeuti(){
       ObservableList<Psihoterapeut> psihoterapeuti = FXCollections.observableArrayList();
       String query = "select distinct p.* from psihoterapeut as p join sertifikat as s on p.psihoterapeut_id = s.psihoterapeut_id";
       try {
           Statement statement = connection.createStatement();
           ResultSet rs = statement.executeQuery(query);
           while(rs.next()){
               psihoterapeuti.add(getPsihoterapeutFromResultSet(rs));
           }
           return psihoterapeuti;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

    private static Termin getTerminiFromResultset(ResultSet rs){
        try {
           Date datum = rs.getDate("datum");
           Time vreme = rs.getTime("vreme");
           Integer psihoterapeut_id = rs.getInt("psihoterapeut_id");
           Integer seansa_id = rs.getInt("seansa_id");
            return new Termin(psihoterapeut_id,seansa_id,datum,vreme);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    private static Klijent getKlijentiFromResultSet(ResultSet rs){
        try {
            Integer klijent_id = rs.getInt("klijent_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Date datum_rodjenja = Date.valueOf(rs.getDate("datum_rodjenja").toLocalDate());
            String pol = rs.getString("pol");
            String email = rs.getString("email");
            String telefon = rs.getString("telefon");
            Integer prva_terapija = rs.getInt("prva_terapija");
            String opis_problema = rs.getString("opis_problema");
            String psihoterapeut_id = rs.getString("psihoterapeut_id");

            return new Klijent(klijent_id,ime,prezime,datum_rodjenja,pol,email,telefon
            ,opis_problema,prva_terapija);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    private static NivoObrazovanja getNivoObrazovanjaFromResultSet(ResultSet rs){
        try {
            Integer nivo_obrazovanja_id = rs.getInt("nivo_obrazovanja_id");
            String naziv = rs.getString("naziv");
            return new NivoObrazovanja(nivo_obrazovanja_id,naziv);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public static NivoObrazovanja getNivoObrazovanjaById(int id){
       String query = "select * from nivo_obrazovanja where ?=nivo_obrazovanja_id";
       try {
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,id);
           ResultSet rs = statement.executeQuery();
           if(rs.next()){
               return getNivoObrazovanjaFromResultSet(rs);
           }
           return null;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
    private static TipPsihoterapeuta getTipoviFromResultSet(ResultSet rs) {
        try {
            String naziv = rs.getString("naziv");
            Integer tip_id = rs.getInt("tip_psihoterapeuta_id");
            return new TipPsihoterapeuta(tip_id, naziv);
        } catch (SQLException e) {
            throw new RuntimeException("Greška pri čitanju iz ResultSet-a", e);
        }
    }

    public static Integer dodajAdresu(Adresa adresa){
       String query = "select * from adresa where opstina=? and ulica=? and broj=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,adresa.getOpsitna());
            statement.setString(2,adresa.getUlica());
            statement.setString(3,adresa.getBroj());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                return rs.getInt("adresa_id");

            query = "insert into adresa (opstina,ulica,broj) values (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1,adresa.getOpsitna());
            statement.setString(2,adresa.getUlica());
            statement.setString(3,adresa.getBroj());
            statement.executeUpdate();

            query = "select * from adresa where opstina=? and ulica=? and broj=?";
            statement = connection.prepareStatement(query);
            statement.setString(1,adresa.getOpsitna());
            statement.setString(2,adresa.getUlica());
            statement.setString(3,adresa.getBroj());
            rs = statement.executeQuery();
            rs.next();
            return rs.getInt("adresa_id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Integer dodajPsihoterapeuta(Psihoterapeut psihoterapeut){
       if(psihoterapeut.getSupervizor_id() == null){

           String query = "insert into psihoterapeut (ime,prezime,JMBG,datum_rodjenja,telefon,email,adresa_id,tip_psihoterapeuta_id,nivo_obrazovanja_id) values (?,?,?,?,?,?,?,?,?)";
           try {
               PreparedStatement statement = connection.prepareStatement(query);
               statement.setString(1,psihoterapeut.getIme());
               statement.setString(2,psihoterapeut.getPrezime());
               statement.setLong(3, psihoterapeut.getJMBG());
               statement.setDate(4, psihoterapeut.getDatum_rodjenja());
               statement.setString(5, psihoterapeut.getTelefon());
               statement.setString(6, psihoterapeut.getEmail());
               statement.setInt(7, psihoterapeut.getAdresa_id());
               statement.setInt(8, psihoterapeut.getTip_psihoterapeuta_id());
               statement.setInt(9, psihoterapeut.getNivo_obrazovanja_id());
               statement.execute();

               query = "select * from psihoterapeut where ime=? and prezime=? and JMBG=? and datum_rodjenja=? and telefon=? and email=? and adresa_id=? and tip_psihoterapeuta_id=? and nivo_obrazovanja_id=?";
               statement = connection.prepareStatement(query);
               statement.setString(1,psihoterapeut.getIme());
               statement.setString(2,psihoterapeut.getPrezime());
               statement.setLong(3, psihoterapeut.getJMBG());
               statement.setDate(4, psihoterapeut.getDatum_rodjenja());
               statement.setString(5, psihoterapeut.getTelefon());
               statement.setString(6, psihoterapeut.getEmail());
               statement.setInt(7, psihoterapeut.getAdresa_id());
               statement.setInt(8, psihoterapeut.getTip_psihoterapeuta_id());
               statement.setInt(9, psihoterapeut.getNivo_obrazovanja_id());
               ResultSet rs = statement.executeQuery();
               if(rs.next())
                   return rs.getInt("psihoterapeut_id");
               else
                   return null;

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
       String query = "insert into psihoterapeut (ime,prezime,JMBG,datum_rodjenja,telefon,email,adresa_id,tip_psihoterapeuta_id,nivo_obrazovanja_id,supervizor_id) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,psihoterapeut.getIme());
            statement.setString(2,psihoterapeut.getPrezime());
            statement.setLong(3, psihoterapeut.getJMBG());
            statement.setDate(4, psihoterapeut.getDatum_rodjenja());
            statement.setString(5, psihoterapeut.getTelefon());
            statement.setString(6, psihoterapeut.getEmail());
            statement.setInt(7, psihoterapeut.getAdresa_id());
            statement.setInt(8, psihoterapeut.getTip_psihoterapeuta_id());
            statement.setInt(9, psihoterapeut.getNivo_obrazovanja_id());
            statement.setInt(10, psihoterapeut.getSupervizor_id());
            statement.execute();

            query = "select * from psihoterapeut where ime=? and prezime=? and JMBG=? and datum_rodjenja=? and telefon=? and email=? and adresa_id=? and tip_psihoterapeuta_id=? and nivo_obrazovanja_id=? and supervizor_id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1,psihoterapeut.getIme());
            statement.setString(2,psihoterapeut.getPrezime());
            statement.setLong(3, psihoterapeut.getJMBG());
            statement.setDate(4, psihoterapeut.getDatum_rodjenja());
            statement.setString(5, psihoterapeut.getTelefon());
            statement.setString(6, psihoterapeut.getEmail());
            statement.setInt(7, psihoterapeut.getAdresa_id());
            statement.setInt(8, psihoterapeut.getTip_psihoterapeuta_id());
            statement.setInt(9, psihoterapeut.getNivo_obrazovanja_id());
            statement.setInt(10, psihoterapeut.getSupervizor_id());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                return rs.getInt("psihoterapeut_id");
            else
                return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Integer dodajNalog(Nalog nalog){
        String query = "insert into nalog (username, password, psihoterapeut_id) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,nalog.getUsername());
            statement.setString(2,nalog.getPassword());
            statement.setInt(3, nalog.getPsihoterapeut_id());
            statement.execute();
            query = "select * from nalog where username=? and password=? and psihoterapeut_id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1,nalog.getUsername());
            statement.setString(2,nalog.getPassword());
            statement.setInt(3,nalog.getPsihoterapeut_id());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                return rs.getInt("nalog_id");
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Integer dodajTermin(Termin termin) {
        String query = "INSERT INTO termin (datum, vreme, psihoterapeut_id, seansa_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, termin.getDatum());
            statement.setTime(2, termin.getVreme());
            statement.setInt(3, termin.getPsihoterapeutID());
            statement.setInt(4, termin.getSeansaID());
            statement.executeUpdate();

            // Ovde dobijamo auto-generisani ID
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static Integer dodajKlijenta(Klijent klijent){
        String query = "insert into klijent (ime,prezime,datum_rodjenja,pol,email,telefon,prva_terapija,opis_problema,psihoterapeut_id) values (?, ?, ?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,klijent.getIme());
            statement.setString(2,klijent.getPrezime());
            statement.setDate(3,klijent.getDatum());
            statement.setString(4,klijent.getPol());
            statement.setString(5,klijent.getEmail());
            statement.setString(6,klijent.getTelefon());
            statement.setInt(7,klijent.getPrvaTerapija());
            statement.setString(8,klijent.getOpisProblema());
            statement.setInt(9,klijent.getPsihoterapeut_id());

            statement.execute();
            query = "select * from klijent where ime=? and prezime=? and datum_rodjenja=? and pol=? and email=? and telefon=? and prva_terapija=? and opis_problema=? and psihoterapeut_id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1,klijent.getIme());
            statement.setString(2,klijent.getPrezime());
            statement.setDate(3,klijent.getDatum());
            statement.setString(4,klijent.getPol());
            statement.setString(5,klijent.getEmail());
            statement.setString(6,klijent.getTelefon());
            statement.setInt(7,klijent.getPrvaTerapija());
            statement.setString(8,klijent.getOpisProblema());
            statement.setInt(9,klijent.getPsihoterapeut_id());
          ResultSet rs = statement.executeQuery();
            if(rs.next())
                return rs.getInt("klijent_id");
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return null;
    }
    private static Fakultet getFakultetFromResultSet(ResultSet rs){
        try {
            return new Fakultet(rs.getString("naziv"),rs.getInt("univerzitet_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Fakultet getFakultetById(int id){
       String query = "select * from fakultet where ?=fakultet_id";
       try {
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,id);
           ResultSet rs = statement.executeQuery();
           if(rs.next()){
               return getFakultetFromResultSet(rs);
           }
           return null;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
    public static ObservableList<Fakultet> getFakultetiByPsihoterapeutId(int psihoterapeut_id){
        ObservableList<Fakultet> fakulteti = FXCollections.observableArrayList();
        String query = "SELECT f.* FROM fakultet f JOIN psihoterapeut_fakultet pf ON f.fakultet_id = pf.fakultet_id WHERE pf.psihoterapeut_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,psihoterapeut_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                fakulteti.add(getFakultetFromResultSet(rs));
            }
            return fakulteti;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Univerzitet getUniverzitetFromResultSet(ResultSet rs){
        try {
            return new Univerzitet(rs.getString("naziv"),rs.getInt("usmerenje_univerziteta_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Univerzitet getUniverzitetById(int id){
       String query = "select * from univerzitet where ?=univerzitet_id";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return getUniverzitetFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Metoda koja vraća naziv načina plačanja na osnovu ID-a
    private static String getNacinPlacanjaName(int nacinPlacanjaId) {
        String nacinPlacanjaName = "";
        String query = "SELECT naziv FROM nacin_placanja WHERE nacin_placanja_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nacinPlacanjaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nacinPlacanjaName = rs.getString("naziv");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nacinPlacanjaName;
    }
    private static Valuta getValutaFromResultSet(ResultSet rs) {
       try{
           Integer valutaId = rs.getInt("valuta_id");
           String naziv = rs.getString("naziv");
           String skraceno = rs.getString("skraceno");
           Valuta valuta = new Valuta(valutaId,naziv, skraceno);

               return valuta;

       }catch (SQLException e){
           throw new RuntimeException(e);
       }

    }

    private static Placanje getPlacanjeFromResultSet(ResultSet rs) {
        try {
            // Uzmi podatke iz ResultSet-a
            int klijentId = rs.getInt("klijent_id");
            int valutaId = rs.getInt("valuta_id");
            int nacinPlacanjaId = rs.getInt("nacin_placanja_id");
            int rata = rs.getInt("rata");
            Double iznos = rs.getDouble("iznos");  // Iznos sada kao double
            String svrha = rs.getString("svrha");
            int seansaId = rs.getInt("seansa_id");
            int placanjeId = rs.getInt("placanje_id");


            String nacinPlacanja = getNacinPlacanjaName(nacinPlacanjaId);

            // Kreiraj i vrati objekat Placanje sa svim podacima
            return new Placanje(placanjeId,svrha,rata,iznos,nacinPlacanjaId,valutaId,seansaId,klijentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Placanje> getPlacanjaByPsihoterapeutId(Integer psihoterapeutId) {
        ObservableList<Placanje> placanja = FXCollections.observableArrayList();
        String query = "select * from placanje p join seansa s on p.seansa_id = s.seansa_id where s.psihoterapeut_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, psihoterapeutId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                placanja.add(getPlacanjeFromResultSet(rs));
            }
            return placanja;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Valuta> getValute() {
        ObservableList<Valuta> valute = FXCollections.observableArrayList();
        String query = "SELECT naziv,skraceno FROM valuta"; // Preporučujem da vratiš i ID, ne samo naziv

        try (
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String naziv = rs.getString("naziv");
                String skracenica = rs.getString("skraceno");
                valute.add(new Valuta(naziv,skracenica)); // Pretpostavljam da tvoja klasa Valuta ima konstruktor Valuta(int, String)
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Ili loguj na drugi način u stvarnom projektu
        }

        return valute;
    }

    public static ObservableList<NacinPlacanja> getNaciniPlacanja() {

        ObservableList<NacinPlacanja> naciniPlacanja = FXCollections.observableArrayList();
        String query = "SELECT naziv FROM nacin_placanja"; // Preporučujem da vratiš i ID, ne samo naziv

        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String naziv = rs.getString("naziv");
                naciniPlacanja.add(new NacinPlacanja(naziv)); // Pretpostavljam da tvoja klasa Valuta ima konstruktor Valuta(int, String)
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Ili loguj na drugi način u stvarnom projektu
        }

        return naciniPlacanja;

    }

    public static Klijent getKlijentById(Integer klijentId)  {
        String query = "select * from klijent where klijent_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,klijentId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return getKlijentiFromResultSet(rs);
            }
            return  null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static Klijent getKlijentBySeansaId(int seansaId) {
        String query = "SELECT *" +
                "FROM klijent k\n" +
                "JOIN klijent_seansa ks ON ks.klijent_id = k.klijent_id\n" +
                "WHERE ks.seansa_id = ?;\n";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, seansaId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int klijentId = rs.getInt("klijent_id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                Date datumRodjenja = rs.getDate("datum_rodjenja");
                String pol = rs.getString("pol");
                String email = rs.getString("email");
                String telefon = rs.getString("telefon");
                String opisProblema = rs.getString("opis_problema");
                int prvaTerapija = rs.getInt("prva_terapija");
                int psihoterapeutId = rs.getInt("psihoterapeut_id");

                return new Klijent(ime, prezime, datumRodjenja, pol,
                        email, telefon, opisProblema, prvaTerapija, psihoterapeutId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static Valuta getValutaById(Integer valutaId) {
        String query = "select * from valuta where valuta_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,valutaId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return getValutaFromResultSet(rs);
            }
            return  null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Seansa getSeansaFromResultSet(ResultSet rs){
        try {
            Integer seansa_id = rs.getInt("seansa_id");
            Date dan = rs.getDate("dan");
            Time vreme = rs.getTime("vreme");
            Integer trajanje = rs.getInt("trajanje");
            Integer cena_id = rs.getInt("cena_id");
            String beleske = rs.getString("beleske");
            Integer psihoterapeut_id = rs.getInt("psihoterapeut_id");
            return new Seansa(seansa_id,dan,vreme,trajanje,cena_id,beleske ,psihoterapeut_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Cena getCenaFromResultSet(ResultSet rs){
        try {
            Integer cena_id = rs.getInt("cena_id");
            Integer valuta_id = rs.getInt("valuta_id");
            Double iznos = rs.getDouble("iznos");
            Date datum_izmene = rs.getDate("datum_izmene");
            return new Cena(cena_id,iznos,datum_izmene,valuta_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Cena getCenaById(Integer cenaId){
       String query = "select * from cena where cena_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,cenaId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return getCenaFromResultSet(rs);
            }
            return  null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Seansa> getSeanseByPsihoterapeutId(Integer psihoterapeutId) {
        ObservableList<Seansa> seanse = FXCollections.observableArrayList();
        String query = "select * from seansa where psihoterapeut_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,psihoterapeutId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                seanse.add(getSeansaFromResultSet(rs));
            }
            return seanse;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Klijent> getKlijentiBySeansaId(Integer seansa_id){
       ObservableList<Klijent> klijenti = FXCollections.observableArrayList();
       String query = "select k.* from klijent k join klijent_seansa ks on k.klijent_id = ks.klijent_id where ks.seansa_id = ?";
       try {
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setInt(1,seansa_id);
           ResultSet rs = statement.executeQuery();
           while(rs.next()){
               klijenti.add(getKlijentiFromResultSet(rs));
           }
           return klijenti;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    private static PsiholoskiTest getPsiholoskiTestFromResultSet(ResultSet rs){
        try {
            Integer psiholoski_test_id = rs.getInt("psiholoski_test_id");
            String naziv = rs.getString("naziv");
            String oblast = rs.getString("oblast");
            Integer cena_id = rs.getInt("cena_id");
            Double rezultat = rs.getDouble("rezultat");
            Integer seansa_id = rs.getInt("seansa_id");
            Integer klijent_id = rs.getInt("klijent_id");
            return new PsiholoskiTest(psiholoski_test_id,naziv,oblast,cena_id,rezultat,seansa_id,klijent_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<PsiholoskiTest> getPsiholoskiTestByPsihoterapeutId(int psihoterapeut_id){
       ObservableList<PsiholoskiTest> psiholoskiTestovi = FXCollections.observableArrayList();
       String query = "select * from psiholoski_test p join seansa s on p.seansa_id = s.seansa_id where s.psihoterapeut_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,psihoterapeut_id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                psiholoskiTestovi.add(getPsiholoskiTestFromResultSet(rs));
            }
            return psiholoskiTestovi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Sertifikat getSertifikatFromResultSet(ResultSet rs){
       try {
            Integer sertifikat_id = rs.getInt("sertifikat_id");
            Date datum = rs.getDate("datum");
            Integer psihoterapeut_id = rs.getInt("psihoterapeut_id");
            Integer oblast_psihoterapije_id = rs.getInt("oblast_psihoterapije_id");
            return new Sertifikat(sertifikat_id,datum,psihoterapeut_id,oblast_psihoterapije_id);
        } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public static ObservableList<Sertifikat> getSertifikatiByPsihoterapeutId(int psihoterapeut_id){
        ObservableList<Sertifikat> sertifikati = FXCollections.observableArrayList();
        String query = "select * from sertifikat where psihoterapeut_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,psihoterapeut_id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                sertifikati.add(getSertifikatFromResultSet(rs));
            }
            return sertifikati;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static OblastPsihoterapije getOblastPsihoterapijeFromResultSet(ResultSet rs){
        try {
            Integer oblast_psihoterapije_id = rs.getInt("oblast_psihoterapije_id");
            String naziv = rs.getString("naziv");
            return new OblastPsihoterapije(oblast_psihoterapije_id,naziv);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static OblastPsihoterapije getOblastPsihoterapijeById(int id){
       String query = "select * from oblast_psihoterapije where oblast_psihoterapije_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return getOblastPsihoterapijeFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Seansa> getNeplaceneSeanseByKlijentId(Integer klijentID) {
    //todo: sta ako su rate?
        String query = "select s.* from seansa s join klijent_seansa ks on s.seansa_id = ks.seansa_id left join placanje p on s.seansa_id = p.seansa_id where ks.klijent_id=? and p.placanje_id is null";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,klijentID);
            ResultSet rs = statement.executeQuery();
            ObservableList<Seansa> seanse = FXCollections.observableArrayList();
            while(rs.next()){
                seanse.add(getSeansaFromResultSet(rs));
            }
            return seanse;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    /*
    placanje_id int AI PK
svrha varchar(50)
rata int
iznos double(6,3)
nacin_placanja_id int
valuta_id int
seansa_id int
klijent_id int
     */

    public static Integer dodajPlacanje(Placanje placanje) {
       String query = "insert into placanje (svrha,rata,iznos,nacin_placanja_id,valuta_id,seansa_id,klijent_id) values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,placanje.getSvrha());
            statement.setInt(2,placanje.getRata());
            statement.setDouble(3,placanje.getIznos());
            statement.setInt(4,placanje.getNacinPlacanjaId());
            statement.setInt(5, placanje.getValuta_id());
            statement.setInt(6, placanje.getSeansa_id());
            statement.setInt(7, placanje.getKlijentId());

            statement.executeUpdate();
            query = "select * from placanje where svrha=? and rata=? and iznos=? and nacin_placanja_id=? and valuta_id=? and seansa_id=? and klijent_id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1,placanje.getSvrha());
            statement.setInt(2,placanje.getRata());
            statement.setDouble(3,placanje.getIznos());
            statement.setInt(4,placanje.getNacinPlacanjaId());
            statement.setInt(5, placanje.getValuta_id());
            statement.setInt(6, placanje.getSeansa_id());
            statement.setInt(7, placanje.getKlijentId());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt("placanje_id");
            }
            return null;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




/*
    public static ObservableList<Termin> getTermini(Integer psihoterapeutId) {
        ObservableList<Termin> termini = FXCollections.observableArrayList();
        String query = """
        SELECT t.termin_id, t.datum, t.vreme, s.klijent_id
        FROM termin t
        LEFT JOIN seansa s ON t.termin_id = s.seansa_id
        WHERE t.psihoterapeut_id = ?
    """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, psihoterapeutId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer klijentId = rs.getInt("klijent_id");
               String ime = getKlijentIme(klijentId);  // Poziv metode za ime
               String prezime = getKlijentPrezime(klijentId);  // Poziv metode za prezime

                Termin termin = new Termin(
                        rs.getInt("termin_id"),
                        klijentId,
                        rs.getDate("datum"),
                        rs.getTime("vreme").toLocalTime().getHour() * 100 + rs.getTime("vreme").toLocalTime().getMinute(),
                        ime,
                        prezime
                );
                termini.add(termin);
            }
            return termini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


*/


}
