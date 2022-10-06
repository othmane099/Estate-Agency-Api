package com.odev.myestateagencyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUsDto {

    private String contactName;
    private String contactEmail;
    private String contactSubject;
    private String contactMessage;

    @Override
    public String toString() {
        return "ContactUsForm{" +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactSubject='" + contactSubject + '\'' +
                ", contactMessage='" + contactMessage + '\'' +
                '}';
    }
}
