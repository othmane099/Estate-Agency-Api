package com.odev.myestateagencyapi.sec.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppRole {
    @Id
    private String id;
    private String roleName;
}
