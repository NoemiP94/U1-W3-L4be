package noemip.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name= "location")
public class Location {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String nome;

    @Column
    private String citta;

    @OneToMany(mappedBy = "location")
    private Set<Evento> eventi = new HashSet<>();

    //COSTRUTTORI

    public Location() {
    }
    public Location(String nome, String citta) {
        this.nome = nome;
        this.citta = citta;
    }

    //GETTER E SETTER

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Set<Evento> getEventi() {
        return eventi;
    }

    public void setEventi(Set<Evento> eventi) {
        this.eventi = eventi;
    }

    //METODI

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }
}
