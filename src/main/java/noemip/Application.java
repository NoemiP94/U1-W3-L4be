package noemip;

import noemip.dao.EventoDAO;
import noemip.dao.LocationDAO;
import noemip.dao.PartecipazioneDAO;
import noemip.dao.PersonaDAO;
import noemip.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
    try {
        EventoDAO evd = new EventoDAO(em);

        /*long id = 7;
        Evento eventoDB = evd.getById(id);
        if(eventoDB != null){
            System.out.println(eventoDB);
        }else{
            System.out.println("L'evento " + id + " non è stato trovato!");
        }*/

    //---------------------------------------------------------------------------------------------
    System.out.println("-------------------------------------------------------------");

    PersonaDAO pDao = new PersonaDAO(em);
    PartecipazioneDAO parDAO = new PartecipazioneDAO(em);
    LocationDAO locDAO = new LocationDAO(em);

    Persona persona1 = new Persona("Michael", "Bublè", "michael75@gmail.com", LocalDate.of(1975, 9, 9), Sesso.M);
    //pDao.save(persona1);

    Location location1 = new Location("Madison Square Garden", "New York");
    Location location2 = new Location("Piazza Matteotti", "Cagliari");
    Location location3 = new Location("Sala feste", "Milano");
    Location location4 = new Location("Castello", "Torino");
    //locDAO.save(location1);
    //locDAO.save(location2);
    //locDAO.save(location3);
    //locDAO.save(location4);

    Location nyDB = locDAO.getById(26);
    Location caDB = locDAO.getById(27);
    Location miDB = locDAO.getById(28);
    Location toDB = locDAO.getById(29);

    Evento compleanno = new Evento("Compleanno", LocalDate.of(2023, 8, 1), "Compleanno a tema cinema", TipoEvento.PRIVATO, 25, miDB);
    Evento matrimonio = new Evento("Matrimonio", LocalDate.of(2023, 10, 15), "Matrimonio a tema fantasy", TipoEvento.PRIVATO, 180, toDB);
    Evento sagra = new Evento("Sagra", LocalDate.of(2023, 7, 23), "Sagra delle patatine fritte", TipoEvento.PUBBLICO, 1000, caDB);
    Evento concerto = new Evento("Concerto di Natale", LocalDate.of(2023, 12, 25), "Concerto di Natale con Michael Bublè", TipoEvento.PUBBLICO, 50000, nyDB);

    //evd.save(compleanno);
    //evd.save(matrimonio);
    //evd.save(sagra);
    //evd.save(concerto);


    Evento c1DB = evd.getById(45);

    //codice per aggiungere partecipazione
        /*if(c1DB != null){
            long idP = 25;
            Persona personaDB = pDao.getById(idP);
            if(personaDB != null){
                System.out.println(personaDB);
                 Partecipazione partec2 = new Partecipazione(personaDB, c1DB, TipoStato.CONFERMATA);
                System.out.println(partec2);
                parDAO.save(partec2);
        }else{
                System.out.println("La persona con id " + idP + " non è stata trovato!");
        }
        }else{
            System.out.println("L'evento non è stato trovato");
        }
*/
    //---------------------------------------------------------------------------------------------------
    Concerto c1 = new Concerto("concerto", LocalDate.of(2024, 05, 23), "concerto dei Coldplay", TipoEvento.PUBBLICO, 700000, miDB, Genere.ROCK, true);
    Concerto c2 = new Concerto("concerto Taylor Swift", LocalDate.of(2024, 06, 19), "concerto di Taylor Swift", TipoEvento.PUBBLICO, 50000, toDB, Genere.POP, true);

   //evd.save(c1);
   //evd.save(c2);


    Persona personaDB = pDao.getById(25);

    Partecipazione partec2 = new Partecipazione(personaDB, c1DB, TipoStato.CONFERMATA);
    //parDAO.save(partec2);


    //---------------------------------------------------------------------------------------------
    System.out.println("Concerti in streaming: ");
    System.out.println(evd.getConcertiInStreaming(true));

    //---------------------------------------------------------------------------------------------
    System.out.println("Concerti per genere: ");
    System.out.println(evd.getConcertiPerGenere(Genere.ROCK));

    //---------------------------------------------------------------------------------------------
    PartitaDiCalcio ItaliaFrancia = new PartitaDiCalcio("Italia-Francia", LocalDate.of(2024,3,14), "semifinale", TipoEvento.PUBBLICO, 80000, nyDB, "Italia", "Francia", "Italia", 3, 0);
    //evd.save(ItaliaFrancia);

    //---------------------------------------------------------------------------------------------
        System.out.println("partite in casa");
        System.out.println(evd.getPartiteVinteInCasa());

    }catch(Exception e){
        System.err.println(e);
    }finally{
        em.close();
        emf.close();
    }
    }
}
