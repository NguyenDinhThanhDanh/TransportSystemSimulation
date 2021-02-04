package modele;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class Validation {
    private ObjectId id;
    private int clientId;
    private String titreTransportId;
    private String origine;
    private String type;
    private LocalDateTime temps;

    /**
     * Met à jour l'id de la validation
     * @param id id de la validation
     * @return Validation validation
     */
    public Validation setId(ObjectId id){
        this.id = id;
        return this;
    }

    /**
     * Met à jour l'id du titre de transport
     * @param titreTransportId id du titre de transport
     * @return Validation validation
     */
    public Validation setTitreTransportId(String titreTransportId){
        this.titreTransportId = titreTransportId;
        return this;
    }

    /**
     * Met à jour l'heure de la validation
     * @param temps heure de la validation
     * @return Validation validation
     */
    public Validation setTemps(LocalDateTime temps){
        this.temps=temps;
        return this;
    }

    /**
     * Met à jour l'id du client lié à la validation
     * @param userId id du client lié à la validation
     * @return Validation validation
     */
    public Validation setClientId(int userId){
        this.clientId=userId;
        return this;
    }

    /**
     * Récupère l'id de la validation
     * @return ObjectId id de la validation
     */
    public ObjectId getId(){return id;}

    /**
     * Récupère l'id du titre de transport
     * @return String id du titre de transport
     */
    public String getTitreTransportId(){return titreTransportId;}

    /**
     * Récupère l'id du client lié à la validation
     * @return id du client lié à la validation
     */
    public int getClientId(){return clientId;}

    /**
     * Récupère l'origine de la validation
     * @return String origine de la validation du ticket
     */
    public String getOrigine(){return origine;}

    /**
     * Met à jour l'origine de la validation
     * @param origin origine de la validation
     * @return Validation validation
     */
    public Validation setOrigine(String origin){
        this.origine =origin;
        return this;
    }

    /**
     * Récupère l'heure de la validation
     * @return LocalDateTime heure de la validation
     */
    public LocalDateTime getTemps(){return temps;}

    /**
     * Récupère le type de la validation
     * @return String type de la validation
     */
    public String getType(){return type;}

    /**
     * Met à jour le type de la validation
     * @param type type de la validation
     * @return Validation validation
     */
    public Validation setType(String type){
        this.type=type;
        return this;
    }

    /**
     * Affiche les informations liées à la validation
     * @return String informations liées à la validation
     */
    @Override
    public String toString() {
        return "Validation{" + "id=" + id +
                ", user_id=" + clientId +
                ",typeTransport_id" + titreTransportId +
                ", origin=" + origine +
                ", temps=" + temps +
                ",type=" + type +
                '}';
    }

}
