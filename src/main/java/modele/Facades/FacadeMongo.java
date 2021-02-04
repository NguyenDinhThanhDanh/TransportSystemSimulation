package modele.Facades;

import Databases.MongoDb;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modele.*;
import modele.Client.User;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

public class FacadeMongo {
    private User user;
    private MongoDatabase db =  MongoDb.getMongoDatabase();
    private final String VALIDATION ="validation";
    private final String MENSUEL ="mensuel";
    private final String ANNUEL ="annuel";
    public FacadeMongo(){}

    /**
     * Ajoute une validation de ticket
     * @param id id du ticket
     * @param transportId id de transport du ticket
     * @param type type du ticket
     * @param origine origine du ticket
     */
    public void ajouterValidation(int id,String transportId,String type, String origine){
        MongoCollection<Validation> validations =db.getCollection(VALIDATION,Validation.class);
        Validation newValidation= new Validation()
                .setClientId(id)
                .setTitreTransportId(transportId)
                .setType(type)
                .setOrigine(origine)
                .setTemps(LocalDateTime.now());
        validations.insertOne(newValidation);
        System.out.println("Operation Success with Validation!");
    }

    /**
     * Ajoute un ticket
     * @param id id tu ticket
     * @param nbrValidation nombre de validation du ticket
     * @return Ticket ticket
     * @throws SQLException erreur de type SQLException
     */
    public Ticket ajouterTicket(int id, int nbrValidation) throws SQLException {
        MongoCollection<Ticket> tickets = db.getCollection("ticket", Ticket.class);
        Ticket newTicket = new Ticket()
                .setClientId(id)
                .setNbrValidation(nbrValidation);
        tickets.insertOne(newTicket);
        System.out.println("Operation Success with Ticket!");
        return newTicket;
    }

    /**
     * Récupère les tickets grâce à leur Id
     * @param id id tu ticket
     * @return Ticket ticket
     */
    public Ticket getTicketById(int id){
        MongoCollection<Ticket> tickets = db.getCollection("ticket", Ticket.class);
        Ticket ticket = tickets.find(Filters.eq("_id",id)).first();
        return ticket;
    }

    /**
     * Récupère le ticket grâce à l'Id du client
     * @param clientId id du client
     * @return Ticket ticket du client
     * @throws NoTicketException erreur de type NoTicketException
     */
    public Ticket getTicketByClientId(int clientId) throws NoTicketException {
        Ticket newTicket = null;
        MongoCollection<Ticket> tickets = db.getCollection("ticket", Ticket.class);
        Collection<Ticket> ticketCollection = new ArrayList<>();
        tickets.find(Filters.eq("clientId",clientId)).into(ticketCollection);
        for (Ticket ticket : ticketCollection){
            if(isValide(ticket)){
                newTicket = ticket;
                break;
            }
        }
        if(newTicket == null){
            throw new NoTicketException();
        }else{
            return newTicket;
        }
    }

    /**
     * Met à jour l'heure de validation du ticket
     * @param titre titre du ticket
     */
    public void setHeureValidationTicket(Ticket titre){
        //enregistrer l'heure de validation du ticket
        MongoCollection<Ticket> tickets = db.getCollection("ticket", Ticket.class);
        titre.setTimeValidation(LocalDateTime.now());
        tickets.replaceOne(Filters.eq("_id",titre.getId()),titre);
    }

    /**
     * Vérifie si le ticket est valide
     * @param titre titre du ticket
     * @return boolean true ou false selon la validité du ticket
     */
    public boolean isValide(Ticket titre){
        // voir si le ticket est valide ou pas
        // il est valide si le nombre de validationRestant est > 0
        // il est valide pour une heure
        if(titre.getTimeValidation() == null){
            return true;
        }
        else{
            if(titre.getNbrValidation() == 0){
                return false;
            }
            else {
                LocalDateTime debut = titre.getTimeValidation();
                LocalDateTime fin = LocalDateTime.now();
                Duration duree = Duration.between(debut, fin);
                if(duree.toMinutes() < 60){
                    return true;
                }else{
                    MongoCollection<Ticket> tickets = db.getCollection("ticket", Ticket.class);
                    //decrementer valeur nbValidation et update
                    titre.setNbrValidation(titre.getNbrValidation()-1);
                    tickets.replaceOne(Filters.eq("_id",titre.getId()),titre);
                    return true;
                }
            }
        }
    }

    /**
     * Vérifie si l'abonnement est valide
     * @param titre titre de l'abonnement
     * @return boolean true ou false selon la validité de l'abonnement
     */
    public boolean isValide(Abonnement titre){
        // voir si l'abonnement est valide
        //l'abonnement est soit mensuel ou annuel

        LocalDate debut = titre.getDate_debut();
        LocalDate fin = LocalDate.now();
        Period periode = Period.between(debut,fin);

       if(titre.getType().equals(MENSUEL)){
           return periode.getDays() < 30;
        }
        else{
           return periode.getMonths() < 12;
        }

    }

}
