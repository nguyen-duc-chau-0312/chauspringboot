package com.example.topcarhomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class TopCar {
    @JsonIgnore
    private int id;
    private String model;
    private String manufacter;
    private int price;
    private int sales;
    private String photo;

    public boolean matchWithKeyword(String keyword) {
        String keywordLowerCase = keyword.toLowerCase();
        return (model.toLowerCase().contains(keywordLowerCase) ||
                manufacter.toLowerCase().contains(keywordLowerCase));
    }
}
