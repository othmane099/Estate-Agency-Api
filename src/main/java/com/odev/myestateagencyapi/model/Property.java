package com.odev.myestateagencyapi.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String propertyTitle;
    @Lob
    private String propertyDescription;
    private String location;
    @ManyToOne
    private PropertyType type;
    @ManyToOne
    private PropertyStatus status;
    private String area;
    @ManyToOne
    private Bedroom beds;
    @ManyToOne
    private Bathroom baths;
    @ManyToOne
    private Garage garage;
    private String defaultPicture;
    private String price;
    private Integer views;
    @Enumerated
    private Visibility visibility;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant dateCreation;
    @UpdateTimestamp
    private Instant dateUpdate;
    @Lob
    private String googleSrc;
    private Boolean latest;
    private Boolean main;


    @OneToMany(mappedBy="property")
    private Set<Picture> pictures = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "property_amenity",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities = new ArrayList<>();

    @Override
    public String toString() {
        return "Property{" +
                "propertyTitle='" + propertyTitle + '\'' +
                '}';
    }
}
