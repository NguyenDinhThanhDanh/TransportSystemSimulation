package modele.Facades;

import Databases.MysqlDb;
import modele.*;
import modele.Client.User;
import modele.Client.UserBuilder;
import modele.Visiteurs.UserOperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;


public class
Facade {
    private User user;
    private Abonnement abonnement;
    private Boolean isconnected = false;
    private final String VALIDATION ="validation";
    public final String MENSUEL ="mensuel";
    public final String ANNUEL ="annuel";
    private final String TICKET = "Ticket";
    private final String ABONNEMENT ="Abonnement";
    public final int DIX = 10;
    public final int UN = 1;

    private FacadeMongo facadeMongo = new FacadeMongo();

    UserOperations userOperations = new UserOperations();

    public Facade(){}

    /**
     * Met à jour l'user
     * @param user user
     */
    private void setUser(User user){
        this.user = user;

    }

    /**
     * Pour récupérer l'user
     * @return User user
     */
    public User getUser(){
        return this.user;
    }


    /**
     * Récupère l'user à l'aide de son email
     * @param email email de l'user
     * @return User user
     * @throws SQLException erreur de type SQLException
     */
    private User getUserByEmail(String email) throws SQLException{
        Connection conn = MysqlDb.getConnection();
        Statement stmt = conn.createStatement();
        String Query = "SELECT  nom,prenom,adresse FROM clients where email = '"+email+"'";
        ResultSet rs = stmt.executeQuery(Query);
        rs.next();
        User user = new UserBuilder(rs.getString("nom"),rs.getString("prenom"))
                .adresse(rs.getString("adresse"))
                .email(email)
                .build();
        rs.close();
        return user;
    }

    /**
     * Récupère l'user grâce à son Id
     * @param id id de l'user
     * @return User user
     * @throws SQLException erreur de type SQLException
     */
    private User getUserById(int id) throws SQLException {
        Connection conn = MysqlDb.getConnection();
        Statement stmt = conn.createStatement();
        String Query = "SELECT  nom,prenom,adresse,email FROM clients where id = '"+id+"'";
        ResultSet rs = stmt.executeQuery(Query);
        if(rs.next()){
            User user = new UserBuilder(rs.getString("nom"),rs.getString("prenom"))
                    .adresse(rs.getString("adresse"))
                    .email(rs.getString("email"))
                    .build();
            return user;
        }
        else{
            throw new SQLException("user not found");
        }
    }

    /**
     * Récupère l'id de l'user grâce à son email
     * @param email email de l'user
     * @return int id de l'user
     * @throws SQLException erreur de type SQLException
     */
    private int getUserIdByEmail(String email) throws SQLException{
        Connection conn = MysqlDb.getConnection();
        Statement stmt = conn.createStatement();
        String Query = "SELECT  id FROM clients where email = '"+email+"'";
        ResultSet rs = stmt.executeQuery(Query);
        if(rs.next()){
            return rs.getInt("id");
        }
        else {
            throw new SQLException("no user has the specified email !");
        }

    }

    /**
     * Vérifie si l'user est connecté
     * @return boolean true ou false selon si l'user est connecté ou non
     */
    public boolean isConnected(){
        return isconnected;
    }

    /**
     * Enregistre l'user suite à une inscription
     * @param user user
     * @throws SQLException erreur de type SQLException
     */
    public void enregistrer(User user) throws SQLException {
        user.accepter(userOperations);
        userOperations.ajouterClient();
        System.out.println("Informations Client Ajoutées");
        userOperations.ajouterIdentifiants();
        System.out.println("Identifiants Créés");
        }
    //test for first trajet

    /**
     * Désinscrit l'user
     * @throws SQLException erreur de type SQLException
     */
    public void seDesinscrir() throws SQLException {
        this.user.accepter(userOperations);
        int id = getUserIdByEmail(this.user.getEmail());
        userOperations.supprimerIdentifiants(this.user.getEmail());
        userOperations.supprimerAbonnement(id);
        userOperations.supprimerClient(id);

        System.out.println("desisncription reussie");
        seDeconnecter();
    }

    /**
     * Connecte l'user
     * @param email email de l'user
     * @param mdp mot de passe de l'user
     * @throws SQLException erreur de type SQLException
     */
    public void seConnecter(String email, String mdp) throws SQLException {
        Connection conn = MysqlDb.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT count(*) as userCount FROM credentials where email = '"+email+"' and mdp = '"+mdp+"'");
         if(rs.next()){

             if(rs.getInt("userCount") > 0){
                 isconnected = true;
                 setUser(getUserByEmail(email));
                 System.out.println("Vous êtes connecté avec succès");

             }
             else {
                 System.out.println("Email ou Mot de passe érroné ");

             }
         }

    }

    /**
     * Déconnecte l'user
     */
    public void seDeconnecter(){
        if(isconnected){
            isconnected = false;
            System.out.println("deconnexion reussie");
        }
        else{
            System.out.println("vous n'êtes pas connecté");
        }
    }

    /**
     * Met à jour l'abonnement de l'user
     * @param type type d'abonnement de l'user
     * @throws SQLException erreur de type SQLException
     */
    public void setAbonnement(String type) throws SQLException {
        this.user.accepter(userOperations);
        int id = getUserIdByEmail(user.getEmail());
        if(userOperations.isAbonne(id)){ // si il est abonnée on va mettre à jour son abonnement
            userOperations.modifierAbonnement(id,type);
        }
        else{
            userOperations.ajouterAbonnement(id,type);
        }
        System.out.println("Vous avez souscrit a un abonnement "+type+" avec succes \n"+
                "Date debut :"+ LocalDateTime.now());
    }

    /**
     * Récupère l'id de l'abonnement de l'user
     * @param id id de l'abonnement
     * @return Abonnement abonnement de l'user
     * @throws SQLException erreur de type SQLException
     */
    private Abonnement getAbonnementbyUserId(int id) throws SQLException{
        Connection conn = MysqlDb.getConnection();
        Statement stmt = conn.createStatement();
        String Query = "SELECT  id,clientId,type,date_debut FROM abonnements where id = '"+id+"'";
        ResultSet rs = stmt.executeQuery(Query);
        if(rs.next()){
            Abonnement abonnement= new Abonnement();
            abonnement.setId(rs.getInt("id"));
            abonnement.setClientId(id);
            abonnement.setType(rs.getString("type"));
            abonnement.setDate_debut(rs.getDate("date_debut").toLocalDate());
            return abonnement;
        }
        else{
            throw new SQLException("No abonnement found");
        }
    }

    /**
     * Récupère l'abonnement de l'user
     * @return Abonnement abonnement de l'user
     * @throws SQLException erreur de type SQLException
     */
    public Abonnement getAbonnement() throws SQLException {
        int id = getUserIdByEmail(this.user.getEmail());
        return getAbonnementbyUserId(id);
    }


    /**
     * Met à jour le ticket de l'user
     * @param nbrValidation nombre de validation du ticket de l'user
     * @throws SQLException erreur de type SQLException
     */
    public void setTicket(int nbrValidation) throws SQLException {
        int id = getUserIdByEmail(this.user.getEmail());
        facadeMongo.ajouterTicket(id, nbrValidation);
    }

    /**
     * Récupère le ticker de l'user
     * @return Ticket ticker de l'user
     * @throws SQLException erreur de type SQLException
     * @throws NoTicketException Erreur de type NoTicketException
     */
    public Ticket getTicket( ) throws SQLException, NoTicketException {
        int id = getUserIdByEmail(this.user.getEmail());
        return facadeMongo.getTicketByClientId(id);
    }

    /**
     * Récupère le ticket de l'user
     * @param ticketId id du ticket
     * @return Ticket ticker de l'user
     */
    public Ticket getTicket(int ticketId){
        return facadeMongo.getTicketById(ticketId);
    }

    /**
     * Valide le ticket de l'user
     * @param titre titre du ticket
     * @param origine origine du ticket
     * @throws SQLException erreur de type SQLExcpetion
     */
    public void valider(Ticket titre,String origine) throws SQLException {
        int id = getUserIdByEmail(this.user.getEmail());
        if(titre.getTimeValidation() == null){
            facadeMongo.setHeureValidationTicket(titre);    //update ticket add heure de validation
            facadeMongo.ajouterValidation(id,titre.getId().toString(),TICKET, origine);
        }
        else{
            if(facadeMongo.isValide(titre)){
                facadeMongo.ajouterValidation(id,titre.getId().toString(),TICKET, origine);
            }else{
                System.out.println("Titre Non Valide");
            }
        }
    }

    /**
     * Valide le titre de l'abonnement
     * @param titre titre de l'abonnement
     * @param origine origine de l'abonnement
     * @throws SQLException erreur de type SQLException
     */
    public void valider(Abonnement titre,String origine) throws SQLException {
        int id = getUserIdByEmail(this.user.getEmail());

        if(facadeMongo.isValide(titre)){
            facadeMongo.ajouterValidation(id,String.valueOf(titre.getId()),ABONNEMENT,origine);
        }
        else{
            System.out.println("Titre non valide");
        }
    }
}
