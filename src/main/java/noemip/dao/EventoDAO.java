package noemip.dao;

import noemip.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventoDAO {

    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    //metodo save
    public void save(Evento evento){
        EntityTransaction transaction = em.getTransaction(); //transazione
        transaction.begin(); //inizio transazione
        em.persist(evento); //aggiungo evento al Persistence Context
        transaction.commit(); //salvataggio in una nuova riga
        System.out.println("L'evento " + evento.getTitolo() + " aggiunto correttamente!");
    }

    //metodo getById
    public Evento getById(long id){
        return em.find(Evento.class, id);
    }

    //metodo delete
    public void delete(long id){
        Evento foundId  = this.getById(id);
        if(foundId != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundId);
            transaction.commit();
            System.out.println("L'evento " + foundId.getTitolo() + " eliminato correttamente!");
        }else {
            System.out.println("L'evento " + id + " non è stato trovato!");
        }
    }

    //getConcertiInStreaming
    public List<Concerto> getConcertiInStreaming(Boolean isStreaming){
        try{
            TypedQuery<Concerto> q = em.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming = :isStreaming", Concerto.class);
            q.setParameter("isStreaming", isStreaming);
            return q.getResultList();
        }catch(Exception e){
            System.err.println("Errore durante il recupero dei concerti" + e);
            throw e;
        }
    }

    //getConcertiPerGenere
    public List<Concerto> getConcertiPerGenere(Genere genere){
        try{
            TypedQuery<Concerto> q = em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere", Concerto.class);
            q.setParameter("genere", genere);
            return q.getResultList();
        }catch(Exception e){
            System.err.println("Errore durante il recupero dei concerti" + e);
            throw e;
        }
    }

    //getPartiteVinteInCasa
    public List<PartitaDiCalcio> getPartiteVinteInCasa(){
        return em.createNamedQuery("trovaVincitoriCasa", PartitaDiCalcio.class).getResultList();
    }

    //getPartiteVinteInTrasferta
    public List<PartitaDiCalcio> getPartiteVinteInTrasferta(){
        return em.createNamedQuery("trovaVincitoriOspiti", PartitaDiCalcio.class).getResultList();
    }

    //getPartitePareggiate
    public List<PartitaDiCalcio> getPartitePareggiate(){
        return em.createNamedQuery("trovaPareggio", PartitaDiCalcio.class).getResultList();
    }
}