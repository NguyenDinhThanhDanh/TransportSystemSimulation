package modele.Client;

public class UserBuilder {
    private User user;

    /**
     * Constructeur
     * @param nom nom de l'user
     * @param prenom prenom de l'user
     */
    public UserBuilder(String nom, String prenom){
    this.user = new User(nom,prenom);
    }

    /**
     * Ajoute l'adresse de l'user
     * @param adresse adresse de l'user
     * @return UserBuilder les informations de l'user mises à jour
     */
    public UserBuilder adresse(String adresse){
        this.user.setAdresse(adresse);
        return this;
    }
    /**
     * Ajoute email de l'user
     * @param email email de l'user
     * @return UserBuilder les informations de l'user mises à jour
     */
    public UserBuilder email(String email){
        this.user.setEmail(email);
        return this;
    }
    /**
     * Ajoute mot de l'user
     * @param mdp mot de passe de l'user
     * @return UserBuilder les informations de l'user mises à jour
     */
    public UserBuilder mdp(String mdp){
        this.user.setMdp(mdp);
        return this;
    }

    /**
     * Construit l'user grâce au pattern builder
     * @return User créé à l'aide du pattern builder
     */
    public User build(){
        return this.user;
    }
}
