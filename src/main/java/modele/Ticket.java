package modele;


import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class Ticket implements TitreTransport {
    private ObjectId id;

    private int clientId;
    private LocalDateTime timeValidation;
    private int nbrValidation;

    /**
     * Récupère l'id du ticket
     * @return ObjectId id du ticket
     */
    public ObjectId getId(){return id;}
    /**
     * Met à jour l'id du ticket
     */
    public Ticket setId(ObjectId id){
        this.id = id;
        return this;
    }

    /**
     * Met à jour l'id du client lié au ticket
     * @param clientId id du client lié au ticket
     * @return Ticket ticket du client
     */
    public Ticket setClientId(int clientId){
        this.clientId=clientId;
        return this;
    }

    /**
     * Met à jour le nombre de validation du ticket
     * @param nbrValidation Nombre de validation du ticket
     * @return Ticket ticket
     */
    public Ticket setNbrValidation(int nbrValidation){
        this.nbrValidation=nbrValidation;
        return this;
    }

    /**
     * Récupère l'id du client lié au ticket
     * @return int id du client lié au ticket
     */
    public int getClientId(){return clientId;}

    /**
     * Récupère le nombre de validation du ticket
     * @return int nombre de validation du ticket
     */
    public int getNbrValidation(){return nbrValidation;}

    /**
     * Récupère l'heure de validation du ticket
     * @return LocalDateTime heure de validation du ticket
     */
    public LocalDateTime getTimeValidation(){return timeValidation;}

    /**
     * Met à jour l'heure de validation du ticket
     * @param timeValidation heure de validation du ticket
     * @return Ticket ticket
     */
    public Ticket setTimeValidation(LocalDateTime timeValidation){
        this.timeValidation=timeValidation;
        return this;
    }

    /**
     * Affiche les informations liées au ticket
     * @return String informations liées au ticket
     */
    @Override
    public String toString() {
        return "Ticket{" + "id=" + id +
                ", user_id=" + clientId +
                ", temps=" + timeValidation +
                ",nbrValidation=" + nbrValidation +
                '}';
    }


}


