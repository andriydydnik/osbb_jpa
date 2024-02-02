package org.example.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "buildings")
@Entity
@NamedQueries({
        @NamedQuery(name = "Building.findByAddress",
                query = "SELECT b FROM Building b WHERE b.address LIKE CONCAT('%',:address,'%')"),
        @NamedQuery(name = "Building.findAll",
                query = "SELECT b FROM Building b")
})
public class Building implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flat> flats = new ArrayList<>();

    public List<Flat> getFlats() {
        return flats;
    }
}
