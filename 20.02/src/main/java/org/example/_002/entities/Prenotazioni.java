package org.example._002.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Prenotazioni")
public class Prenotazioni {
    @Id
    private String id;
    @ManyToOne
    private Users prenotante;
    @ManyToOne
    private Eventi evento;

    public Prenotazioni() {
    }

    ;

    public Prenotazioni(Users prenotante, Eventi evento) {
        this.id = UUID.randomUUID().toString();
        this.prenotante = prenotante;
        this.evento = evento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getPrenotante() {
        return prenotante;
    }

    public void setPrenotante(Users prenotante) {
        this.prenotante = prenotante;
    }

    public Eventi getEvento() {
        return evento;
    }

    public void setEvento(Eventi evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "Prenotazioni{" +
                "id='" + id + '\'' +
                ", prenotante='" + prenotante + '\'' +
                ", evento='" + evento + '\'' +
                '}';
    }
}
