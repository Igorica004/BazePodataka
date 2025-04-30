package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.time.LocalDate;
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
    public static ObservableList<Klijent> getKlijentByPsihoterapeutId(Integer id){
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

    public static Psihoterapeut getPsihoterapeutFromResultSet(ResultSet rs){
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
    public static Klijent getKlijentiFromResultSet(ResultSet rs){
        try {
            Integer klijent_id = rs.getInt("klijent_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            LocalDate datum_rodjenja = rs.getDate("datum_rodjenja").toLocalDate();
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
    public static NivoObrazovanja getNivoObrazovanjaFromResultSet(ResultSet rs){
        try {
            Integer nivo_obrazovanja_id = rs.getInt("nivo_obrazovanja_id");
            String naziv = rs.getString("naziv");
            return new NivoObrazovanja(nivo_obrazovanja_id,naziv);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public static TipPsihoterapeuta getTipoviFromResultSet(ResultSet rs) {
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
            statement.setInt(3,adresa.getAdresa_id());
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                return rs.getInt("adresa_id");

            query = "insert into adresa (opstina,ulica,broj) values (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1,adresa.getOpsitna());
            statement.setString(2,adresa.getUlica());
            statement.setInt(3,adresa.getAdresa_id());
            statement.executeUpdate();

            query = "select * from adresa where opstina=? and ulica=? and broj=?";
            statement = connection.prepareStatement(query);
            statement.setString(1,adresa.getOpsitna());
            statement.setString(2,adresa.getUlica());
            statement.setInt(3,adresa.getAdresa_id());
            rs = statement.executeQuery();
            rs.next();
            return rs.getInt("adresa_id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void dodajPsihoterapeuta(Psihoterapeut psihoterapeut){
       String query = "insert into psihoterapeut (ime,prezime,JMBG,datum_rodjenja,telefon,email,adresa_id,tip_psihoterapeuta_id,nivo_obrazovanja_id,supervizor_id) values (?,?,?,?,?,?,?,?,?,?)";
    }
    public static void dodajNalog(Nalog nalog){

    }
}
