package programme;



import modele.Client.User;
import modele.Facades.Facade;
import modele.Facades.NoTicketException;
import modele.Facades.Station;

import java.sql.SQLException;

import static com.mongodb.client.model.Filters.eq;

public class programme {
    public static void main(String[] args) throws SQLException, NoTicketException {

//Enregistrer un client
        /*
         Facade facade = new Facade();

        User user = new UserBuilder("Henni","Mohamed")
                .adresse("15 rue bouzid mohamed")
                .email("hennimohamed@hotmail.com")
                .mdp("123")
                .build();
        facade.enregistrer(user);
         */

// TEST CONNECTION
        /*
       Facade facade = new Facade();
       facade.seConnecter("hennitayeb@gmail.com","motdepasse");
        if(facade.isConnected()){
            User user = facade.getUser();
            System.out.println(user.toString());
        }
        */

// TEST DESINSCRIPTION

        /*
        Facade facade = new Facade();
        facade.seConnecter("henniyassine@hotmail.com","123");
        if(facade.isConnected()){
            facade.seDesinscrir();
        }
    */

// TEST AJOUT ABONNEMENT
        /*
       Facade facade = new Facade();
       facade.seConnecter("hennitayeb@gmail.com","motdepasse");
        if(facade.isConnected()){
            User user = facade.getUser();
            System.out.println(user.toString());
            facade.setAbonnement(facade.MENSUEL);
        }
        */

// TEST VALIDATION ABONNEMENT
      /*
        Facade facade = new Facade();
        facade.seConnecter("hennitayeb@gmail.com","motdepasse");
        if(facade.isConnected()){
            User user = facade.getUser();
            System.out.println(user.toString());
            facade.valider(facade.getAbonnement(), Station.DE_GAULE.toString());
        }
*/

// TEST AJOUT TICKET ET VALIDATION
        Facade facade = new Facade();
        facade.seConnecter("hennitayeb@gmail.com","motdepasse");
        if(facade.isConnected()){
            User user = facade.getUser();
            System.out.println(user.toString());
            facade.setTicket(10);
            facade.valider(facade.getTicket(),Station.DE_GAULE.toString());
        }


    }
}

