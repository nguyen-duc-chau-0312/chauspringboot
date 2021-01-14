package com.moneyconverter.demo.rest;

import com.moneyconverter.demo.request.ConverterRequest;
import com.moneyconverter.demo.response.ConverterResult;
import com.moneyconverter.demo.service.MoneyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/converter_money")
public class ConverterRest {

    @Autowired
    private MoneyConverter moneyConverter;

    @PostMapping()
    public ConverterResult handleConverterPost(@RequestBody ConverterRequest request){
        return moneyConverter.calculateAmount(request);
    }
}
