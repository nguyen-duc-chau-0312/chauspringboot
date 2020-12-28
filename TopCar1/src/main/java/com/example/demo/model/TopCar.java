package com.example.topcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopCar {
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
