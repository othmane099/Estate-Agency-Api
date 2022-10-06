package com.odev.myestateagencyapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String pId;
    private String title;
    private Boolean isDefault;
    private Boolean isSlide;

    @ManyToOne
    @JsonIgnore
    private Property property;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant dateCreation;
    @UpdateTimestamp
    private Instant dateUpdate;

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", pId='" + pId + '\'' +
                ", title='" + title + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
