package com.odev.myestateagencyapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String garageNumber;

    @JsonIgnore
    @OneToMany(mappedBy="garage")
    private List<Property> properties = new ArrayList<>();

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", garageNumber='" + garageNumber + '\'' +
                '}';
    }
}
