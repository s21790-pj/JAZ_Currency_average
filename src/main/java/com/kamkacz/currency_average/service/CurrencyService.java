package com.kamkacz.currency_average.service;

import com.kamkacz.currency_average.model.Root;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Double getCurrency(String cur, int days){
        String fooResourceUrl = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/?format=json", cur, days);
        Root temp_currency = restTemplate.getForEntity(fooResourceUrl, Root.class).getBody();
        List<Double> currency_values = new ArrayList<Double>();
        double sum_currnecy = 0;

        for(int i=0; i<days; i++){
            currency_values.add(temp_currency.getRates().get(i).getMid());
            sum_currnecy += currency_values.get(i);
        }

        double avg_currnecy = sum_currnecy/days;

        return avg_currnecy;
    }




}
