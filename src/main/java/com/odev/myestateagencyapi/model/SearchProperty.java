package com.odev.myestateagencyapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class SearchProperty {
    private String keyword;
    private String city;
    private String beds;
    private String baths;
    private String garages;
    private String status;
    private String type;

    @Override
    public String toString() {
        return "SearchProperty{" +
                "keyword='" + keyword + '\'' +
                ", city='" + city + '\'' +
                ", beds='" + beds + '\'' +
                ", baths='" + baths + '\'' +
                ", garages='" + garages + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
