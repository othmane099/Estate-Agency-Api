package com.odev.myestateagencyapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class AppSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receptionMail;
    private String agencyMail;
    private String agencyPhone;
    private String agencyLocation;
    private String facebookLink;
    private String instagramLink;
    @Lob
    private String googleMapSrc;
    @Lob
    private String defaultImageLink;

    @Override
    public String toString() {
        return "AppSetting{" +
                "id=" + id +
                ", receptionMail='" + receptionMail + '\'' +
                ", agencyMail='" + agencyMail + '\'' +
                ", agencyPhone='" + agencyPhone + '\'' +
                ", agencyLocation='" + agencyLocation + '\'' +
                '}';
    }
}
