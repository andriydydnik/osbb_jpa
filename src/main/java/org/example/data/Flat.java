package org.example.data;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;

@Table(name = "flats")
@Entity
public class Flat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long seq;

    public long getId() {
        return seq;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String number;

    public String getNumber() {
        return number;
    }

    @Column(name = "level")
    private byte stage;

    public byte getStage() {
        return stage;
    }

    public byte getFloor() {
        return stage;
    }

    private float square;

    public float getSquare() {
        return square;
    }

    @Transient
    public Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    public void setBuilding(Building building){
        this.building = building;
    }
}
