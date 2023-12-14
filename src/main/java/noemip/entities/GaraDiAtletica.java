package noemip.entities;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class GaraDiAtletica extends Evento{
    @ManyToMany
    @JoinTable(name= "partecipanti_atletica", joinColumns = @JoinColumn(name= "event_id"), inverseJoinColumns = @JoinColumn(name="person_id"))
    private Set<Persona> persone = new HashSet<>();

    @ManyToOne
    @JoinColumn(name= "persona_id")
    private Persona vincitore;

    //COSTRUTTORI
    public GaraDiAtletica() {
    }

    public GaraDiAtletica(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti, Location location, Set<Persona> persone,Persona vincitore) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
        this.persone = persone;
        this.vincitore = vincitore;
    }

    //GETTER E SETTER

    public Set<Persona> getPersone() {
        return persone;
    }

    public void setPersone(Set<Persona> persone) {
        this.persone = persone;
    }

    public Persona getVincitore() {
        return vincitore;
    }

    public void setVincitore(Persona vincitore) {
        this.vincitore = vincitore;
    }
}
