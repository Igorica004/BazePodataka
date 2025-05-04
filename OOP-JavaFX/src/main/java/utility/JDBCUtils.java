package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import view.Fakultet;

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
      String query = "select psihoterapeut_id from nalog where ?=username and ?=password";
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

   public static Adresa getAdresaFromResultSet(ResultSet rs){

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
    } public static Integer dodajKlijenta(Klijent klijent){
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
    public static Fakultet getFakultetFromResultSet(ResultSet rs){
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




}
