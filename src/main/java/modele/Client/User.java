package modele.Client;

import modele.Visiteurs.Visitable;
import modele.Visiteurs.Visiteur;

public class User implements Visitable {
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String mdp;

    /**
     * Constructeur
     * @param nom nom de l'user
     * @param prenom prenom de l'user
     */
    User(String nom, String prenom){
        this.nom=nom;
        this.prenom=prenom;
    }

    /**
     * Pour récupérer le nom de l'user
     * @return String nom de l'user
     */
    public String getNom() {
        return nom;
    }
    /**
     * Pour récupérer le prenom de l'user
     * @return String prenom de l'user
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * Pour récupérer l'adresse de l'user
     * @return String adresse de l'user
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * Pour récupérer le mail de l'user
     * @return String mail de l'user
     */
    public String getEmail() {
        return email;
    }
    /**
     * Pour récupérer le mot de passe de l'user
     * @return String mot de passe de l'user
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Pour modifier le nom de l'user
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * Pour modifier le prenom de l'user
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    /**
     * Pour modifier l'adresse de l'user
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    /**
     * Pour modifier l'email de l'user
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Pour modifier le mot de passe de l'user
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    /**
     * Pour afficher les informations de l'user
     * @return String les informations de l'user
     */
    public String toString (){
        return "Nom : "+this.nom+"" +
                "\n Prenom : "+this.prenom+""+
            "\n Adresse : "+this.adresse+""+
            "\n Email : "+this.email;
    }

    /**
     * Pattern visiteur qui accepte l'user
     * @param visiteur ( pattern visiteur )
     */
    @Override
    public void accepter(Visiteur visiteur) {
        visiteur.visit(this);
    }
}
