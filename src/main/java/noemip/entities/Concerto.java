package noemip.entities;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Concerto extends Evento{
    private Genere genere;
    private Boolean inStreaming;

    //COSTRUTTORI

    public Concerto() {
    }

    public Concerto(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti, Location location, Genere genere, Boolean inStreaming) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
        this.genere = genere;
        this.inStreaming = inStreaming;
    }
}
