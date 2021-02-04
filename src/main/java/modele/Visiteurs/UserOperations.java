package modele.Visiteurs;

import Databases.MysqlDb;
import modele.Client.User;
import modele.Visiteurs.Visiteur;


import java.sql.*;



public class UserOperations implements Visiteur {
    User user;
    Connection conn = MysqlDb.getConnection();


    @Override
    public void visit(User user) {
        this.user=user;

    }
    private Date getDate(){
        long millis=System.currentTimeMillis();
        Date date=new java.sql.Date(millis);
        return date;
    }

    public void ajouterClient() throws SQLException {
        String query = " insert into clients (nom,prenom,adresse,email)"
                + " values (?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, user.getNom().toLowerCase());
        preparedStmt.setString (2, user.getPrenom().toLowerCase());
        preparedStmt.setString   (3, user.getAdresse());
        preparedStmt.setString(4, user.getEmail().toLowerCase());
        preparedStmt.execute();
    }
    public void supprimerClient(int id ) throws SQLException {
        String query ="delete  from clients where id='"+id+"'";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
    public void ajouterIdentifiants() throws SQLException {
        String query = "insert into credentials (email,mdp)"+
                "values(?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, user.getEmail().toLowerCase());
        preparedStmt.setString (2, user.getMdp());
        preparedStmt.execute();
    }

    public void supprimerIdentifiants(String email) throws SQLException {
        String query ="delete  from credentials where email='"+email+"'";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
    public void supprimerAbonnement(int id) throws SQLException {
        String query ="delete  from abonnements where clientId='"+id+"'";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
    public boolean isAbonne(int id) throws SQLException {
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("select count(*) as abonnementcount from abonnements where clientId='"+id+"'");
     if(rs.next()){
         if (rs.getInt("abonnementcount")> 0){
             return true;
         }
         else{
             return false;
         }
     }else{
         throw new SQLException();
     }
    }

    public void modifierAbonnement(int id, String type) throws SQLException {

            String query = "UPDATE abonnements SET type = ?, date_debut = ? WHERE clientId ='"+id+"'";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,type);
            pstmt.setDate(2,getDate());
            pstmt.execute();
            pstmt.close();
            System.out.println("Abonnement ajouté avec succès");

    }

    public void ajouterAbonnement(int id,String type) throws SQLException {
        String query = "insert into abonnements (clientId,type,date_debut)"+
                "values(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,id);
        pstmt.setString(2,type);
        pstmt.setDate(3,getDate());
        pstmt.execute();
        pstmt.close();
        System.out.println("Abonnement ajouté avec succès");
    }

}
