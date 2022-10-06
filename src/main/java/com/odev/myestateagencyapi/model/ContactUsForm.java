package com.odev.myestateagencyapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ContactUsForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactName;
    private String contactEmail;
    private String contactSubject;
    private String contactMessage;

    @Override
    public String toString() {
        return "ContactUsForm{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactSubject='" + contactSubject + '\'' +
                ", contactMessage='" + contactMessage + '\'' +
                '}';
    }
}
