package org.example._002.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Eventi")
public class Eventi {
    @Id
    private String id;
    private String location;
    private LocalDate data;
    private boolean disponibile;
    @OneToMany(mappedBy = "evento")
    private List<Prenotazioni> prenotazioni;

    public Eventi() {
    }

    ;

    public Eventi(String location, LocalDate data, boolean disponibile) {
        this.id = UUID.randomUUID().toString();
        this.location = location;
        this.data = data;
        this.disponibile = disponibile;
    }

    public String getId() {
        return id;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public List<Prenotazioni> getPrenotazioni() {
        return prenotazioni;
    }

    @Override
    public String toString() {
        return "Eventi{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", data=" + data +
                ", disponibile=" + disponibile +
                ", prenotazioni=" + prenotazioni +
                '}';
    }
}
