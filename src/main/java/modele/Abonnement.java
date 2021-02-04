package modele;

import java.time.LocalDate;
import java.util.Date;

public class Abonnement implements TitreTransport{
    private int id;
    private int clientId;
    private String type;
    private LocalDate date_debut;

    /**
     * Met à jour l'id de l'abonnement
     * @param id id de l'abonnement
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Met à jour l'id du client
     * @param clientId id du client
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Met à jour le type de l'abonnement
     * @param type type de l'abonnement
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Met à jour la date du début de l'abonnement
     * @param date_debut date du début de l'abonnement
     */
    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    /**
     * Récupère l'id de l'abonnement
     * @return int id de l'abonnement
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère l'id du client
     * @return int id du client
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Récupère le type de l'abonnement
     * @return String type de l'abonnement
     */
    public String getType() {
        return type;
    }

    /**
     * Récupère la date de début de l'abonnement
     * @return LocalDate date du début de l'abonnement
     */
    public LocalDate getDate_debut() {
        return date_debut;
    }

    /**
     * Affiche les informations liées à l'abonnement
     * @return String informations liées à  l'abonnement
     */
    public String  toString (){
        return "Id : "+this.id+"" +
                "\n Id Client : "+this.clientId+""+
                "\n Type : "+this.type+""+
                "\n Date Debut : "+date_debut;
    }
}
