package com.odev.myestateagencyapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Bedroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bedNumber;

    @JsonIgnore
    @OneToMany(mappedBy="beds")
    private List<Property> properties = new ArrayList<>();

    @Override
    public String toString() {
        return "Bedroom{" +
                "id=" + id +
                ", bedNumber='" + bedNumber + '\'' +
                '}';
    }
}
